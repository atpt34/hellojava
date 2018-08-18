package test_project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class MergeQuickSortTest {

    /* Divide and conquer implementation of sorting */
    public static List<Integer> mergeSort(List<Integer> list) {
        ArrayList<Integer> res = new ArrayList<>(list);
        ArrayList<Integer> buf = new ArrayList<>(list);
        mergeSort(res, buf, 0, list.size() - 1);
        return res;
    }
    
    private static void mergeSort(ArrayList<Integer> res, ArrayList<Integer> buf, int i, int j) {
        if (i < j) {
            int mid = (i + j) / 2;
            mergeSort(res, buf, i, mid);
            mergeSort(res, buf, mid + 1, j);
            merge(res, buf, i, mid, j);
        }
    }

    private static void merge(ArrayList<Integer> res, ArrayList<Integer> buf, int i, int mid, int j) {
        for (int k = i; k <= j; k++) {
            buf.set(k, res.get(k));
        }
        int left = i;
        int right = mid + 1;
        int curr = i;
        while (left <= mid && right <= j) {
            if (buf.get(left) <= buf.get(right)) {
                res.set(curr, buf.get(left));
                left++;
            } else {
                res.set(curr, buf.get(right));
                right++;
            }
            ++curr;
        }
        int rem = mid - left;
        for (int k = 0; k <= rem; k++) {
            res.set(curr + k, buf.get(left + k));
        }
    }

    /* Randomized sorting algorithm */
    public static List<Integer> quickSort(List<Integer> list) {
        ArrayList<Integer> res = new ArrayList<>(list);
        quickSort(res, 0, list.size() - 1);
        return res;
    }
    
    private static void quickSort(ArrayList<Integer> res, int i, int j) {
        if (i < j) {
            int index = partition(res, i, j);
            quickSort(res, i, index - 1);
            quickSort(res, index, j);
        }
    }

    private static int partition(ArrayList<Integer> res, int i, int j) {
        int pivot = res.get((i + j) / 2);
        while (i <= j) {
            while (res.get(i) < pivot)
                i++;
            while (res.get(j) > pivot)
                j--;
            if (i <= j) {
                Collections.swap(res, i, j);
                ++i;
                --j;
            }
        }
        return i;
    }

    
    
    @Test
    void testEmpty() throws Exception {
        Object expected = Collections.emptyList();
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(List.of()), quickSort(List.of()))
                );
    }
    
    @Test
    void testOne() throws Exception {
        List<Integer> list = List.of(4);
        Object expected = list;
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
    
    @Test
    void testTwo() throws Exception {
        List<Integer> list = List.of(-1, 2);
        Object expected = list;
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );;
    }
    
    @Test
    void testTwo2() throws Exception {
        List<Integer> list = List.of(1, -2);
        Object expected = List.of(-2, 1);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
    
    @Test
    void testThree() throws Exception {
        List<Integer> list = List.of(-1, 0, 1);
        Object expected = List.of(-1, 0, 1);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
    
    @Test
    void testThree2() throws Exception {
        List<Integer> list = List.of(-1, 1, 0);
        Object expected = List.of(-1, 0, 1);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );;
    }
    
    @Test
    void testThree3() throws Exception {
        List<Integer> list = List.of(0, 1, -1);
        Object expected = List.of(-1, 0, 1);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
    
    @Test
    void testThree4() throws Exception {
        List<Integer> list = List.of(0, -1, 1);
        Object expected = List.of(-1, 0, 1);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
    
    @Test
    void testThree5() throws Exception {
        List<Integer> list = List.of(1, 0, -1);
        Object expected = List.of(-1, 0, 1);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
    
    @Test
    void testThree6() throws Exception {
        List<Integer> list = List.of(1, -1, 0);
        Object expected = List.of(-1, 0, 1);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
    
    @Test
    void testWithDuplicates() throws Exception {
        List<Integer> unmodifiableList = List.of(-1, -1, 1, 2, -3, 0, 2, -1, 0, -3, 0, -3, -1, -3, -1);
        List<Integer> list = new ArrayList<>(unmodifiableList);
        List<Integer> expected = new ArrayList<>(list);
        Collections.sort(expected);
        Collections.shuffle(list);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
    
    @Test
    void testRandomWithDuplicates() throws Exception {
        List<Integer> list = ThreadLocalRandom.current().ints(-100, 100).limit(1000).boxed().collect(Collectors.toList());
        System.out.println(list);
        List<Integer> expected = new ArrayList<>(list);
        Collections.sort(expected);
        System.out.println(expected);
        Collections.shuffle(list);
        assertIterableEquals(
                Collections.nCopies(2, expected), 
                List.of(mergeSort(list), quickSort(list))
                );
    }
}
