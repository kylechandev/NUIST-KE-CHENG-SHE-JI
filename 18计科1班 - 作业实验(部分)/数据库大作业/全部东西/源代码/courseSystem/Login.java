package courseSystem;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import administrator.Administrator;
import student.Student;
import teacher.Teacher;
import tools.ConnectionFactory;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class Login extends JFrame {
	public static String id;
	private char[] password;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	static int flag1=0;
	
	ImageIcon background;
	ImageIcon imageIcon;
	ImageIcon imageIcon1;
	JPanel myPanel;
	JLabel label;
	JButton button;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Window frame;
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		getContentPane().setForeground(SystemColor.desktop);
		setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().setFont(new Font("Arial", Font.BOLD, 18));
		setBackground(new Color(0, 0, 139));
		getContentPane().setBackground(new Color(119, 136, 153));
		setTitle("MyMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(580, 220, 800, 590);
		this.setResizable(false); 
		contentPane = new JPanel();				
		Container contentPane=getContentPane();
		getContentPane().setLayout(null);
		
		//用户名
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(224, 189, 105, 40);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		//输入 
		textField = new JTextField();
		textField.setFont(new Font("楷体", Font.PLAIN, 23));
		textField.setToolTipText("");
		textField.setColumns(10);
		textField.setBorder(null);
		
		textField.setForeground(UIManager.getColor("FormattedTextField.foreground"));
		textField.setBackground(new Color(255, 255, 240));
		textField.setBounds(344, 195, 172, 30);
		contentPane.add(textField);
		id=textField.getText();
		
		//密码框
		JPasswordField textField_1=new JPasswordField(15);
		textField_1.setEchoChar('*');
		textField_1.setBackground(SystemColor.controlShadow);
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("楷体", Font.PLAIN, 23));
		textField_1.setBounds(344, 244, 172, 30);
		textField_1.setColumns(10);
		textField_1.setBorder(null);;
		contentPane.add(textField_1);
		password=textField_1.getPassword();
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setForeground(new Color(255, 255, 255));
		label.setBackground(new Color(255, 255, 255));
		label.setBounds(236, 243, 89, 24);
		label.setFont(new Font("楷体", Font.BOLD, 26));
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");//登录按钮
		btnNewButton.setForeground(SystemColor.controlText);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(483, 395, 132, 52);
		//对登录按钮添加监听事件
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=textField.getText().trim();
				String password=textField_1.getText().trim();
				if(name.equals("")||password.equals(""))//如果没输用户名或密码,则提示对不起,请输入用户名或密码
				{
					JOptionPane.showMessageDialog(frame,"对不起,请输入用户名或密码");
				}
				else//如果都有数据了就开始连接数据库验证
				{
					try
					{
						boolean isMatch=false;
						String password2=null;
						String name_label=(String)textField.getText();
						String password_lable=textField_1.getText();
						//数据库连接，执行sql语句
				        Connection connection=ConnectionFactory.getConnection();
				        System.out.println("ssss.");
				        Statement statement=connection.createStatement();
				        ResultSet resultSet=statement.executeQuery("SELECT * FROM Login WHERE 用户名='"+name_label+"';");
				        if(resultSet.next())
						{
							String name1=(String)resultSet.getString("用户名");
							String sql = "SELECT 密码  FROM Login WHERE 用户名='"+name_label+"';";
							ResultSet set = statement.executeQuery(sql);
							if(set.next()){
				                password2 = set.getString("密码").trim();
				                System.out.println(password2);
				                System.out.println(name_label);
				            }
							System.out.println(name1);
							if(name1.equals(name_label))
							{
								System.out.println(password_lable);
								if(password2.equals(password_lable))
								{
									if(Login.flag1==2 && name1.substring(0, 1).equals("2")){
										//name1为工号
										id=name1;
										Student student1= new Student();
										student1.open();
										setVisible(false);
										System.out.println("111");
									}
									else if(Login.flag1==1 && name1.substring(0, 1).equals("1")){
										//name1为工号
										id=name1;
										Teacher teacher= new Teacher();
										teacher.open();
										setVisible(false);
									}
									
									
									else if(Login.flag1==3 && name1.substring(0, 1).equals("0")){
										id=name;
										Administrator administrator=new Administrator();
										administrator.open();		//System.out.print("1111")	;				
									}else {
										JOptionPane.showMessageDialog(frame,"未选择身份或身份勾选错误!");
									}
								}else
									{
										JOptionPane.showMessageDialog(frame,"对不起,密码错误,请重新输入,登陆失败");
									}									
							}
						}
						else//如果没有查找到用户名就提示
						{
							JOptionPane.showMessageDialog(frame,"用户名不存在,请重新输入,错误!");
						}
					}
					catch(Exception ee)
					{
						
					}
				}				
			}
		});
		
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		JRadioButton radioButton = new JRadioButton("\u6559\u5E08");
		radioButton.setForeground(Color.WHITE);
		radioButton.setBounds(352, 318, 112, 52);
		radioButton.setOpaque(false);//设置透明
		buttonGroup.add(radioButton);
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				flag1=1;
			}
		});
		
		radioButton.setFont(new Font("FZCuHeiSongS-B-GB", Font.PLAIN, 20));
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u5B66\u751F");
		radioButton_1.setBackground(new Color(230, 230, 250));
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setBounds(236, 318, 112, 52);
		radioButton_1.setOpaque(false);//设置透明
		buttonGroup.add(radioButton_1);
		radioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				flag1=2;
			}
		});
		
		radioButton_1.setFont(new Font("FZCuHeiSongS-B-GB", Font.PLAIN, 20));
		contentPane.add(radioButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 794, 10);
		getContentPane().add(panel);
		
		JRadioButton radioButton_2 = new JRadioButton("\u7BA1\u7406\u5458");
		radioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag1=3;
			}
		});
		radioButton_2.setOpaque(false);
		radioButton_2.setForeground(Color.WHITE);
		radioButton_2.setFont(new Font("FZCuHeiSongS-B-GB", Font.PLAIN, 20));
		radioButton_2.setBounds(456, 318, 112, 52);
		getContentPane().add(radioButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u751F\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("FZYaoTi", Font.BOLD, 42));
		lblNewLabel_1.setBounds(236, 71, 379, 64);
		getContentPane().add(lblNewLabel_1);
	}
	
	public void showMe(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame=new Login();
					frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

