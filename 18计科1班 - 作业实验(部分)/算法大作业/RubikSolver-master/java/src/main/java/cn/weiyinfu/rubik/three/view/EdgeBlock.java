package cn.weiyinfu.rubik.three.view;

import java.awt.Graphics2D;

import cn.weiyinfu.rubik.three.view.math3D.Point3D;

/**
 * ��飬��12��
 * @author Administrator
 *
 */
public class EdgeBlock {

	private Square square1;
	private Square square2;
	
	/**
	 * ��鹹�캯��
	 * @param square1
	 * @param square2
	 */
	public EdgeBlock(Square square1, Square square2) {
		super();
		this.square1 = square1;
		this.square2 = square2;
	}
	
	/**
	 * ����ƾ���(0,0,0)��point�����ֱ����תangle�Ƕ�
	 * @param point���ռ�һ��
	 * @param angle����ת�Ƕ�
	 * @param clockwise: trueΪ˳ʱ�룬falseΪ��ʱ��
	 */
	public void rotate(Point3D point, double angle, boolean clockwise){
		square1.rotate(point, angle, clockwise);
		square2.rotate(point, angle, clockwise);
	}
	
	/**
	 * ��XY����ת�Ƕ�angle
	 * @param angle
	 */
	public void rotateXY(double angleX, double angleY) {
		square1.rotateXY(angleX, angleY);
		square2.rotateXY(angleX, angleY);
	}
	
	public void draw(Graphics2D graphics2D){
		square1.draw(graphics2D);
		square2.draw(graphics2D);
	}
}
