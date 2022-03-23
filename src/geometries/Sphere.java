package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

public class Sphere implements Geometry{

	Point center;
	double radius;
	
	/**
	 * ctor that gets a center and radius of a sphere
	 * @param center -point
	 * @param radius
	 */
	public Sphere(Point center, double radius) {
		super();
		this.center = center;
		this.radius = radius;
	}

	/**
	 * a function that returns the center point
	 * @return the center of the sphere
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * a function that returns the radius
	 * @return the radius of the sphere
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point p) {
		Vector v=p.subtract(center);
		return v.normalize();
	}

	@Override
	public String toString() {
		return "Sphere: center=" + center + ", radius=" + radius + " ";
	}

	@Override
	public List<Point> findIntersections(Ray ray) {

		if (ray.getP0().equals(center)) // if the begin of the ray in the center, the point, is on the radius
			return List.of(ray.getPoint(radius));
		//List<Point> rayPoints = new ArrayList<Point>();
		Vector u = center.subtract(ray.getP0());
		double tM = alignZero(ray.getDir().dotProduct(u));
		double d = alignZero(Math.sqrt(u.lengthSquared()- tM * tM));
		double tH = alignZero(Math.sqrt(radius*radius - d*d));
		double t1 = alignZero(tM+tH);
		double t2 = alignZero(tM-tH);
		
		
		if (d > radius)
			return null; // there are no instructions

		
		if (t1 <=0 && t2<=0)
			return null;
		
		if (t1 > 0 && t2 >0)
			return List.of(ray.getPoint(t1),ray.getPoint(t2));
		if (t1 > 0)
		{
			return List.of(ray.getPoint(t1));
		}

		else
			return List.of(ray.getPoint(t2));

	}
}
