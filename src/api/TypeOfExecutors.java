/*
 * TypeOfExecutors.java
 *
 * Da Nang, Viet Nam
 * Da Nang, Viet Nam
 * All rights reserved.
 */
package api;

import java.util.ArrayList;
import java.util.concurrent.Executors;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class TypeOfExecutors
{
    public static void main(String[] args)
    {
        Executors.newWorkStealingPool();
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newSingleThreadScheduledExecutor();
        Executors.newFixedThreadPool(1);
        Executors.newScheduledThreadPool(1);
        
    }
}



/*
 * Changes:
 * $Log: $
 */