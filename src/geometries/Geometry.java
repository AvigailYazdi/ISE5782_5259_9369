package geometries;

import primitives.*;

/**
 * @author shilat and Avigail
 * Geometry class
 */
public interface Geometry extends Intersectable{

	/**
	 * a function that return the normal to a geometry in a point
	 * @param p the point
	 * @return the normal in the point
	 */
	public Vector getNormal(Point p);
}
