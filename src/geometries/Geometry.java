package geometries;

import primitives.*;

public interface Geometry {

	/**
	 * a function that return the normal to a geometry in a point
	 * @param p the point
	 * @return the normal in the point
	 */
	public Vector getNormal(Point p);
}
