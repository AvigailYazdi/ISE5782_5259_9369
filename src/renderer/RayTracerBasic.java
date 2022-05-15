/**
 * 
 */
package renderer;

import java.util.List;


import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
/**
 * @author Shilat and Avigail
 *
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * constructor
	 * 
	 * @param myscene Scene value
	 */
	public RayTracerBasic(Scene myscene) {
		super(myscene);
		//return null;
		
	}
	
	/**
	 * Function that calculates the color for the nearest intersection point, 
	 * if no intersection points are returned the color of the background	
	 * 
	 * @param ray Ray value
	 * @return Color
	 * @throws Exception
	 *  */
	public Color traceRay(Ray ray) /*throws Exception*/{
		    List<GeoPoint> intersections = myscene.geometries.findGeoIntersections(ray);
			if(intersections == null)
				return  myscene.background;
			GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
			return calcColor(closestPoint,ray);
	}
	
	/**
	 * Function for calculating a point color	
	 * 
	 * @param point Point value
	 * @return Color
	 * */
	/*private Color calcColor(GeoPoint intersection, Ray ray){/////////////////
		//return myscene.ambientLight.getIntensity();
		Color KaIa = myscene.ambientLight.getIntensity();
		Color Ie = intersection.geometry.getEmission(); 

		return KaIa.add(Ie);
	}*/
	
	

	/**
	 * Function for calculating a point color	
	 * 
	 * @param point Point3D value
	 * @return Color
	 * @throws IllegalArgumentException 
	 * */
	private Color calcColor(GeoPoint intersection, Ray ray) throws IllegalArgumentException {
		Color KaIa = myscene.ambientLight.getIntensity();
		Color Ie = intersection.geometry.getEmission(); 

		return KaIa.add(Ie).add(calcLocalEffects(intersection, ray));
	}
	
	/**
	 * help function that calculate the local color
	 * 
	 * @param intersection GeoPoint value
	 * @param ray Ray value
	 * */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir().normalize();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (nv == 0) //לא רואים את הנקודה עליה האור משפיע מחזיר שחור
			return Color.BLACK;
		//רוצים לבדוק את ההשפעה של האור עלי לפי סוג החומר ממנו הגוף עשוי
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.getKD();
		double ks = material.getKS();
		Color color = Color.BLACK; //עוד לא יודעים השפעות
		for (LightSource lightSource : myscene.lights) //עוברים כעל כל מקור אור בסצנה ובודקים איך הוא משפיע על הצבע בנקודה המסויימת
		{
			Vector l = lightSource.getL(intersection.point);//וקטור ממקור אור עד לנקודה
			double nl = Util.alignZero(n.dotProduct(l));//רוצים לדעת שאני באותו כיוון כי אם לא לא רואים את ההשפעות
			if (nl * nv > 0) 
			{ 
				// sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return color;
		}

	/**
	 * help function that calculate the specolar effect
	 * 
	 * @param ks double value
	 * @param l Vector value
	 * @param n Vector value
	 * @param v Vector value
	 * @param nShininess int value
	 * @param lightIntensity Color value
	 * @throws IllegalArgumentException
	 * */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) throws IllegalArgumentException {
		Vector r = l.subtract(n.scale(Util.alignZero(2*(l.dotProduct(n))))).normalize();
		double RV = Util.alignZero(r.dotProduct(v));
		double minusRV = RV*(-1);
		if (minusRV <= 0)
			return Color.BLACK;
		return lightIntensity.scale(Util.alignZero(Math.pow(minusRV, nShininess))*ks);
	}

	/**
	 * help function that calculate the difusive effect
	 * 
	 * @param kd double value
	 * @param l Vector value
	 * @param n Vector value
	 * @param lightIntensity Color value
	 * */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double ln = Util.alignZero(l.dotProduct(n));
		return lightIntensity.scale(Util.alignZero(Math.abs(ln)*kd));
	}
	
	
}
