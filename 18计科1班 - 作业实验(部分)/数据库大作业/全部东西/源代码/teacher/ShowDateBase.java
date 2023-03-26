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
		// �������
		private JScrollPane scpDemo;
		private JTableHeader jth;
		private JTable tabDemo;
		private JButton btnShow;
		// ���췽��
		public ShowDateBase(){
			setTitle("DATA");
			// �����������ԵĶ���
			//super("JTable���ݰ�ʾ��");
			this.setSize(832,557);
			getContentPane().setLayout(null);
			this.setLocation(100,50);
			
			
			// �������
			this.scpDemo = new JScrollPane();
			this.scpDemo.setBounds(10,68,785,418);
			getContentPane().add(this.scpDemo);
			
			this.btnShow = new JButton("\u6570\u636E\u663E\u793A");
			btnShow.setFont(new Font("SimSun", Font.PLAIN, 20));
			btnShow.setForeground(new Color(0, 0, 128));
			btnShow.setBackground(new Color(255, 255, 255));
			this.btnShow.setBounds(248,14,300,39);
			// ����ťע�����
			this.btnShow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			btnShow_ActionPerformed(ae);
			}
			});
			// ��������뵽������
			;
			getContentPane().add(this.btnShow);
			// ��ʾ����
			this.setVisible(true);
		}
		// �����ťʱ���¼�����
		public void btnShow_ActionPerformed(ActionEvent ae){
		// ��������������Դ����ʾ���ݵľ��崦��������ע����
		try{
			// �������
			Connection conn=ConnectionFactory.getConnection();
			
			String sql = "select * from Login"; 
			PreparedStatement pstm = conn.prepareStatement(sql);
			// ִ�в�ѯ
			ResultSet rs = pstm.executeQuery();
			// �����ж�������¼
			int count = 0;
			while(rs.next()){
				count++;
			}
			rs = pstm.executeQuery();
			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			Object[][] info = new Object[count][4];
			count = 0;
			while(rs.next()){
				info[count][0] = Integer.valueOf( rs.getInt(1));
				info[count][1] = rs.getString("����");
				count++;
			}
			// �����ͷ
			String[] title = {"�û���","����"};
			// ����JTable
			this.tabDemo = new JTable(info,title);
			// ��ʾ��ͷ
			this.jth = this.tabDemo.getTableHeader();
			this.scpDemo.setViewportView(tabDemo);
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public static void main(String[] args){
		new ShowDateBase();
		}
	}
