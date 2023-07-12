/*
 * TrueAsyncDemo2.java
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class TrueAsyncDemo2
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
       // ExecutorService executorService = Executors.newFixedThreadPool(5);
       final CompletableFuture<Void> thenApply = CompletableFuture.supplyAsync(() -> {
           
           System.out.println("Thread : " + Thread.currentThread());
           System.out.println("Is Daemon : " + Thread.currentThread().isDaemon());
           try
           {
               Thread.sleep(5000);
           }
           catch (InterruptedException e)
           {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           return Integer.MAX_VALUE;
       }).thenApply(x -> x -1).thenAccept((x) -> System.out.println(x));
       
       //System.out.println(thenApply.get());
       
       ForkJoinPool.commonPool().awaitQuiescence(5000, TimeUnit.SECONDS);
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