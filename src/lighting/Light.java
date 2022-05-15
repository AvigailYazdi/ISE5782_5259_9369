package lighting;

import primitives.Color;
/**
 * @author shilat and Avigail
 *
 */

public abstract class Light {
	
    private Color intensity;
	
	
	/**
	 * constructor for light
	 * @return the intensity
	 */
	protected Light(Color intensity){
		this.intensity=intensity;
	}
 
	/**
	 * getter to intensity
	 * @return intensity Color 
	 * */
	public Color getIntensity() {
		return intensity;
	}

}
