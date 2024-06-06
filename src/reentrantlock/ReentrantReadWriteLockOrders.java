/*
 * ReentrantReadWriteLockOrders.java
 *
 * Da Nang, Viet Nam
 * Da Nang, Viet Nam
 * All rights reserved.
 */
package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ReentrantReadWriteLockOrders
{
    /*
     * In this example, an attempt is made to upgrade a read lock to a write lock, which is not possible. 
     * It should be noted that a write lock can be downgraded to a read lock. 
     * Consider the following scenarios: Attempting to obtain the read lock using the lock() method after the write lock has been acquired is allowed:
        writeLock.lock();
        readLock.lock();
        Attempting to obtain a write lock using the lock() method after the read lock has been acquired will not succeed:
        
        readLock.lock();
        writeLock.lock();
        Attempting to obtain a write lock using the tryLock() method after the read lock has been acquired will return false—that is, the write lock is not acquired:
        
        readLock.lock();
        writeLock.tryLock();
        In the finally block, the isWriteLocked() method checks whether the write lock has been acquired, but we have already established that this would not be the case. 
        So only the "Read lock acquired" and "The end" messages will be printed in this scenario.

     */
    public static void main(String[] args)
    {
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        Lock rl = rwl.readLock();
        Lock wl = rwl.writeLock();
        try
        {
            rl.lock();
            System.out.println("Read lock acquired");
            if (wl.tryLock())
            {
                System.out.println("Write lock acquired");
            }
        }
        catch (Exception e)
        {
            System.out.println("Lock acquisition failed");
        }
        finally
        {
            rl.unlock();
            if (rwl.isWriteLocked())
            {
                wl.unlock();
                System.out.println("Write lock released");
            }
        }
        System.out.println("The end");
    }
}



/*
 * Changes:
 * $Log: $
 */