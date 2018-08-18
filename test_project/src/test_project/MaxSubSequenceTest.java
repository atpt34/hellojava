package test_project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class MaxSubSequenceTest {

    public static int maxSubSequence(List<Integer> list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        int maxsum = 0;
        int sum = 0;
        for (int a : list) {
            sum += a;
            if (maxsum < sum) {
                maxsum = sum;
            } else if (sum < 0) {
                sum = 0;
            }
        }
        // in case all negative return max
        if (list.stream().allMatch(e -> e < 0)) {
            return Collections.max(list);
        }
        return maxsum;
    }
    
    @Test
    void testEmpty() throws Exception {
        assertThrows(NoSuchElementException.class, () -> maxSubSequence(Collections.emptyList()));
    }
    
    @Test
    void testZero() throws Exception {
        int expected = 0;
        int actual = maxSubSequence(List.of(0)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testMinusOne() throws Exception {
        int expected = -1;
        int actual = maxSubSequence(List.of(-1)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testOne() throws Exception {
        int expected = 1;
        int actual = maxSubSequence(List.of(1)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testTwoElems() throws Exception {
        int expected = 3;
        int actual = maxSubSequence(List.of(1 , 2)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testTwoElems2() throws Exception {
        int expected = 1;
        int actual = maxSubSequence(List.of(1 , -2)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testTwoElems3() throws Exception {
        int expected = 2;
        int actual = maxSubSequence(List.of(-1 , 2)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testAllNegatives() throws Exception {
        int expected = -7;
        int actual = maxSubSequence(List.of(-51, -44, -984, -543, -294, -7, -9, -55, -51)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testAllNegativesWithZero() throws Exception {
        int expected = 0;
        int actual = maxSubSequence(List.of(-51, -44, 0, -984, -543, -294, -7, -9, -55, -51)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testAllNegativesWithPositive() throws Exception {
        int expected = 43;
        int actual = maxSubSequence(List.of(-51, -44, -984, 43, -294, -7, -9, -55, -51)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testThreeElems() throws Exception {
        int expected = 6;
        int actual = maxSubSequence(List.of(1, 2, 3)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testThreeElems2() throws Exception {
        int expected = 3;
        int actual = maxSubSequence(List.of(1, 2, -3)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testThreeElems3() throws Exception {
        int expected = 3;
        int actual = maxSubSequence(List.of(1, -2, 3)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testThreeElems4() throws Exception {
        int expected = 1;
        int actual = maxSubSequence(List.of(1, -2, -3)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testThreeElems5() throws Exception {
        int expected = 5;
        int actual = maxSubSequence(List.of(-1, 2, 3)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testThreeElems6() throws Exception {
        int expected = 2;
        int actual = maxSubSequence(List.of(-1, 2, -3)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testThreeElems7() throws Exception {
        int expected = 3;
        int actual = maxSubSequence(List.of(-1, -2, 3)); 
        assertEquals(expected, actual);
    }
    
    @Test
    void testThreeElems8() throws Exception {
        int expected = -1;
        int actual = maxSubSequence(List.of(-1, -2, -3)); 
        assertEquals(expected, actual);
    }
}
