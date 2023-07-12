/*
 * ThreadInterationEmailProcessingConcurrentCollectionDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package basic.thread_interaction;

import java.util.LinkedList;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class BlockingQueue
{
    /*
     * 1. If queue is full -> block putting thread
     * 2. If queue is empty -> block polling thread
     * 3. If queue is NOT full -> put elem
     * 4. If queue is NOT empty -> poll elem
     * 5. If queue become available(not empty, size=1) at the time the first elem is put -> notify potential blocked taking threads.
     * 6. If queue is full at the time an elem will be taken -> notify potential blocked putting threads
     */
    private LinkedList<Object> queue = new LinkedList<>();
    private final int MAX_SIZE = 5;
    synchronized void enqueue(Object object)
    {
       // System.out.println(Thread.currentThread().getName() + " before ENqueue - size " + queue.size());
        while (queue.size() == MAX_SIZE)
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + " Waiting for enqueue...");
                this.wait();// cause the current enqueue thread waiting for on this object until it is wakened
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        this.queue.add(object);
        System.out.println(Thread.currentThread().getName() + " - added " + object);

        if (queue.size() == 1)
        {
            notifyAll();
        }
    }
    
    
    synchronized Object dequeue()
    {
        //System.out.println(Thread.currentThread().getName() + " before Dequeue - size " + queue.size());
        while (queue.size() == 0)
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + " Waiting for dequeue...");
                this.wait();// cause the current dequeue thread waiting for on this object until it is wakened
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        if(queue.size() == MAX_SIZE)
        {
            notifyAll();
        }
        
        
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return this.queue.removeFirst();
    }

    
    public static void main(String[] args)
    {
        BlockingQueue demo = new BlockingQueue();
        
        
        Thread th0 = new Thread(() -> {
            
            for (int i = 0; i < 30; i++)
            {
                demo.enqueue("OCP-" + i);
            }
        });
        
        Thread th1 = new Thread(() -> {

            while (true)
            {
                System.out.println(Thread.currentThread().getName() + " - " + demo.dequeue());
            }
        });

        Thread th2 = new Thread(() -> {
            while (true)
            {
                System.out.println(Thread.currentThread().getName() + " - " + demo.dequeue());
            }
        });
         
        
        
        th0.start();
        th1.start();
        th2.start();
    }
}



/*
 * Changes:
 * $Log: $
 */