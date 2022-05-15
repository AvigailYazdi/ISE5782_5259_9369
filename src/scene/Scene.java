/**
 * 
 */
package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;
import java.util.LinkedList;
import java.util.List;
import lighting.LightSource;
/**
 * @author Shilat and Avigail
 *
 */
public class Scene {

	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;
	public List<LightSource>lights=new LinkedList<LightSource>() ;
	
	/**
	 * constructor 
	 * 
	 * */
	public Scene(String name){
		this.name=name;
		geometries = new Geometries();
		background=Color.BLACK;
		ambientLight=new AmbientLight();
	}
	

	/**
	 * setter function to lights  and return this for builder pattern
	 * 
	 * @param lights the lights to set
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}
	
	/**
	 * setter, and return this for builder pattern
	 * 
	 * @param background the background to set
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}
	

	/**
	 * setter, and return this for builder pattern
	 * 
	 * @param ambientLight the ambientLight to set
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
		
	}
	

	/**
	 * setter, and return this for builder pattern
	 * 
	 * @param geometries the geometries to set
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
}
