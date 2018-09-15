package test_project;

import static org.junit.Assert.assertThat;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.core.Is.*;
import org.junit.jupiter.api.Test;

public class DivisibilityWithRegex {

    public boolean isADivisibleByB(int a, int b) {
        if (a == 0 || b == 1) {
            return true;
        }
        return Integer.toString(a, b).matches("[0-9a-z]*0");
    }
    
    @Test
    void testIsADivisibleByB() throws Exception {
        int limit = 36;
        for (int a = 0; a <= limit; a++) {
            for (int b = 1; b <= limit; b++) {
                System.out.println(a + ", " + b);
                assertThat(isADivisibleByB(a * 8191, b), is((a % b) == 0));
            }
        }
    }
    
    @Test
    void testZeroIsDivisibleByInt() throws Exception {
        assertThat(isADivisibleByB(0, 
                ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE)),
                is(true));
    }
    
    @Test
    void testOneIsNotDivisibleByInt() throws Exception {
        assertThat(isADivisibleByB(1, 
                ThreadLocalRandom.current().nextInt(2, Integer.MAX_VALUE)),
                is(Boolean.FALSE));
    }
}
