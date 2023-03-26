package administrator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import tools.ConnectionFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;

public class ApproveCourse extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JTextField textField;
	private Window frame;
	static boolean flag=true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApproveCourse frame = new ApproveCourse();
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
	public ApproveCourse() {
		setTitle("\u8BFE\u7A0B\u5BA1\u6279");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 935, 639);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.scpDemo=new JScrollPane();
		this.scpDemo.setBounds(15,54,883,346);
		getContentPane().add(this.scpDemo);
		
		//显示模块
		JButton btnNewButton = new JButton("\u663E\u793A\u5168\u90E8\u5F85\u5BA1\u6279\u7684\u8BFE\u7A0B");
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_ActionPerformed(e);
			}
		});
		btnNewButton.setBounds(346, 15, 240, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\u5BA1\u6279\u8BFE\u7A0B\uFF08\u8F93\u5165\u8BFE\u7A0B\u53F7\uFF0C\u9009\u62E9\u662F\u5426\u901A\u8FC7\uFF09\uFF1A");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(25, 402, 349, 21);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		label.setFont(new Font("SimSun", Font.PLAIN, 21));
		label.setForeground(Color.WHITE);
		label.setBounds(141, 451, 91, 35);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(287, 455, 153, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u662F\u5426\u901A\u8FC7\u7533\u8BF7\uFF1A");
		label_1.setFont(new Font("SimSun", Font.PLAIN, 21));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(97, 507, 170, 35);
		contentPane.add(label_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u901A\u8FC7");
		chckbxNewCheckBox.setFont(new Font("SimSun", Font.PLAIN, 20));
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag=true;
			}
		});
		chckbxNewCheckBox.setBounds(287, 510, 149, 29);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u4E0D\u901A\u8FC7");
		chckbxNewCheckBox_1.setFont(new Font("SimSun", Font.PLAIN, 20));
		chckbxNewCheckBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag=false;
			}
		});
		chckbxNewCheckBox_1.setBounds(468, 510, 149, 29);
		contentPane.add(chckbxNewCheckBox_1);
		
		//提交申请
		JButton btnNewButton_1 = new JButton(" \u63D0\u4EA4");
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 21));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String kch=textField.getText();
				System.out.println(kch);
				try{
					Connection conn=ConnectionFactory.getConnection();
					String sql="UPDATE Course SET 是否通过审批='"+flag+"' WHERE 课程号='"+kch+"'"; 
					PreparedStatement pstm=conn.prepareStatement(sql);
					//执行查询
					pstm.executeUpdate();
					System.out.println(1);
					JOptionPane.showMessageDialog(frame,"操作成功!");
					
				}catch(SQLException sqle){
					
				}
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_1.setBounds(740, 466, 91, 54);
		contentPane.add(btnNewButton_1);
	}

	public void btnNewButton_ActionPerformed(ActionEvent ae){
		//以下是连接数据源和显示数据的具体处理方法，请注意下
		try{
			//获得连接
			Connection conn=ConnectionFactory.getConnection();
			String sql="SELECT * FROM Course WHERE 是否通过审批='0'"; 
			PreparedStatement pstm=conn.prepareStatement(sql);
			//执行查询
			ResultSet rs=pstm.executeQuery();
			//计算有多少条记录
			int count=0;
			while(rs.next()){
				count++;
			}
			rs=pstm.executeQuery();
			//将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info=new Object[count][7];
			count=0;
			while(rs.next()){
				info[count][0]=rs.getString(1);
				info[count][1]=rs.getString(2);
				info[count][2]=Integer.toString(rs.getInt(3));
				info[count][3]=Integer.toString(rs.getInt(4));
				info[count][4]=Integer.toString(rs.getInt(5));
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
