package geometries;

import primitives.Ray;

import java.util.List;

import primitives.Point;

/**
 * @author shilat and Avigail
 * Intersectable interface
 */
	
public abstract class Intersectable {

	/**
	 * a function that a list of intersections with a geometry
	 * @param ray
	 * @return list of intersections points
	 */
	public List<Point> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
	    return geoList == null ? null
	                           : geoList.stream().map(gp -> gp.point).toList();
	}

	
	protected abstract List<GeoPoint> findGeoIntersections(Ray ray);

	
	/**
	 * Static Internal Auxiliary Department (as a completely passive data structure - PDS)
	 * @param geometry Geometry value
	 * @param point Point3D value
	 * */
	public static class GeoPoint 
	{
	    public Geometry geometry;
	    public Point point;
	    
	    
	    /**
	     * constructor for geo point
	     * @param geometry Geometry
	     * @param point Point3D
	     * */
	    public GeoPoint(Geometry geometry,Point point)
	    {
	    	this.geometry = geometry;
	    	this.point = point;
	    }
	    
		@Override
		public boolean equals(Object obj) 
		{
			if (this == obj) return true;
			if (obj == null) return false;
			if (!(obj instanceof GeoPoint)) return false;
			GeoPoint other = (GeoPoint)obj;
			return this.geometry.equals(other.geometry) && this.point.equals(other.point);
		}

		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}
		
		
	}
}
