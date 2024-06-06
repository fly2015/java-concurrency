/*
 * PersonIdentificationService.java
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