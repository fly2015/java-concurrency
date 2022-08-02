/*
 * ValidationException.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package completable.example2;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ValidationException extends Exception
{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    public ValidationException (String msg)
    {
        super(msg);
    }
}



/*
 * Changes:
 * $Log: $
 */