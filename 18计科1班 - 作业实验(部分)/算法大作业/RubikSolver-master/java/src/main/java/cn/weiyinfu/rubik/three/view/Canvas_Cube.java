package cn.weiyinfu.rubik.three.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import cn.weiyinfu.rubik.three.view.math3D.Point3D;
import cn.weiyinfu.rubik.three.view.math3D.VTs;

public class Canvas_Cube extends JPanel {
private final int VIEWERROR = 1000;
private boolean rotating = false;

public boolean isRotating() {
    return rotating;
}

public void setRotating(boolean rotating) {
    this.rotating = rotating;
}

private Polygon backPolygon = new Polygon();

public final String BLUE = "blue";
public final String RED = "red";
public final String GREEN = "green";
public final String ORANGE = "orange";
public final String YELLOW = "yellow";
public final String WHITE = "white";
public String selected = null;

private final Color colorOrange = new Color(255, 100, 0);
private final Color colorYellow = new Color(255, 255, 100);
private Point3D a = new Point3D(-120, 120, 120);
private Point3D b = new Point3D(-120, 120, -120);
private Point3D c = new Point3D(120, 120, -120);
private Point3D d = new Point3D(120, 120, 120);
private Point3D e = new Point3D(-120, -120, 120);
private Point3D f = new Point3D(-120, -120, -120);
private Point3D g = new Point3D(120, -120, -120);
private Point3D h = new Point3D(120, -120, 120);

private Point3D ad = new Point3D(-40, 120, 120);
private Point3D da = new Point3D(40, 120, 120);
private Point3D dh = new Point3D(120, 40, 120);
private Point3D hd = new Point3D(120, -40, 120);
private Point3D he = new Point3D(40, -120, 120);
private Point3D eh = new Point3D(-40, -120, 120);
private Point3D ea = new Point3D(-120, -40, 120);
private Point3D ae = new Point3D(-120, 40, 120);

private Point3D bc = new Point3D(-40, 120, -120);
private Point3D cb = new Point3D(40, 120, -120);
private Point3D cg = new Point3D(120, 40, -120);
private Point3D gc = new Point3D(120, -40, -120);
private Point3D gf = new Point3D(40, -120, -120);
private Point3D fg = new Point3D(-40, -120, -120);
private Point3D fb = new Point3D(-120, -40, -120);
private Point3D bf = new Point3D(-120, 40, -120);

private Point3D ab = new Point3D(-120, 120, 40);
private Point3D ba = new Point3D(-120, 120, -40);
private Point3D ef = new Point3D(-120, -120, 40);
private Point3D fe = new Point3D(-120, -120, -40);

private Point3D dc = new Point3D(120, 120, 40);
private Point3D cd = new Point3D(120, 120, -40);
private Point3D hg = new Point3D(120, -120, 40);
private Point3D gh = new Point3D(120, -120, -40);

// ???????????�B??
private Point3D yellow_a = new Point3D(-40, 120, 40);
private Point3D yellow_b = new Point3D(-40, 120, -40);
private Point3D yellow_c = new Point3D(40, 120, -40);
private Point3D yellow_d = new Point3D(40, 120, 40);

private Point3D white_e = new Point3D(-40, -120, 40);
private Point3D white_f = new Point3D(-40, -120, -40);
private Point3D white_g = new Point3D(40, -120, -40);
private Point3D white_h = new Point3D(40, -120, 40);

private Point3D orange_a = new Point3D(-120, 40, 40);
private Point3D orange_b = new Point3D(-120, 40, -40);
private Point3D orange_f = new Point3D(-120, -40, -40);
private Point3D orange_e = new Point3D(-120, -40, 40);

private Point3D red_d = new Point3D(120, 40, 40);
private Point3D red_c = new Point3D(120, 40, -40);
private Point3D red_g = new Point3D(120, -40, -40);
private Point3D red_h = new Point3D(120, -40, 40);

private Point3D blue_a = new Point3D(-40, 40, 120);
private Point3D blue_d = new Point3D(40, 40, 120);
private Point3D blue_h = new Point3D(40, -40, 120);
private Point3D blue_e = new Point3D(-40, -40, 120);

private Point3D green_b = new Point3D(-40, 40, -120);
private Point3D green_c = new Point3D(40, 40, -120);
private Point3D green_g = new Point3D(40, -40, -120);
private Point3D green_f = new Point3D(-40, -40, -120);

private CornerBlock cornerBlock_a = new CornerBlock(
        new Square(a.clone(), ae.clone(), blue_a.clone(), ad.clone(), Color.blue),
        new Square(a.clone(), ad.clone(), yellow_a.clone(), ab.clone(), colorYellow),
        new Square(a.clone(), ab.clone(), orange_a.clone(), ae.clone(), colorOrange));
private CornerBlock cornerBlock_b = new CornerBlock(
        new Square(b.clone(), ba.clone(), yellow_b.clone(), bc.clone(), colorYellow),
        new Square(b.clone(), bf.clone(), orange_b.clone(), ba.clone(), colorOrange),
        new Square(b.clone(), bc.clone(), green_b.clone(), bf.clone(), Color.green));
private CornerBlock cornerBlock_c = new CornerBlock(
        new Square(c.clone(), cb.clone(), yellow_c.clone(), cd.clone(), colorYellow),
        new Square(c.clone(), cd.clone(), red_c.clone(), cg.clone(), Color.red),
        new Square(c.clone(), cg.clone(), green_c.clone(), cb.clone(), Color.green));
private CornerBlock cornerBlock_d = new CornerBlock(
        new Square(d.clone(), dc.clone(), yellow_d.clone(), da.clone(), colorYellow),
        new Square(d.clone(), dh.clone(), red_d.clone(), dc.clone(), Color.red),
        new Square(d.clone(), da.clone(), blue_d.clone(), dh.clone(), Color.blue));
private CornerBlock cornerBlock_e = new CornerBlock(
        new Square(e.clone(), ef.clone(), white_e.clone(), eh.clone(), Color.white),
        new Square(e.clone(), ea.clone(), orange_e.clone(), ef.clone(), colorOrange),
        new Square(e.clone(), eh.clone(), blue_e.clone(), ea.clone(), Color.blue));
private CornerBlock cornerBlock_f = new CornerBlock(
        new Square(f.clone(), fg.clone(), white_f.clone(), fe.clone(), Color.white),
        new Square(f.clone(), fe.clone(), orange_f.clone(), fb.clone(), colorOrange),
        new Square(f.clone(), fb.clone(), green_f.clone(), fg.clone(), Color.green));
private CornerBlock cornerBlock_g = new CornerBlock(
        new Square(g.clone(), gh.clone(), white_g.clone(), gf.clone(), Color.white),
        new Square(g.clone(), gf.clone(), green_g.clone(), gc.clone(), Color.green),
        new Square(g.clone(), gc.clone(), red_g.clone(), gh.clone(), Color.red));
private CornerBlock cornerBlock_h = new CornerBlock(
        new Square(h.clone(), he.clone(), white_h.clone(), hg.clone(), Color.white),
        new Square(h.clone(), hg.clone(), red_h.clone(), hd.clone(), Color.red),
        new Square(h.clone(), hd.clone(), blue_h.clone(), he.clone(), Color.blue));

private EdgeBlock Blue_Yellow = new EdgeBlock(
        new Square(da.clone(), ad.clone(), blue_a.clone(), blue_d.clone(), Color.blue),
        new Square(ad.clone(), da.clone(), yellow_d.clone(), yellow_a.clone(), colorYellow));
private EdgeBlock Yellow_Green = new EdgeBlock(
        new Square(cb.clone(), bc.clone(), yellow_b.clone(), yellow_c.clone(), colorYellow),
        new Square(bc.clone(), cb.clone(), green_c.clone(), green_b.clone(), Color.green));
private EdgeBlock Green_White = new EdgeBlock(
        new Square(gf.clone(), fg.clone(), green_f.clone(), green_g.clone(), Color.green),
        new Square(fg.clone(), gf.clone(), white_g.clone(), white_f.clone(), Color.white));
private EdgeBlock White_Blue = new EdgeBlock(
        new Square(he.clone(), eh.clone(), white_e.clone(), white_h.clone(), Color.white),
        new Square(eh.clone(), he.clone(), blue_h.clone(), blue_e.clone(), Color.blue));
private EdgeBlock Yellow_Orange = new EdgeBlock(
        new Square(ba.clone(), ab.clone(), yellow_a.clone(), yellow_b.clone(), colorYellow),
        new Square(ab.clone(), ba.clone(), orange_b.clone(), orange_a.clone(), colorOrange));
private EdgeBlock Yellow_Red = new EdgeBlock(
        new Square(dc.clone(), cd.clone(), yellow_c.clone(), yellow_d.clone(), colorYellow),
        new Square(cd.clone(), dc.clone(), red_d.clone(), red_c.clone(), Color.red));
private EdgeBlock Red_White = new EdgeBlock(
        new Square(hg.clone(), gh.clone(), red_g.clone(), red_h.clone(), Color.red),
        new Square(gh.clone(), hg.clone(), white_h.clone(), white_g.clone(), Color.white));
private EdgeBlock White_Orange = new EdgeBlock(
        new Square(ef.clone(), fe.clone(), white_f.clone(), white_e.clone(), Color.white),
        new Square(fe.clone(), ef.clone(), orange_e.clone(), orange_f.clone(), colorOrange));
private EdgeBlock Orange_Blue = new EdgeBlock(
        new Square(ea.clone(), ae.clone(), orange_a.clone(), orange_e.clone(), colorOrange),
        new Square(ae.clone(), ea.clone(), blue_e.clone(), blue_a.clone(), Color.blue));
private EdgeBlock Orange_Green = new EdgeBlock(
        new Square(bf.clone(), fb.clone(), orange_f.clone(), orange_b.clone(), colorOrange),
        new Square(fb.clone(), bf.clone(), green_b.clone(), green_f.clone(), Color.green));
private EdgeBlock Red_Green = new EdgeBlock(
        new Square(gc.clone(), cg.clone(), red_c.clone(), red_g.clone(), Color.red),
        new Square(cg.clone(), gc.clone(), green_g.clone(), green_c.clone(), Color.green));
private EdgeBlock Red_Blue = new EdgeBlock(
        new Square(dh.clone(), hd.clone(), red_h.clone(), red_d.clone(), Color.red),
        new Square(hd.clone(), dh.clone(), blue_d.clone(), blue_h.clone(), Color.blue));

private CenterBlock blue = new CenterBlock(
        new Square(blue_a.clone(), blue_e.clone(), blue_h.clone(), blue_d.clone(), Color.blue));
private CenterBlock red = new CenterBlock(
        new Square(red_g.clone(), red_c.clone(), red_d.clone(), red_h.clone(), Color.red));
private CenterBlock green = new CenterBlock(
        new Square(green_b.clone(), green_c.clone(), green_g.clone(), green_f.clone(), Color.green));
private CenterBlock orange = new CenterBlock(
        new Square(orange_a.clone(), orange_b.clone(), orange_f.clone(), orange_e.clone(), colorOrange));
private CenterBlock yellow = new CenterBlock(
        new Square(yellow_a.clone(), yellow_d.clone(), yellow_c.clone(), yellow_b.clone(), colorYellow));
private CenterBlock white = new CenterBlock(
        new Square(white_e.clone(), white_f.clone(), white_g.clone(), white_h.clone(), Color.white));

Point oldPoint;
double angleX, angleY;

private Image offScreenImage;

private CenterBlock[] centerBlocks = {blue, red, green, orange, yellow, white};
;
private CornerBlock[] cornerBlocks = {cornerBlock_a, cornerBlock_b, cornerBlock_c, cornerBlock_d, cornerBlock_e,
        cornerBlock_f, cornerBlock_g, cornerBlock_h};
private EdgeBlock[] edgeBlocks = {Blue_Yellow, Yellow_Green, Green_White, White_Blue, Yellow_Orange, Yellow_Red,
        Red_White, White_Orange, Orange_Blue, Orange_Green, Red_Green, Red_Blue};
// ????????????????block????,block[0]????
private Object[] block = new Object[21];
// block1????��???????
private Object[] block1 = new Object[9];
// block2????����???????
private Object[] block2 = new Object[17];

public Canvas_Cube() {
    addMouseListener(new MyMouseListner());
    addMouseMotionListener(new MyMouseMotionListner());
    setBackground(Color.cyan);

    block[1] = cornerBlock_a;
    block[2] = Blue_Yellow;
    block[3] = cornerBlock_d;
    block[4] = Orange_Blue;
    block[5] = Red_Blue;
    block[6] = cornerBlock_e;
    block[7] = White_Blue;
    block[8] = cornerBlock_h;
    block[9] = Yellow_Orange;
    block[10] = Yellow_Red;
    block[11] = White_Orange;
    block[12] = Red_White;
    block[13] = cornerBlock_b;
    block[14] = Yellow_Green;
    block[15] = cornerBlock_c;
    block[16] = Orange_Green;
    block[17] = Red_Green;
    block[18] = cornerBlock_f;
    block[19] = Green_White;
    block[20] = cornerBlock_g;
}

/**
 * ???????90??
 *
 * @param clockWise??ture?????false??????
 */
public void rotateBlue90(final Boolean clockWise) {
    new Thread(new Runnable() {
        int count = 0;

        @Override
        public void run() {
            setRotating(true);
            while (isRotating()) {
                CornerBlock tempCornerBlock = (CornerBlock) block[1];
                tempCornerBlock.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[3];
                tempCornerBlock.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[6];
                tempCornerBlock.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[8];
                tempCornerBlock.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);

                EdgeBlock tempEdgeBlock = (EdgeBlock) block[2];
                tempEdgeBlock.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[4];
                tempEdgeBlock.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[5];
                tempEdgeBlock.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[7];
                tempEdgeBlock.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);

                blue.rotate(blue.getCenterPoint(), Math.PI / 90, clockWise);
                if (blue.getSquare().getHidden() < VIEWERROR) {
                    backPolygon = getPolygon1();
                }
                update(getGraphics());
                count++;
                if (count >= 45) {
                    setRotating(false);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                ;
            }
        }
    }).start();
    if (clockWise) {
        Object temp = block[1];
        block[1] = block[6];
        block[6] = block[8];
        block[8] = block[3];
        block[3] = temp;
        temp = block[2];
        block[2] = block[4];
        block[4] = block[7];
        block[7] = block[5];
        block[5] = temp;
    } else {
        Object temp = block[1];
        block[1] = block[3];
        block[3] = block[8];
        block[8] = block[6];
        block[6] = temp;
        temp = block[2];
        block[2] = block[5];
        block[5] = block[7];
        block[7] = block[4];
        block[4] = temp;
    }
}

