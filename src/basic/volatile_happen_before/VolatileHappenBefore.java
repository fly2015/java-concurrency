/*
 * VolatileHappenBefore.java
 *
 * Copyright by Hien Ng
 * Da Nang

 */
package basic.volatile_happen_before;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
//Technically speaking, any write to a volatile field happens before every subsequent read of the same field.
public class VolatileHappenBefore
{
    int a;
    volatile int b;

    public void write(){ // thread 1 call
        a=1;
        b=1;
    }

    public void read(){ // thread 2 call
      int rb=b;
      int ra=a;
      if(rb==1 && ra==0) 
          System.out.print("violation");
    }
    
    
    
    /*
     * operations before "volatile write" can not re-ordered after "volatile write" action. (a = 1 can not put after b = 1)
     * operations after "volatile read"  can not re-ordered before "volatile read" action.(rb = a can not put before rb = b)
     * 
     */
    
    
    /*
     * 
     * So the question is if thread2 sees rb=1,will it see ra=1?

        a=1->b=1 due to program order rule.
        
        b=1->rb=b (since we see the value 1) due to the volatile variable rule.
        
        rb=b->ra=a due to program order rule.
        
        Now we can apply the transitivity rule twice and we can conclude that that a=1->ra=a. And therefor ra needs to be 1.
        
        This means that:
        
        a=1 and b=1 can't be reordered.
        rb=b and ra=a can't be reordered
        otherwise we could end up with an rb=1 and ra=0.
    */
}



/*
 * Changes:
 * $Log: $
 */