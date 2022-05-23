/**
 * 
 */
package renderer;

import primitives.*;
import static primitives.Util.*;

import java.util.MissingResourceException;

import renderer.*;

/**
 * @author shilat and Avigail
 * Camera class
 */
public class Camera {
	private Point p0;
	private Vector vUp;
	private Vector vRight;
	private Vector vTo;
	private double width;
	private double height;
	private double distance;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;
	
	/**
	 * ctor
	 * @param p0 
	 * @param vTo
	 * @param vUp
	 */
	public Camera(Point p0, Vector vTo, Vector vUp) /*throws Exception*/{
		if(!isZero(vTo.dotProduct(vUp))) 
			throw new IllegalArgumentException("vUp is not ortogonal to vTo");

		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		vRight = (vTo.crossProduct(vUp)).normalize();
		
		this.p0 = p0;
	}
	
	/**
	 * sets the sizes of view plane
	 * @param width
	 * @param height
	 * @return the camera
	 */
	public Camera setVPSize(double width, double height){
		this.width = width;
		this.height = height;
		return this;
	}
	
	/**
	 * sets imageWriter
	 * @param imageWriter
	 * @return the camera
	 */
	public Camera setImageWriter(ImageWriter imageWriter){
		this.imageWriter=imageWriter;
		return this;
	}
	
	/**
	 * sets rayTracer
	 * @param rayTracer
	 * @return the camera
	 */
	public Camera setRayTracer(RayTracerBase rayTracer){
		this.rayTracer=rayTracer;
		return this;
	}
	
	/**
	 * sets the distance between the camera and the view plane
	 * @param distance
	 * @return the camera
	 */
	public Camera setVPDistance(double distance){
		this.distance = distance;
		return this;
	}
	
	public void renderImage () {
		if (this == null)
			throw new MissingResourceException("this function must have values in all the fileds", "Camera", "camera");
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("this function must have values in all the fileds", "RayTracerBase", "rayTracer");
		
		for (int i = 0; i < imageWriter.getNx(); i++)
		{
			for (int j = 0; j < imageWriter.getNy(); j++)	
			{
				Color rayColor = castRay(j, i);
				imageWriter.writePixel(j,i, rayColor); 
			}
		}
	}
	
	private Color castRay(int j, int i) {
		Ray ray = this.constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
		Color rayColor = rayTracer.traceRay(ray);
		return rayColor;
	}
	
	/**
	 * A function that creates a grid of lines
	 * @param interval int value
	 * @param color Color value
	 * */
	public void printGrid(int interval, Color color)
	{
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "imageWriter");
		

		for (int i = 0; i < imageWriter.getNx(); i++)
		{
			for (int j = 0; j < imageWriter.getNy(); j++)	
			{
				if(i % interval == 0 || j % interval == 0)
					imageWriter.writePixel(i, j, color); 
			}
		}

	}
	
	/**
	 * A function that finally creates the image.
	 * This function delegates the function of a class imageWriter
	 * */
	public void writeToImage()
	{
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "imageWriter");
		
		imageWriter.writeToImage();
	}
	
	/**
	 * 
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return
	 */
	public Ray constructRay(int nX, int nY, int j, int i)/*throws Exception*/{//constructRayThroughPixel
		Point Pc;
		if (isZero(distance))
			Pc=p0;
		else
			Pc=p0.add(vTo.scale(distance));
		
		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;///////////////
		double Xj=(j-(nX-1)/2d)*Rx;
		
		if(isZero(Xj) && isZero(Yi))
			return new Ray (p0, Pc.subtract(p0));
		
		Point Pij = Pc;
		
		if(!isZero(Xj))
			Pij = Pij.add(vRight.scale(Xj));
		
		if(!isZero(Yi))
			Pij = Pij.add(vUp.scale(-Yi));
		
		Vector Vij = Pij.subtract(p0);
		
		if(Pij.equals(p0))
			return new Ray(p0, new Vector(Pij.getX(), Pij.getY(), Pij.getZ()));
		return new Ray(p0, Vij);
	}

	/**
	 * @return the p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

}
