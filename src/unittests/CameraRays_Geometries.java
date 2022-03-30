/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import renderer.Camera;
import geometries.*;
import primitives.*;

/**
 * @author Home
 *
 */
public class CameraRays_Geometries 
{
	
	static final Point ZERO_POINT = new Point(0, 0, 0);

	/**
	 * A function that create 9 rays from vp 3x3
	 * @author Avigail Yazdi & Shilat Shimon
	 * @return List<Ray> value for the rays 
	 * */
	private List<Ray> Creat9RaysToVeiwPlane(Camera camera, int pixelX, int pixelY){
		List<Ray> raysFromCamera = new ArrayList<Ray>();
		for (int i = 0; i < pixelX; i++)
		{
			for (int j = 0; j < pixelY; j++)
			{
				try{
					raysFromCamera.add(camera.constructRay(pixelX, pixelY, j, i));
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					fail("There can be no zero rays");
				}
			}
		}
		return raysFromCamera;
	}
	
	/**
	 * A function that return list of intersection points
	 * @author Avigail Yazdi & Shilat Shimon
	 * @return List<Point> value for the intersection points 
	 * */
	private List<Point> findIntersectionPoints(Camera camera, Intersectable geomety){
		
		List<Ray> raysList = Creat9RaysToVeiwPlane(camera,3,3);
		
		List<Point> intersectionPoints = new ArrayList<Point>();
		for (Ray ray : raysList) 
		{
			List<Point> temp;
			
			temp = geomety.findIntersections(ray);
		
			if (temp != null)
			{
				intersectionPoints.addAll(temp);
			}
		}
		if(intersectionPoints.size() == 0)
			return null;
		return intersectionPoints;
	}

	@Test
	public void constructRaySphere() {
		
			//TC01:2 intersection points
			Sphere sphere=new Sphere(new Point(0,0,-3), 1);
			Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1).setVPSize(3, 3);
			assertEquals(2, findIntersectionPoints(camera, sphere).size(),"The count of intersections are not correct");

		
			//TC02:18 intersection points
			sphere=new Sphere(new Point(0,0,-2.5), 2.5);
			camera = new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1).setVPSize(3, 3);	
			assertEquals(18, findIntersectionPoints(camera, sphere).size(),"The count of intersections are not correct");
		
			//TC03:10 intersection points
			sphere=new Sphere(new Point(0,0,-2), 2);
			//same camera like tc02
			assertEquals(10, findIntersectionPoints(camera, sphere).size(),"The count of intersections are not correct");
		
			//TC04:9 intersection points
			sphere = new Sphere(ZERO_POINT, 4);
			camera = new Camera(ZERO_POINT, new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1).setVPSize(3, 3);
			assertEquals( 9, findIntersectionPoints(camera, sphere).size(),"The count of intersections are not correct");	
		
			//TC05:0 intersection points
			sphere=new Sphere(new Point(0,0,1), 0.5);
			camera = new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1).setVPSize(3, 3);	
			assertNull(findIntersectionPoints(camera, sphere),"The count of intersections are not correct");

	}
	
	@Test
	public void constructRayPlane() 
	{	
			//TC01:9 intersection points
			Plane plane =new Plane(new Point(0,2,0), new Vector(0,1,0));
			Camera camera =new Camera(ZERO_POINT, new Vector(0,1,0) , new Vector(0,0,-1)).setVPDistance(1).setVPSize(3, 3);
			assertEquals( 9, findIntersectionPoints(camera, plane).size(),"The count of intersections are not correct");	

			//TC02:9 intersection points	
			plane =new Plane(new Point(2,0,0),new Vector(1,2,-0.5));
			//same camera
			assertEquals( 9, findIntersectionPoints(camera, plane).size(),"The count of intersections are not correct");	

			//TC03:6 intersection points
			plane =new Plane(new Point(2,0,0), new Vector(1,1,0));
			//same camera
			assertEquals( 6, findIntersectionPoints(camera, plane).size(),"The count of intersections are not correct");	

	}
	
	@Test
	public void constructRayTriangle() 
	{
			//TC01:1 intersection points
			Triangle triangle=new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
			Camera camera=new Camera(ZERO_POINT, new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1).setVPSize(3, 3);
			assertEquals( 1, findIntersectionPoints(camera, triangle).size(),"The count of intersections are not correct");	
			
			//TC02:2 intersection points
			triangle=new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
			//same camera
			assertEquals( 2, findIntersectionPoints(camera, triangle).size(),"The count of intersections are not correct");	

	}

}
