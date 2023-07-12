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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class TrueAsyncDemo
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
       // ExecutorService executorService = Executors.newFixedThreadPool(5);
       CompletableFuture.supplyAsync(() -> {

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
       }).thenAccept(x -> {
           
           //throw new RejectedExecutionException("ABC");
           
             final Path path = FileSystems.getDefault().getPath("resources1/TrueAsynch.txt").toAbsolutePath();
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
            
       }).exceptionally(TrueAsyncDemo::handleException).thenRun(() -> System.out.println("XDone"));

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