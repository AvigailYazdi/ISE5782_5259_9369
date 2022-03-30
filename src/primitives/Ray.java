package primitives;

import java.util.Objects;

/**
 * @author shilat and Avigail
 * Ray class
 */
public class Ray {
	
	final Point p0;
	final Vector dir;
	
	/**
	 * ctor that gets a point and a direction vector
	 * @param p0 the point
	 * @param dir the direction vector
	 * @throws Exception a zero vector-illegal argument
	 */
	public Ray(Point p0, Vector dir) /*throws Exception*/ {
		super();
		this.p0 = p0;
		this.dir = dir.normalize();
	}

	/**
	 * a function that returns the point
	 * @return the point-p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * a function that returns the direction vector
	 * @return the direction vector-dir
	 */
	public Vector getDir() {
		return dir;
	}
	
	/**
	 * a function that returns a vector that scale
	 * @param t
	 * @return vector
	 */
	public Point getPoint(double t) {
		return p0.add(dir.scale(t));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		return Objects.equals(dir, other.dir) && Objects.equals(p0, other.p0);
	}

	@Override
	public String toString() {
		return "Ray: p0=" + p0 + ", dir=" + dir + " ";
	}
	
}