/**
 * ???????90??
 *
 * @param clockWise??ture?????false??????
 */
public void rotateOrange90(final Boolean clockWise) {
    new Thread(new Runnable() {
        int count = 0;

        @Override
        public void run() {
            setRotating(true);
            while (isRotating()) {
                CornerBlock tempCornerBlock = (CornerBlock) block[13];
                tempCornerBlock.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[1];
                tempCornerBlock.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[18];
                tempCornerBlock.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[6];
                tempCornerBlock.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);

                EdgeBlock tempEdgeBlock = (EdgeBlock) block[9];
                tempEdgeBlock.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[16];
                tempEdgeBlock.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[4];
                tempEdgeBlock.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[11];
                tempEdgeBlock.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);

                orange.rotate(orange.getCenterPoint(), Math.PI / 90, clockWise);
                if (orange.getSquare().getHidden() < VIEWERROR) {
                    backPolygon = getPolygon1();
                }
                update(getGraphics());
                count++;
                if (count >= 45) {
                    setRotating(false);
                }
            }
        }

    }).start();

    if (clockWise) {
        Object temp = block[13];
        block[13] = block[18];
        block[18] = block[6];
        block[6] = block[1];
        block[1] = temp;
        temp = block[9];
        block[9] = block[16];
        block[16] = block[11];
        block[11] = block[4];
        block[4] = temp;
    } else {
        Object temp = block[13];
        block[13] = block[1];
        block[1] = block[6];
        block[6] = block[18];
        block[18] = temp;
        temp = block[9];
        block[9] = block[4];
        block[4] = block[11];
        block[11] = block[16];
        block[16] = temp;
    }
}

