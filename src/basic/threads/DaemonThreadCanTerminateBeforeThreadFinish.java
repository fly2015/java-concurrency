/*
 * DaemonThreadCanTerminateBeforeThreadFinish.java
 *
 * Da Nang, Viet Nam
 * Da Nang, Viet Nam
 * All rights reserved.
 */
package basic.threads;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class DaemonThreadCanTerminateBeforeThreadFinish
{
    static Thread makeThread(String id, boolean daemon)
    {
        Thread t = new Thread(() -> System.out.println(id), id);
        t.setDaemon(daemon);
        t.start();
        return t;
    }


    public static void main(String[] args)
    {
        Thread a = makeThread("A", false);
        Thread b = makeThread("B", true);
        System.out.println("End");
    }
}



/*
 * Changes:
 * $Log: $
 */