/*
 * ServingFoodTableWithoutCyclicBarrier.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package cyclicbarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ServingFoodTableWithoutCyclicBarrier
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
        prepareTable();
        inviteCustomer();
        System.out.println(Thread.currentThread().getName() + " Serving food.");
    }
    
    public static void main(String[] args)
    {
        ServingFoodTableWithoutCyclicBarrier server = new ServingFoodTableWithoutCyclicBarrier();
        ExecutorService newWorkStealingPool = Executors.newFixedThreadPool(4);
        try
        {
            for (int i = 0; i < 2; i++)
            {
                newWorkStealingPool.submit(() -> server.serveFood());
            }
            
        }
        finally
        {
            newWorkStealingPool.shutdown();
        }
    }
}



/*
 * Changes:
 * $Log: $
 */