/**
 * ???????90??
 *
 * @param clockWise??ture?????false??????
 */
public void rotateGreen90(final Boolean clockWise) {
    new Thread(new Runnable() {
        int count = 0;

        @Override
        public void run() {
            setRotating(true);
            while (isRotating()) {
                CornerBlock tempCornerBlock = (CornerBlock) block[15];
                tempCornerBlock.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[13];
                tempCornerBlock.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[20];
                tempCornerBlock.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[18];
                tempCornerBlock.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);

                EdgeBlock tempEdgeBlock = (EdgeBlock) block[14];
                tempEdgeBlock.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[17];
                tempEdgeBlock.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[16];
                tempEdgeBlock.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[19];
                tempEdgeBlock.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);

                green.rotate(green.getCenterPoint(), Math.PI / 90, clockWise);
                if (green.getSquare().getHidden() < VIEWERROR) {
                    backPolygon = getPolygon1();
                }
                update(getGraphics());
                count++;
                if (count >= 45) {
                    setRotating(false);
                }
            }
        }
    }).start();
    if (clockWise) {
        Object temp = block[15];
        block[15] = block[20];
        block[20] = block[18];
        block[18] = block[13];
        block[13] = temp;
        temp = block[14];
        block[14] = block[17];
        block[17] = block[19];
        block[19] = block[16];
        block[16] = temp;
    } else {
        Object temp = block[15];
        block[15] = block[13];
        block[13] = block[18];
        block[18] = block[20];
        block[20] = temp;
        temp = block[14];
        block[14] = block[16];
        block[16] = block[19];
        block[19] = block[17];
        block[17] = temp;
    }
}

