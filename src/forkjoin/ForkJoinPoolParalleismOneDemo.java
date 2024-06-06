/*
 * ForkJoinPoolParalleismDefaultDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package forkjoin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ForkJoinPoolParalleismOneDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        ForkJoinPool executorService = new ForkJoinPool(1, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
        System.out.println(executorService.getAsyncMode());
        //Runtime.getRuntime().availableProcessors() - 1
        System.out.println(executorService.toString());
        for (int i = 0; i < 100; i++)
        {
            final var val = i;
            CompletableFuture.runAsync(() -> {

                System.out.println(Thread.currentThread().getName() + " is running - " + val);
            }, executorService);

        }
        ;
        
        executorService.awaitTermination(5000, TimeUnit.SECONDS);
        
    }
}



/*
 * Changes:
 * $Log: $
 */