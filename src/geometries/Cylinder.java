package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
	
	double height;

	/**
	 * ctor that gets ray, radius and height of the cylinder
	 * @param axisRay -the ray
	 * @param radius
	 * @param height
	 */
	public Cylinder(Ray axisRay,double radius, double height) {
		super(axisRay,radius);
		this.height = height;
	}

	/**
	 * a function that return the height 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return super.toString()+"Cylinder: height=" + height + " ";
	}

}
