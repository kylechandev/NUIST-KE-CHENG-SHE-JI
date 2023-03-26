package cn.weiyinfu.rubik.three.view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	private Canvas_Cube canvas_Cube = new Canvas_Cube();

	public static void main(String args[]) {
		new MainFrame();
	}

	public MainFrame() {
		setSize(700, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		addKeyListener(myKeyListenner);
		JLabel label = new JLabel("press up/down/left/right to turn the face");
		add(label, BorderLayout.NORTH);
		add(canvas_Cube);
	}

	KeyListener myKeyListenner = new KeyAdapter() {
		public void keyPressed(java.awt.event.KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT && !canvas_Cube.isRotating()) {
				if (canvas_Cube.selected == canvas_Cube.BLUE) {
					canvas_Cube.rotateBlue90(false);
				} else if (canvas_Cube.selected == canvas_Cube.ORANGE) {
					canvas_Cube.rotateOrange90(false);
				} else if (canvas_Cube.selected == canvas_Cube.GREEN) {
					canvas_Cube.rotateGreen90(false);
				} else if (canvas_Cube.selected == canvas_Cube.RED) {
					canvas_Cube.rotateRed90(false);
				} else if (canvas_Cube.selected == canvas_Cube.YELLOW) {
					canvas_Cube.rotateYellow90(false);
				} else if (canvas_Cube.selected == canvas_Cube.WHITE) {
					canvas_Cube.rotateWhite90(false);
				}
				canvas_Cube.repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !canvas_Cube.isRotating()) {
				if (canvas_Cube.selected == canvas_Cube.BLUE) {
					canvas_Cube.rotateBlue90(true);
				} else if (canvas_Cube.selected == canvas_Cube.ORANGE) {
					canvas_Cube.rotateOrange90(true);
				} else if (canvas_Cube.selected == canvas_Cube.GREEN) {
					canvas_Cube.rotateGreen90(true);
				} else if (canvas_Cube.selected == canvas_Cube.RED) {
					canvas_Cube.rotateRed90(true);
				} else if (canvas_Cube.selected == canvas_Cube.YELLOW) {
					canvas_Cube.rotateYellow90(true);
				} else if (canvas_Cube.selected == canvas_Cube.WHITE) {
					canvas_Cube.rotateWhite90(true);
				}
				canvas_Cube.repaint();
			}
		}
	};
}
