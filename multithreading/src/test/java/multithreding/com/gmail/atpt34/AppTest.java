package multithreding.com.gmail.atpt34;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import multithreding.com.gmail.atpt34.company.UniversityCompany;
import multithreding.com.gmail.atpt34.util.Pair;

/**
 * 
 * @author atpt34
 *
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testCompanyWithZero()
    {
        int mathCount = 15;
        int lingCount = 0;
        Pair<Integer, Integer> expected = new Pair<>(lingCount, mathCount);
        Pair<Integer,Integer> actual = UniversityCompany.runCompany(lingCount, mathCount);        
        assertEquals(expected, actual);
    }

    public void testCompanyEqualSize()
    {
        int mathCount = 5;
        int lingCount = 5;
        Pair<Integer, Integer> expected = new Pair<>(lingCount, mathCount);
        Pair<Integer,Integer> actual = UniversityCompany.runCompany(lingCount, mathCount);        
        assertEquals(expected, actual);
    }
    
    public void testCompanyTask()
    {
        int mathCount = 250;
        int lingCount = 200;
        Pair<Integer, Integer> expected = new Pair<>(lingCount, mathCount);
        Pair<Integer,Integer> actual = UniversityCompany.runCompany(lingCount, mathCount);        
        assertEquals(expected, actual);
    }
}
