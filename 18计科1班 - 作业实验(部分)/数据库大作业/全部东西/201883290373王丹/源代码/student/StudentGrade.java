package student;

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

public class StudentGrade extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGrade frame = new StudentGrade();
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
	public StudentGrade() {
		setTitle("\u8BFE\u7A0B\u6210\u7EE9");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 939, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(0,0,728,299);
		getContentPane().add(this.scpDemo);
		try{
			 //获得连接
			System.out.println("1");
			Connection conn=ConnectionFactory.getConnection();
			System.out.println("1");
			String sql="select * from Grade_count"; 
			PreparedStatement pstm=conn.prepareStatement(sql);
			 //执行查询
			
			ResultSet rs=pstm.executeQuery();
			System.out.println("1");
			 //计算有多少条记录
			int count=0;
			while(rs.next()){				
				count++;
			}
			System.out.println(count);
			rs=pstm.executeQuery();
			 //将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info=new Object[count][7];
			count = 0;
			while(rs.next()){
				info[count][0]=rs.getString(1);
				info[count][1]=rs.getString(2);
				System.out.println(info[count][0]);
				info[count][2]=Integer.toString(rs.getInt(3));
				System.out.println(info[count][1]);
				info[count][3]=Integer.toString(rs.getInt(4));
				info[count][4]=Integer.toString(rs.getInt(5));
				info[count][5]=rs.getFloat(6);
				System.out.println(info[count][4]);
				info[count][6]=Integer.toString(rs.getInt(7));
				count++;
			}		
			// 定义表头
			String[] title={"课程号","课程名","选课人数","最高成绩","最低成绩","平均成绩","及格人数"};
			// 创建JTable
			this.tabDemo = new JTable(info,title);
			// 显示表头
			this.jth = this.tabDemo.getTableHeader();
			this.scpDemo.setViewportView(tabDemo);
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
			}
	}
}

