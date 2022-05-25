/**
 * 
 */
package renderer;

import primitives.*;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;
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
	private int numOfRays=1;///////////////////////////////////
	
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
	
	/**
	 * A setter function for parameter rayTracer
	 * this function return the object - this for builder pattern
	 * 
	 * @param rayTracer RayTracerBase value
	 * */
	public Camera setNumOfRays(int numOfRays){
		if(numOfRays == 0)
			this.numOfRays=1;
		else
		 this.numOfRays = numOfRays;
		return this;
	}
	
	/**
	 * The function transfers beams from camera to pixel, tracks the beam
	 *  and receives the pixel color from the point of intersection
	 * */
	public void renderImage () {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("this function must have values in all the fileds", "RayTracerBase", "rayTracer");
		
		/*for (int i = 0; i < imageWriter.getNx(); i++)
		{
			for (int j = 0; j < imageWriter.getNy(); j++)	
			{
				Color rayColor = castRay(j, i);
				imageWriter.writePixel(j,i, rayColor); 
			}
		}*/
		for (int i = 0; i < imageWriter.getNx(); i++)
		{
			for (int j = 0; j < imageWriter.getNy(); j++)	
			{
				if(numOfRays == 1 || numOfRays == 0)
				{
					Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
					Color rayColor = rayTracer.traceRay(ray);
					imageWriter.writePixel(j, i, rayColor); 
				}
				else
				{	
					List<Ray> rays = constructBeamThroughPixel(imageWriter.getNx(), imageWriter.getNy(), j, i,numOfRays);
					Color rayColor = rayTracer.traceRay(rays);
					imageWriter.writePixel(j, i, rayColor); 
				}
				
			}
		}
	}
	
	/*private Color castRay(int j, int i) {
		Ray ray = this.constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
		Color rayColor = rayTracer.traceRay(ray);
		return rayColor;
	}*/
	
	/**
	 * A function that creates a grid of lines
	 * @param interval int value
	 * @param color Color value
	 * */
	public void printGrid(int interval, Color color){
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
	public void writeToImage(){
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

	
	
	/////////////////////////////////////////////////////////////////
	public List<Ray> constructBeamThroughPixel (int nX, int nY, int j, int i, int raysAmount){

		//The distance between the screen and the camera cannot be 0
        if (isZero(distance))
        {
            throw new IllegalArgumentException("distance cannot be 0");
        }
        
		int numOfRays = (int)Math.floor(Math.sqrt(raysAmount)); //num of rays in each row or column
		
		if (numOfRays==1) 
			return List.of(constructRay(nX, nY, j, i));
//
		/*Point Pc;
		if (isZero(distance))
			Pc=p0;
		else
			Pc=p0.add(vTo.scale(distance));*/
		
		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;
		double Xj=(j-(nX-1)/2d)*Rx;

//        
        double PRy = Ry / numOfRays; //height distance between each ray
        double PRx = Rx / numOfRays; //width distance between each ray

        List<Ray> sample_rays = new ArrayList<>();
//
//        double Ry = height/nY; //The number of pixels on the y axis
//        double Rx = width/nX;  //The number of pixels on the x axis
//        double yi =  ((i - nY/2d)*Ry); //distance of original pixel from (0,0) on Y axis
////     double xj=   ((j - nX/2d)*Rx); //distance of original pixel from (0,0) on x axis
//        double pixel_Ry = Ry/num_of_sample_rays; //The height of each grid block we divided the parcel into
//        double pixel_Rx = Rx/num_of_sample_rays; //The width of each grid block we divided the parcel into

        for (int row = 0; row < numOfRays; ++row) {//foreach place in the pixel grid
            for (int column = 0; column < numOfRays; ++column) {
                sample_rays.add(constructRaysThroughPixel(PRy,PRx,Yi, Xj, row, column));//add the ray
            }
        }
        sample_rays.add(constructRay(nX, nY, j, i));//add the center screen ray
        return sample_rays;
	}
	
	 /**
     * In this function we treat each pixel like a little screen of its own and divide it to smaller "pixels".
     * Through each one we construct a ray. This function is similar to ConstructRayThroughPixel.
     * @param Ry height of each grid block we divided the pixel into
     * @param Rx width of each grid block we divided the pixel into
     * @param yi distance of original pixel from (0,0) on Y axis
     * @param xj distance of original pixel from (0,0) on X axis
     * @param j j coordinate of small "pixel"
     * @param i i coordinate of small "pixel"
     * @param distance distance of screen from camera
     * @return beam of rays through pixel
     */
    private Ray constructRaysThroughPixel(double Ry,double Rx, double yi, double xj, int j, int i){
        Point Pc = p0.add(vTo.scale(distance)); //the center of the screen point

        double y_sample_i =  (i *Ry + Ry/2d); //The pixel starting point on the y axis
        double x_sample_j=   (j *Rx + Rx/2d); //The pixel starting point on the x axis

        Point Pij = Pc; //The point at the pixel through which a beam is fired
        //Moving the point through which a beam is fired on the x axis
        if (!isZero(x_sample_j + xj))
        {
            Pij = Pij.add(vRight.scale(x_sample_j + xj));
        }
        //Moving the point through which a beam is fired on the y axis
        if (!isZero(y_sample_i + yi))
        {
            Pij = Pij.add(vUp.scale(-y_sample_i -yi ));
        }
        Vector Vij = Pij.subtract(p0);
        return new Ray(p0,Vij);//create the ray throw the point we calculate here
    }
}
