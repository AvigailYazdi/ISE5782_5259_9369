package primitives;

import java.util.Objects;
import java.util.List;
import geometries.Intersectable.GeoPoint; 

/**
 * @author shilat and Avigail
 * Ray class
 */
public class Ray {
	/**
	 * A constant for the size of moving first rays for shading rays
	 * */
	private static final double DELTA = 0.1;

	
	final Point p0;
	final Vector dir;
	
	/**
	 * ctor that gets a point and a direction vector
	 * @param p0 the point
	 * @param dir the direction vector
	 * @throws Exception a zero vector-illegal argument
	 */
	public Ray(Point p0, Vector dir) /*throws Exception*/ {
		super();
		this.p0 = p0;
		this.dir = dir.normalize();
	}
	
	public Ray(Point head, Vector lightDirection, Vector n) 
	{
		if(Util.alignZero(lightDirection.dotProduct(n)) < 0)
			 p0= head.add(n.scale(-DELTA));
		else if(Util.alignZero(lightDirection.dotProduct(n)) > 0)
			 p0= head.add(n.scale(DELTA));
		else //if(Util.isZero(lightDirection.dotProduct(n)))
			 p0=head;

		dir=lightDirection;
		dir.normalize();		
	}

	/**
	 * a function that returns the point
	 * @return the point-p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * a function that returns the direction vector
	 * @return the direction vector-dir
	 */
	public Vector getDir() {
		return dir;
	}
	
	/**
	 * a function that returns a vector that scale
	 * @param t
	 * @return vector
	 */
	public Point getPoint(double t) {
		return p0.add(dir.scale(t));
	}
	
	/**
	 * The function returns the point closest to the beginning of the beam
	 * from all the intersection points of the resulting list.
	 * 
	 * @param points List<Point3D> value
	 * @return Point3D value
	 * */
	public Point findClosestPoint (List<Point> points){
		return points == null || points.isEmpty() ? null
		           : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;

	}
	
	public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections){
		
		if(intersections == null)
			return null;
		GeoPoint closet = intersections.get(0);
		for (GeoPoint geoPoint : intersections) 
		{
			if(geoPoint.point.distance(p0) < closet.point.distance(p0))
				closet= geoPoint;
			
		}
		return closet;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		return Objects.equals(dir, other.dir) && Objects.equals(p0, other.p0);
	}

	@Override
	public String toString() {
		return "Ray: p0=" + p0 + ", dir=" + dir + " ";
	}
	
}
