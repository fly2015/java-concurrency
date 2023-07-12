/*
 * LazySettingThreadLocalValue.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class LazySettingThreadLocalValue
{
    public static void main(String[] args)
    {
        MyDateFormatter myDateFormatter = new MyDateFormatter();
        System.out.println(myDateFormatter.format(new Date()));
        
        Thread thread = new Thread(() -> {
            
            System.out.println(myDateFormatter.format(new Date()));
        });
        
        thread.start();
    }
}


class MyDateFormatter
{
    private ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();
    public String format(Date date)
    {
        SimpleDateFormat simpleDateFormat = getThreadLocalSimpleDateFormat();
        return simpleDateFormat.format(date);
    }


    private SimpleDateFormat getThreadLocalSimpleDateFormat()
    {
        SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
        if (simpleDateFormat == null)
        {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormatThreadLocal.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }
}
/*
 * Changes:
 * $Log: $
 */