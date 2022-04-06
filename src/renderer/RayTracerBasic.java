/**
 * 
 */
package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
/**
 * @author Shilat and Avigail
 *
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * constructor
	 * 
	 * @param myscene Scene value
	 */
	public RayTracerBasic(Scene myscene) {
		super(myscene);
		//return null;
		
	}
	
	/**
	 * Function that calculates the color for the nearest intersection point, 
	 * if no intersection points are returned the color of the background	
	 * 
	 * @param ray Ray value
	 * @return Color
	 * @throws Exception
	 *  */
	public Color traceRay(Ray ray) /*throws Exception*/{
			List<Point> intersections = myscene.geometries.findIntersections(ray);
			if(intersections == null)
				return  myscene.background;
			Point closestPoint = ray.findClosestPoint(intersections);
			return calcColor(closestPoint);
	}
	
	/**
	 * Function for calculating a point color	
	 * 
	 * @param point Point value
	 * @return Color
	 * */
	private Color calcColor(Point point){
		return myscene.ambientLight.getIntensity();
	}
}
