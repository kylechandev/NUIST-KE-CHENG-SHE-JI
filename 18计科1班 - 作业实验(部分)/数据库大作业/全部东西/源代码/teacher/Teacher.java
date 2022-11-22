package teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class Teacher extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teacher frame = new Teacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Teacher() {
		setBackground(new Color(0, 0, 0));
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("\u6559\u5E08\u9875\u9762");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 764, 559);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//增加课程
		JButton btnNewButton = new JButton("\u589E\u52A0\u8BFE\u7A0B");
		btnNewButton.setFont(new Font("FZYaoTi", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddCourse addCourse=new AddCourse();
				addCourse.open();
			}
		});
		btnNewButton.setBounds(137, 121, 154, 77);
		contentPane.add(btnNewButton);
		
		//删除课程
		JButton btnNewButton_1 = new JButton("\u5220\u9664\u8BFE\u7A0B");
		btnNewButton_1.setFont(new Font("FZYaoTi", Font.PLAIN, 18));
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteCourse deleteCourse=new DeleteCourse();
				deleteCourse.open();
			}
		});
		btnNewButton_1.setBounds(404, 121, 154, 77);
		contentPane.add(btnNewButton_1);
		
		//修改课程
		JButton btnNewButton_2 = new JButton("\u4FEE\u6539\u8BFE\u7A0B");
		btnNewButton_2.setFont(new Font("FZYaoTi", Font.PLAIN, 18));
		btnNewButton_2.setForeground(new Color(0, 0, 128));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnModifyCourseNum returnModifyCourseNum=new ReturnModifyCourseNum();
				returnModifyCourseNum.open();
			}
		});
		btnNewButton_2.setBounds(137, 275, 154, 69);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u5F55\u5165\u6210\u7EE9");
		btnNewButton_3.setFont(new Font("FZYaoTi", Font.PLAIN, 18));
		btnNewButton_3.setForeground(new Color(0, 0, 128));
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterFinalGrade enterFinalGrade=new EnterFinalGrade();
				enterFinalGrade.open();
			}
		});
		btnNewButton_3.setBounds(404, 275, 154, 68);
		contentPane.add(btnNewButton_3);
	}
	public void open(){
		this.setVisible(true);
	}
}
