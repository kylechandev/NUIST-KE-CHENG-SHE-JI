package student;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import java.awt.event.ActionEvent;

public class GradeTable extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	public static String StuId;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grade frame = new Grade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public GradeTable() {
		setTitle("\u6210\u7EE9\u67E5\u8BE2");
		StuId=Login.id;
		System.out.println(StuId);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 618, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(70,10,481,251);
		getContentPane().add(this.scpDemo);
			JButton btnNewButton = new JButton("\u67E5\u770B\u6392\u540D");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButton_ActionPerformed(e);
				}
			});
			btnNewButton.setBounds(398, 295, 107, 35);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("\u8BFE\u7A0B\u6210\u7EE9");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButton_1_ActionPerformed(e);
				}
			});
			btnNewButton_1.setBounds(123, 295, 107, 35);
			contentPane.add(btnNewButton_1);
	}
	public void btnNewButton_ActionPerformed(ActionEvent e) {
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
				cno[j++]=rs.getString(2);//���γ̺Ŵ����cno������
			}
			System.out.println(cno[0]);
			//��ֵi������ѭ�� 
			int i=count;
			int m=0;
			int Ccount=10,Ccount1=10;
			Object[][] info1=new Object[Ccount][3];
			Object[][] info2=new Object[Ccount1][4];
			int[] rank=new int[10];
			Ccount=0;
			Ccount1=0;
			while((i--)>0) {//ѭ������ѯcno������ÿ���γ̺ŵ���Ϣ
			String sql1="select * FROM Stu_Course where �γ̺�='"+cno[i]+"' ORDER BY �ɼ� DESC";
			Statement pst=conn.createStatement();								
			ResultSet rs1=pst.executeQuery(sql1);
			Ccount=0;
			while(rs1.next()){
				info1[Ccount][0]=rs1.getString(1);
				System.out.println(info1[Ccount][0]);
				info1[Ccount][1]=rs1.getString(2);				
				info1[Ccount][2]=Integer.toString(rs1.getInt(3));
				Ccount++;
			}
			for(int n=0;n<Ccount;n++) {
				//System.out.println(info1[n][0]);
				if(info1[n][0].equals(StuId)) {
					rank[m++]=n+1;
					System.out.println(n+1);
				}
			}
			}
			String sql2="select * from Stu_Course where ѧ��='"+StuId+"'"; 
			Statement pst2=conn.createStatement();	
			ResultSet rs2=pst2.executeQuery(sql2);			
			while(rs2.next()) {
				info2[Ccount1][0]=rs2.getString(1);
				info2[Ccount1][1]=rs2.getString(2);				
				info2[Ccount1][2]=Integer.toString(rs2.getInt(3));
				info2[Ccount1][3]=rank[--m];
				Ccount1++;
			}
			System.out.println(Ccount1+"ewdw");
			String[] title={"ѧ��","�γ̺�","�ɼ�","����"};
			// ����JTable
			this.tabDemo = new JTable(info2,title);
			// ��ʾ��ͷ
			this.jth = this.tabDemo.getTableHeader();
			this.scpDemo.setViewportView(tabDemo);
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
			}
	}
	public void btnNewButton_1_ActionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		//�Ȱ�ѧ�Ų�ѯ�������еĿγ̣��ٰ��κŲ�ѯ�γ���Ϣ
		try {
			Connection conn=ConnectionFactory.getConnection();
			String sql="select * from Stu_Course where ѧ��='"+StuId+"'"; 
			System.out.println(sql);
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
				cno[j++]=rs.getString(2);//���γ̺Ŵ����cno������
			}
			//��ֵi������ѭ�� 
			int i=count;
			int Ccount=count;
			Object[][] info1=new Object[Ccount][8];
			Ccount=0;
			while((i--)>0) {//ѭ������ѯcno������ÿ���γ̺ŵ���Ϣ
			String sql1="select * FROM Grade_count where �γ̺�='"+cno[i]+"'";
			Statement pst=conn.createStatement();
								
			ResultSet rs1=pst.executeQuery(sql1);
			//����Ϣ����info1��
			while(rs1.next()){
				info1[Ccount][0]=rs1.getString(1);
				info1[Ccount][1]=rs1.getString(2);
				info1[Ccount][2]=Integer.toString(rs1.getInt(3));
				info1[Ccount][3]=Integer.toString(rs1.getInt(4));
				info1[Ccount][4]=Integer.toString(rs1.getInt(5));
				info1[Ccount][5]=rs1.getFloat(6);
				info1[Ccount][6]=Integer.toString(rs1.getInt(7));
				String sql2="select * from Stu_Course where ѧ��='"+StuId+"'AND �γ̺�='"+cno[i]+"'"; 
				PreparedStatement pstm2=conn.prepareStatement(sql2);
				// ִ�в�ѯѧ��ΪStuId�Ŀγ�
				ResultSet rs2=pstm2.executeQuery();
				rs2=pstm2.executeQuery();
				if(rs2.next()) {
					info1[Ccount][7]=Integer.toString(rs2.getInt(3));
				}
				Ccount++;
				}}
				//ѭ������
				// �����ͷ
				String[] title={"�γ̺�","�γ���","ѡ������","��߳ɼ�","��ͳɼ�","ƽ���ɼ�","��������","�ɼ�"};
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
