/**
 * 
 */
package lighting;

import primitives.*;

/**
 * class to AmbientLight - the basic light in the scene
 * An ambient light source represents a fixed-intensity
 * and fixed color light source that affects all objects in the scene equally.
 * @author Shilat and Avigail
 *
 */
public class AmbientLight {

	private Color Ia; //the color
	private Double3 Ka; 
	private Color intensity;
	
	
	/**
	 *default ctor 
	 */
	public AmbientLight() {
		intensity=Color.BLACK;
	}

	/**
	 * constructor that save the intensity=Ia*Ka
	 * @param Ia Color value
	 * @param Ka double value
	 */
	public AmbientLight(Color Ia,Double3 Ka ) {
		this.intensity = Ia.scale(Ka);
	}

	/**
	 * getter function for the intensity filed
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}
