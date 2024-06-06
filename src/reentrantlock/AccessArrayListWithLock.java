/*
 * AccessArrayListWithLock.java
 *
 * Da Nang, VN
 * Da Nang
 * All rights reserved.
 */
package reentrantlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class AccessArrayListWithLock
{
    private static List<String> lst = new ArrayList<String>();
    private static ReentrantLock lock = new ReentrantLock();
    
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock rl = rwl.readLock();
    private static ReentrantReadWriteLock.WriteLock wl = rwl.writeLock();
    
    public static void main(String[] args) throws InterruptedException
    {
        concurentDoing();
    }
    private static void concurentDoing() throws InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++)
        {
            int x = i;
            executorService.execute(() -> {
                //writeConcurrent(x);
                try
                {
                    writeWithLock(x);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            
            executorService.execute(() -> {
                //readConcurrent(x);
               readWithLock(x);
            });
        }
        
        executorService.shutdown();

        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }
    
    private static void readWithLock(int x)
    {
        try
        {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " read size " + lst.size());
        }
        finally {
            lock.unlock();
        }
    }
    
    private static void writeWithLock(int x) throws InterruptedException
    {
        try
        {
            lock.lock();
            Thread.sleep(3000);
            lst.add("" + x);
            System.out.println(Thread.currentThread().getName() + " write " + x);
        }
        finally {
            lock.unlock();
        }
    }
    
    
    private static void readConcurrent(int x)
    {
        System.out.println(Thread.currentThread().getName() + " read " + lst.get(x));
    }
}




/*
 * Changes:
 * $Log: $
 */