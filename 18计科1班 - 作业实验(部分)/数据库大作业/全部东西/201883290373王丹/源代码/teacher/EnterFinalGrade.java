package teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import tools.ConnectionFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnterFinalGrade extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Window frame;
	private JButton btnNewButton_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterFinalGrade frame = new EnterFinalGrade();
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
	public EnterFinalGrade() {
		setTitle("\u6210\u7EE9\u5F55\u5165");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 732, 573);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(15,15,680,265);
		getContentPane().add(this.scpDemo);
		
		lblNewLabel = new JLabel("\u5B66   \u53F7\uFF1A");
		lblNewLabel.setFont(new Font("等线", Font.PLAIN, 20));
		lblNewLabel.setBounds(216, 322, 107, 21);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\u8BFE \u7A0B \u53F7\uFF1A");
		lblNewLabel_1.setFont(new Font("等线", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(216, 357, 100, 21);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(351, 317, 143, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(351, 353, 143, 27);
		contentPane.add(textField_1);
		
		lblNewLabel_2 = new JLabel("\u671F\u672B\u6210\u7EE9\uFF1A");
		lblNewLabel_2.setFont(new Font("等线", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(216, 396, 107, 21);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(351, 395, 143, 27);
		contentPane.add(textField_2);
		
		btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("等线", Font.PLAIN, 18));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String xh=textField.getText();
				String kch=textField_1.getText();
				String cj1=textField_2.getText();
				int cj=Integer.parseInt(cj1);
				try{
					Connection conn=ConnectionFactory.getConnection();
					String sql1="update Stu_Course SET 成绩='"+cj+"' WHERE 学号='"+xh+"' AND 课程号='"+kch+"'"; 
					System.out.println(sql1);
					PreparedStatement pst=null;
		            pst=conn.prepareStatement(sql1);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(frame,"提交成功!");
				}catch(SQLException sqle){
					JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(351, 437, 82, 45);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u786E\u5B9A\u66F4\u65B0");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("等线", Font.PLAIN, 18));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showTable();
				JOptionPane.showMessageDialog(frame,"数据更新成功!");
			}
		});
		btnNewButton_1.setBounds(141, 438, 125, 44);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("\u5237\u65B0\u6210\u7EE9");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTable1();
			}
		});
		btnNewButton_2.setBounds(522, 437, 112, 45);
		contentPane.add(btnNewButton_2);
		showTable();	
	}
	public void showTable(){
		try{
			// 获得连接
			Connection conn=ConnectionFactory.getConnection();
			String sql="SELECT * FROM Stu_Course"; 
			PreparedStatement pstm=conn.prepareStatement(sql);
			// 执行查询
			ResultSet rs=pstm.executeQuery();
			// 计算有多少条记录
			int count=0;
			while(rs.next()){
				count++;
			}
			System.out.println(count);
			rs=pstm.executeQuery();
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info=new Object[count][6];
			count = 0;
			while(rs.next()){
				info[count][0]=rs.getString(1); //学号 0
				System.out.println(info[count][0]);
				String sql1="SELECT * FROM Student WHERE 学号='"+info[count][0]+"';";	
				PreparedStatement pstm1=conn.prepareStatement(sql1);
				ResultSet rs1=pstm1.executeQuery();
				if(rs1.next()){
					info[count][1]=rs1.getString(2);//学生姓名 
					info[count][2]=rs1.getString(3);//专业名	
				}
				info[count][3]=rs.getString(2);//课程号
				System.out.println(info[count][3]);
				String sql2="SELECT * FROM Course WHERE 课程号='"+info[count][3]+"';";	
				PreparedStatement pstm2=conn.prepareStatement(sql2);
				ResultSet rs2=pstm2.executeQuery();
				if(rs2.next()){
					info[count][4]=rs2.getString(2);//课
				}
				info[count][5]=rs.getString(3);
				count++;
			}
			// 定义表头
			String[] title={"学号","学生姓名","专业名","课程号","课程名称","成绩"};
			// 创建JTable
			this.tabDemo = new JTable(info,title);
			// 显示表头
			this.jth = this.tabDemo.getTableHeader();
			this.scpDemo.setViewportView(tabDemo);
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
			}
		
	}
	public void showTable1(){
		try{
			// 获得连接
			Connection conn=ConnectionFactory.getConnection();
			String sql1="SELECT * FROM Stu_Course"; 
			PreparedStatement pstm1=conn.prepareStatement(sql1);
			// 执行查询
			ResultSet rs1=pstm1.executeQuery();
			// 计算有多少条记录
			int count=0;
			while(rs1.next()){
				count++;
			}
			System.out.println(count);
			rs1=pstm1.executeQuery();
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info1=new Object[count][6];
			count = 0;
			while(rs1.next()){
				info1[count][0]=rs1.getString(1); //学号 0
				System.out.println(info1[count][0]);
				String sql11="SELECT * FROM Student WHERE 学号='"+info1[count][0]+"';";	
				PreparedStatement pstm11=conn.prepareStatement(sql11);
				ResultSet rs11=pstm11.executeQuery();
				if(rs11.next()){
					info1[count][1]=rs11.getString(2);//学生姓名 
					info1[count][2]=rs11.getString(3);//专业名	
				}
				info1[count][3]=rs1.getString(2);//课程号
				System.out.println(info1[count][3]);
				String sql2="SELECT * FROM Course WHERE 课程号='"+info1[count][3]+"';";	
				PreparedStatement pstm2=conn.prepareStatement(sql2);
				ResultSet rs2=pstm2.executeQuery();
				if(rs2.next()){
					info1[count][4]=rs2.getString(2);//课
				}
				info1[count][5]=rs1.getString(3);
				count++;
			}
			// 定义表头
			String[] title={"学号","学生姓名","专业名","课程号","课程名称","成绩"};
			// 创建JTable
			this.tabDemo = new JTable(info1,title);
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
