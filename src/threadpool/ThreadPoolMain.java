/*
 * ThreadPoolMain.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package threadpool;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ThreadPoolMain
{
    public static void main(String[] args) throws Exception {

        ThreadPool threadPool = new ThreadPool(3, 10);

        for (int i = 0; i < 20; i++)
        {

            int taskNo = i;
            if(taskNo >0)
            {
                //System.out.println(taskNo);
            }
            threadPool.execute(() -> {
                String message = Thread.currentThread().getName() + ": Task " + taskNo;
                System.out.println(message);
            });
        }

        //threadPool.waitUntilAllTasksFinished();
        //threadPool.stop();
        //System.out.println("Done");
    }
}



/*
 * Changes:
 * $Log: $
 */