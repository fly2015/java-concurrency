/*
 * PersonCredit.java
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
public class PersonCredit
{
    private String score;
    private int id;
    
    PersonCredit(int id, String score)
    {
        this.id = id;
        this.score = score;
    }
    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}



/*
 * Changes:
 * $Log: $
 */