/**
 * ???????90??
 *
 * @param clockWise??ture?????false??????
 */
public void rotateRed90(final Boolean clockWise) {
    new Thread(new Runnable() {
        int count = 0;

        @Override
        public void run() {
            setRotating(true);
            while (isRotating()) {
                CornerBlock tempCornerBlock = (CornerBlock) block[3];
                tempCornerBlock.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[15];
                tempCornerBlock.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[8];
                tempCornerBlock.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[20];
                tempCornerBlock.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);

                EdgeBlock tempEdgeBlock = (EdgeBlock) block[10];
                tempEdgeBlock.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[5];
                tempEdgeBlock.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[17];
                tempEdgeBlock.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[12];
                tempEdgeBlock.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);

                red.rotate(red.getCenterPoint(), Math.PI / 90, clockWise);
                if (red.getSquare().getHidden() < VIEWERROR) {
                    backPolygon = getPolygon1();
                }
                update(getGraphics());
                count++;
                if (count >= 45) {
                    setRotating(false);
                }
            }
        }
    }).start();
    if (clockWise) {
        Object temp = block[3];
        block[3] = block[8];
        block[8] = block[20];
        block[20] = block[15];
        block[15] = temp;
        temp = block[10];
        block[10] = block[5];
        block[5] = block[12];
        block[12] = block[17];
        block[17] = temp;
    } else {
        Object temp = block[3];
        block[3] = block[15];
        block[15] = block[20];
        block[20] = block[8];
        block[8] = temp;
        temp = block[10];
        block[10] = block[17];
        block[17] = block[12];
        block[12] = block[5];
        block[5] = temp;
    }
}

/**
 * ???????90??
 *
 * @param clockWise??ture?????false??????
 */
public void rotateYellow90(final Boolean clockWise) {
    new Thread(new Runnable() {
        int count = 0;

        @Override
        public void run() {
            setRotating(true);
            while (isRotating()) {
                CornerBlock tempCornerBlock = (CornerBlock) block[13];
                tempCornerBlock.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[15];
                tempCornerBlock.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[1];
                tempCornerBlock.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[3];
                tempCornerBlock.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);

                EdgeBlock tempEdgeBlock = (EdgeBlock) block[14];
                tempEdgeBlock.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[9];
                tempEdgeBlock.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[10];
                tempEdgeBlock.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[2];
                tempEdgeBlock.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);

                yellow.rotate(yellow.getCenterPoint(), Math.PI / 90, clockWise);
                if (yellow.getSquare().getHidden() < VIEWERROR) {
                    backPolygon = getPolygon1();
                }
                update(getGraphics());
                count++;
                if (count >= 45) {
                    setRotating(false);
                }
            }
        }
    }).start();
    if (clockWise) {
        Object temp = block[13];
        block[13] = block[1];
        block[1] = block[3];
        block[3] = block[15];
        block[15] = temp;
        temp = block[14];
        block[14] = block[9];
        block[9] = block[2];
        block[2] = block[10];
        block[10] = temp;
    } else {
        Object temp = block[13];
        block[13] = block[15];
        block[15] = block[3];
        block[3] = block[1];
        block[1] = temp;
        temp = block[14];
        block[14] = block[10];
        block[10] = block[2];
        block[2] = block[9];
        block[9] = temp;
    }
}

/**
 * ???????90??
 *
 * @param clockWise??ture?????false??????
 */
public void rotateWhite90(final Boolean clockWise) {
    new Thread(new Runnable() {
        int count = 0;

        @Override
        public void run() {
            setRotating(true);
            while (isRotating()) {
                CornerBlock tempCornerBlock = (CornerBlock) block[6];
                tempCornerBlock.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[8];
                tempCornerBlock.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[18];
                tempCornerBlock.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);
                tempCornerBlock = (CornerBlock) block[20];
                tempCornerBlock.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);

                EdgeBlock tempEdgeBlock = (EdgeBlock) block[7];
                tempEdgeBlock.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[11];
                tempEdgeBlock.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[12];
                tempEdgeBlock.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);
                tempEdgeBlock = (EdgeBlock) block[19];
                tempEdgeBlock.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);

                white.rotate(white.getCenterPoint(), Math.PI / 90, clockWise);
                if (white.getSquare().getHidden() < VIEWERROR) {
                    backPolygon = getPolygon1();
                }
                update(getGraphics());
                count++;
                if (count >= 45) {
                    setRotating(false);
                }
            }
        }
    }).start();
    if (clockWise) {
        Object temp = block[6];
        block[6] = block[18];
        block[18] = block[20];
        block[20] = block[8];
        block[8] = temp;
        temp = block[7];
        block[7] = block[11];
        block[11] = block[19];
        block[19] = block[12];
        block[12] = temp;
    } else {
        Object temp = block[6];
        block[6] = block[8];
        block[8] = block[20];
        block[20] = block[18];
        block[18] = temp;
        temp = block[7];
        block[7] = block[12];
        block[12] = block[19];
        block[19] = block[11];
        block[11] = temp;
    }
}

public Polygon getPolygon() {
    if (selected == BLUE) {
        return getBluePolygon();
    } else if (selected == RED) {
        return getRedPolygon();
    } else if (selected == GREEN) {
        return getGreenPolygon();
    } else if (selected == ORANGE) {
        return getOrangePolygon();
    } else if (selected == YELLOW) {
        return getYellowPolygon();
    } else if (selected == WHITE) {
        return getWhitePolygon();
    } else {
        return null;
    }
}

/**
 * ??????????
 *
 * @return
 */
