	package teacher;
	
	import javax.swing.*;
	import javax.swing.table.JTableHeader;

import tools.ConnectionFactory;

import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.*;
import java.awt.Color;
import java.awt.Font;
	
	public class ShowDateBase extends JFrame{
		// 定义组件
		private JScrollPane scpDemo;
		private JTableHeader jth;
		private JTable tabDemo;
		private JButton btnShow;
		// 构造方法
		public ShowDateBase(){
			setTitle("DATA");
			// 窗体的相关属性的定义
			//super("JTable数据绑定示例");
			this.setSize(832,557);
			getContentPane().setLayout(null);
			this.setLocation(100,50);
			
			
			// 创建组件
			this.scpDemo = new JScrollPane();
			this.scpDemo.setBounds(10,68,785,418);
			getContentPane().add(this.scpDemo);
			
			this.btnShow = new JButton("\u6570\u636E\u663E\u793A");
			btnShow.setFont(new Font("SimSun", Font.PLAIN, 20));
			btnShow.setForeground(new Color(0, 0, 128));
			btnShow.setBackground(new Color(255, 255, 255));
			this.btnShow.setBounds(248,14,300,39);
			// 给按钮注册监听
			this.btnShow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			btnShow_ActionPerformed(ae);
			}
			});
			// 将组件加入到窗体中
			;
			getContentPane().add(this.btnShow);
			// 显示窗体
			this.setVisible(true);
		}
		// 点击按钮时的事件处理
		public void btnShow_ActionPerformed(ActionEvent ae){
		// 以下是连接数据源和显示数据的具体处理方法，请注意下
		try{
			// 获得连接
			Connection conn=ConnectionFactory.getConnection();
			
			String sql = "select * from Login"; 
			PreparedStatement pstm = conn.prepareStatement(sql);
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			// 计算有多少条记录
			int count = 0;
			while(rs.next()){
				count++;
			}
			rs = pstm.executeQuery();
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info = new Object[count][4];
			count = 0;
			while(rs.next()){
				info[count][0] = Integer.valueOf( rs.getInt(1));
				info[count][1] = rs.getString("密码");
				count++;
			}
			// 定义表头
			String[] title = {"用户名","密码"};
			// 创建JTable
			this.tabDemo = new JTable(info,title);
			// 显示表头
			this.jth = this.tabDemo.getTableHeader();
			this.scpDemo.setViewportView(tabDemo);
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public static void main(String[] args){
		new ShowDateBase();
		}
	}
