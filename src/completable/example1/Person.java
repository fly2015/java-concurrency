/*
 * Person.java
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
public class Person
{
    private int id;
    private String name;
    
    public Person(int id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }
}



/*
 * Changes:
 * $Log: $
 */