public Polygon getBluePolygon() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock1 = (CornerBlock) block[1];
    Point point1 = cornerBlock1.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point1);
    CornerBlock cornerBlock3 = (CornerBlock) block[3];
    Point point3 = cornerBlock3.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point3);
    CornerBlock cornerBlock6 = (CornerBlock) block[6];
    Point point6 = cornerBlock6.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point6);
    CornerBlock cornerBlock8 = (CornerBlock) block[8];
    Point point8 = cornerBlock8.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point8);
    polygon.addPoint(point1.x, point1.y);
    polygon.addPoint(point3.x, point3.y);
    polygon.addPoint(point8.x, point8.y);
    polygon.addPoint(point6.x, point6.y);
    return polygon;
}

/**
 * ??��??????
 *
 * @return
 */
public Polygon getOrangePolygon() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock13 = (CornerBlock) block[13];
    Point point13 = cornerBlock13.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point13);
    CornerBlock cornerBlock1 = (CornerBlock) block[1];
    Point point1 = cornerBlock1.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point1);
    CornerBlock cornerBlock6 = (CornerBlock) block[6];
    Point point6 = cornerBlock6.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point6);
    CornerBlock cornerBlock18 = (CornerBlock) block[18];
    Point point18 = cornerBlock18.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point18);
    polygon.addPoint(point13.x, point13.y);
    polygon.addPoint(point1.x, point1.y);
    polygon.addPoint(point6.x, point6.y);
    polygon.addPoint(point18.x, point18.y);
    return polygon;
}

/**
 * ??????????
 *
 * @return
 */
public Polygon getGreenPolygon() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock15 = (CornerBlock) block[15];
    Point point15 = cornerBlock15.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point15);
    CornerBlock cornerBlock13 = (CornerBlock) block[13];
    Point point13 = cornerBlock13.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point13);
    CornerBlock cornerBlock18 = (CornerBlock) block[18];
    Point point18 = cornerBlock18.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point18);
    CornerBlock cornerBlock20 = (CornerBlock) block[20];
    Point point20 = cornerBlock20.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point20);
    polygon.addPoint(point15.x, point15.y);
    polygon.addPoint(point13.x, point13.y);
    polygon.addPoint(point18.x, point18.y);
    polygon.addPoint(point20.x, point20.y);
    return polygon;
}

/**
 * ??��??????
 *
 * @return
 */
public Polygon getRedPolygon() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock3 = (CornerBlock) block[3];
    Point point3 = cornerBlock3.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point3);
    CornerBlock cornerBlock15 = (CornerBlock) block[15];
    Point point15 = cornerBlock15.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point15);
    CornerBlock cornerBlock20 = (CornerBlock) block[20];
    Point point20 = cornerBlock20.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point20);
    CornerBlock cornerBlock8 = (CornerBlock) block[8];
    Point point8 = cornerBlock8.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point8);
    polygon.addPoint(point3.x, point3.y);
    polygon.addPoint(point15.x, point15.y);
    polygon.addPoint(point20.x, point20.y);
    polygon.addPoint(point8.x, point8.y);
    return polygon;
}

/**
 * ?????????
 *
 * @return
 */
public Polygon getYellowPolygon() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock13 = (CornerBlock) block[13];
    Point point13 = cornerBlock13.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point13);
    CornerBlock cornerBlock15 = (CornerBlock) block[15];
    Point point15 = cornerBlock15.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point15);
    CornerBlock cornerBlock3 = (CornerBlock) block[3];
    Point point3 = cornerBlock3.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point3);
    CornerBlock cornerBlock1 = (CornerBlock) block[1];
    Point point1 = cornerBlock1.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point1);
    polygon.addPoint(point13.x, point13.y);
    polygon.addPoint(point15.x, point15.y);
    polygon.addPoint(point3.x, point3.y);
    polygon.addPoint(point1.x, point1.y);
    return polygon;
}

/**
 * ?????????
 *
 * @return
 */
public Polygon getWhitePolygon() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock6 = (CornerBlock) block[6];
    Point point6 = cornerBlock6.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point6);
    CornerBlock cornerBlock8 = (CornerBlock) block[8];
    Point point8 = cornerBlock8.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point8);
    CornerBlock cornerBlock20 = (CornerBlock) block[20];
    Point point20 = cornerBlock20.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point20);
    CornerBlock cornerBlock18 = (CornerBlock) block[18];
    Point point18 = cornerBlock18.getCornerPoint().getPoint2D();
    VTs.view_To_screen(point18);
    polygon.addPoint(point6.x, point6.y);
    polygon.addPoint(point8.x, point8.y);
    polygon.addPoint(point20.x, point20.y);
    polygon.addPoint(point18.x, point18.y);
    return polygon;
}

/**
 * ????????????
 *
 * @return
 */
public Polygon getBluePolygon1() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock1 = (CornerBlock) block[1];
    Point3D point1 = cornerBlock1.getBackPoint(blue.getCenterPoint());
    VTs.view_To_screen(point1.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point1.getPoint2D()).x, VTs.view_To_screen(point1.getPoint2D()).y);

    CornerBlock cornerBlock3 = (CornerBlock) block[3];
    Point3D point3 = cornerBlock3.getBackPoint(blue.getCenterPoint());
    VTs.view_To_screen(point3.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point3.getPoint2D()).x, VTs.view_To_screen(point3.getPoint2D()).y);

    CornerBlock cornerBlock8 = (CornerBlock) block[8];
    Point3D point8 = cornerBlock8.getBackPoint(blue.getCenterPoint());
    VTs.view_To_screen(point8.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point8.getPoint2D()).x, VTs.view_To_screen(point8.getPoint2D()).y);

    CornerBlock cornerBlock6 = (CornerBlock) block[6];
    Point3D point6 = cornerBlock6.getBackPoint(blue.getCenterPoint());
    VTs.view_To_screen(point6.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point6.getPoint2D()).x, VTs.view_To_screen(point6.getPoint2D()).y);

    return polygon;
}

/**
 * ??��????????
 *
 * @return
 */
