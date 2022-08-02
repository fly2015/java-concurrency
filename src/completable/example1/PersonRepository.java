/*
 * PersonRepository.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package completable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class PersonRepository
{
    final List<Person> personList = new ArrayList<Person>();
    
    final List<PersonCredit> personCreditList = new ArrayList<PersonCredit>();
    PersonRepository()
    {
        int i = 0;
        while (i < 100)
        {
            personList.add(new Person(i, "Name-" + new Random().nextInt()));
            personCreditList.add(new PersonCredit(i, "A." + new Random().nextInt()));
            i++;
        }
    }
    
    Person readPerson(int id)
    {
        return personList.get(id);
    }
    
    
    PersonCredit readPersonCredit(int id)
    {
        return personCreditList.get(id);
    }
}



/*
 * Changes:
 * $Log: $
 */