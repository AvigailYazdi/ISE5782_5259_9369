package geometries;

import primitives.*;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Sphere: center=" + center + ", radius=" + radius + " ";
	}
}