public Polygon getOrangePolygon1() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock13 = (CornerBlock) block[13];
    Point3D point13 = cornerBlock13.getBackPoint(orange.getCenterPoint());
    VTs.view_To_screen(point13.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point13.getPoint2D()).x, VTs.view_To_screen(point13.getPoint2D()).y);

    CornerBlock cornerBlock1 = (CornerBlock) block[1];
    Point3D point1 = cornerBlock1.getBackPoint(orange.getCenterPoint());
    VTs.view_To_screen(point1.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point1.getPoint2D()).x, VTs.view_To_screen(point1.getPoint2D()).y);

    CornerBlock cornerBlock6 = (CornerBlock) block[6];
    Point3D point6 = cornerBlock6.getBackPoint(orange.getCenterPoint());
    VTs.view_To_screen(point6.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point6.getPoint2D()).x, VTs.view_To_screen(point6.getPoint2D()).y);

    CornerBlock cornerBlock18 = (CornerBlock) block[18];
    Point3D point18 = cornerBlock18.getBackPoint(orange.getCenterPoint());
    VTs.view_To_screen(point18.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point18.getPoint2D()).x, VTs.view_To_screen(point18.getPoint2D()).y);

    return polygon;
}

/**
 * ????????????
 *
 * @return
 */
public Polygon getGreenPolygon1() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock15 = (CornerBlock) block[15];
    Point3D point15 = cornerBlock15.getBackPoint(green.getCenterPoint());
    VTs.view_To_screen(point15.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point15.getPoint2D()).x, VTs.view_To_screen(point15.getPoint2D()).y);

    CornerBlock cornerBlock13 = (CornerBlock) block[13];
    Point3D point13 = cornerBlock13.getBackPoint(green.getCenterPoint());
    VTs.view_To_screen(point13.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point13.getPoint2D()).x, VTs.view_To_screen(point13.getPoint2D()).y);

    CornerBlock cornerBlock18 = (CornerBlock) block[18];
    Point3D point18 = cornerBlock18.getBackPoint(green.getCenterPoint());
    VTs.view_To_screen(point18.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point18.getPoint2D()).x, VTs.view_To_screen(point18.getPoint2D()).y);

    CornerBlock cornerBlock20 = (CornerBlock) block[20];
    Point3D point20 = cornerBlock20.getBackPoint(green.getCenterPoint());
    VTs.view_To_screen(point20.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point20.getPoint2D()).x, VTs.view_To_screen(point20.getPoint2D()).y);

    return polygon;
}

/**
 * ??��????????
 *
 * @return
 */
public Polygon getRedPolygon1() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock3 = (CornerBlock) block[3];
    Point3D point3 = cornerBlock3.getBackPoint(red.getCenterPoint());
    VTs.view_To_screen(point3.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point3.getPoint2D()).x, VTs.view_To_screen(point3.getPoint2D()).y);

    CornerBlock cornerBlock15 = (CornerBlock) block[15];
    Point3D point15 = cornerBlock15.getBackPoint(red.getCenterPoint());
    VTs.view_To_screen(point15.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point15.getPoint2D()).x, VTs.view_To_screen(point15.getPoint2D()).y);

    CornerBlock cornerBlock20 = (CornerBlock) block[20];
    Point3D point20 = cornerBlock20.getBackPoint(red.getCenterPoint());
    VTs.view_To_screen(point20.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point20.getPoint2D()).x, VTs.view_To_screen(point20.getPoint2D()).y);

    CornerBlock cornerBlock8 = (CornerBlock) block[8];
    Point3D point8 = cornerBlock8.getBackPoint(red.getCenterPoint());
    VTs.view_To_screen(point8.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point8.getPoint2D()).x, VTs.view_To_screen(point8.getPoint2D()).y);

    return polygon;
}

/**
 * ???????????
 *
 * @return
 */
public Polygon getYellowPolygon1() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock13 = (CornerBlock) block[13];
    Point3D point13 = cornerBlock13.getBackPoint(yellow.getCenterPoint());
    VTs.view_To_screen(point13.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point13.getPoint2D()).x, VTs.view_To_screen(point13.getPoint2D()).y);

    CornerBlock cornerBlock15 = (CornerBlock) block[15];
    Point3D point15 = cornerBlock15.getBackPoint(yellow.getCenterPoint());
    VTs.view_To_screen(point15.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point15.getPoint2D()).x, VTs.view_To_screen(point15.getPoint2D()).y);

    CornerBlock cornerBlock3 = (CornerBlock) block[3];
    Point3D point3 = cornerBlock3.getBackPoint(yellow.getCenterPoint());
    VTs.view_To_screen(point3.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point3.getPoint2D()).x, VTs.view_To_screen(point3.getPoint2D()).y);

    CornerBlock cornerBlock1 = (CornerBlock) block[1];
    Point3D point1 = cornerBlock1.getBackPoint(yellow.getCenterPoint());
    VTs.view_To_screen(point1.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point1.getPoint2D()).x, VTs.view_To_screen(point1.getPoint2D()).y);

    return polygon;
}

/**
 * ???????????
 *
 * @return
 */
public Polygon getWhitePolygon1() {
    Polygon polygon = new Polygon();
    CornerBlock cornerBlock6 = (CornerBlock) block[6];
    Point3D point6 = cornerBlock6.getBackPoint(white.getCenterPoint());
    VTs.view_To_screen(point6.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point6.getPoint2D()).x, VTs.view_To_screen(point6.getPoint2D()).y);

    CornerBlock cornerBlock8 = (CornerBlock) block[8];
    Point3D point8 = cornerBlock8.getBackPoint(white.getCenterPoint());
    VTs.view_To_screen(point8.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point8.getPoint2D()).x, VTs.view_To_screen(point8.getPoint2D()).y);

    CornerBlock cornerBlock20 = (CornerBlock) block[20];
    Point3D point20 = cornerBlock20.getBackPoint(white.getCenterPoint());
    VTs.view_To_screen(point20.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point20.getPoint2D()).x, VTs.view_To_screen(point20.getPoint2D()).y);

    CornerBlock cornerBlock18 = (CornerBlock) block[18];
    Point3D point18 = cornerBlock18.getBackPoint(white.getCenterPoint());
    VTs.view_To_screen(point18.getPoint2D());
    polygon.addPoint(VTs.view_To_screen(point18.getPoint2D()).x, VTs.view_To_screen(point18.getPoint2D()).y);

    return polygon;
}

