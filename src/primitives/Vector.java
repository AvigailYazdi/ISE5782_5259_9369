package primitives;

public class Vector extends Point {

	/**
	 * ctor that gets 3 values for a vector
	 * @param d1 the first value-x
	 * @param d2 the second value-y
	 * @param d3 the third value-z
	 * @throws Exception a zero vector-illegal argument
	 */
	public Vector(double d1, double d2, double d3) throws Exception{
		super(d1, d2, d3);
		if((this.xyz).equals(Double3.ZERO))
			throw new IllegalArgumentException();	
		}
	
	/**
	 * ctor that gets a Double3 variable
	 * @param xyz the values of a vector
	 * @throws Exception a zero vector-illegal argument
	 */
	Vector(Double3 xyz) throws Exception{
		super(xyz);
		if((this.xyz).equals(Double3.ZERO))
			throw new IllegalArgumentException();
	}
	
	/**
	 * ??????
	 */
	///למה חייב לשנות שם פונקציה
	public Vector add(Vector v) throws Exception {
		return new Vector(this.xyz.add(v.xyz));
	}
	
	/**
	 * a function that scales the vector with scalar
	 * @param num the scalar to scale
	 * @return result of scale-vector
	 * @throws Exception a zero vector-illegal argument
	 */
	public Vector scale(double num) throws Exception
	{
		return new Vector(this.xyz.scale(num));
	}
	
	/**
	 * a function that gets two vectors and does dot product
	 * @param v the second vector
	 * @return result of dot product
	 */
	///לא הבנתי מה הוא רוצה מהחיים שלי עם כל הבדיקות המוזרות
	public double dotProduct(Vector v)
	{
		Double3 d=this.xyz.product(v.xyz);
		return d.d1+d.d2+d.d3;
	}
	
	/**
	 * a function that gets two vectors and return the ? vector to them
	 * @param v the second vector
	 * @return the vertical vector 
	 * @throws Exception a zero vector-illegal argument
	 */
	////צריך לטפל בחריגות
	public Vector crossProduct(Vector v) throws Exception
	{
		double x=this.xyz.d2*v.xyz.d3-this.xyz.d3*v.xyz.d2;
		double y=this.xyz.d3*v.xyz.d1-this.xyz.d1*v.xyz.d3;
		double z=this.xyz.d1*v.xyz.d2-this.xyz.d2*v.xyz.d1;
		return new Vector(x,y,z);
	}
	
	/**
	 * a function that calculates the squared length of a vector
	 * @return squared length
	 */
	public double lengthSquared()
	{
		Point p=new Point(this.xyz);
		return p.distanceSquared(new Point(Double3.ZERO));
	}
	
	/**
	 * a function that calculates the length of a vector
	 * @return length
	 */
	public double length()
	{
		return Math.sqrt(this.lengthSquared());
	}
	
	/**
	 * a function that normalizes the length of a vector to 1
	 * @return the normalized vector
	 * @throws Exception a zero vector-illegal argument
	 */
	public Vector normalize() throws Exception 
	{
		double len=this.length();
		return this.scale(1/len);
	}
	
	@Override
	public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null) return false;
	if (!(obj instanceof Point)) return false;
	Point other = (Point)obj;
	return super.equals(other);
	}
	
	@Override
	public String toString() { return "->" + super.toString(); }
}