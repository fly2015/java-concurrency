/*
 * SingleThreadExecutorDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
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
public class SingleThreadExecutorDemo
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try
        {
            
            var result = executorService.submit(() ->  System.out.println("Wake Staff"));
            System.out.println(result.get()); // w4
            
            
            // Executes Runnable task at some point in future
            Runnable command = () -> System.out.println(Thread.currentThread().getName());
            executorService.execute(command);
            
            Collection<Callable<Integer>> listTask = new ArrayList<Callable<Integer>>();
            listTask.add(() -> Integer.MAX_VALUE);
            listTask.add(() -> Integer.MIN_VALUE);
            
            
            // Invoke All
            List<Future<Integer>> invokeAll = executorService.invokeAll(listTask);
            
            // Invoke Any
            for (int i = 0; i < invokeAll.size(); i++)
            {
                System.out.println(invokeAll.get(i).get());
            }
            
            // Invoke Any
            Integer invokeAny = executorService.invokeAny(listTask);
            System.out.println(invokeAny);
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