/**
 * ?????????????????��????????????
 *
 * @return
 */
public Polygon getPolygon1() {
    Polygon polygon = new Polygon();
    if (selected == BLUE) {
        polygon = getBluePolygon1();
    } else if (selected == ORANGE) {
        polygon = getOrangePolygon1();
    } else if (selected == GREEN) {
        polygon = getGreenPolygon1();
    } else if (selected == RED) {
        polygon = getRedPolygon1();
    } else if (selected == YELLOW) {
        polygon = getYellowPolygon1();
    } else if (selected == WHITE) {
        polygon = getWhitePolygon1();
    } else {
        polygon = backPolygon;
    }
    return polygon;
}

BufferedImage buf;

@Override
protected void paintComponent(Graphics g) {
    if (buf == null || buf.getWidth() != getWidth() || buf.getHeight() != getHeight()) {
        buf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
    }
    Graphics2D graphics2D = buf.createGraphics();
    graphics2D.clearRect(0, 0, buf.getWidth(), buf.getHeight());
    graphics2D.setColor(Color.gray);
    if (backPolygon != null) {
        graphics2D.fillPolygon(backPolygon);
    }
    drawCube(graphics2D);
    graphics2D.setColor(Color.magenta);
    if (getPolygon() != null) {
        graphics2D.drawPolygon(getPolygon());
    }
    g.drawImage(buf, 0, 0, null);
}

private void drawCube(Graphics2D graphics2D) {
    getBlocks();
    if (selected == BLUE) {
        if (blue.getSquare().getHidden() > VIEWERROR) {
            drawBlocks(graphics2D, block2);
            drawBlocks(graphics2D, block1);
        } else {
            drawBlocks(graphics2D, block1);
            drawBlocks(graphics2D, block2);
        }
    }

    if (selected == ORANGE) {
        if (orange.getSquare().getHidden() > VIEWERROR) {
            drawBlocks(graphics2D, block2);
            drawBlocks(graphics2D, block1);
        } else {
            drawBlocks(graphics2D, block1);
            drawBlocks(graphics2D, block2);
        }
    }

    if (selected == GREEN) {
        if (green.getSquare().getHidden() > VIEWERROR) {
            drawBlocks(graphics2D, block2);
            drawBlocks(graphics2D, block1);
        } else {
            drawBlocks(graphics2D, block1);
            drawBlocks(graphics2D, block2);
        }
    }

    if (selected == RED) {
        if (red.getSquare().getHidden() > VIEWERROR) {
            drawBlocks(graphics2D, block2);
            drawBlocks(graphics2D, block1);
        } else {
            drawBlocks(graphics2D, block1);
            drawBlocks(graphics2D, block2);
        }
    }

    if (selected == YELLOW) {
        if (yellow.getSquare().getHidden() > VIEWERROR) {
            drawBlocks(graphics2D, block2);
            drawBlocks(graphics2D, block1);
        } else {
            drawBlocks(graphics2D, block1);
            drawBlocks(graphics2D, block2);
        }
    }

    if (selected == WHITE) {
        if (white.getSquare().getHidden() > VIEWERROR) {
            drawBlocks(graphics2D, block2);
            drawBlocks(graphics2D, block1);
        } else {
            drawBlocks(graphics2D, block1);
            drawBlocks(graphics2D, block2);
        }
    }

    if (selected == null) {
        for (int i = 0; i < centerBlocks.length; i++) {
            centerBlocks[i].draw(graphics2D);
        }
        for (int i = 0; i < edgeBlocks.length; i++) {
            edgeBlocks[i].draw(graphics2D);
        }
        for (int i = 0; i < cornerBlocks.length; i++) {
            cornerBlocks[i].draw(graphics2D);
        }
    }
}

