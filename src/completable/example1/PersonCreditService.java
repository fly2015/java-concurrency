/*
 * PersonCreditService.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package completable.example1;

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