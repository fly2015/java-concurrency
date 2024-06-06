/*
 * ThreadLocalBasic.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package threadlocal;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ThreadLocalBasic
{
    public static void main(String[] args)
    {
        ThreadLocal<String> myThreadLocal = new ThreadLocal<String>();
        myThreadLocal.set("Hello ThreadLocal");
        String threadLocalValue = myThreadLocal.get();
        System.out.println(Thread.currentThread().getName() + " - " + threadLocalValue);
        
        Thread thead = new Thread(() -> {
            ThreadLocal<String> myThreadLocal1 = new ThreadLocal<String>();
            myThreadLocal1.set("Hello ThreadLocal 1");
            System.out.println(Thread.currentThread().getName() + " - " + myThreadLocal1.get());
        });
        thead.start();
    }
}



/*
 * Changes:
 * $Log: $
 */