/*
 * ServingFoodTableWithCyclicBarrier.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ServingFoodTableWithCyclicBarrier
{
    private void prepareTable()
    {
        System.out.println(Thread.currentThread().getName() + " Cleaning table.");
    }
    
    private void inviteCustomer()
    {
        System.out.println(Thread.currentThread().getName() + " Inviting customer.");
    }
    
    public void serveFood()
    {
        System.out.println(Thread.currentThread().getName() + " Serving food.");
    }
    
    
    public void serveFood(CyclicBarrier cyclicBarrier1, CyclicBarrier cyclicBarrier2)
    {
        
        try
        {
            prepareTable();
            // A set of threads to all wait for each other to reach a common barrier point. 
            // All other thread must wait each other until 4 tables prepared already.
            cyclicBarrier1.await(); 
            
            inviteCustomer();
            // All other thread must wait each other until 4 tables invited already.
            cyclicBarrier2.await();
            
            
            serveFood();
            
        }
        catch (InterruptedException | BrokenBarrierException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        ServingFoodTableWithCyclicBarrier server = new ServingFoodTableWithCyclicBarrier();
        ExecutorService newWorkStealingPool = Executors.newFixedThreadPool(10);
        int numberOfTask = 2;
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(numberOfTask, () -> System.out.println("*** The table is cleaned ! Please invite customer !"));
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(numberOfTask, () -> System.out.println("*** Please serve food !"));
       

        for (int i = 0; i < numberOfTask; i++)
        {
            newWorkStealingPool.submit(() -> server.serveFood(cyclicBarrier1, cyclicBarrier2));
        }
        newWorkStealingPool.shutdown();

    }
}



/*
 * Changes:
 * $Log: $
 */