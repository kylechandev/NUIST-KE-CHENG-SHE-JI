package administrator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
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
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class GradeResult extends JFrame {

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
					GradeResult frame = new GradeResult();
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
	public GradeResult() {
		setTitle("\u6210\u7EE9\u5355");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 912, 601);
		contentPane=new JPanel();
		contentPane.setBackground(UIManager.getColor("MenuBar.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.scpDemo=new JScrollPane();
		this.scpDemo.setBounds(15,15,860,459);
		getContentPane().add(this.scpDemo);
		JButton btnNewButton = new JButton("\u663E\u793A\u6240\u6709\u6210\u7EE9");
		btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_ActionPerformed(e);
			}
		});
		btnNewButton.setBounds(331, 489, 209, 41);
		contentPane.add(btnNewButton);
		
	}
	
	public void btnNewButton_ActionPerformed(ActionEvent ae){
		//��������������Դ����ʾ���ݵľ��崦��������ע����
		try{
			//�������
			Connection conn=ConnectionFactory.getConnection();
			String sql="select * from Stu_Course"; 
			PreparedStatement pstm=conn.prepareStatement(sql);
			//ִ�в�ѯ
			ResultSet rs=pstm.executeQuery();
			//�����ж�������¼
			int count=0;
			while(rs.next()){
				count++;
			}
			rs=pstm.executeQuery();
			//����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			Object[][] info=new Object[count][7];
			count=0;
			while(rs.next()){
				info[count][1]=rs.getString(1);//ѧ��
				info[count][2]=rs.getString(2);
				info[count][3]=Integer.toString(rs.getInt(3));
				//����ѧ����ʾѧ��������
				System.out.println(info[count][1]);
				String sql1="SELECT * FROM Student WHERE ѧ��='"+info[count][1]+"';";			
				PreparedStatement pstm1=conn.prepareStatement(sql1);
				//ִ�в�ѯ
				ResultSet rs1=pstm1.executeQuery();
				if(rs1.next()){
					System.out.println(info[count][1]);
					System.out.println(rs1.getString(2));
					info[count][0]=rs1.getString(2);
				}
				count++;
			}
			// �����ͷ
			String[] title={"ѧ������","ѧ��","�γ�����","�ɼ�"};
			// ����JTable
			this.tabDemo = new JTable(info,title);
			// ��ʾ��ͷ
			this.jth = this.tabDemo.getTableHeader();
			this.scpDemo.setViewportView(tabDemo);
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
			}
		}
	public void open(){
		this.setVisible(true);
	}
}
