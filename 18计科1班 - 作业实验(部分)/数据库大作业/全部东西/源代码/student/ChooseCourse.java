package student;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import courseSystem.Login;
import tools.ConnectionFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class ChooseCourse extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JTextField textField;
	private JButton btnNewButton_1;
	private Window frame;
	public static String StuId_Choose;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseCourse frame = new ChooseCourse();
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
	public ChooseCourse() {
		setTitle("\u9009\u8BFE");
		StuId_Choose=Login.id;
		System.out.println(StuId_Choose);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 906, 634);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(15,60,854,449);
		getContentPane().add(this.scpDemo);
		
		JLabel lblNewLabel = new JLabel("请输入要选择的课程号：");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 20));
		lblNewLabel.setBounds(42, 535, 227, 21);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(279, 532, 141, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//提交按钮
		JButton btnNewButton = new JButton("\u786E\u5B9A\u9009\u8BFE");
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 20));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String kch=textField.getText();
				try{
					Connection connection=ConnectionFactory.getConnection();
				    Statement statement=connection.createStatement();
				    Statement statement1=connection.createStatement();
				    String sql="INSERT INTO Stu_Course(学号,课程号) VALUES('"+StuId_Choose+"','"+kch+"')";
				    String sql2="select * from Course where 课程号='"+kch+"'";
				    PreparedStatement pstm=connection.prepareStatement(sql2);
					ResultSet rs = pstm.executeQuery();
					rs=pstm.executeQuery();
					System.out.println(sql2);
					String CName =null;
					while(rs.next()){					
						CName=rs.getString(2);//将课程号存放在cno数组内
					}					
					System.out.println(CName);
				    String sql1="INSERT INTO Grade_count(课程号,课程名) VALUES('"+kch+"','"+CName+"')";
				    System.out.println(sql1);
				    statement.executeUpdate(sql);
					statement1.executeUpdate(sql1);
					JOptionPane.showMessageDialog(frame,"添加成功!");
				}catch(Exception ee)
				{ 
					ee.printStackTrace();
				}
				
			}
		});
		
		btnNewButton.setBounds(661, 524, 152, 35);
		contentPane.add(btnNewButton);
		
		//显示所有的课程记录
		btnNewButton_1 = new JButton("显示所有课程");
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 21));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_ActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(347, 8, 188, 37);
		contentPane.add(btnNewButton_1);
	}
	public void btnNewButton_ActionPerformed(ActionEvent ae){
		// 以下是连接数据源和显示数据的具体处理方法，请注意下
		try{
			// 获得连接
			Connection conn=ConnectionFactory.getConnection();
			String sql="select * from Course where 是否通过审批='True'"; 
			PreparedStatement pstm=conn.prepareStatement(sql);
			// 执行查询
			ResultSet rs=pstm.executeQuery();
			// 计算有多少条记录
			int count=0;
			while(rs.next()){
				count++;
			}
			rs=pstm.executeQuery();
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info=new Object[count][6];
			count = 0;
			while(rs.next()){
				info[count][0]=rs.getString(1);
				info[count][1]=rs.getString(2);
				info[count][2]=Integer.toString(rs.getInt(3));
				info[count][3]=Integer.toString(rs.getInt(4));
				info[count][4]=Integer.toString(rs.getInt(5));System.out.println(info[count][4]);
				info[count][5]=rs.getString(6);
				count++;
			}
			// 定义表头
			String[] title={"课程号","课程名","学分","课时","开课学期","课程简介"};
			// 创建JTable
			this.tabDemo = new JTable(info,title);
			// 显示表头
			this.jth = this.tabDemo.getTableHeader();
			this.scpDemo.setViewportView(tabDemo);
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	

	public void open() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

}
