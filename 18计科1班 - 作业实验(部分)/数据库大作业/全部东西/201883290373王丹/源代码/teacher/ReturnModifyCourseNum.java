package teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tools.ConnectionFactory;
import java.awt.Font;
import java.awt.Color;

public class ReturnModifyCourseNum extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public static String ModCourseNum;
	private Window frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnModifyCourseNum frame = new ReturnModifyCourseNum();
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
	public ReturnModifyCourseNum() {
		setTitle("\u8BFE\u7A0B\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 631, 436);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 245, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u8981\u8FDB\u884C\u4FEE\u6539\u7684\u8BFE\u7A0B\u53F7\uFF1A");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("DengXian", Font.PLAIN, 26));
		lblNewLabel.setBounds(15, 36, 358, 51);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(26, 102, 250, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//监听提交窗口
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					
					ModCourseNum=textField.getText();
					Connection connection=ConnectionFactory.getConnection();
			        Statement statement=connection.createStatement();
			        ResultSet resultSet=statement.executeQuery("SELECT * FROM Course WHERE 课程号='"+ModCourseNum+"'");
			        if(resultSet.next()){
			        	ModifyCourse modifyCourse=new ModifyCourse();
			        	setVisible(false);
			        	modifyCourse.open();
			        }
			        else{
			        	JOptionPane.showMessageDialog(frame,"课程"+ModCourseNum+"不存在,请重新输入,错误!");
			        }	        
				}catch(Exception ee){			
				}
			}
		});
		btnNewButton.setBounds(407, 272, 142, 58);
		contentPane.add(btnNewButton);
	}
	public String open(){
		this.setVisible(true);
		return ModCourseNum;
	}

}
