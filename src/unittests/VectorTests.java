/**
 *
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for primitives.Vector class
 * @author Shilat and Avigail
 */
class VectorTests {

	/**
	* Test method for {@link primitives.Vector#add(primitives.Vector)}.
	*/
	@Test
	void testAddVector() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: regular add between two vectors 
		Vector v = new Vector(1, 2, 3);
		assertTrue((v.add(new Vector(1, -2, -3)).equals(new Vector(2, 0, 0))),"ERROR: addVector() does not work correctly");
		
		//////////////////////////
		assertThrows(IllegalArgumentException.class,()->v.add(new Vector(-1, -2, -3)),"adding the vectors equals to (0,0,0)");
	}
	
	/**
	* Test method for {@link primitives.Vector#scale(double)}.
	*/
	@Test
	void testScale() {///////////////////////////////////////////////////חריגה של 0
		// ============ Equivalence Partitions Tests ==============
		//TC01: scale a vector by a scalar 
		Vector v = new Vector(1, 2, 3);
		assertTrue((v.scale(3).equals(new Vector(3,6,9))),"ERROR: scaleVector() does not work correctly");
		
		//////////////////////////////////
		assertThrows(IllegalArgumentException.class,()->v.scale(0),"scale vector by 0 equals to (0,0,0)");
	}
	
	/**
	* Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	*/
	@Test
	void testDotProduct() {
		// ============ Equivalence Partitions Tests ==============
		///////////////////////////////////////////
		Vector v1 = new Vector(1, 2, 3);
	    Vector v2 = new Vector(-2, -4, -6);
	    Vector v3 = new Vector(0, 3, -2);
		try {
		assertTrue(isZero(v1.dotProduct(v3))," dotProduct() for orthogonal vectors is not zero");
		assertTrue(isZero(v1.dotProduct(v2) + 28),"dotProduct() wrong value"); 
	}
		catch(IllegalArgumentException e) {
			
		}
	}
	
	/**
	* Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	*/
	@Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not throw an exception");
    }

	
	/**
	* Test method for {@link primitives.Vector#lengthSquared()}.
	*/
	@Test
	void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: the length squared of a vector
		Vector v1 = new Vector(1, 2, 3);
		assertTrue(isZero(v1.lengthSquared() - 14),"ERROR: lengthSquared() wrong value");	
	}
	
	/**
	* Test method for {@link primitives.Vector#length()}.
	*/
	@Test
	void testLength() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: the length of a vector
		assertTrue(isZero(new Vector(0, 3, 4).length() - 5),"ERROR: length() wrong value");
	}
	
	/**
	* Test method for {@link primitives.Vector#normalize()}.
	*/
	@Test
	void testNormalize() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: normalize the vector to length-1
		Vector v = new Vector(1, 2, 3);
		Vector u = v.normalize();
	    assertTrue(isZero(u.length() - 1),"ERROR: the normalized vector is not a unit vector");
	    
	    // test vector normalization vs vector length and cross-product
	    ///////////////////////////////////////////
	    try { // test that the vectors are co-lined
			v.crossProduct(u);
			fail("ERROR: the normalized vector is not parallel to the original one");
		} catch (Exception e) {
		}
	    
	    //////////////////////////////////////////////
	    assertFalse(v.dotProduct(u) < 0,"ERROR: the normalized vector is opposite to the original one");
	}
	
	/**
	* Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	*/
	@Test
	void testSubtractVector() {
		// ============ Equivalence Partitions Tests ==============
		//
	}

}
