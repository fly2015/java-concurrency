/*
 * PrintStackTrace.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package basic;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class PrintStackTrace
{
    public static void main(String[] args)
    {
        System.out.println(Integer.MAX_VALUE);
        Thread.currentThread();
        Thread.dumpStack();
    }
}



/*
 * Changes:
 * $Log: $
 */