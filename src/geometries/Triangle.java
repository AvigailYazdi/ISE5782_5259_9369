package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * @author shilat and Avigail
 * Triangle class
 */
public class Triangle extends Polygon{

	/**
	 * ctor the gets 3 point of a triangle
	 * @param p1 the first point
	 * @param p2 the second point
	 * @param p3 the third point
	 * @throws Exception a zero vector-illegal argument
	 */
	public Triangle(Point p1,Point p2,Point p3) /*throws Exception*/ {
		super(new Point[]{p1,p2,p3});
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> rayPoints = plane.findIntersections(ray);
		if (rayPoints == null)
			return null;
		//check if the point in out or on the triangle:
		Vector v1 = vertices.get(0).subtract(ray.getP0());
		Vector v2 = vertices.get(1).subtract(ray.getP0());
		Vector v3 = vertices.get(2).subtract(ray.getP0());
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		
		//The point is inside if all  have the same sign (+/-)
		
		if (alignZero(n1.dotProduct(ray.getDir())) > 0 && alignZero(n2.dotProduct(ray.getDir())) > 0 && alignZero(n3.dotProduct(ray.getDir())) > 0)
		{
			return rayPoints;
		}
		else if (alignZero(n1.dotProduct(ray.getDir())) < 0 && alignZero(n2.dotProduct(ray.getDir())) < 0 && alignZero(n3.dotProduct(ray.getDir())) < 0)
		{
			return rayPoints;
		}
		if (isZero(n1.dotProduct(ray.getDir())) || isZero(n2.dotProduct(ray.getDir())) || isZero(n3.dotProduct(ray.getDir())))
			return null; //there is no instruction point
		return null;
	}
}
