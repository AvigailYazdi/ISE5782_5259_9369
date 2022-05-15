/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author shilat and Avigail
 *
 */
public interface LightSource {


	/**
	 * A function that return the intensity at a point
	 * @param p Point3D value
	 * @return intensity color in this point
	 */
	public Color getIntensity(Point p) throws IllegalArgumentException;
	
	
	/**
	 * A function that return the  vector L of the lighting direction at a point
	 * @param p Point3D value
	 * @return the lighting direction on a point
	 */
	public Vector getL(Point p) throws IllegalArgumentException;
}
