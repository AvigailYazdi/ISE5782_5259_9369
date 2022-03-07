/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;

/**
 * Unit tests for primitives.Point class
 * @author Shilat and Avigail
 */

class PointTests {

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1= new Point(2,4,6);
        // ============ Equivalence Partitions Tests ==============
		Point p2= new Point(1,2,3);
		Point pr= p1.subtract(p2);
		// TC01: Test the result of subtract 2 positive points
        assertEquals("subtract() wrong result", p2, pr);
	}

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		fail("Not yet implemented");
	}

}
