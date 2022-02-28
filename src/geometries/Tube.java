package geometries;

import primitives.*;

public class Tube implements Geometry{
	
	Ray axisRay;
	double radius;

	/**
	 * ctor that gets a ray and radius of a tube
	 * @param axisRay
	 * @param radius
	 */
	public Tube(Ray axisRay, double radius) {
		super();
		this.axisRay = axisRay;
		this.radius = radius;
	}

	/**
	 * a function that returns the ray of the tube
	 * @return the ray
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * a function that returns the radius of the tube
	 * @return the radius
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
		return "Tube: axisRay=" + axisRay + ", radius=" + radius + " ";
	}

}