private void getBlocks() {
    if (selected == BLUE) {
        block1[0] = block[1];
        block1[1] = block[2];
        block1[2] = block[3];
        block1[3] = block[4];
        block1[4] = block[5];
        block1[5] = block[6];
        block1[6] = block[7];
        block1[7] = block[8];
        block1[8] = blue;
        block2[0] = block[9];
        block2[1] = block[10];
        block2[2] = block[11];
        block2[3] = block[12];
        block2[4] = block[13];
        block2[5] = block[14];
        block2[6] = block[15];
        block2[7] = block[16];
        block2[8] = block[17];
        block2[9] = block[18];
        block2[10] = block[19];
        block2[11] = block[20];
        block2[12] = orange;
        block2[13] = green;
        block2[14] = red;
        block2[15] = yellow;
        block2[16] = white;
    } else if (selected == ORANGE) {
        block1[0] = block[13];
        block1[1] = block[9];
        block1[2] = block[1];
        block1[3] = block[4];
        block1[4] = block[6];
        block1[5] = block[11];
        block1[6] = block[18];
        block1[7] = block[16];
        block1[8] = orange;
        block2[0] = block[2];
        block2[1] = block[3];
        block2[2] = block[5];
        block2[3] = block[7];
        block2[4] = block[8];
        block2[5] = block[10];
        block2[6] = block[12];
        block2[7] = block[14];
        block2[8] = block[15];
        block2[9] = block[17];
        block2[10] = block[19];
        block2[11] = block[20];
        block2[12] = blue;
        block2[13] = green;
        block2[14] = red;
        block2[15] = yellow;
        block2[16] = white;
    } else if (selected == GREEN) {
        block1[0] = block[15];
        block1[1] = block[14];
        block1[2] = block[13];
        block1[3] = block[17];
        block1[4] = block[16];
        block1[5] = block[20];
        block1[6] = block[19];
        block1[7] = block[18];
        block1[8] = green;
        block2[0] = block[1];
        block2[1] = block[2];
        block2[2] = block[3];
        block2[3] = block[4];
        block2[4] = block[5];
        block2[5] = block[6];
        block2[6] = block[7];
        block2[7] = block[8];
        block2[8] = block[9];
        block2[9] = block[10];
        block2[10] = block[11];
        block2[11] = block[12];
        block2[12] = blue;
        block2[13] = orange;
        block2[14] = red;
        block2[15] = yellow;
        block2[16] = white;
    } else if (selected == RED) {
        block1[0] = block[3];
        block1[1] = block[10];
        block1[2] = block[15];
        block1[3] = block[5];
        block1[4] = block[17];
        block1[5] = block[8];
        block1[6] = block[12];
        block1[7] = block[20];
        block1[8] = red;
        block2[0] = block[1];
        block2[1] = block[2];
        block2[2] = block[4];
        block2[3] = block[6];
        block2[4] = block[7];
        block2[5] = block[9];
        block2[6] = block[11];
        block2[7] = block[13];
        block2[8] = block[14];
        block2[9] = block[16];
        block2[10] = block[18];
        block2[11] = block[19];
        block2[12] = blue;
        block2[13] = orange;
        block2[14] = green;
        block2[15] = yellow;
        block2[16] = white;
    } else if (selected == YELLOW) {
        block1[0] = block[13];
        block1[1] = block[14];
        block1[2] = block[15];
        block1[3] = block[9];
        block1[4] = block[10];
        block1[5] = block[1];
        block1[6] = block[2];
        block1[7] = block[3];
        block1[8] = yellow;
        block2[0] = block[4];
        block2[1] = block[5];
        block2[2] = block[6];
        block2[3] = block[7];
        block2[4] = block[8];
        block2[5] = block[11];
        block2[6] = block[12];
        block2[7] = block[16];
        block2[8] = block[17];
        block2[9] = block[18];
        block2[10] = block[19];
        block2[11] = block[20];
        block2[12] = blue;
        block2[13] = orange;
        block2[14] = green;
        block2[15] = red;
        block2[16] = white;
    } else if (selected == WHITE) {
        block1[0] = block[6];
        block1[1] = block[7];
        block1[2] = block[8];
        block1[3] = block[11];
        block1[4] = block[12];
        block1[5] = block[18];
        block1[6] = block[19];
        block1[7] = block[20];
        block1[8] = white;
        block2[0] = block[1];
        block2[1] = block[2];
        block2[2] = block[3];
        block2[3] = block[4];
        block2[4] = block[5];
        block2[5] = block[16];
        block2[6] = block[9];
        block2[7] = block[10];
        block2[8] = block[13];
        block2[9] = block[14];
        block2[10] = block[15];
        block2[11] = block[17];
        block2[12] = blue;
        block2[13] = orange;
        block2[14] = green;
        block2[15] = red;
        block2[16] = yellow;
    }
}

private void drawBlocks(Graphics2D graphics2D, Object[] objects) {
    for (int i = 0; i < objects.length; i++) {
        if (objects[i].getClass().equals(CornerBlock.class)) {
            CornerBlock temp = (CornerBlock) objects[i];
            temp.draw(graphics2D);
        } else if ((objects[i].getClass().equals(EdgeBlock.class))) {
            EdgeBlock temp = (EdgeBlock) objects[i];
            temp.draw(graphics2D);
        } else {
            CenterBlock temp = (CenterBlock) objects[i];
            temp.draw(graphics2D);
        }
    }
}

public void drawCenterBlock(Graphics2D graphics2D) {
    blue.draw(graphics2D);
    orange.draw(graphics2D);
    green.draw(graphics2D);
    yellow.draw(graphics2D);
    red.draw(graphics2D);
    white.draw(graphics2D);
}

@Override
public void update(Graphics g) {
    super.update(g);

    offScreenImage = this.createImage(800, 800);
    Graphics gImage = offScreenImage.getGraphics();
    paint(gImage);
    g.drawImage(offScreenImage, 0, 0, null);
    gImage.dispose();
}

@Override
public void paint(Graphics g) {
    super.paint(g);
}

private class MyMouseListner extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        oldPoint = e.getPoint();
        if (getBluePolygon().contains(e.getPoint()) && blue.getSquare().getHidden() > VIEWERROR && !isRotating()) {
            selected = BLUE;
        } else if (getRedPolygon().contains(e.getPoint()) && red.getSquare().getHidden() > VIEWERROR
                && !isRotating()) {
            selected = RED;
        } else if (getGreenPolygon().contains(e.getPoint()) && green.getSquare().getHidden() > VIEWERROR
                && !isRotating()) {
            selected = GREEN;
        } else if (getOrangePolygon().contains(e.getPoint()) && orange.getSquare().getHidden() > VIEWERROR
                && !isRotating()) {
            selected = ORANGE;
        } else if (getYellowPolygon().contains(e.getPoint()) && yellow.getSquare().getHidden() > VIEWERROR
                && !isRotating()) {
            selected = YELLOW;
        } else if (getWhitePolygon().contains(e.getPoint()) && white.getSquare().getHidden() > VIEWERROR
                && !isRotating()) {
            selected = WHITE;
        } else {
            selected = null;
        }
        backPolygon = getPolygon1();
        update(getGraphics());
    }
}

private class MyMouseMotionListner extends MouseMotionAdapter {
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        angleX = (e.getY() - oldPoint.y) * Math.PI / 200;
        angleY = (e.getX() - oldPoint.x) * Math.PI / 200;
        if (!isRotating()) {
            for (int i = 0; i < centerBlocks.length; i++) {
                centerBlocks[i].rotateXY(angleX, angleY);
            }
            for (int i = 0; i < cornerBlocks.length; i++) {
                cornerBlocks[i].rotateXY(angleX, angleY);
            }
            for (int i = 0; i < edgeBlocks.length; i++) {
                edgeBlocks[i].rotateXY(angleX, angleY);
            }
        }
        backPolygon = getPolygon1();
        oldPoint = e.getPoint();
        update(getGraphics());
    }
}

}
