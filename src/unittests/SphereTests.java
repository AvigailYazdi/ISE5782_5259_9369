/**
 *
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Sphere class
 * @author Shilat and Avigail
 */
class SphereTests {

	/**
	* Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	*/
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		//////////////////////////////
		Sphere sp=new Sphere(new Point(0,0,0),1);
		try {
		assertEquals(new Vector(1,0,0),sp.getNormal(new Point(1,0,0)),"Normal calculation is incorrect");
		}
		catch(IllegalArgumentException e) {
		}

	}
}
