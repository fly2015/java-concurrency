/*
 * CompletableFutureDemo.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package completable;

import java.util.ArrayList;
import java.util.Collections;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class CompletableFutureDemo2
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
        //Map<String, String> personRates = new  HashMap<String, String>();
        List<String> creditList = new ArrayList<String>();
        List<Future<PersonCredit>> futureCreditList = new ArrayList<>();
        final PersonIdentificationService personIdentificationService = new PersonIdentificationService();
        final PersonCreditService personCreditService = new PersonCreditService();
        Map<String, Future<PersonCredit>> futurePersobCreditMap = new  HashMap<String,  Future<PersonCredit>>();
        long startTime = System.currentTimeMillis();

        
        @SuppressWarnings("unchecked")
        CompletableFuture<PersonCredit> [] arr1 = new CompletableFuture [personList.size()];
        int i = 0;

        for (Person person : personList)
        {
        
            //String personIdentity = personIdentificationService.getPersonIdentity(person.getId());
               

            CompletableFuture<PersonCredit> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
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

           // CompletableFuture.allOf(supplyAsync2).join();

             
            arr1[i] = supplyAsync2;
            i++;
        }
        
        Stream.of(arr1).map(CompletableFuture::join).collect(Collectors.toList()).forEach((p) -> System.out.println(p.getScore()));
       //  Void join = CompletableFuture.allOf(arr1).join();

        // CompletableFuture.allOf(supplyAsync1, supplyAsync2).join();
        System.out.println("Done requested !");
        System.out.println("Waiting for the result");
       
        
        
        //personRates.forEach((ident, score) -> System.out.println(ident + ": " + score));
        
        System.out.println(System.currentTimeMillis() - startTime);
    }
}



/*
 * Changes:
 * $Log: $
 */