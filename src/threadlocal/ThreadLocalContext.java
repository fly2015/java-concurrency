/*
 * ThreadLocalContext.java
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
public class ThreadLocalContext
{
    public static void main(String[] args)
    {
        
        ThreadLocal<String> myThreadLocal = new ThreadLocal<String>();
        myThreadLocal.set("Hello ThreadLocal");
        String threadLocalValue = myThreadLocal.get();
        System.out.println(Thread.currentThread().getName() + " - " + threadLocalValue);
        
        Context.get().setMajorVersion("1.0");
        Context.get().setName("Name");
        System.out.println(Thread.currentThread().getName() + ":" + Context.get().getName() + "-" + Context.get().getMajorVersion());
        
        Thread thead = new Thread(() -> {
            ThreadLocal<String> myThreadLocal1 = new ThreadLocal<String>();
            myThreadLocal1.set("Hello ThreadLocal 1");
            System.out.println(Thread.currentThread().getName() + " - " + myThreadLocal1.get());
            
            System.out.println(Thread.currentThread().getName() + ":" + Context.get().getName() + "-" + Context.get().getMajorVersion());
        });
        thead.start();
    }
}



final class Context
{
    private static final DemoContextThreadLocal DEMO_CONTEXT_THREAD_LOCAL = new DemoContextThreadLocal();
    private String name;
    private String majorVersion;
    
    
    public static Context get()
    {
        return DEMO_CONTEXT_THREAD_LOCAL.get();
    }

    Context()
    {
    }
    
    public String getName()
    {
        return name;
    }
    public String getMajorVersion()
    {
        return majorVersion;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setMajorVersion(String majorVersion)
    {
        this.majorVersion = majorVersion;
    }
    
    
    static final class DemoContextThreadLocal extends ThreadLocal<Context>
    {
        @Override
        protected Context initialValue()
        {
            return new Context();
        }

        //DemoContextThreadLocal(){ }
    }
}

/*
 * Changes:
 * $Log: $
 */