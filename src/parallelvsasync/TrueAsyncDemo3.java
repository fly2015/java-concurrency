/*
 * TrueAsyncDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package parallelvsasync;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class TrueAsyncDemo3
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
       ExecutorService executor = Executors.newFixedThreadPool(2);
       //ForkJoinPool executor = new ForkJoinPool(2, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
       
       /*
        * System.out.println(executor.getActiveThreadCount());
        * System.out.println(executor.getParallelism());
        * System.out.println(executor.getAsyncMode());
        */
       
       // ExecutorService executorService = Executors.newFixedThreadPool(5);
       CompletableFuture.supplyAsync(() -> {

           try
           {
               System.out.println(Thread.currentThread().getName() + " doing task 1");
               Thread.sleep(5000);
           }
           catch (InterruptedException e)
           {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           return Integer.MAX_VALUE;
       }, executor).thenRunAsync(() -> {
           System.out.println(Thread.currentThread().getName() + " doing task 2");
           try
           {
               Thread.sleep(2000);
           }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }, executor)
       .thenRunAsync(() -> {
           System.out.println(Thread.currentThread().getName() + " doing task 3");
           try
           {
               Thread.sleep(2000);
           }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }, executor)
       .thenRunAsync(() -> {
           System.out.println(Thread.currentThread().getName() + " doing task 5");
           try
           {
               Thread.sleep(2000);
           }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }, executor)
       .thenRunAsync(() -> {
           System.out.println(Thread.currentThread().getName() + " doing task 4");
           try
           {
               Thread.sleep(2000);
           }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }, executor)
       .thenAccept(x -> {
           
           //throw new RejectedExecutionException("ABC");
           System.out.println(Thread.currentThread().getName() + " doing another task");
             final Path path = FileSystems.getDefault().getPath("resources/TrueAsynch.txt").toAbsolutePath();
             System.out.println(path);
             try
             {
             Files.writeString(path, String.valueOf(x), StandardOpenOption.CREATE);
             }
             catch (IOException e)
             {
             // TODO Auto-generated catch block
             e.printStackTrace();
             throw new RejectedExecutionException(e.getMessage());
             }
            
       }).exceptionally(TrueAsyncDemo3::handleException).thenRun(() -> System.out.println("XDone") );
       
       //ForkJoinPool.commonPool().awaitQuiescence(5000, TimeUnit.SECONDS);
      // executor.awaitQuiescence(5000, TimeUnit.SECONDS);
       // Do not use ExecutorService
       // Main thread is terminated while 
       System.out.println("Done");
    }


    // stays in error channel
    private static Void handleException(Throwable throwable)
    {
        System.out.println("error -------------------------------:" + throwable);
        throw new RuntimeException("We can not handle it");
    }

}



/*
 * Changes:
 * $Log: $
 */