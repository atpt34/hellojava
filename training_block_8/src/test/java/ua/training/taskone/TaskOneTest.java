package ua.training.taskone;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * 
 * @author atpt34
 *
 */
public class TaskOneTest extends TestCase {

    
    public void testCalculateDuplicatesDefault() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(4, 5, 6, 4, 5, 3, 4, 2, 4, 5, 7));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(2, 1);
        expected.put(3, 1);
        expected.put(4, 4);
        expected.put(5, 3);
        expected.put(6, 1);
        expected.put(7, 1);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicates(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesEmpty() {
        ArrayList<Integer> defaultList = new ArrayList<>();
        Map<Integer, Integer> expected = new HashMap<>();
        Map<Integer, Integer> actual = TaskOne.calculateDuplicates(defaultList);
        assertEquals(expected, actual);
    }

    public void testCalculateDuplicatesNoDuplicates() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(4, 5, 6, 3, 1, 7, 8, 2, 9, 10));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 1);
        expected.put(2, 1);
        expected.put(3, 1);
        expected.put(4, 1);
        expected.put(5, 1);
        expected.put(6, 1);
        expected.put(7, 1);
        expected.put(8, 1);
        expected.put(9, 1);
        expected.put(10, 1);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicates(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesWithGaps() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(40, 50, 60, 30, 40, 50, 60, 80, 30, 90, 50));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(30, 2);
        expected.put(40, 2);
        expected.put(50, 3);
        expected.put(60, 2);
        expected.put(80, 1);
        expected.put(90, 1);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicates(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesAllSame() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3, 3));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(3, 9);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicates(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesTwoSets() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(3, 7, 3, 7, 3, 7, 3, 3, 7, 7, 3, 7, 3, 7, 3));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(3, 8);
        expected.put(7, 7);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicates(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesAllNulls() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(null, null, null));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(null, 3);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicates(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesRandomWithNulls() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(2, null, 4, 4, 2, null, 4, 2, 4, null, 7));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(2, 3);
        expected.put(null, 3);
        expected.put(4, 4);
        expected.put(7, 1);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicates(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesWithLambdaDefault() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(4, 5, 6, 4, 5, 3, 4, 2, 4, 5, 7));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(2, 1);
        expected.put(3, 1);
        expected.put(4, 4);
        expected.put(5, 3);
        expected.put(6, 1);
        expected.put(7, 1);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicatesWithLambda(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesWithLambdaEmpty() {
        ArrayList<Integer> defaultList = new ArrayList<>();
        Map<Integer, Integer> expected = new HashMap<>();
        Map<Integer, Integer> actual = TaskOne.calculateDuplicatesWithLambda(defaultList);
        assertEquals(expected, actual);
    }

    public void testCalculateDuplicatesWithLambdaNoDuplicates() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(4, 5, 6, 3, 1, 7, 8, 2, 9, 10));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 1);
        expected.put(2, 1);
        expected.put(3, 1);
        expected.put(4, 1);
        expected.put(5, 1);
        expected.put(6, 1);
        expected.put(7, 1);
        expected.put(8, 1);
        expected.put(9, 1);
        expected.put(10, 1);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicatesWithLambda(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesWithLambdaWithGaps() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(40, 50, 60, 30, 40, 50, 60, 80, 30, 90, 50));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(30, 2);
        expected.put(40, 2);
        expected.put(50, 3);
        expected.put(60, 2);
        expected.put(80, 1);
        expected.put(90, 1);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicatesWithLambda(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesWithLambdaAllSame() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3, 3));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(3, 9);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicatesWithLambda(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesWithLambdaTwoSets() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(3, 7, 3, 7, 3, 7, 3, 3, 7, 7, 3, 7, 3, 7, 3));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(3, 8);
        expected.put(7, 7);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicatesWithLambda(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesWithLambdaAllNulls() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(null, null, null));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(null, 3);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicatesWithLambda(defaultList);
        assertEquals(expected, actual);
    }
    
    public void testCalculateDuplicatesWithLambdaRandomWithNulls() {
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(2, null, 4, 4, 2, null, 4, 2, 4, null, 7));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(2, 3);
        expected.put(null, 3);
        expected.put(4, 4);
        expected.put(7, 1);
        Map<Integer, Integer> actual = TaskOne.calculateDuplicatesWithLambda(defaultList);
        assertEquals(expected, actual);
    }
}
