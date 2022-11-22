package student;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import courseSystem.Login;
import tools.ConnectionFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class StudentTable extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	public static String StuId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentTable frame = new StudentTable();
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
	public StudentTable() {
		setTitle("\u4E2A\u4EBA\u4FE1\u606F");
		StuId=Login.id;
		System.out.println(StuId);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 857, 543);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(15,15,805,457);
		getContentPane().add(this.scpDemo);		
				try {
				Connection conn=ConnectionFactory.getConnection();
				String sql="select * from Student where 学号='"+StuId+"'"; 
				PreparedStatement pstm=conn.prepareStatement(sql);
				// 执行查询学号为StuId的信息
				ResultSet rs=pstm.executeQuery();
				int count=0;
				while(rs.next()){
					count++;					
				}
				rs=pstm.executeQuery();
				Object[][] info=new Object[count][4];
				count=0;
				//将信息存入info中
				while(rs.next()){
					info[count][0]=rs.getString(1);
					info[count][1]=rs.getString(2);
					info[count][2]=rs.getString(4);
					info[count][3]=rs.getString(3);
					count++;
				}
				// 定义表头
				String[] title={"学号","姓名","性别","专业名"};
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
