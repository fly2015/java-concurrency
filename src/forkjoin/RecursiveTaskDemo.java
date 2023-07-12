/*
 * RecursiveTaskDemo.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


/**
 * @author nhqhien
 * @version $Revision: $
 */
public class RecursiveTaskDemo
{
    public static void main(String[] args)
    {
        
    }
}


class MyRecursiveTask extends RecursiveTask<Long>
{
    /** serialVersionUID */
    private static final long serialVersionUID = 7235137147233740269L;
    private long workLoad = 0;
    private final static int THRESHOLD = 4;
    public MyRecursiveTask(long workLoad)
    {
        this.workLoad = workLoad;
    }


    protected Long compute()
    {

        // if work is above threshold, break tasks up into smaller tasks
        if (this.workLoad > THRESHOLD)
        {
            System.out.println("Splitting workLoad : " + this.workLoad);

            List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();
            subtasks.addAll(createSubtasks());

            for (MyRecursiveTask subtask : subtasks)
            {
                subtask.fork();
            }

            long result = 0;
            for (MyRecursiveTask subtask : subtasks)
            {
                result += subtask.join();
            }
            return result;

        }
        else
        {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;
        }
    }

    private List<MyRecursiveTask> createSubtasks()
    {
        List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();

        MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
        MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
}

/*
 * Changes:
 * $Log: $
 */