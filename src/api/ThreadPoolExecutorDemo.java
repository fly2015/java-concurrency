/*
 * ThreadPoolExecutorDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package api;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ThreadPoolExecutorDemo
{
    public static void main(String[] args)
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);

        // Stats before tasks execution
        System.out.println("Largest executions: " + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + executor.getPoolSize());
        System.out.println("Currently executing threads: " + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + executor.getTaskCount());
    }
}



/*
 * Changes:
 * $Log: $
 */