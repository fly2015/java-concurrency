/*
 * SynchronizeRule.java
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
public class SynchronizeRule
{
    private static int staticField;
    private int nonstaticField;

    public static synchronized int getStaticField()
    {
        return staticField;
    }


    public static synchronized void setStaticField(int staticField)
    {
        SynchronizeRule.staticField = staticField;
    }


    public synchronized int getNonstaticField()
    {
        return nonstaticField;
    }


    public synchronized void setNonstaticField(int nonstaticField)
    {
        this.nonstaticField = nonstaticField;
    }
    
    
    public static void main(String[] args)
    {

        // Lock on class
        Thread th0 = new Thread(() -> setStaticField(10));
        Thread th1 = new Thread(() -> System.out.println(getStaticField()));
        th0.start();
        th1.start();
        
        // Lock on an instance
        SynchronizeRule instance0 = new SynchronizeRule();
        Thread th2 = new Thread(() -> instance0.setNonstaticField(20));
        Thread th3 = new Thread(() -> System.out.println(instance0.getNonstaticField()));
        th2.start();
        th3.start();

    }
}



/*
 * Changes:
 * $Log: $
 */