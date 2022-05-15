/**
 * 
 */
package primitives;

/**
 * @author Shilat and Avigail
 *
 */
public class Material {

	/**
	 * 
	 */
	
	public int nShininess=0; 
	public Double3 KD=Double3.ZERO; 
	public Double3 KS=Double3.ZERO; 
	
	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setShininess(int nShininess) 
	{
		this.nShininess = nShininess;
		return this;
		
	}

	/**
	 * @param kD the kD to set
	 */
	public Material setKd(double kD) 
	{
		KD = new Double3(kD);
		return this;
	}

	/**
	 * @param kS the kS to set
	 */
	public Material setKs(double kS) 
	{
		KS = new Double3(kS);
		return this;
	}
	
	/****************************************************************************/


	/**
	 * @param kD the kD to set
	 */
	public Material setKd(Double3 kD) 
	{
		KD = kD;
		return this;
	}

	/**
	 * @param kS the kS to set
	 */
	public Material setKs(Double3 kS) 
	{
		KS = kS;
		return this;
	}

	/**
	 * @return the kD
	 */
	public double getKD() {
		return KD.d1;
	}

	/**
	 * @return the kS
	 */
	public double getKS() {
		return KS.d1;
	}


	
	
}
