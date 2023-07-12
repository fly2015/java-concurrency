/*
 * ExchangerDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package exchanger;

import java.util.concurrent.Exchanger;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ExchangerDemo
{
    public static void main(String[] args)
    {
        Exchanger<Object> exchanger = new Exchanger<Object>();
        
        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");

        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
    }
}


class ExchangerRunnable implements Runnable
{

    private Exchanger<Object> exchanger = null;
    private Object object = null;

    public ExchangerRunnable(Exchanger<Object> exchanger, Object object)
    {
        this.exchanger = exchanger;
        this.object = object;
    }


    public void run()
    {
        try
        {
            Object previous = this.object;

            this.object = this.exchanger.exchange(this.object);

            System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}


/*
 * Changes:
 * $Log: $
 */