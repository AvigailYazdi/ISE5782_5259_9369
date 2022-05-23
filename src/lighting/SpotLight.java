/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author Shilat and Avigail
 *
 */
public class SpotLight extends PointLight{

	private Vector direction;

	/**
	 * constructor for spotlight that receives all the params
	 * @param direction Vector value 
	 * @param intensity Color value
	 * @param position Point3D value
	 * @param KC double value
	 * @param KL double value
	 * @param KQ double value
	 */
	public SpotLight(Color intensity, Point position, double KC, double KL, double KQ, Vector direction) {
		super(intensity, position, KC, KL, KQ);
		this.direction=direction.normalize();
	}

	/**
	 * constructor for spotlight that receives 3 params
	 * @param direction Vector value 
	 * @param intensity Color value
	 * @param position Point3D value
	 */
	public SpotLight(Color intensity, Point position, Vector direction)  {
		super(intensity, position);
		this.direction=direction.normalize();
	}
	
	
	/*******************************************************************************/
	@Override
	public Color getIntensity(Point p) {
		double pl = Util.alignZero(direction.dotProduct(getL(p)));
		if(getL(p) == null)
			return Color.BLACK;
		if (pl <= 0)
			return Color.BLACK;
		return super.getIntensity(p).scale(pl);
	}
}
