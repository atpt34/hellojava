package testing.algo.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Just some algorithms.
 * @author atpt34
 *
 */
public class Algorithms {
    
    private Algorithms() {}

    public static int nthPowerOfTwo(int n) {
        if (n < 0) {
            return 0;
        }
        return 1 << n;
    }
    
    public static int sum(int n) {
        return n * (n + 1) / 2;
    }
    
    public static int sumRecursive(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumRecursive(n - 1);
    }
    
    public static long factorial(long n) {
        long res = 1;
        for (long i = 2; i <= n; ++i) {
            res *= i;
        }
        return res;
    }
    
    public static long factorialRecursive(long n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
    
    // in JLS: a == (a / b) * b + a % b
    public static boolean preCondition(int a, int b) {
        return (a % b) == (a - (a / b) * b);
    }
    
    /**
     * Classic iterative Greatest Common Divisor algorithm.
     * Complexity:
     * Time
     * space O(1)
     * @param aa
     * @param bb
     * @return
     */
    public static int gcd(int aa, int bb) {
        int a = aa;
        int b = bb;
        int r;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    
    /**
     * Recursive version of Euclid's GCD algo.
     * @param a
     * @param b
     * @return
     */
    public static int gcdRecursive(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdRecursive(b, a % b);
    }
    
    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
    
    /**
     * General trial division algo for primelity testing.
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (int d = 3; d * d <= n; d += 2) {
            if ((n % d) == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static List<String> allPermutations(String str) { 
        int numOfPerms = (int)factorial(str.length());
        List<String> res = new ArrayList<>(numOfPerms);
        allPermutations(str, "", res);
        return res;
        
    }
    private static void allPermutations(String str, String res, List<String> perms) {
        if (str.isEmpty()) {
            perms.add(res);
        }
        for (int i = 0; i < str.length(); ++i ) {
            allPermutations(str.substring(0, i).concat(str.substring(i + 1)), 
                    res.concat(Character.toString(str.charAt(i))), perms);
        }
    }
    
    public static List<String> allSubsets(String str) {
        int numOfSubsets = nthPowerOfTwo(str.length());
        List<String> subsets = new ArrayList<>(numOfSubsets);
        for (int i = 0; i < numOfSubsets; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str.length(); ++j) {
                if ((i & (1 << j)) != 0) {
                    sb.append(str.charAt(j));
                }
            }
            subsets.add(sb.toString());
        }
        return subsets;
    }
    
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new HashSet<>(arr.length); // approx size
        for (int e : arr) {
            set.add(e);
        }
        int[] res = new int[set.size()];
        int i = 0;
        for (int e : set) {
            res[i++] = e;
        }
        return res;
    }
    public static int[] removeDuplicatesSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        Arrays.sort(arr);
        ArrayList<Integer> arrayList = new ArrayList<>(arr.length);
        int prev = arr[0];
        arrayList.add(prev);
        for (int elem : arr) {
            if (elem != prev) {
                arrayList.add(elem);
                prev = elem;
            }
        }
        int[] res = new int[arrayList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = arrayList.get(i);
        }
        return res;
    }
    
    // Sorting and searching
    
    public static int linearSearch(int[] arr, int elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == elem) {
                return i;
            }
        }
        return -1; // no such elem in arr
    }
    
    public static int binarySearchIter(int[] arr, int elem) {
        Objects.requireNonNull(arr, "no null arrays");
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == elem) {
                return mid;
            } 
            if (elem > arr[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
    private static int binarySearchRecHelper(int[] arr, int elem, int l, int r) {
        if (l > r)
            return -1;
        int mid = l + (r - l) / 2;
        if (arr[mid] == elem) {
            return mid;
        } 
        if (elem > arr[mid]) {
            return binarySearchRecHelper(arr, elem, mid + 1, r);
        }
        return binarySearchRecHelper(arr, elem, l, mid - 1);        
    }
    public static int binarySearchRec(int[] arr, int elem) {
        Objects.requireNonNull(arr, "no null arrays");
        return binarySearchRecHelper(arr, elem, 0, arr.length - 1);
    }
    
    public static int[] bitSort(int[] arr) {
        
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, res.length);
        // impl
        return res;
    }
    
    public static int[] selectionSort(int[] arr) {
        return arr;
    }
    
    public static int[] insertionSort(int[] arr) {
        return arr;
    }
    
    public static int[] bubbleSort(int[] arr) {
        return arr;
    }
    
    private static final int[] SHELLSORT_GAPS = new int[] { 701, 301, 132, 57, 23, 10, 4, 1};
    
    public static int[] shellSort(int[] arr) {
        for(int gap : SHELLSORT_GAPS) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i;
                for (; j >= gap && arr[j - gap] > tmp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
        return arr;
    }
    
    public static int[] heapSort(int[] arr) {
        Queue<Integer> q = new PriorityQueue<>();
        for (Integer i : arr) {
            q.add(i);
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, res.length);
        for (int i = 0; i < arr.length; i++) {
            Integer poll = q.poll();
//            System.out.println(poll);
            res[i] = poll;
        }
//        System.out.println(Arrays.toString(res));
        return res;
    }
    
    
    // graphs
    // DFS, BFS
}
