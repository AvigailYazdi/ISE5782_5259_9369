/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author Shilat and Avigail
 *
 */
public class PointLight extends Light implements LightSource{

	private Point position;
	private double KC = 1;
	private double KL = 0;
	private double KQ = 0;
	
	
	
	/**
	 * constructor of PointLight that receives all the params
	 * @param intensity Color value
	 * @param position Point3D value
	 * @param KC double value
	 * @param KL double value
	 * @param KQ double value
	 */
	public PointLight(Color intensity, Point position, double KC, double KL, double KQ) 
	{
		super(intensity);
		this.position=position;
		this.KC=KC;
		this.KL=KL;
		this.KQ=KQ;
	}
	
	/**
	 * constructor of PointLight that receives two params
	 * kc,kl,kq are with default value
	 * @param intensity Color value
	 * @param position Point3D value
	 */
	public PointLight(Color intensity, Point position)
	{
		super(intensity);
		this.position = position;
	}
	
	
	
	/**
	 * setter to filed position
	 * @param position the position to set
	 * @return the object - builder
	 */
	public PointLight setPosition(Point position) 
	{
		this.position = position;
		return this;
	}


	/**
	 * setter to filed kc
	 * @param kC the kC to set
	 * @return the object - builder
	 */
	public PointLight setKc(double kC) 
	{
		KC = kC;
		return this;
	}

	/**
	 * setter to filed kl
	 * @param kL the kL to set
	 * @return the object - builder
	 */
	public PointLight setKl(double kL) 
	{
		KL = kL;
		return this;
	}


	/**
	 * setter to filed kq
	 * @param kQ the kQ to set
	 * @return the object - builder
	 */
	public PointLight setKq(double kQ) 
	{
		KQ = kQ;
		return this;
	}

	/**************************************************************************************/
	@Override
	public Color getIntensity(Point p) throws IllegalArgumentException
	{
		return getIntensity().reduce((KC + KL * p.distance(position)+ KQ * p.distanceSquared(position)));	
	}
	
	@Override
	public Vector getL(Point p) throws IllegalArgumentException 
	{	
		if (p.equals(position))
			return null; //In order not to reach a state of exception due to the zero vector
		return p.subtract(position).normalize();		
	}
}
