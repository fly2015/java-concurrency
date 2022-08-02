/*
 * PersonCreditService.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package completable;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class PersonCreditService
{
    private final static PersonRepository personRepository = new PersonRepository();
    PersonCredit getPersonCreditScore(int id) throws InterruptedException
    {
        Thread.sleep(4000);
        return personRepository.readPersonCredit(id);
    }
}



/*
 * Changes:
 * $Log: $
 */