package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import base.Point;
import base.Square;

class SquareTest {

	@Test
	void constructorTest() 
	{
		Point position = Point.zero();
		Point size = new Point(2,2);
		Square mySquare = new Square(position, size);
		assertTrue(mySquare.getPosition().equals(position));			
		assertTrue(mySquare.getSize().equals(size));			
	}
	
	@Test
	void copyConstructorTest()
	{
		Point position = Point.zero();
		Point size = new Point(2,2);
		Square mySquare = new Square(position, size);
		Square myOtherSquare = new Square(mySquare);
		assertTrue(myOtherSquare.getPosition().equals(position));			
		assertTrue(myOtherSquare.getSize().equals(size));			
	}
	
	@Test
	void containsTest()
	{
		/*
		 * OX
		 * XX = ok
		 */
		{
			Point position = Point.zero();
			Point size = new Point(2,2);
			Square mySquare = new Square(position, size);
			Point testPos = Point.zero();
			assertTrue(mySquare.contains(testPos));
		}
		/*
		 * OXX
		 *  XX = Nok
		 */
		{
			Point position = Point.zero();
			Point size = new Point(2,2);
			Square mySquare = new Square(position, size);
			Point testPos = new Point(-1, 0);
			assertFalse(mySquare.contains(testPos));
		}
		
		/*
		 *  XXO
		 *  XX = Nok
		 */
		{
			Point position = Point.zero();
			Point size = new Point(2,2);
			Square mySquare = new Square(position, size);
			Point testPos = new Point(2, 0);
			assertFalse(mySquare.contains(testPos));
		}
		
		/*  O
		 *  XX
		 *  XX = Nok
		 */
		{
			Point position = Point.zero();
			Point size = new Point(2,2);
			Square mySquare = new Square(position, size);
			Point testPos = new Point(0, -1);
			assertFalse(mySquare.contains(testPos));
		}
		
		/*  
		 *  XX
		 *  XX = Nok
		 *  O
		 */
		{
			Point position = Point.zero();
			Point size = new Point(2,2);
			Square mySquare = new Square(position, size);
			Point testPos = new Point(0, 2);
			assertFalse(mySquare.contains(testPos));
		}
		/*  
		 *  XX
		 *  XO = ok
		 *  
		 */
		{
			Point position = Point.zero();
			Point size = new Point(2,2);
			Square mySquare = new Square(position, size);
			Point testPos = new Point(1, 1);
			assertTrue(mySquare.contains(testPos));
		}
	}

}
