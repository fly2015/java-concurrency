/*
 * SynchonizationThreadsAcquireLockOnTheSameObject.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package basic.synchronization;


/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class SynchonizationThreadsAcquireLockOnObject
{
    public synchronized void printName()
    {
        System.out.println(Thread.currentThread().getName() + ": Synchronized Name");
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void printAge()
    {
        System.out.println(Thread.currentThread().getName() + ": Age");
    }
    
    
    public static void main(String[] args)
    {
        SynchonizationThreadsAcquireLockOnObject synchonizationThreadsAcquireLockOnTheSameObject = new SynchonizationThreadsAcquireLockOnObject();
        Thread th0 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
            {
                synchonizationThreadsAcquireLockOnTheSameObject.printName();
            }
        });
        
        
        Thread th1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                synchonizationThreadsAcquireLockOnTheSameObject.printAge();
            }
        });
        
        
        Thread th2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                synchonizationThreadsAcquireLockOnTheSameObject.printAge();
            }
        });
        
        // th1 and th2 will NOT be blocked if the th0 has acquired the object lock
        th0.start();
        th1.start();
        th2.start();
    }
}



/*
 * Changes:
 * $Log: $
 */