/*
 * PersonIdentificationService.java
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
public class PersonIdentificationService
{
    private final static PersonRepository personRepository = new PersonRepository();
    String getPersonIdentity(int id) throws InterruptedException
    {
        //Thread.sleep(1000);
        Person readPerson = personRepository.readPerson(id);
        return String.format("%s-%s", readPerson.getId(), readPerson.getName());
    }
}



/*
 * Changes:
 * $Log: $
 */