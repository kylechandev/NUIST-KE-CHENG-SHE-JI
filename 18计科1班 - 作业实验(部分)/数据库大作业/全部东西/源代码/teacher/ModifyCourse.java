package teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import tools.ConnectionFactory;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class ModifyCourse extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	private Window frame;
	public static String ModCourseNum1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyCourse frame = new ModifyCourse();
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
	public ModifyCourse() {
		ReturnModifyCourseNum returnModifyCourseNum=new ReturnModifyCourseNum();
		ModCourseNum1=returnModifyCourseNum.ModCourseNum;
		setTitle("\u4FEE\u6539\u8BFE\u7A0B");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 865, 622);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(15,65,813,332);
		getContentPane().add(this.scpDemo);
	
		//提交
		JButton btnNewButton = new JButton("\u786E\u5B9A\u4FEE\u6539");
		btnNewButton.setFont(new Font("FZYaoTi", Font.PLAIN, 18));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					String kcm_label=textField.getText();
					int xf_label=Integer.parseInt(textField_1.getText());
					int ks_label=Integer.parseInt(textField_2.getText());
					int kkxq_label=Integer.parseInt(textField_3.getText());
					String kcjj_label=textField_4.getText();
					Connection connection=ConnectionFactory.getConnection();
				    Statement statement=connection.createStatement();
				    String sql="UPDATE Course SET 课程名='"+kcm_label+"', 学分='"+xf_label+"',课时='"+ks_label+"',开课学期='"+kkxq_label+"',课程简介='"+kcjj_label+"' WHERE 课程号='"+ModCourseNum1+"';";
					statement.executeUpdate(sql);
					JOptionPane.showMessageDialog(frame,"修改成功！");
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnNewButton.setBounds(680, 480, 136, 55);
		contentPane.add(btnNewButton);
		
		//显示全部记录按钮
		JButton btnNewButton_1 = new JButton("\u5168\u90E8\u8BFE\u7A0B");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_ActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(329, 15, 143, 35);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		lblNewLabel.setBounds(15, 429, 81, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u5206\uFF1A");
		lblNewLabel_1.setBounds(253, 429, 81, 21);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(335, 418, 117, 35);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u8BFE\u65F6\uFF1A");
		lblNewLabel_1_1.setBounds(485, 429, 81, 21);
		contentPane.add(lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(540, 415, 96, 35);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("\u5F00\u8BFE\u5B66\u671F\uFF1A");
		lblNewLabel_2.setBounds(15, 480, 106, 39);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(111, 418, 109, 35);
		contentPane.add(textField);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(114, 480, 106, 35);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u8BFE\u7A0B\u7B80\u4ECB\uFF1A");
		lblNewLabel_2_1.setBounds(249, 480, 96, 39);
		contentPane.add(lblNewLabel_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(338, 480, 114, 35);
		contentPane.add(textField_4);
		//
		this.setVisible(true);
		
	}
	public void btnNewButton_ActionPerformed(ActionEvent ae){
		// 以下是连接数据源和显示数据的具体处理方法，请注意下
		try{
			// 获得连接
			Connection conn=ConnectionFactory.getConnection();
			String sql="select * from Course"; 
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
			Object[][] info=new Object[count][7];
			count = 0;
			while(rs.next()){
				info[count][0]=rs.getString(1);
				info[count][1]=rs.getString(2);
				info[count][2]=Integer.toString(rs.getInt(3));
				info[count][3]=Integer.toString(rs.getInt(4));
				info[count][4]=Integer.toString(rs.getInt(5));System.out.println(info[count][4]);
				info[count][5]=rs.getString(6);
				info[count][6]=rs.getString(7);
				count++;
			}
			// 定义表头
			String[] title={"课程号","课程名","学分","课时","开课学期","课程简介","是否通过审批"};
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
