package student;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import courseSystem.Login;
import tools.ConnectionFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class ChooseCourse extends JFrame {

	private JPanel contentPane;
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JTextField textField;
	private JButton btnNewButton_1;
	private Window frame;
	public static String StuId_Choose;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseCourse frame = new ChooseCourse();
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
	public ChooseCourse() {
		setTitle("\u9009\u8BFE");
		StuId_Choose=Login.id;
		System.out.println(StuId_Choose);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 906, 634);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(15,60,854,449);
		getContentPane().add(this.scpDemo);
		
		JLabel lblNewLabel = new JLabel("������Ҫѡ��Ŀγ̺ţ�");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 20));
		lblNewLabel.setBounds(42, 535, 227, 21);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(279, 532, 141, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//�ύ��ť
		JButton btnNewButton = new JButton("\u786E\u5B9A\u9009\u8BFE");
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 20));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String kch=textField.getText();
				try{
					Connection connection=ConnectionFactory.getConnection();
				    Statement statement=connection.createStatement();
				    Statement statement1=connection.createStatement();
				    String sql="INSERT INTO Stu_Course(ѧ��,�γ̺�) VALUES('"+StuId_Choose+"','"+kch+"')";
				    String sql2="select * from Course where �γ̺�='"+kch+"'";
				    PreparedStatement pstm=connection.prepareStatement(sql2);
					ResultSet rs = pstm.executeQuery();
					rs=pstm.executeQuery();
					System.out.println(sql2);
					String CName =null;
					while(rs.next()){					
						CName=rs.getString(2);//���γ̺Ŵ����cno������
					}					
					System.out.println(CName);
				    String sql1="INSERT INTO Grade_count(�γ̺�,�γ���) VALUES('"+kch+"','"+CName+"')";
				    System.out.println(sql1);
				    statement.executeUpdate(sql);
					statement1.executeUpdate(sql1);
					JOptionPane.showMessageDialog(frame,"��ӳɹ�!");
				}catch(Exception ee)
				{ 
					ee.printStackTrace();
				}
				
			}
		});
		
		btnNewButton.setBounds(661, 524, 152, 35);
		contentPane.add(btnNewButton);
		
		//��ʾ���еĿγ̼�¼
		btnNewButton_1 = new JButton("��ʾ���пγ�");
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 21));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_ActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(347, 8, 188, 37);
		contentPane.add(btnNewButton_1);
	}
	public void btnNewButton_ActionPerformed(ActionEvent ae){
		// ��������������Դ����ʾ���ݵľ��崦��������ע����
		try{
			// �������
			Connection conn=ConnectionFactory.getConnection();
			String sql="select * from Course where �Ƿ�ͨ������='True'"; 
			PreparedStatement pstm=conn.prepareStatement(sql);
			// ִ�в�ѯ
			ResultSet rs=pstm.executeQuery();
			// �����ж�������¼
			int count=0;
			while(rs.next()){
				count++;
			}
			rs=pstm.executeQuery();
			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			Object[][] info=new Object[count][6];
			count = 0;
			while(rs.next()){
				info[count][0]=rs.getString(1);
				info[count][1]=rs.getString(2);
				info[count][2]=Integer.toString(rs.getInt(3));
				info[count][3]=Integer.toString(rs.getInt(4));
				info[count][4]=Integer.toString(rs.getInt(5));System.out.println(info[count][4]);
				info[count][5]=rs.getString(6);
				count++;
			}
			// �����ͷ
			String[] title={"�γ̺�","�γ���","ѧ��","��ʱ","����ѧ��","�γ̼��"};
			// ����JTable
			this.tabDemo = new JTable(info,title);
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
