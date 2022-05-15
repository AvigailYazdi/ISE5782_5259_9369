/**
 * 
 */
package lighting;
/**
 * @author shilat and Avigail
 *
 */

import primitives.*;

/**
 * class to AmbientLight - the basic light in the scene
 * An ambient light source represents a fixed-intensity
 * and fixed color light source that affects all objects in the scene equally.
 * @author Shilat and Avigail
 *
 */
public class AmbientLight extends Light{

	private Color Ia; //the color
	private Double3 Ka; 

	
	/**
	 * constructor that save the intensity=Ia*Ka
	 * @param Ia Color value
	 * @param Ka double value
	 */
	public AmbientLight(Color Ia,Double3 Ka ) {
		super(Ia.scale(Ka));
		
	}

	/**
	 * A default constructor
	 * this c-tor put the defalt color - black to the ambition light
	 */
	public AmbientLight() {
		super(Color.BLACK);
	}
	

}
