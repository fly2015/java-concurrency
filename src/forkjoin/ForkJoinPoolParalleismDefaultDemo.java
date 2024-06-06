/*
 * ForkJoinPoolParalleismDefaultDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package forkjoin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ForkJoinPoolParalleismDefaultDemo
{
    public static void main(String[] args)
    {
        //Runtime.getRuntime().availableProcessors() - 1
        System.out.println(ForkJoinPool.commonPool().toString());
        System.out.println(ForkJoinPool.commonPool().getAsyncMode());
        for (int i = 0; i < 100; i++)
        {
            final var val = i;
            CompletableFuture.runAsync(() -> {

                System.out.println(Thread.currentThread().getName() + " is running - " + val);
               
            });

        }
        ;
        
        ForkJoinPool.commonPool().awaitQuiescence(5000, TimeUnit.SECONDS);
        
    }
}



/*
 * Changes:
 * $Log: $
 */