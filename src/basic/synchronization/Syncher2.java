/*
 * Syncher2.java
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
public class Syncher2
{
    static final int[] intArray = new int[2];

    private static void pause()
    {
        while (intArray[0] == 0)
        {
            try
            {
                intArray.wait(); //put threads accessing it to the wait (WAITING status)
            }
            catch (InterruptedException ie)
            {
                System.out.println(Thread.currentThread() + " interrupted.");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException
    {
        Thread runner = new Thread()
        {
            public void run()
            {
                synchronized (intArray)
                {
                    pause();
                    System.out.println(intArray[0] + intArray[1]);
                }
            }
        };

        runner.start();
        Thread.sleep(5000);
        intArray[0] = intArray[1] = 10;
        synchronized (intArray)
        {
            intArray.notify();// Thread only notify to the object that is it holding the lock to access
        }
    }
    
    /*
     * The runner thread can only proceed if intArray[0] is not 0. 
     * If this element is not 0, it has been initialized to 10 by the main thread. 
     * If this element is 0, the runner thread is put into the wait set of the intArray object, and must wait for notification.(From object intArray)
     * The main thread only notifies after initializing both elements of the array to 10. 
     * Calling the notify() method on an object with no threads in its wait set does not pose any problems. 
     * A thread can only call notify() on an object whose lock it holds(Thread is holding the lock). Therefore, the last synchronized statement in the main() method is necessary.
     */
}



/*
 * Changes:
 * $Log: $
 */