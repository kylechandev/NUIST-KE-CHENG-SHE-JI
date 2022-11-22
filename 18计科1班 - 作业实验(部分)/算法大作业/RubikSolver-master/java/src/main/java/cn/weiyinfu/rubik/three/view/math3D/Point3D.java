package cn.weiyinfu.rubik.three.view.math3D;
import java.awt.Point;


public class Point3D implements Cloneable{

	public double x;
	public double y;
	public double z;
	private double d;
	
	public Point3D() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Point3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void getPoint2D(Point point) {
		d = ((double)2000/2)/(double)Math.tan(80/2);		//�ӽ�2000
		point.x=(int)((d*this.x)/(d-(-z)));
		point.y=(int)((d*this.y)/(d-(-z)));
	}
	
	public Point getPoint2D() {
		Point point=new Point();
		d = ((double)2000/2)/(double)Math.tan(80/2);		//�ӽ�2000
		point.x=(int)((d*this.x)/(d-(-z)));
		point.y=(int)((d*this.y)/(d-(-z)));
		return point;
	}
	
	public void rotateX(double angle) {
		double sinAngle = (double)Math.sin(angle);
		double cosAngle = (double)Math.cos(angle);
	    double y=this.y;
	    double z=this.z;
	    this.y = y*cosAngle - z*sinAngle;
	    this.z = y*sinAngle + z*cosAngle;
	}
	
	public void rotateY(double angle) {
		double sinAngle = (double)Math.sin(angle);
		double cosAngle = (double)Math.cos(angle);
	    double z=this.z;
	    double x=this.x;
	    this.z = z*cosAngle - x*sinAngle;
	    this.x = z*sinAngle + x*cosAngle;
	}
	
	public void rotateZ(double angle) {
		double sinAngle = (double)Math.sin(angle);
		double cosAngle = (double)Math.cos(angle);
	    double x=this.x;
	    double y=this.y;
	    this.x = x*cosAngle - y*sinAngle;
	    this.y = x*sinAngle + y*cosAngle;
	}
	
	/**
	 * 
	 * @param angleX ��X����ת�ĽǶ�
	 * @param angleY ��Y����ת�ĽǶ�
	 */
	public void rotateXY(double angleX, double angleY) {
		rotateX(angleX);
		rotateY(angleY);
	}
	
	/**
	 * ������(0,0,0)-(x,y,z)��ת�Ƕ�angle
	 * @param x
	 * @param y
	 * @param z
	 * @param angle
	 */
	public void rotateXYZ(double x,double y,double z,double angle){
		/*double angleX=Math.acos(y/Math.sqrt(y*y+z*z));
		double angleZ=Math.acos(Math.sqrt(y*y+z*z)/Math.sqrt(x*x+y*y+z*z));
		rotateX(-angleX);
		rotateZ(angleZ);
		rotateY(angle);
		rotateZ(-angleZ);
		rotateX(angleX);*/
		double cosAngle=Math.cos(angle);double sinAngle=Math.sin(angle);
		double r=Math.sqrt(x*x+y*y+z*z);
		double ax=x/r,ay=y/r,az=z/r;
		
		double m11=ax*ax*(1-cosAngle)+cosAngle;
		double m12=ay*ax*(1-cosAngle)+az*sinAngle;
		double m13=az*ax*(1-cosAngle)-ay*sinAngle;
		
		double m21=ax*ay*(1-cosAngle)-az*sinAngle;
		double m22=ay*ay*(1-cosAngle)+cosAngle;
		double m23=az*ay*(1-cosAngle)+ax*sinAngle;
		
		double m31=ax*az*(1-cosAngle)+ay*sinAngle;
		double m32=ay*az*(1-cosAngle)-ax*sinAngle;
		double m33=az*az*(1-cosAngle)+cosAngle;
		
		double x1=this.x;double y1=this.y;double z1=this.z;
		this.x=x1*m11+y1*m21+z1*m31;
		this.y=x1*m12+y1*m22+z1*m32;
		this.z=x1*m13+y1*m23+z1*m33;
	}
	
	@Override
	public Point3D clone() {
		Point3D point3D=new Point3D();
		try {
			point3D = (Point3D) super.clone();
			point3D.x=this.x;
			point3D.y=this.y;
			point3D.z=this.z;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return point3D;
	}
}
