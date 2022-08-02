/*
 * CompletableFutureDemo.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package completable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class CompletableFutureDemo
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
     // Input: A list of persons
        List<Person> personList = new ArrayList<Person>();
        for (int i=0; i<=10; i++)
        {
            personList.add(new Person(i, String.valueOf(new Random().nextInt())));
        }
        
        // Read Person identity and credit score
        Map<String, String> personRates = new  HashMap<String, String>();
        final PersonIdentificationService personIdentificationService = new PersonIdentificationService();
        final PersonCreditService personCreditService = new PersonCreditService();
        
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
       
        Map<String, Future<PersonCredit>> futurePersobCreditMap = new  HashMap<String,  Future<PersonCredit>>();
        long startTime = System.currentTimeMillis();
        
        List<String> values = new ArrayList<String>();
        //CompletableFuture< ? > supplyAsync1;
        //CompletableFuture< ? > supplyAsync2;

        for (Person person : personList)
        {
        
            String personIdentity = personIdentificationService.getPersonIdentity(person.getId());
               

            Future<PersonCredit> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
                try
                {
                    return personCreditService.getPersonCreditScore(person.getId());
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            });

            //CompletableFuture.allOf(supplyAsync1, supplyAsync2).join();

             
            futurePersobCreditMap.put(personIdentity, supplyAsync2);
        }

        
        

        // CompletableFuture.allOf(supplyAsync1, supplyAsync2).join();
        System.out.println("Done requested !");
        System.out.println("Waiting for the result");
        Iterator<Entry<String, Future<PersonCredit>>> iterator = futurePersobCreditMap.entrySet().iterator();
        while (iterator.hasNext())
        {
            Entry<String, Future<PersonCredit>> entry = iterator.next();
            PersonCredit personCredit = entry.getValue().get();
            personRates.put(entry.getKey(), personCredit.getScore());
        }
        
        
        personRates.forEach((ident, score) -> System.out.println(ident + ": " + score));
        
        System.out.println(System.currentTimeMillis() - startTime);
    }
}



/*
 * Changes:
 * $Log: $
 */