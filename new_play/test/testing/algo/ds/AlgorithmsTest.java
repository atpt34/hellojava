package testing.algo.ds;

import static org.junit.Assert.*;
import static testing.algo.ds.Algorithms.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class AlgorithmsTest {

    private static final int[] SMALL_ARRAY = new int[] {2, -4, 3, -1};
    private static final int[] SMALL_ARRAY_SORTED;
    private static final int[] SMALL_NONNEGATIVE_DISTINCT_ARRAY = new int[] {92, 5, 111, 10, 503, 0, 4, 12, 17, 888, 103, 999, 93, 667, 19};
    private static final int[] SMALL_NONNEGATIVE_DISTINCT_ARRAY_SORTED; 
    static {
        int[] sorted = SMALL_ARRAY.clone();
        int[] clone2 = SMALL_NONNEGATIVE_DISTINCT_ARRAY.clone();
        Arrays.sort(sorted);
        Arrays.sort(clone2);
        SMALL_ARRAY_SORTED = sorted;
        SMALL_NONNEGATIVE_DISTINCT_ARRAY_SORTED = clone2;
    }
    private static final int[] SIMPLE_ARRAY = new int[]{ -3, 14, 0, -13, 27, 5, -2, 4, 23, 7 };
    private static final int[] SIMPLE_ARRAY_SORTED = new int[]{ -13, -3, -2, 0, 4, 5, 7, 14, 23, 27 };
    
    private static final int[] DUPLICATES_ARRAY = {3, -4, 3, 5, -4, 1, 3, 2, 5, 7, 1, -7, 5, 0, 0};
    private static final int[] DUPLICATES_ARRAY_SORTED = IntStream.of(DUPLICATES_ARRAY).sorted().toArray();
    private static final int[] NO_DUPLICATES_ARRAY = {-7, -4, 0, 1, 2, 3, 5, 7};

    @Test
    public void testBitNegation() throws Exception {
        assertEquals(0, bitNegation(0));
        assertEquals(-10, bitNegation(10));
        assertEquals(-1024, bitNegation(1024));
        assertEquals(87, bitNegation(-87));
        assertEquals(Integer.MIN_VALUE, bitNegation(Integer.MIN_VALUE));
        assertEquals(-Integer.MAX_VALUE, bitNegation(Integer.MAX_VALUE));
    }
    
    @Test
    public void testIsPowerOfTwo() throws Exception {
        assertTrue(isPowerOfTwo(0));
        assertTrue(isPowerOfTwo(1));
        assertTrue(isPowerOfTwo(2));
        assertTrue(isPowerOfTwo(4));
        assertTrue(isPowerOfTwo(Integer.MIN_VALUE));
        
        assertFalse(isPowerOfTwo(3));
        assertFalse(isPowerOfTwo(5));
        assertFalse(isPowerOfTwo(6));
        assertFalse(isPowerOfTwo(Integer.MAX_VALUE));
    }
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
    
    @Test
    public void testBigFactorial() throws Exception {
        assertEquals(BigInteger.ONE, factorialBig(0));
        assertEquals(BigInteger.ONE, factorialBig(1));
        assertEquals(BigInteger.TWO, factorialBig(2));
        assertEquals(BigInteger.valueOf(3628800), factorialBig(10));
        assertEquals(BigInteger.valueOf(121645100408832000L), factorialBig(19));
        assertEquals(new BigInteger("2432902008176640000"), factorialBig(20));
        assertEquals(new BigInteger("93326215443944152681699238856266700490715968264381621468592"
                + "963895217599993229915608941463976156518286253697920827223758251185210916864000"
                + "000000000000000000000"), factorialBig(100));
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
    public void testLcm() {
        fail("Not yet implemented");
    }

    @Test
    public void testIsPrime() {
        fail("Not yet implemented");
    }

    */
    
    @Test
    public void testGcd() {
        for (int i = 1000; i < 10000; i += 31) {
            for (int j = 999990000; j < 1000000000; j += 3) {
                BigInteger expected = BigInteger.valueOf(i).gcd(BigInteger.valueOf(j));
                assertEquals(expected.intValue(), gcd(i, j));
            }
        }
    }

    @Test
    public void testGcdRecursive() {
        for (int j = 999990000; j < 1000000000; j += 19) {
            for (int i = 1000; i < 10000; i += 7) {
                BigInteger expected = BigInteger.valueOf(i).gcd(BigInteger.valueOf(j));
                assertEquals(expected.intValue(), gcdRecursive(i, j));
            }
        }
    }
    
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
        List<String> actual = allPermutations("abc");
        for (String s: actual) {
            System.out.println(s);
        }
        List<String> expected = List.of("abc", "acb", "bac", "bca", "cab", "cba");
        assertEquals(expected, actual);
    }

    
    @Test
    public void testAllSubsets() {
        System.out.println("AllSubsets: ");
        List<String> actual = allSubsets("abc");
        for (String s : actual) {
            System.out.println(s);
        }
        List<String> expected = List.of("", "a", "b", "ab", "c", "ac", "bc", "abc");
        assertEquals(expected, actual);
    }

    @Test
    public void testDuplicatesSort() {
        int[] actual = removeDuplicatesSort(DUPLICATES_ARRAY);
        assertArrayEquals(NO_DUPLICATES_ARRAY, actual);
    }
    
    @Test
    public void testDuplicatesSortStream() {
        int[] actual = removeDuplicatesSortStream(DUPLICATES_ARRAY);
        assertArrayEquals(NO_DUPLICATES_ARRAY, actual);
    }
    
    @Test
    public void testDuplicatesWoSort() {
        int[] result = removeDuplicatesWoSort(DUPLICATES_ARRAY);
        Set<Integer> actual = IntStream.of(result).boxed().collect(Collectors.toSet());
        Set<Integer> expected = IntStream.of(NO_DUPLICATES_ARRAY).boxed().collect(Collectors.toSet());
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDuplicates() {
        int[] actual = removeDuplicates(DUPLICATES_ARRAY);
        Arrays.sort(actual);
        assertArrayEquals(NO_DUPLICATES_ARRAY, actual);
    }
    
    
    @Test
    public void testHeapSort() {
        int[] actual = heapSort(SIMPLE_ARRAY);
        final int[] expected = SIMPLE_ARRAY_SORTED;
        assertArrayEquals(expected, actual);
        assertArrayEquals(SMALL_ARRAY_SORTED, heapSort(SMALL_ARRAY));
        assertArrayEquals(DUPLICATES_ARRAY_SORTED, heapSort(DUPLICATES_ARRAY));
    }
    
    
    @Test
    public void testShellSort() {
        int[] actual = shellSort(SIMPLE_ARRAY);
        final int[] expected = SIMPLE_ARRAY_SORTED;
        assertArrayEquals(expected, actual);
        assertArrayEquals(SMALL_ARRAY_SORTED, shellSort(SMALL_ARRAY));
        assertArrayEquals(DUPLICATES_ARRAY_SORTED, shellSort(DUPLICATES_ARRAY));
    }

    @Test
    public void testBitSort() {
        assertArrayEquals(SMALL_NONNEGATIVE_DISTINCT_ARRAY_SORTED,
                bitSort(SMALL_NONNEGATIVE_DISTINCT_ARRAY));
    }
    
    @Test
    public void testArraySwap() throws Exception {
        int[] arr = new int[] { 0, 1, 2};
        arraySwap(arr, 0, 0);
        assertArrayEquals(new int[] {0, 1, 2}, arr);
        arraySwap(arr, 0, 1);
        assertArrayEquals(new int[] {1, 0, 2}, arr);
        arraySwap(arr, 2, 0);
        assertArrayEquals(new int[] {2, 0, 1}, arr);
    }
    
    private static class User implements Comparable<User> {
        private int age;  
        private String name;
        public User(String name, int age) {
            this.age = age; this.name = name;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
        @Override
        public int compareTo(User user) {
            return name.compareTo(user.name) == 0 
                    ? Integer.compare(age, user.age)
                           : name.compareTo(user.name);
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + age;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            User other = (User) obj;
            if (age != other.age)
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }
    }
    @Test
    public void testArraySelectionSortWith() throws Exception {
        Integer[] array = new Integer[] {7, 14, -19, 1, 7, 0, -6 };
        Integer[] expected = new Integer[] {-19, -6, 0, 1, 7, 7, 14 };
        assertArrayEquals(expected, selectionSortWith(array, Integer::compareTo));
        
        array = new Integer[] {5, 1024, -1, -4, 11, 0, 60, -3 };
        expected = new Integer[] { 0, 1024, 5, 11, 60, -4, -3, -1 };
        assertArrayEquals(expected, selectionSortWith(array, (x, y) -> Integer.bitCount(x) - Integer.bitCount(y) ));
        
        String[] strs = new String[] {"abcd", "", "a", "abc", "ab" };
        String[] expectedStrs = new String[] {"", "a", "ab", "abc", "abcd" };
        assertArrayEquals(expectedStrs, selectionSortWith(strs, (x, y) -> x.length() - y.length() ));
        
        User[] users = new User[] {new User("B", 8), new User("A", 9), new User("B", 7), new User("C", 0), new User("B", 5) };
        User[] expectedUsers = new User[] { new User("A", 9), new User("B", 5), new User("B", 7), new User("B", 8), new User("C", 0) };
        assertArrayEquals(expectedUsers, selectionSortWith(users, User::compareTo ));
    }
    
    @Test
    public void testListSelectionSortWith() throws Exception {
        List<Integer> list = List.of(-75, 23, 0, -4, 14);
        List<Integer> expected = List.of(23, 14, 0, -4, -75);
        assertEquals(expected, selectionSortListWith(list, 
                Comparator.reverseOrder()));
        
        list = List.of(1024, 11, -1, 0, 60, 5);
        expected = List.of(-1, 60, 11, 5, 1024, 0);
        assertEquals(expected, selectionSortListWith(list, 
                Comparator.comparingInt(Integer::bitCount).reversed() ));
        
        List<String> strs = List.of("abcd", "", "a", "abc", "ab");
        List<String> expectedStrs = List.of("", "a", "ab", "abc", "abcd");
        assertEquals(expectedStrs, selectionSortListWith(strs, 
                Comparator.comparingInt(String::length) ));
        
        List<User> users = List.of(new User("B", 8), new User("A", 9), new User("B", 7), new User("C", 0), new User("B", 5));
        List<User> expectedUsers = List.of( new User("A", 9), new User("B", 5), new User("B", 7), new User("B", 8), new User("C", 0));
        assertEquals(expectedUsers, selectionSortListWith(users, 
                Comparator.comparing(User::getName).thenComparingInt(User::getAge) ));
    }
    
    @Test
    public void testSelectionSort() throws Exception {
        int[] actual = selectionSort(SIMPLE_ARRAY);
        int[] expected = SIMPLE_ARRAY_SORTED;
        assertArrayEquals(expected, actual);
        assertArrayEquals(SMALL_ARRAY_SORTED, selectionSort(SMALL_ARRAY));
        assertArrayEquals(DUPLICATES_ARRAY_SORTED, selectionSort(DUPLICATES_ARRAY));
    }
    
    @Test
    public void testInsertionSort() throws Exception {
        int[] actual = insertionSort(SIMPLE_ARRAY);
        int[] expected = SIMPLE_ARRAY_SORTED;
        assertArrayEquals(expected, actual);
        assertArrayEquals(SMALL_ARRAY_SORTED, insertionSort(SMALL_ARRAY));
        assertArrayEquals(DUPLICATES_ARRAY_SORTED, insertionSort(DUPLICATES_ARRAY));
    }
    
    @Test
    public void testBubbleSort() throws Exception {
        int[] actual = bubbleSort(SIMPLE_ARRAY);
        int[] expected = SIMPLE_ARRAY_SORTED;
        assertArrayEquals(expected, actual);
        assertArrayEquals(SMALL_ARRAY_SORTED, bubbleSort(SMALL_ARRAY));
        assertArrayEquals(DUPLICATES_ARRAY_SORTED, bubbleSort(DUPLICATES_ARRAY));
    }
    
    private static final List<Vertex> GRAPH;
    private static final List<Vertex> EXPECTED_DFS;
    private static final List<Vertex> EXPECTED_BFS;
    static {
        Vertex v0 = new Vertex(0, null);
        Vertex v1 = new Vertex(1, null);
        Vertex v2 = new Vertex(2, null);
        Vertex v3 = new Vertex(3, null);
        Vertex v4 = new Vertex(4, null);
        Vertex v5 = new Vertex(5, null);
        Vertex v6 = new Vertex(6, null);
        Vertex v7 = new Vertex(7, null);
        Vertex v8 = new Vertex(8, null);
        v0.setAdjacentEdges(List.of(v0, v1, v2, v5, v6));
        v1.setAdjacentEdges(List.of(v0, v3, v4));
        v2.setAdjacentEdges(List.of(v0, v3, v5));
        v3.setAdjacentEdges(List.of(v1, v2));
        v4.setAdjacentEdges(List.of(v1, v6, v7));
        v5.setAdjacentEdges(List.of(v0, v2));
        v6.setAdjacentEdges(List.of(v0, v4, v8));
        v7.setAdjacentEdges(List.of(v4));
        v8.setAdjacentEdges(List.of(v6));
        GRAPH = List.of(v0, v1, v2, v3, v4, v5, v6, v7, v8);
        EXPECTED_DFS = List.of(v0, v1, v3, v2, v5, v4, v6, v8, v7);
        EXPECTED_BFS = List.of(v0, v1, v2, v5, v6, v3, v4, v8, v7);
    }
    
    @Test
    public void testDfs() throws Exception {
        assertEquals(EXPECTED_DFS, dfs(GRAPH));
    }
    
    @Test
    public void testDfsStack() throws Exception {
        assertEquals(EXPECTED_DFS, dfsStack(GRAPH));
    }
    
    @Test
    public void testBfs() throws Exception {
        assertEquals(EXPECTED_BFS, bfs(GRAPH));
    }
}
