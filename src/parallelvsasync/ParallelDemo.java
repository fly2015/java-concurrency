/*
 * ParallelDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package parallelvsasync;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ParallelDemo
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ArrayList<String> data = new ArrayList<>();
        final Future<ArrayList<String>> future = executorService.submit(() -> {
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            data.add(String.valueOf(Integer.MAX_VALUE));
            
        }, data);
        
        
        executorService.shutdown();
        
        /*
         * try
         * {
         * final ArrayList<String> arrayList = future.get(); // block main thread.
         * System.out.println(arrayList.get(0));
         * }
         * catch (InterruptedException | ExecutionException e)
         * {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
         
        
        boolean terminated = executorService.isTerminated();
        System.out.println(terminated); // main thread still need to wait the executor terminate first
    }
}



/*
 * Changes:
 * $Log: $
 */