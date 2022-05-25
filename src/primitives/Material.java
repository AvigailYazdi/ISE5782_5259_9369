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
	
	public int nShininess=0; //shine
	public Double3 KD=Double3.ZERO; //refraction
	public Double3 KS=Double3.ZERO; //specular
	public Double3 KT=Double3.ZERO; //diffuse
	public Double3 KR=Double3.ZERO;//reflaction
	
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
	
	/**
	 * @param kT the kT to set
	 */
	public Material setKt(double kt) 
	{
		KT = new Double3(kt);
		return this;
	}
	
	/**
	 * @param kR the kR to set
	 */
	public Material setKr(double kR) 
	{
		KR = new Double3(kR);
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
	 * @param kT the kT to set
	 */
	public Material setKt(Double3 kT) 
	{
		KT = kT;
		return this;
	}
	
	/**
	 * @param kR the kR to set
	 */
	public Material setKr(Double3 kR) 
	{
		KR = kR;
		return this;
	}
	
	
	/****************************************************************************/

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

	/**
	 * @return the kT
	 */
	public double getKT() {
		return KT.d1;
	}
	
	/**
	 * @return the kR
	 */
	public double getKR() {
		return KR.d1;
	}
	
	
}
