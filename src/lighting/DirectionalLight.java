/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author Shilat and Avigail
 *
 */
public class DirectionalLight extends Light implements LightSource {
	
    private Vector direction;
	
	
	/**
	 * Constructor for DirectionalLight
	 * this c-tor is normalize the direction vector
	 * @param intensity Color value
	 * @param direction Vector value
	 */
	public DirectionalLight(Color intensity, Vector direction) 
	{
		super(intensity);
		this.direction=direction.normalize();
	}

	/**************************************************************************************/
	@Override
	public Color getIntensity(Point p)
	{
		return super.getIntensity();
	}
	
	@Override
	public Vector getL(Point p)
	{
		return direction;
	}

}
