package geometries;

import primitives.*;

public class Plane implements Geometry{

	Point p0;
	Vector normal;
	
	/**
	 * ctor that gets 3 points and calculates the plane
	 * @param p1 the first point
	 * @param p2 the second point
	 * @param p3 the third point
	 */
	///בדיקות
	public Plane(Point p1, Point p2, Point p3)
	{
		this.p0=p1;
		this.normal=null;
	}
	
	/**
	 * ctor that gets a point and a vertical vector
	 * @param p point
	 * @param v vertical vector
	 */
	public Plane(Point p, Vector v)
	{
		this.p0=p;
		this.normal=v;
	}
	
	/**
	 * a function that returns the point
	 * @return the point-p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * a function that returns the normal
	 * @return the normal
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public Vector getNormal(Point p) {
		return normal;
	}

	@Override
	public String toString() {
		return "Plane: p0=" + p0 + ", normal=" + normal + " ";
	}
	
}
