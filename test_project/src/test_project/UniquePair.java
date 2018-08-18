package test_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UniquePair {

    /*
     * Returns pair of numbers that repeat only one time in array
     * while all others repeat two.
     */
    static int[] getUniquePairFromArrayWithRepeatablePairs(int[] array) {
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res ^= array[i];
        }
        System.out.println(res);
        int first = 0, second = 0;
        for (int i = 0; i < array.length; i++) {
            if ((res & array[i]) == 0) {
                first ^= array[i];
            } else {
                second ^= array[i];
            }
        }
        System.out.println(first + ", " + second);
        int[] result = new int[] { first, second };
        return result;
        
    }
    
    @Test
    void testCustom() {
        int[] expected = new int[] {0, 7};
        int[] array = new int[] {3, 1, 2, 9, 9, 1, 4, 2, 3, 6, 4, 0, 7, 6};
        int[] actual = UniquePair.getUniquePairFromArrayWithRepeatablePairs(array);
        assertArrayEquals(expected, actual);
    }
    
    @Test
    void testLesson() throws Exception {
        int[] expected = new int[] {4, 5};
        int[] array = new int[] { 2, 1, 7, 1, 4, 3, 5, 3, 2, 7};
        int[] actual = UniquePair.getUniquePairFromArrayWithRepeatablePairs(array);
        assertArrayEquals(expected, actual);
    }

}
