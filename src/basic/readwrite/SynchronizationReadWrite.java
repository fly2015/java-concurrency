/*
 * SynchronizationReadWrite.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package basic.readwrite;

import java.util.concurrent.TimeUnit;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class SynchronizationReadWrite
{
    public static void main(String[] args) throws InterruptedException
    {
        ReadWrite readWrite = new ReadWrite();
        
        new Thread(() -> readWrite.increase()).start();
        //new Thread(() -> readWrite.increase()).start();
        //new Thread(() -> readWrite.increase()).start();
        //new Thread(() -> readWrite.increase()).start();
        //new Thread(() -> readWrite.increase()).start();
       //Thread.interrupted(); 
      // Thread.currentThread().join();
       System.out.println(readWrite.readCurrentValue());
    }
}


class ReadWrite
{
    private Long count = 0L;
    
    public synchronized void increase()
    {
        /*
         * try
         * {
         * Thread.sleep(1000);
         * }
         * catch (InterruptedException e)
         * {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
        count++;
    }
    
    public Long readCurrentValue()
    {
        return count;
    }
}


/*
 * Changes:
 * $Log: $
 */