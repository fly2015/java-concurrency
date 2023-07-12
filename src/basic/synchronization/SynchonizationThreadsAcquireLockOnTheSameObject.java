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
public class SynchonizationThreadsAcquireLockOnTheSameObject
{
    public synchronized void printName()
    {
        System.out.println(Thread.currentThread().getName() + ": Name");
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public synchronized void printAge()
    {
        System.out.println(Thread.currentThread().getName() + ": Age");
    }
    
    
    public static void main(String[] args)
    {
        SynchonizationThreadsAcquireLockOnTheSameObject synchonizationThreadsAcquireLockOnTheSameObject = new SynchonizationThreadsAcquireLockOnTheSameObject();
        Thread th0 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
            {
                synchonizationThreadsAcquireLockOnTheSameObject.printName();
            }
        });
        
        
        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
            {
                synchonizationThreadsAcquireLockOnTheSameObject.printAge();
            }
        });
        
        // th1 will be blocked if the th0 has acquired the object lock and vice-versa
        th0.start();
        th1.start();
    }
}



/*
 * Changes:
 * $Log: $
 */