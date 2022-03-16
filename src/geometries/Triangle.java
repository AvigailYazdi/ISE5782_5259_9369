package geometries;

import primitives.*;

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

}
