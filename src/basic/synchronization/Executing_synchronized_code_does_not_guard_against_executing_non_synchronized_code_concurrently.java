/*
 * Executing_synchronized_code_does_not_guard_against_executing_non_synchronized_code_concurrently.java
 *
 * Da Nang, Viet Nam
 * Da Nang, Viet Nam
 * All rights reserved.
 */
package basic.synchronization;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class Executing_synchronized_code_does_not_guard_against_executing_non_synchronized_code_concurrently
{
    private int count;
    synchronized void increasing()
    {
        try
        {
            Thread.currentThread().sleep(5000);
            System.out.println(Thread.currentThread().getName() + " increasing count Sleeping");
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " increasing count.");
        count++;
    }
    
    void decreasing()
    {
        System.out.println(Thread.currentThread().getName() + " decreasing count.");
        count--;
    }
    
    public static void main(String[] args)
    {
        Executing_synchronized_code_does_not_guard_against_executing_non_synchronized_code_concurrently instance = new Executing_synchronized_code_does_not_guard_against_executing_non_synchronized_code_concurrently();
        Thread th1 = new Thread(() -> instance.increasing());
        th1.start();
        
        Thread th2 = new Thread(() -> instance.decreasing());// not protected
        th2.start();
        
        Thread th3 = new Thread(() -> instance.decreasing());// not protected
        th3.start();
        
        try
        {
            Thread.currentThread().sleep(6000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println(instance.count);
    }
}



/*
 * Changes:
 * $Log: $
 */