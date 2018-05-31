package testing.algo.ds;

import static org.junit.Assert.*;
import static testing.algo.ds.Algorithms.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;

public class AlgorithmsTest {

    private static final int[] SIMPLE_ARRAY = new int[]{ -3, 14, 0, -13, 27, 5, -2, 4, 23, 7 };
    private static final int[] SIMPLE_ARRAY_SORTED = new int[]{ -13, -3, -2, 0, 4, 5, 7, 14, 23, 27 };
    
    private static final int[] DUPLICATES_ARRAY = {3, -4, 3, 5, -4, 1, 3, 2, 5, 7, 1, -7, 5, 0, 0};
    private static final int[] NO_DUPLICATES_ARRAY = {-7, -4, 0, 1, 2, 3, 5, 7};

    @Test
    public void testPreCondition() {
        int n = 16;// n = 1024
        for (int i = -n; i <= n; ++i) {
            for (int j = -n; j < 0; ++j) {
                assertTrue("i = " + i + " j = " + j, preCondition(i, j));
            }
            for (int j = 1; j <= n; ++j) {
                assertTrue("i = " + i + " j = " + j, preCondition(i, j));
            }
        }
    }
    
    @Test
    public void testPreCondition2() {
        class Pair { // local class
            public int a, b; 
            public Pair(int a, int b) { this.a = a; this.b = b;}
           }
        Function<Pair, Integer> f = (p) -> p.a % p.b;
                
        System.out.println("Using lambda: ");
        assertTrue(preCondition(Integer.MIN_VALUE, -1));
        System.out.println(f.apply(new Pair(Integer.MIN_VALUE, -1)));
        assertTrue(preCondition(Integer.MAX_VALUE, -1));
        System.out.println((f.apply(new Pair(Integer.MAX_VALUE, -1))));
        assertTrue(preCondition(Integer.MAX_VALUE, Integer.MAX_VALUE));
        System.out.println(f.apply(new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE)));
        assertTrue(preCondition(Integer.MAX_VALUE, Integer.MIN_VALUE));
        System.out.println(f.apply(new Pair(Integer.MAX_VALUE, Integer.MIN_VALUE)));
        assertTrue(preCondition(Integer.MIN_VALUE, Integer.MIN_VALUE));
        System.out.println(f.apply(new Pair(Integer.MIN_VALUE, Integer.MIN_VALUE)));
        assertTrue(preCondition(Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(f.apply(new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE)));
    }
    /*
    @Test
    public void testNthPowerOfTwo() {
        fail("Not yet implemented");
    }

    @Test
    public void testSum() {
        fail("Not yet implemented");
    }

    @Test
    public void testSumRecursive() {
        fail("Not yet implemented");
    }

    @Test
    public void testFactorial() {
        fail("Not yet implemented");
    }

    @Test
    public void testFactorialRecursive() {
        fail("Not yet implemented");
    }

    @Test
    public void testPreCondition() {
        fail("Not yet implemented");
    }

    @Test
    public void testGcd() {
        fail("Not yet implemented");
    }

    @Test
    public void testGcdRecursive() {
        fail("Not yet implemented");
    }

    @Test
    public void testLcm() {
        fail("Not yet implemented");
    }

    @Test
    public void testIsPrime() {
        fail("Not yet implemented");
    }

    */
    
    @Test
    public void testLinearSearch() {
        int elem = 0;
        assertEquals(2, linearSearch(SIMPLE_ARRAY, elem));
        assertEquals(3, linearSearch(SIMPLE_ARRAY_SORTED, elem));
    }
    
    @Test
    public void testBinarySearchIter() {
        int elem = 0;
        assertEquals(3, binarySearchIter(SIMPLE_ARRAY_SORTED, elem));
        assertEquals(0, binarySearchIter(SIMPLE_ARRAY_SORTED, -13));
        assertEquals(1, binarySearchIter(SIMPLE_ARRAY_SORTED, -3));
        assertEquals(5, binarySearchIter(SIMPLE_ARRAY_SORTED, 5));
        assertEquals(SIMPLE_ARRAY_SORTED.length - 1, binarySearchIter(SIMPLE_ARRAY_SORTED, 27));
    }
    
    @Test
    public void testBinarySearchRec() {
        int elem = 0;
        assertEquals(3, binarySearchRec(SIMPLE_ARRAY_SORTED, elem));
        assertEquals(0, binarySearchRec(SIMPLE_ARRAY_SORTED, -13));
        assertEquals(1, binarySearchRec(SIMPLE_ARRAY_SORTED, -3));
        assertEquals(5, binarySearchRec(SIMPLE_ARRAY_SORTED, 5));
        assertEquals(SIMPLE_ARRAY_SORTED.length - 1, binarySearchRec(SIMPLE_ARRAY_SORTED, 27));
    }
    
    @Test
    public void testAllPermutations() {
        System.out.println("AllPermutations: ");
        List<String> allPermutations = allPermutations("abc");
        for (String s: allPermutations) {
            System.out.println(s);
        }
    }

    
    @Test
    public void testAllSubsets() {
        System.out.println("AllSubsets: ");
        List<String> allSubsets = allSubsets("abc");
        for (String s : allSubsets) {
            System.out.println(s);
        }
    }

    @Test
    public void testDuplicatesSort() {
        int[] actual = removeDuplicatesSort(DUPLICATES_ARRAY);
        assertArrayEquals(NO_DUPLICATES_ARRAY, actual);
    }
    
    @Test
    public void testDuplicates() {
        int[] actual = removeDuplicates(DUPLICATES_ARRAY);
        Arrays.sort(actual);
        assertArrayEquals(NO_DUPLICATES_ARRAY, actual);
    }
    
    /*
    @Test
    public void testBitSort() {
        fail("Not yet implemented");
    }
    */

    @Test
    public void testHeapSort() {
        int[] actual = heapSort(SIMPLE_ARRAY);
        final int[] expected = SIMPLE_ARRAY_SORTED;
        assertArrayEquals(expected, actual);
    }

}
