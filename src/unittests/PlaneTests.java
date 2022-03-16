/**
 *
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Plane class
 * @author Shilat and Avigail
 */
class PlaneTests {

	
	@Test
	public void testConstructor() {
	// ============ Equivalence Partitions Tests ==============
	
	// TC01: three points on the same line
	try {
	new Plane(new Point(0, 0, 1), new Point(0,0,2), new Point(0,0,4));
	fail("Failed constructing a correct plane");
	} catch (IllegalArgumentException e) {}
	
	// TC02: there are points are the same
	try {
		new Plane(new Point(0, 0, 1), new Point(0,0,1), new Point(0,1,2));
		fail("Failed constructing a correct plane");
		} catch (IllegalArgumentException e) {
		}
	}
	
	/**
	* Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	*/
	@Test
	void testGetNormalPoint() {
		// ============ Equivalence Partitions Tests ==============
		//
		Plane plane=new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
		try {
		assertEquals(new Vector(1,1,1).normalize(),plane.getNormal(),"Normal calculation is incorrect");
		}
		catch(IllegalArgumentException e) {
			
		}
	}

}
