/*
 * ManipulationWithList.java
 *
 * Da Nang, Viet Nam
 * Da Nang, Viet Nam
 * All rights reserved.
 */
package paralellstream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ManipulationWithList
{
    public static void main(String[] args)
    {
        var values = List.of("1", "2", "3", "4", "5", "6", "7", "8");
        var list1 = new CopyOnWriteArrayList<Integer>();
        for (String value: values) {
            new Thread(() -> {
                list1.add(Integer.valueOf(value));
            }).start();
        }
        System.out.println(list1);

        var list2 = values.parallelStream().peek(System.out::print).map(v->Integer.valueOf(v))
                                           .toList();
        
        System.out.println(list2);//same order with original

        var list3 = new ArrayList<Integer>();
        values.parallelStream().map(v->Integer.valueOf(v)).forEach(v->list3.add(v));
        System.out.println(list3);//not same order with original
    }
}



/*
 * Changes:
 * $Log: $
 */