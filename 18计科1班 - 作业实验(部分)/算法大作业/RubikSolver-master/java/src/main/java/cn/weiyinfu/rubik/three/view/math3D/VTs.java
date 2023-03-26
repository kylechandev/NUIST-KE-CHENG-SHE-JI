package cn.weiyinfu.rubik.three.view.math3D;
import java.awt.*;

public class VTs {

  public int viewX_To_screenX(int x) {
    return x + 700/2;
  }

  public int viewY_To_screenY(int y) {
    return -y + 700/2;
  }
  
  public static Point view_To_screen(Point point){
	  point.x=point.x+700/2;
	  point.y=700/2-point.y;
	  return point;
  }
  
  /*public Point view_To_screen1(Point point){
	  point.x=point.x+700/2;
	  point.y=700/2-point.y;
	return point;
  }*/
}