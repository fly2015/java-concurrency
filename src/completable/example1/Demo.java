/*
 * Demo.java
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class Demo
{
    public static void main(String[] args) throws InterruptedException
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

        long startTime = System.currentTimeMillis();
        
        for (Person person : personList)
        {
            personRates.put(personIdentificationService.getPersonIdentity(person.getId()), personCreditService.getPersonCreditScore(person.getId()).getScore());
        }
        
        personRates.forEach((ident, score) -> System.out.println(ident + ": " + score));
        
        System.out.println(System.currentTimeMillis() - startTime);
    }
}



/*
 * Changes:
 * $Log: $
 */