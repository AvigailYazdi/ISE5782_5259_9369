/**
 * 
 */
package renderer;

import primitives.*;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;


/**
 * @author Shilat and Avigail
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
	private int numOfRays=1;
	
	/**
     * numOfRaysForSuperSampling - number of rays for super sampling
     * AdaptiveSuperSamplingFlag - Flag to choose whether to apply the Adaptive Super Sampling
     */
    private int maxRaysForSuperSampling = 500;
    private boolean AdaptiveSuperSamplingFlag = false;
	
	

	private int _threads = 1;
	private static final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
	private boolean print = false; // printing progress percentage
	
	//private int MAX_LEVEL=10;
	
	
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
	private void renderImageB() {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fields", "ImageWriter", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("this function must have values in all the fields", "RayTracerBase", "rayTracer");
		
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
	
	/*private void castRay(int j, int i) {
		Ray ray = this.constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
		Color rayColor = rayTracer.traceRay(ray);
		imageWriter.writePixel(j, i, rayColor);
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
		double Yi=(i-(nY-1)/2d)*Ry;
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
		
		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;
		double Xj=(j-(nX-1)/2d)*Rx;
    
        double PRy = Ry / numOfRays; //height distance between each ray
        double PRx = Rx / numOfRays; //width distance between each ray

        List<Ray> sample_rays = new ArrayList<>();

//        double Ry = height/nY; //The number of pixels on the y axis
//        double Rx = width/nX;  //The number of pixels on the x axis
//        double yi =  ((i - nY/2d)*Ry); //distance of original pixel from (0,0) on Y axis
//        double xj=   ((j - nX/2d)*Rx); //distance of original pixel from (0,0) on x axis
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
    ////////////////////////////////////////////////////////////////////////////////////

    /**
    * Pixel is an internal helper class whose objects are associated with a Render
    * object that they are generated in scope of. It is used for multithreading in
    * the Renderer and for follow up its progress.<br/>
    * There is a main follow up object and several secondary objects - one in each
    * thread.
    *
    * @author Dan
    *
    */
    private class Pixel {
    	 private long _maxRow = 0;
         private long _maxCols = 0;
         private long _pixels = 0;
         public volatile int row = 0;
         public volatile int col = -1;
         private long _counter = 0;
         private int _percent = 0;
         private long _nextCounter = 0;

         /**
          * constructor - gets the image params
          * @param maxRow the nx
          * @param maxCols the ny
          */
         public Pixel(int maxRow, int maxCols){
             _maxCols = maxCols;
             _maxRow = maxRow;
             _pixels = maxCols * maxRow;
             _nextCounter = _pixels/100;
             if(print)
                 System.out.printf("\r %02d%%", _percent);
         }

         /**
          * default constructor
          */
         public Pixel() {}

         /**
          * Pixel - nextPixel
          * return true if there is a next pixel
          */
         public boolean nextPixel(Pixel target) {
             int percent = nextp(target);
             if(percent > 0 && print) {
                 synchronized(System.out){System.out.printf("\r %02d%%", percent);}
             }
             if(percent >= 0)
                 return true;
             if(print){
                 synchronized(System.out){System.out.printf("\r %02d%%", 100);}
         }
             return false;
         }

         /**
          * Pixel - nextP
          * changing target pixel to the next pixel
          * return 0 if there is a next pixel, and -1 else
          */
         private synchronized int nextp(Pixel target) {
             col++;
             _counter++;
             if(col < _maxCols) {
                 target.row = this.row;
                 target.col = this.col;
                 if(_counter == _nextCounter)
                 {
                     _percent++;
                     _nextCounter = _pixels * (_percent + 1) / 100;
                     return _percent;
                 }
                 return 0;
             }

             row++;
             if(row < _maxRow) {
                 col = 0;
                 if(_counter == _nextCounter)
                 {
                     _percent++;
                     _nextCounter = _pixels * (_percent + 1) / 100;
                     return _percent;
                 }
                 return 0;
             }
             return -1;
         }
     }

	////////////////////////////////////////////////////////////////////////////////////////
	
    /**
     * Render - Image
     * Converts the image from D3 to D2
     * The result is written in imageWriter
     */
    public void renderImage() throws Exception{
    	if (AdaptiveSuperSamplingFlag) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        //Multi threads for calculating pixel color
        final Pixel thePixel = new Pixel(nY,nX);
        Thread[] threads = new Thread[_threads];
        for(int i = 0 ; i<_threads; i++)
        {
            threads[i] = new Thread(()->{
                Pixel pixel = new Pixel();
                try {
                    //while there is a pixel that is not processed by a thread
                    while (thePixel.nextPixel(pixel)) {
                                primitives.Color color = AdaptiveSuperSampling(nX, nY, pixel.col, pixel.row,maxRaysForSuperSampling);
                                imageWriter.writePixel(pixel.col, pixel.row, new Color(color.getColor()));
                    }
                }
                catch (Exception e){}
            });
        }


        //starts all threads
        for (Thread thread : threads)
            thread.start();

        //Wait for all threads to finish
        for (Thread thread : threads)
            thread.join();

        //finish to create the image
        if(print)
            System.out.print("\r100%\n");
    	}
    	
        else
        {this.renderImageB();}

    }
    

    /**
     * set Max Rays For Super Sampling
     * the maximum rays for Super Sampling
     */
    public void setMaxRaysForSuperSampling(int maxRaysForSuperSampling) {
        this.maxRaysForSuperSampling = maxRaysForSuperSampling;
    }
    
    /**
     * set Adaptive Super Sampling Flag
     * Flag to choose whether to apply the Adaptive Super Sampling
     */
    public void setAdaptiveSuperSamplingFlag(boolean adaptiveSuperSamplingFlag) {
        AdaptiveSuperSamplingFlag = adaptiveSuperSamplingFlag;
    }
    
    /**
     * Adaptive Super Sampling
     *@param nX number of pixels in x axis
     *@param nY number of pixels in y axis
     *@param j number of pixels in x axis
     *@param i number of pixels in x axis
     *@param numOfRays max num of ray for pixel
     *@return color for pixel
     */
    private primitives.Color AdaptiveSuperSampling(int nX, int nY, int j, int i, int numOfRays) throws Exception {
        int numOfRaysInRowCol = (int)Math.floor(Math.sqrt(numOfRays));
        if(numOfRaysInRowCol == 1)
            return rayTracer.traceRay(constructRay(nX, nY, j, i));
        Point Pc;
        if (distance != 0)
            Pc = p0.add(vTo.scale(distance));
        else
            Pc = p0;
        Point Pij = Pc;
        double Ry = height/nY;
        double Rx = width/nX;
        double Yi= (i - (nY/2d))*Ry + Ry/2d;
        double Xj= (j - (nX/2d))*Rx + Rx/2d;
        if(Xj != 0) Pij = Pij.add(vRight.scale(-Xj)) ;
        if(Yi != 0) Pij = Pij.add(vUp.scale(-Yi)) ;
        double PRy = Ry/numOfRaysInRowCol;
        double PRx = Rx/numOfRaysInRowCol;
        return AdaptiveSuperSamplingRec(Pij, Rx, Ry, PRx, PRy,null);
    }

    /**
     * Adaptive Super Sampling recursive
     *@param centerP the screen location
     *@param Width the screen width
     *@param Height the screen height
     *@param minWidth the min cube width
     *@param minHeight the min cube height
     *@param prePoints list of pre points to
     *@return color for pixel
     */
    private primitives.Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, List<Point> prePoints) throws Exception {

        if (Width < minWidth * 2 || Height < minHeight * 2) {
            return rayTracer.traceRay(new Ray(p0, centerP.subtract(p0)));
        }

        List<Point> nextCenterPList = new LinkedList<>();
        List<Point> cornersList = new LinkedList<>();
        List<primitives.Color> colorList = new LinkedList<>();
        Point tempCorner;
        Ray tempRay;
        for (int i = -1; i <= 1; i += 2){
            for (int j = -1; j <= 1; j += 2) {
            	tempCorner = centerP.add(vRight.scale(i * Width / 2)).add(vUp.scale(j * Height / 2));
                cornersList.add(tempCorner);
                if (prePoints == null || !isInList(prePoints, tempCorner)) {
                    tempRay = new Ray(p0, tempCorner.subtract(p0));
                    nextCenterPList.add(centerP.add(vRight.scale(i * Width / 4)).add(vUp.scale(j * Height / 4)));
                    colorList.add(rayTracer.traceRay(tempRay));
                    }
                }
            }

        if (nextCenterPList == null || nextCenterPList.size() == 0) {
            return primitives.Color.BLACK;
        }


        boolean isAllEquals = true;
        primitives.Color tempColor = colorList.get(0);
        for (primitives.Color color : colorList) {
            if (!tempColor.isAlmostEquals(color))
                isAllEquals = false;
        }
        if (isAllEquals && colorList.size() > 1)
            return tempColor;


        tempColor = primitives.Color.BLACK;
        for (Point center : nextCenterPList) {
            tempColor = tempColor.add(AdaptiveSuperSamplingRec(center, Width/2,  Height/2,  minWidth,  minHeight ,  cornersList));
        }
        return tempColor.reduce(nextCenterPList.size());


    }
    
    /**
     * is In List - Checks whether a point is in a points list
     * @param point the point we want to check
     * @param pointsList where we search the point
     * @return true if the point is in the list, false otherwise
     */
    private boolean isInList(List<Point> pointsList, Point point) {
        for (Point tempPoint : pointsList) {
            if(point.isAlmostEquals(tempPoint))
                return true;
        }
        return false;
    }
    
    /**
    * Set multi-threading <br>
    * - if the parameter is 0 - number of cores less 2 is taken
    *
    * @param threads number of threads
    * @return the Render object itself
    */
    public Camera setMultithreading(int threads) {
    if (threads < 0)
	    throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
    if (threads != 0)
	    _threads = threads;
    else {
	    int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
	    _threads = cores <= 2 ? 1 : cores;
    }
    return this;
    }

    /**
    * Set debug printing on
    *
    * @return the Render object itself
    */
    public Camera setDebugPrint(double d) {
	    print = true;
	    return this;
    }
}
