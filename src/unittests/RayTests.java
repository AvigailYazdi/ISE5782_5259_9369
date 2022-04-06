/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Shilat and Avigail
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	public void testGetPoint() {

			Ray ray = new Ray(new Point(0,0,1), new Vector(1,0,0));
			assertEquals(new Point(2, 0, 1), ray.getPoint(2),"The function getPoint dont work correct");
			
			assertEquals(new Point(1, 0, 1), ray.getPoint(1),"The function getPoint dont work correct");

	}

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
			// =============== Boundary Values Tests ==================
			Ray ray = new Ray(new Point(0,0,1), new Vector(1,0,0));
			
			Point p1 = new Point(1,0,0);
			Point p2 = new Point(2,0,0);
			Point p3 = new Point(3,0,0);

			//The first point is closest to the beginning of the foundation
			List<Point>points = List.of(p1,p2,p3);
			assertEquals(p1, ray.findClosestPoint(points));
			
			//The last point is closest to the beginning of the foundation
			points = List.of(p2,p3,p1);
			assertEquals(p1, ray.findClosestPoint(points));
			
			//An empty list
			points = null;
			assertEquals( null, ray.findClosestPoint(points));
			
			// ============ Equivalence Partitions Tests ==============
			//A point in the middle of the list is closest to the beginning of the fund
			points = List.of(p2,p1,p3);
			assertEquals( p1, ray.findClosestPoint(points));

	}
}
