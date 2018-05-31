package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import strings.StringDriver;

/**
 * 
 * @author Oleksii Tretinichenko
 *
 */
public class StringDriverTest {

    private StringDriver sd;
    
    @Before
    public void setUp() throws Exception {
        sd = new StringDriver();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
//        fail("Not yet implemented");
        assertNull(null);
    }
    
    @Test
    public void testIsDivisibleDoubleTwoOdds() {
        double a = 3.0;
        double b = 5.0;
        assertTrue(StringDriver.isDivisibleDoublesIndirection(sd, a, b));
    }
    
    @Test
    public void testIsDivisibleDoubleTwoEvens() {
        double a = 4.0;
        double b = 10.0;
        assertTrue(StringDriver.isDivisibleDoublesIndirection(sd, a, b));
    }

}
