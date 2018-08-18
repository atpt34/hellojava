package test_project;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class NextToMax {

    /**
     * Find the next to max element in the array
     * in one loop
     * 
     * @param arr
     * @return
     */
    static int nextToMaxOneLoop(int[] arr) {
        if (arr.length < 2) {
            throw new NoSuchElementException();
        }
        int max;
        int next;
        if (arr[0] > arr[1]) {
            max = arr[0];
            next = arr[1];
        } else {
            max = arr[1];
            next = arr[0];
        }
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > max) {
                next = max;
                max = arr[i];
            } else if (arr[i] > next) {
                next = arr[i];
            }
        }  
        return next;
    }
    
    static int nextToMaxTwoLoops(int[] arr) {
        if (arr.length < 2) {
            throw new NoSuchElementException();
        }
        int max = arr[0];
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        int next;
        if (0 == maxIndex) {
            next = arr[1];
        } else {
            next = arr[0];
        }
        for (int i = 0; i < arr.length; i++) {
            if (i == maxIndex) {
                continue;
            }
            if (next < arr[i]) {
                next = arr[i];
            }
        }
        return next;
        
    }

    @Test(expected = NoSuchElementException.class)
    public void testOne() throws Exception {
        nextToMaxOneLoop(new int[] {4 });
        fail();
    }
    
    @Test
    public void testTwoElems() throws Exception {
        int[] arr = new int[] { 4, 7};
        int expected = 4;
        int actual = nextToMaxOneLoop(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTwoElems2() throws Exception {
        int[] arr = new int[] { 7, 5};
        int expected = 5;
        int actual = nextToMaxOneLoop(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDesc() throws Exception {
        int[] arr = new int[] { 10, 7, 5, 1, -16};
        int expected = 7;
        int actual = nextToMaxOneLoop(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testAsc() throws Exception {
        int[] arr = new int[] { -5, 1, 4, 15};
        int expected = 4;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testArb() throws Exception {
        int[] arr = new int[] { 6, 18, 51, -4, 27, 13, 55};
        int expected = 51;
        int actual = nextToMaxOneLoop(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testArb2() throws Exception {
        int[] arr = new int[] { 6, 18, 180, 5, -4, 27, 13, 55};
        int expected = 55;
        int actual = nextToMaxOneLoop(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testRepeatMax() throws Exception {
        int[] arr = new int[] { 6, 6, 5, -3};
        int expected = 6;
        int actual = nextToMaxOneLoop(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testRepeatMaxInEnd() throws Exception {
        int[] arr = new int[] { 5, 2, -1, 6, 6};
        int expected = 6;
        int actual = nextToMaxOneLoop(arr);
        assertEquals(expected, actual);
    }
    

    @Test
    public void test2TwoElems() throws Exception {
        int[] arr = new int[] { 4, 7};
        int expected = 4;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void test2TwoElems2() throws Exception {
        int[] arr = new int[] { 7, 5};
        int expected = 5;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void test2Desc() throws Exception {
        int[] arr = new int[] { 10, 7, 5, 1, -16};
        int expected = 7;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void test2Asc() throws Exception {
        int[] arr = new int[] { -5, 1, 4, 15};
        int expected = 4;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void test2Arb() throws Exception {
        int[] arr = new int[] { 6, 18, 51, -4, 27, 13, 55};
        int expected = 51;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void test2Arb2() throws Exception {
        int[] arr = new int[] { 6, 18, 180, 5, -4, 27, 13, 55};
        int expected = 55;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void test2RepeatMax() throws Exception {
        int[] arr = new int[] { 6, 6, 5, -3};
        int expected = 6;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
    
    @Test
    public void test2RepeatMaxInEnd() throws Exception {
        int[] arr = new int[] { 5, 2, -1, 6, 6};
        int expected = 6;
        int actual = nextToMaxTwoLoops(arr);
        assertEquals(expected, actual);
    }
}
