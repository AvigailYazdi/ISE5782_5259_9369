/**
 * 
 */
package geometries;

import primitives.*;

/**
 * @author shilat and Avigail
 * Geometry class
 */

public abstract class Geometry extends Intersectable {

	protected  Color emission = Color.BLACK;
	private Material material=new Material();
	
	/**
	 * a function that return the normal to a geometry in a point
	 * @param p the point
	 * @return the normal in the point
	 */
	public abstract Vector getNormal(Point p);
	
	/**
	 * getter function for the color filed in geometry class
	 * @return emission Color value
	 * */
	public Color getEmission() {
		return emission;
	}
	
	/**
	 * setter function for the color filed and return this- geometry class
	 * @return the geometry-this
	 * */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
	
	/**
	 * getter function for the Material filed in geometry class
	 * 
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * setter function for the Material filed 
	 * 
	 * @param material the material to set
	 * @return the object - builder
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

}
