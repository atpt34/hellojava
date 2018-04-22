package ua.training.tasktwo;


import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

import java.util.List;
import junit.framework.TestCase;

public class ProxiedArrayListTest extends TestCase {

    public void testMyArrayListDefaultConstructor() {
        List<String> s = ProxiedArrayList.newInstance();
        List<Integer> i = ProxiedArrayList.newInstance();
    }
    
    public void testMyArrayListConstructorWithCapacity() {
        int cap = 10;                
        MyArrayList<Integer> i = new MyArrayList<Integer>(Integer.class, cap);
        List<Integer> l = ProxiedArrayList.newInstance((List<Integer>)i);
    }
    
    public void testMyArrayListConstructorWithCollection() {
        Collection<String> cs = new LinkedList<String>();
        cs.add("a"); cs.add("b"); cs.add("c"); cs.add("d"); cs.add("f"); cs.add("e");
        Collection<Integer> ci = new TreeSet<Integer>();
        ci.add(1); ci.add(2); ci.add(23); ci.add(11); ci.add(2); ci.add(233);
        
        MyArrayList<String> s = new MyArrayList<String>(String.class, cs);
        List<String> ls = ProxiedArrayList.newInstance(s);
        assertEquals(ls.size(), cs.size());
        int j = 0;
        for (String string : cs) {
            assertEquals(string, ls.get(j++));
        }
        
        MyArrayList<Integer> i = new MyArrayList<Integer>(Integer.class, ci);
        List<Integer> li = ProxiedArrayList.newInstance(i);
        assertEquals(li.size(), ci.size());
        j = 0;
        for (Integer ii : ci) {
            assertEquals(ii, li.get(j++));
        }
    }
    
    public void testMyArrayListAdd() {
        List<Integer> i = ProxiedArrayList.newInstance();
        int limit = 1000;
        for (int j = 1; j <= limit; j++) {
            i.add(j);
            assertEquals(i.size(), j);
        }
        for (int j = 1; j <= limit; j++) {
            assertEquals(j, i.get(j - 1).intValue());
        }
        
    }
    
    public void testMyArrayListRemove() {
        List<Integer> i = ProxiedArrayList.newInstance();
        i.add(1);
        try {
            i.remove(0);
            fail();
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
    
    public void testMyArrayListRemoveAll() {
        List<Integer> i = ProxiedArrayList.newInstance();
        i.add(1);
        try {
            i.removeAll(Arrays.asList(1, 2));
            fail();
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }        
    }
}

