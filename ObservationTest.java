
import org.junit.Assert;

import org.junit.Test;

/**
 * 
 * @author yangzomdolma
 * @version 2018-10-04
 */
public class ObservationTest
{
    MapData map = new MapData(2018, 8, 30, 17, 45, "data");
    Observation observe = new Observation(34.9, "HOOK");

    /**
     * Test for isValid()
     */
    @Test
    public void testIsValid()
    {
        double expected = 34.9;
        double actual = observe.getValue();
        Assert.assertEquals("Error detected", expected, actual, 0.01);
    }

    /**
     * Test for getValue()
     */

    @Test
    public void testGetValue()
    {
        double expected = 34.9;
        double actual = observe.getValue();
        Assert.assertEquals("Error detected", expected, actual, 0.01);

    }

    /**
     * Test for getStid()
     */

    @Test
    public void testGetStid()
    {
        String expected = "HOOK";
        String actual = observe.getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Test for toString()
     */
    @Test
    public void testToString()
    {
        String expected = "HOOK";
        String actual = observe.getStid();
        Assert.assertEquals(expected, actual);
    }

}
