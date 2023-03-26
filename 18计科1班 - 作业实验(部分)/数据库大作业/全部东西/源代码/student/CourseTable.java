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

public class CourseTable extends JFrame {

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
					CourseTable frame = new CourseTable();
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
	public CourseTable() {
		setTitle("\u8BFE\u8868\u4FE1\u606F");
		StuId=Login.id;
		System.out.println(StuId);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(15,15,760,467);
		getContentPane().add(this.scpDemo);		
		        //�Ȱ�ѧ�Ų�ѯ�������еĿγ̣��ٰ��κŲ�ѯ�γ���Ϣ
				try {
				Connection conn=ConnectionFactory.getConnection();
				String sql="select * from Stu_Course where ѧ��='"+StuId+"'"; 
				PreparedStatement pstm=conn.prepareStatement(sql);
				// ִ�в�ѯѧ��ΪStuId�Ŀγ�
				ResultSet rs=pstm.executeQuery();
				rs=pstm.executeQuery();
				String[] cno = new String[100];
				// ����ѧ��ΪStuId�ж������γ̼�¼
				int count=0;
				int j=0;
				while(rs.next()){
					count++;					
					cno[j++]=rs.getString(2);//���γ̴����cno������
				}			
				//��ֵi������ѭ�� 
				int i=count;
				int Ccount=count;
				Object[][] info1=new Object[Ccount][4];
				Ccount=0;
				while((i--)>0) {//ѭ������ѯcno������ÿ���γ̺ŵ���Ϣ
				String sql1="select * from Course where �γ̺�='"+cno[i]+"'";
				PreparedStatement pst=conn.prepareStatement(sql1);
				ResultSet rs1=pst.executeQuery();
				//����Ϣ����info1��
				while(rs1.next()){
					info1[Ccount][0]=rs1.getString(2);
					info1[Ccount][1]=Integer.toString(rs1.getInt(3));
					info1[Ccount][2]=Integer.toString(rs1.getInt(4));
					info1[Ccount][3]=Integer.toString(rs1.getInt(5));
					Ccount++;
				}}
				//ѭ������
				// �����ͷ
				String[] title={"�γ���","ѧ��","��ʱ","����ѧ��"};
				// ����JTable
				this.tabDemo = new JTable(info1,title);
				// ��ʾ��ͷ
				this.jth = this.tabDemo.getTableHeader();
				this.scpDemo.setViewportView(tabDemo);
				}catch(SQLException sqle){
					JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
				}
				
			}

	public void open() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
}
