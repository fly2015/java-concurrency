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
public class SynchronizationReadWriteReference
{
    public static void main(String[] args) throws InterruptedException
    {
        ReadWriteObject readWrite = new ReadWriteObject();
        
        new Thread(() -> readWrite.increase()).start();
        new Thread(() -> readWrite.increase()).start();
        new Thread(() -> readWrite.increase()).start();
        new Thread(() -> readWrite.increase()).start();
        final Thread thread = new Thread(() -> readWrite.increase());
        thread.start();
        thread.join();
       //Thread.currentThread().join();
       System.out.println(readWrite.readCurrentValue());
    }
}


class ReadWriteObject
{
    private float count = 0f;
    
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
        System.out.println(Thread.currentThread().getName() + " Running Write");
    }
    
    public float readCurrentValue()
    {
        System.out.println(Thread.currentThread().getName() + " Running Read");
        return count;
    }
}


/*
 * Changes:
 * $Log: $
 */