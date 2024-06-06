/*
 * AsyncDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package parallelvsasync;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class AsyncDemo
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ArrayList<String> data = new ArrayList<>();
        final CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
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
        }, executorService);

        executorService.shutdown();

        /*
         * try
         * {
         * runAsync.get(); // block main thread
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