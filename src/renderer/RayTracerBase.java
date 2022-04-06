/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
/**
 * @author Shilat and Avigail
 *
 */
public abstract class RayTracerBase {

	protected Scene myscene;
	
	/**
	 * constructor 
	 * 
	 * @param myscene Scene value	
	 */
	public  RayTracerBase(Scene myscene){
		this.myscene=myscene;
	}
	
	/**
	 * Statement of an abstract function that calculates the color for the nearest intersection point, 
	 * if no intersection points are returned the color of the background	
	 * 
	 * @param ray Ray value
	 * @throws Exception
	 * @return Color
	 *  */
	public abstract Color traceRay(Ray ray) /*throws Exception*/;
}
