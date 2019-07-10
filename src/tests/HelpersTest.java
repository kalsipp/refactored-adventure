package tests;

import org.junit.jupiter.api.Test;

import static base.Helpers.getGreatest;
import static base.Helpers.getSmallest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpersTest
{
    @Test
    void getGreatestTest()
    {
        assertEquals(getGreatest(0, 1), 1);
        assertEquals(getGreatest(-1, 1), 1);
        assertEquals(getGreatest(0, 0), 0);
        assertEquals(getGreatest(-100, -50), -50);
    }
    @Test
    void getSmallestTest()
    {
        assertEquals(getSmallest(0, 1), 0);
        assertEquals(getSmallest(-1, 1), -1);
        assertEquals(getSmallest(0, 0), 0);
        assertEquals(getSmallest(-100, -50), -100);
    }
}
