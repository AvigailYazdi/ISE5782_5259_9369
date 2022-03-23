package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

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
	public Plane(Point p1, Point p2, Point p3)/*throws Exception*/{
		if(p1==p2 || p2==p3 || p3==p1)
			throw new IllegalArgumentException();	
		this.p0=p1;
		Vector v1=p1.subtract(p2);
		Vector v2=p1.subtract(p3);
		////////////if(??????????????????????????)
		this.normal=v1.crossProduct(v2).normalize();
	}
	
	/**
	 * ctor that gets a point and a vertical vector
	 * @param p point
	 * @param v vertical vector
	 */
	public Plane(Point p, Vector v){
		this.p0=p;
		this.normal=v.normalize();
	}
	
	/**
	 * a function that returns the point
	 * @return the point-p0
	 */
	public Point getP0(){
		return p0;
	}

	/**
	 * a function that returns the normal
	 * @return the normal
	 */
	public Vector getNormal(){
		return normal;
	}

	@Override
	public Vector getNormal(Point p){
		return normal;
	}

	@Override
	public String toString(){
		return "Plane: p0=" + p0 + ", normal=" + normal + " ";
	}

	@Override
	public List<Point> findIntersections(Ray ray) {

		double nv = normal.dotProduct(ray.getDir());
		if (isZero(nv))
		{
			return null;
		}
		
		try 
		{
			Vector pSubtractP0 = p0.subtract(ray.getP0());
			double t = alignZero((normal.dotProduct(pSubtractP0))/nv);

			if(t <= 0)
			{
				return null;
			}
			return List.of(ray.getPoint(t));
		}
		catch(Exception ex)
		{
			return null;
		}
	
	}


	
}
