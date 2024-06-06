/*
 * CountUsingAtomicWrongWay.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package basic.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class CountUsingAtomicWrongWay
{
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increaseCount()
    {
        System.out.println(count.getAndSet(count.getAndIncrement()));
    }
    
    
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountUsingAtomicWrongWay  countUsingAtomicWrongWay = new CountUsingAtomicWrongWay();
        for (int i = 1; i <= 1000; i++)
        {
            executorService.submit(() -> {
                countUsingAtomicWrongWay.increaseCount();
            });
        }
        
        executorService.shutdown();
        
        //boolean terminated = executorService.isTerminated();
        while (!executorService.isTerminated())
        {
            //terminated = executorService.isTerminated();
            //System.out.println(executorService.isTerminated());
        }
        
        System.out.println("The final result: " + countUsingAtomicWrongWay.count);
    }
}



/*
 * Changes:
 * $Log: $
 */