package ua.training.tasktwo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

import junit.framework.TestCase;

public class MyArrayListTest extends TestCase {

    public void testMyArrayListDefaultConstructor() {
        MyArrayList<String> s = new MyArrayList<String>(String.class);
        MyArrayList<Integer> i = new MyArrayList<Integer>(Integer.class);
    }
    
    public void testMyArrayListConstructorWithCapacity() {
        int cap = 10;
        MyArrayList<String> s = new MyArrayList<String>(String.class, cap);
        MyArrayList<Integer> i = new MyArrayList<Integer>(Integer.class, cap);
    }
    
    public void testMyArrayListConstructorWithCollection() {
        Collection<String> cs = new LinkedList<String>();
        cs.add("a"); cs.add("b"); cs.add("c"); cs.add("d"); cs.add("f"); cs.add("e");
        Collection<Integer> ci = new TreeSet<Integer>();
        ci.add(1); ci.add(2); ci.add(23); ci.add(11); ci.add(2); ci.add(233);
        
        MyArrayList<String> s = new MyArrayList<String>(String.class, cs);
        assertEquals(s.size(), cs.size());
        int j = 0;
        for (String string : cs) {
            assertEquals(string, s.get(j++));
        }
        
        MyArrayList<Integer> i = new MyArrayList<Integer>(Integer.class, ci);
        assertEquals(i.size(), ci.size());
        j = 0;
        for (Integer ii : ci) {
            assertEquals(ii, i.get(j++));
        }
    }
    
    public void testMyArrayListAdd() {
        int cap = 1;
        MyArrayList<Integer> i = new MyArrayList<Integer>(Integer.class, cap);
        int limit = 1000;
        for (int j = 1; j <= limit; j++) {
            i.add(j);
//            System.out.println(i.getCurrentCapacity());
            assertEquals(i.size(), j);
        }
        for (int j = 1; j <= limit; j++) {
            assertEquals(j, i.get(j - 1).intValue());
        }
        
    }
    
    public void testMyArrayListRemove() {
        int cap = 1;
        MyArrayList<Integer> i = new MyArrayList<Integer>(Integer.class, cap);
        i.add(1);
        try {
            i.remove(0);
            fail();
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
}
