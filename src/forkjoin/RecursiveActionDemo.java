/*
 * RecursiveActionDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class RecursiveActionDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.toString());
        //ForkJoinPool forkJoinPool = new ForkJoinPool(1, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, false);
        forkJoinPool.invoke(myRecursiveAction);
        
        Thread.currentThread().join();
    }
}


class MyRecursiveAction extends RecursiveAction
{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private long workLoad = 0;
    private final static int THRESHOLD = 4;
    public MyRecursiveAction(long workLoad)
    {
        this.workLoad = workLoad;
    }


    @Override
    protected void compute()
    {
        // if work is above threshold, break tasks up into smaller tasks
        if (this.workLoad > THRESHOLD)
        {
            System.out.println(Thread.currentThread().getName() + " Splitting workLoad : " + this.workLoad);

            List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>();

            subtasks.addAll(createSubtasks());

            for (RecursiveAction subtask : subtasks)
            {
                subtask.fork();
            }

        }
        else
        {
            System.out.println(Thread.currentThread().getName() + " Doing workLoad myself: " + this.workLoad);
        }
    }


    private List<MyRecursiveAction> createSubtasks()
    {
        List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>();

        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

}
/*
 * Changes:
 * $Log: $
 */