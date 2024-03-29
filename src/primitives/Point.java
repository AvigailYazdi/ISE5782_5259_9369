package primitives;

/**
 * @author shilat and Avigail
 * Point class
 */
public class Point {
	/////������ ������
	Double3 xyz;
	
	/**
	 * ctor that gets 3 values for a point
	 * @param d1 the first value-x
	 * @param d2 the second value-y
	 * @param d3 the third value-z
	 */
	public Point(double d1, double d2, double d3) {
		this.xyz=new Double3(d1,d2,d3);
	}

	/**
	 * ctor that gets a Double3 variable
	 * @param xyz the values of a point
	 */
	Point(Double3 xyz) {
		super();
		this.xyz = xyz;
	}
	
	/**
	 * Subtract two points triads into a new triad where each couple of
	 * numbers is subtracted
	 * @param p the right point
	 * @return result of subtract-vector
	 * @throws Exception creation a zero vector
	 */
	public Vector subtract(Point p) /*throws Exception*/ {
		return new Vector(this.xyz.subtract(p.xyz));
	}

	/**
	 * add vector to point into a new triad where each couple of
	 * numbers is subtracted
	 * @param v the vector to add
	 * @return result of add-point
	 * @throws Exception ?
	 */
	////��� ���� �� ������ �����
	public Point add(Vector v) /*throws Exception*/ {
		return new Point(this.xyz.add(v.xyz));
	}
	
	/**
	 * a function that calculates squared distance between two points
	 * @param p the second point
	 * @return the squared distance
	 */
	public double distanceSquared(Point p) {
		Double3 d=this.xyz.subtract(p.xyz);
		d= d.product(d);
		return d.d1+d.d2+d.d3;
	}
	/**
	 * a function that calculates distance between two points 
	 * @param p the second point
	 * @return the distance
	 */
	public double distance(Point p){
		return Math.sqrt(this.distanceSquared(p));
	}
	
	@Override
	public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null) return false;
	if (!(obj instanceof Point)) return false;
	Point other = (Point)obj;
	return xyz.equals(other.xyz);
	}
	
	@Override
	public String toString() { return xyz.toString(); }
	
	public double getX() {
		return xyz.d1;
	}

	public double getY() {
		return xyz.d2;
	}

	public double getZ() {
		return xyz.d3;
	}
	
	/**
     * Checks whether the different between the points is [almost] zero
     * @param point
     * @return true if the different between the points is zero or almost zero, false otherwise
     */
    public boolean isAlmostEquals(Point point) {

        return  (Util.isZero(xyz.d1-point.getX())) &&
                (Util.isZero(xyz.d2-point.getY())) &&
                (Util.isZero(xyz.d3-point.getZ()));
    }
}
