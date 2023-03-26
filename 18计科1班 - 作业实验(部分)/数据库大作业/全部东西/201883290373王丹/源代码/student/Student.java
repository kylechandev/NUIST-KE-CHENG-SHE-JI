package student;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import courseSystem.Login;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Student extends JFrame {

	private JPanel contentPane;
	public static String StuId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
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
	public Student() {
		System.out.println("shi");
		StuId=Login.id;
		System.out.println(StuId);
		setTitle("\u5B66\u751F\u9875\u9762");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 479);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("选择课程");
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 21));
		btnNewButton.setForeground(new Color(0, 0, 139));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChooseCourse chooseCourse=new ChooseCourse();
				chooseCourse.open();
			}
		});
		btnNewButton.setBounds(144, 101, 137, 82);
		contentPane.add(btnNewButton);
		
		//课程表
		JButton btnNewButton_1 = new JButton("课表查看");
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 21));
		btnNewButton_1.setForeground(new Color(0, 0, 139));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CourseTable courseTable=new CourseTable();
				courseTable.open();
				
			}
		});
		btnNewButton_1.setBounds(363, 101, 137, 82);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("成绩查询");
		btnNewButton_1_1.setFont(new Font("SimSun", Font.PLAIN, 21));
		btnNewButton_1_1.setForeground(new Color(0, 0, 139));
		btnNewButton_1_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GradeTable gradeTable=new GradeTable();
				gradeTable.open();
			}
		});
		btnNewButton_1_1.setBounds(144, 236, 137, 82);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("个人信息");
		btnNewButton_2.setFont(new Font("SimSun", Font.PLAIN, 21));
		btnNewButton_2.setForeground(new Color(0, 0, 139));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentTable studentTable=new StudentTable();
				studentTable.open();
			}
		});
		btnNewButton_2.setBounds(363, 236, 137, 82);
		contentPane.add(btnNewButton_2);
	}
	
	public void open(){
		this.setVisible(true);
		
	}
}
