package geometries;

import primitives.Ray;

import java.util.List;

import primitives.Point;

/**
 * @author shilat and Avigail
 * Intersectable interface
 */
public interface Intersectable {
	/**
	 * a function that a list of intersections with a geometry
	 * @param ray
	 * @return list of intersections points
	 */
	List<Point> findIntersections(Ray ray);
}
