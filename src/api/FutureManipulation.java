/*
 * FutureManipulation.java
 *
 * Da Nang, Viet Nam
 * Da Nang, Viet Nam
 * All rights reserved.
 */
package api;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


/**
 * @author nhqhien
 * @version $Revision: $
 */
public class FutureManipulation
{
    /**
     * In the for(;;) loop, there is no guarantee that a task will actually be cancelled before it completes. 
     * Cancelled or not, each task is added to the results list. 
     * The shutdown() method initiates the shutdown of the executor service, allowing those tasks that are already running to complete execution.
       The method isDone() only returns true if the task completed due to normal termination, an exception, or cancellation. 
       If any task was still running, the allMatch(r>r.isDone()) expression will return false, causing the letter Z to be printed.
       If all tasks completed, then there could be some among them that were cancelled. 
       An attempt to get the value of a Future whose task has been cancelled will result in an exception. 
       In order to concatenate the values returned by the tasks in the Future objects, all cancelled tasks are filtered from the stream by calling the isCancelled() method. 
       Since it is unpredictable which tasks were cancelled and which terminated normally, the output from the program may contain any of the letters A, B, C, D, or E.
     */
    public static void main(String[] args)
    {
        Map<Integer, String> map = new ConcurrentHashMap<>(Map.of(1, "a", 2, "b", 3, "c", 4, "d", 5, "e"));
        List<Future<String>> results = new CopyOnWriteArrayList<>();

        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= map.size(); i++)
        {
            final int key = i;
            //System.out.println(i + map.get(key).toUpperCase());
            Future<String> f = es.submit(() -> map.get(key).toUpperCase());
            if (i % 2 != 0)
            {
                System.out.println(i + map.get(key).toUpperCase());
                
                //Attempts to cancel execution of this task.
                f.cancel(true);// No guaranteed what future will be cancel here.
            }

            results.add(f);
        }
        es.shutdown();
        String result = (results.stream().allMatch(r -> r.isDone())) ? results.stream().filter(r -> !r.isCancelled()).map(r -> {
            try
            {
                return r.get();
            }
            catch (InterruptedException | ExecutionException e)
            {
                return "X";
            }
        }).collect(Collectors.joining()) : "Z";
        System.out.println(result);
    }
}

/*
 * Changes:
 * $Log: $
 */