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
		Vector v=this.axisRay.getDir();
		Point p0=this.axisRay.getP0();
		double t=v.dotProduct(p.subtract(p0));
		Point o=p0.add(v.scale(t));
		Vector normal=p.subtract(o);
		return normal.normalize();
	}
	
	@Override
	public String toString() {
		return "Tube: axisRay=" + axisRay + ", radius=" + radius + " ";
	}

}
