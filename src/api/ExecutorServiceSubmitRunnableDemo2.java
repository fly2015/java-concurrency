/*
 * ExecutorServiceSubmitRunnableDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package api;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ExecutorServiceSubmitRunnableDemo2
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        Runtime rt = Runtime.getRuntime();
        int cpus = rt.availableProcessors();
        
        System.out.println(cpus);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try
        {
            for (int i = 0; i < 100; i++)
            {
                final int val = i;
                executorService.submit(() -> System.out.println(Thread.currentThread().getName() +  " Hello - " + val));
            }
            
            final Future<Class<String>> submit = executorService.submit(() -> System.out.println(Thread.currentThread().getName() +  " Hello - " + 9999000), String.class);
            System.out.println(submit.get());
        }
        finally
        {
            executorService.shutdown();
        }
    }
}



/*
 * Changes:
 * $Log: $
 */