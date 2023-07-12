/*
 * CopyOnWriteArrayListDemo1.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package collections;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class CopyOnWriteArrayListDemo1
{
    private static CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
    public static void main(String[] args)
    {
        Thread thread = null;
        for (int i = 0; i < 50; i++)
        {
            final int val = i;
            thread = new Thread(() -> {
                copyOnWriteArrayList.add(String.valueOf(val));
                copyOnWriteArraySet.add(String.valueOf(val));
            });
            thread.start();
            
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        
        System.out.println(copyOnWriteArrayList.size());
        // OK
        for (String string : copyOnWriteArrayList)
        {
            copyOnWriteArrayList.remove(string);
        }

        System.out.println(copyOnWriteArrayList.size());
    }
}



/*
 * Changes:
 * $Log: $
 */