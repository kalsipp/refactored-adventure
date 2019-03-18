package Tests;
import Crawl.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PointTest 
{

	/*
	 *  x| | |
	 *   | | |
	 * */
//	@Test
//    public void IsWithinBox_test()
//    {
//
//		{ /* Box is of size 0, point on startpos */
//			Point position = new Point(0,0);
//            Point size = new Point(0,0);
//            Point point = new Point(0,0);
//            assertFalse(point.IsWithinBox(position, size));
//        }
//        { /*Box is size 0, point outside box */
//            Point position = new Point(0,0);
//            Point size = new Point(0,0);
//            Point point = new Point(1,1);
//            assertFalse(point.IsWithinBox(position, size));
//        }
//        { /*Box size 0, point negative outside box */
//            Point position = new Point(0,0);
//            Point size = new Point(0,0);
//            Point point = new Point(-1,-1);
//            assertFalse(point.IsWithinBox(position, size));
//        }
//        
//        { /*Box ok size, point top-left corner */
//			/*		  
//			 *   |x|
//			 *   
//			 * 
//			 * */
//            Point position = new Point(0,0);
//            Point size = new Point(1,1);
//            Point point = new Point(0,0);
//            assertTrue(point.IsWithinBox(position, size));
//        }
//        { /*Box ok size, point bottom-right corner */
//			/*		  
//			 *   | | 
//			 *      x
//			 * 
//			 * */
//            Point position = new Point(0,0);
//            Point size = new Point(1,1);
//            Point point = new Point(1,1);
//            assertFalse(point.IsWithinBox(position, size));
//        }
//        { /*Position in negatives, point outside box*/
//        	/*   -1 
//			 * -1 | | 
//			 *       x
//			 * 
//			 * */
//            Point position = new Point(-1,-1);
//            Point size = new Point(1,1);
//            Point point = new Point(0,0);
//            assertFalse(point.IsWithinBox(position, size));
//        }
//        { /*Position in negatives, point inside box */
//        	/*   -1  
//			 * -1 |x| 
//			 *   
//			 * 
//			 * */
//            Point position = new Point(-1,-1);
//            Point size = new Point(1,1);
//            Point point = new Point(-1,-1);
//            assertTrue(point.IsWithinBox(position, size));
//        }
//        {
//        	/*    x  
//			 *   | | 
//			 *    
//			 * */
//            Point position = new Point(0,0);
//            Point size = new Point(1,1);
//            Point point = new Point(0,1);
//            assertFalse(point.IsWithinBox(position, size));
//
//        }
//        {
//        	/*      
//			 *   | | x 
//			 *    
//			 * */
//            Point position = new Point(0,0);
//            Point size = new Point(1,1);
//            Point point = new Point(1,0);
//            assertFalse(point.IsWithinBox(position, size));
//        }
//        {
//        	/*      
//			 *   x | |  
//			 *    
//			 * */
//            Point position = new Point(0,0);
//            Point size = new Point(1,1);
//            Point point = new Point(-1,0);
//            assertFalse(point.IsWithinBox(position, size));
//        }
//        {
//        	/*      
//			 *    | |  
//			 *     x
//			 * */
//            Point position = new Point(0,0);
//            Point size = new Point(1,1);
//            Point point = new Point(0,-1);
//            assertFalse(point.IsWithinBox(position, size));
//        }

//    }
}
