package ua.training.taskone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author atpt34
 *
 */
public class TaskOne {

    /**
     * Method calculates number of duplicates in given list.
     * @param list of Integers
     * @return map of (number, count) pairs 
     */
    public static Map<Integer, Integer> calculateDuplicates(ArrayList<Integer> arr) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (Integer elem : arr) {
            Integer previousValue = result.get(elem);
            result.put(elem, previousValue == null ? 1 : previousValue + 1);
        }
        return result;
    }
    
    /**
     * The same as calculateDuplicates, but using compute method.
     */
    public static Map<Integer, Integer> calculateDuplicatesWithLambda(ArrayList<Integer> arr) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (Integer elem : arr) {
            result.compute(elem, (key, count) -> count == null ? 1 : ++count);
        }
        return result;
    }
}
