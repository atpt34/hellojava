package kpi_training_block_2;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kpi.training.Model;

public class RandTest {
    
    private static final int MIN = 1;
    private static final int MAX = 8;
    private static final int LENGTH = MAX - MIN + 1;
    private static final int SMALL_TRIALS = 1000;
    private static final int BIG_TRIALS = 80000;
    private static final double EXPECTED = (double)BIG_TRIALS / LENGTH;
    private static final double ERROR = 0.05;
    private static final double BIAS = ERROR * EXPECTED;
    private static final double MIN_BIAS = EXPECTED - BIAS;
    private static final double MAX_BIAS = EXPECTED + BIAS;

    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRandUniformity() {
        int min = MIN;
        int max = MAX;
        int cap = LENGTH;
        HashMap<Integer, Integer> hm = new HashMap<>(cap);
        int numOfTrials = BIG_TRIALS;
        for (int i = 0; i < numOfTrials; ++i) {
            int rand = Model.rand(min, max);
            if (!hm.containsKey(rand)) {
                hm.put(rand, 0);
            }
            int count = hm.get(rand);
            hm.put(rand, count + 1);
        }
        
        System.out.println(MIN_BIAS);
        System.out.println(MAX_BIAS);
        for (Entry<Integer, Integer> i : hm.entrySet()) {
            System.out.println(i.getKey() + " : " + i.getValue());
            int value = i.getValue();
            assertTrue(i.getKey() + " is unbiased ", value > MIN_BIAS && value < MAX_BIAS);
        }
      
        
    }

}
