package tests;

import base.Vector2;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2Test
{
    @Test
    void addTest()
    {
        { /* Posiive Vals */
            Vector2 start = new Vector2(1, 10);
            start.add(new Vector2(5, 20));
            assertEquals(start.getX(), 6);
            assertEquals(start.getY(), 30);
        }
        { /* Negative Vals */
            Vector2 start = new Vector2(1, 10);
            start.add(new Vector2(-5, -30));
            assertEquals(start.getX(), -4);
            assertEquals(start.getY(), -20);
        }
    }

    @Test
    void multVector2Test()
    {
        { /* Posiive Vals */
            Vector2 start = new Vector2(2, 10);
            start.mult(new Vector2(5, 20));
            assertEquals(start.getX(), 10);
            assertEquals(start.getY(), 200);
        }
        { /* Negative Vals */
            Vector2 start = new Vector2(2, 10);
            start.mult(new Vector2(-5, -30));
            assertEquals(start.getX(), -10);
            assertEquals(start.getY(), -300);
        }

    }
    @Test
    void multDoubleTest()
    {
        { /* Posiive Vals */
            Vector2 start = new Vector2(2, 10);
            start.mult(6);
            assertEquals(start.getX(), 12);
            assertEquals(start.getY(), 60);
        }
        { /* Negative Vals */
            Vector2 start = new Vector2(2, 10);
            start.mult(-6);
            assertEquals(start.getX(), -12);
            assertEquals(start.getY(), -60);
        }
    }

    @Test
    void rotateTest()
    {
        final double delta = 0.000001;
        { /* Rotate 90 deg right */
            Vector2 start = new Vector2(1, 0);
            start.rotate(90);
            assertEquals(start.getX(), 0, delta);
            assertEquals(start.getY(), -1, delta);
        }
        { /* Rotate 180 degrees right */
            Vector2 start = new Vector2(0, -1);
            start.rotate(180);
            assertEquals(start.getX(), 0, delta);
            assertEquals(start.getY(), 1, delta);
        }
        { /* Rotate a big number */
            Vector2 start = new Vector2(1, 0);
            start.rotate(360*100000+90);
            assertEquals(start.getX(), 0, delta);
            assertEquals(start.getY(), -1, delta);
        }
        { /* Rotate many times */
            Vector2 start = new Vector2(1, 0);
            for(int i = 0; i < 4*10000+1; i++)
            {
                start.rotate(90);
            }
            assertEquals(start.getX(), 0, delta);
            assertEquals(start.getY(), -1, delta);
        }
        { /* Rotate 90 degs left */
            Vector2 start = new Vector2(1, 0);
            start.rotate(-90);
            assertEquals(start.getX(), 0, delta);
            assertEquals(start.getY(), 1, delta);

        }
    }

    @Test
    void zeroTest()
    {
        Vector2 start = Vector2.zero();
        assertEquals(start.getX(), 0);
        assertEquals(start.getY(), 0);
    }

}
