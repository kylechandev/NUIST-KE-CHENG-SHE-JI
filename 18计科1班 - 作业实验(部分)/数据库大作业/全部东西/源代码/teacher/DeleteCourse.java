package teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tools.ConnectionFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class DeleteCourse extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private Window frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCourse frame = new DeleteCourse();
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
	public DeleteCourse() {
		setTitle("\u5220\u9664\u8BFE\u7A0B");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 363);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u5148\u8F93\u5165\u8981\u5220\u9664\u7684\u8BFE\u7A0B\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("FZYaoTi", Font.PLAIN, 22));
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setBounds(64, 67, 264, 48);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(74, 130, 238, 40);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		//�ύ
		JButton btnNewButton = new JButton("\u786E\u5B9A\u5220\u9664");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
					Connection connection=ConnectionFactory.getConnection();
				    Statement statement=connection.createStatement();
				    System.out.println(textField_1.getText());
				    Statement statement2=connection.createStatement();
				    ResultSet resultSet2=statement2.executeQuery("SELECT *FROM Stu_Course WHERE �γ̺�='"+textField_1.getText()+"';");
				    if(resultSet2.next()) {
				    	String sql="DELETE FROM Stu_Course WHERE �γ̺�='"+textField_1.getText()+"'";
				        PreparedStatement pst=null;
			            System.out.println(sql);
			            pst = connection.prepareStatement(sql);
			            pst.executeUpdate();
				    }
				    ResultSet resultSet1=statement.executeQuery("SELECT *FROM Course WHERE �γ̺�='"+textField_1.getText()+"';");
		            if(textField_1.equals("")){
		            	JOptionPane.showMessageDialog(frame,"��δ��дҪɾ���Ŀγ̺ţ�");
			        }else if(!resultSet1.next()){
			        	JOptionPane.showMessageDialog(frame,"û�иÿγ�,���������룡");        	
			        }else{
		            String sql="DELETE FROM Course WHERE �γ̺�='"+textField_1.getText()+"'";
			        PreparedStatement pst=null;
		            System.out.println(sql);
		            pst = connection.prepareStatement(sql);
		            pst.executeUpdate();
					System.out.println("�ɹ������������ɹ����ӷ�����");
					System.out.println("ִ�����");
					System.out.println("�չ�");
			        JOptionPane.showMessageDialog(frame,"ɾ���ɹ�!");//ɾ���ɹ�
			        setVisible(false);
			        }
				}catch(Exception ee)
				{ 
					ee.printStackTrace();
				}
			}
		});
		//
		
		btnNewButton.setBounds(127, 218, 123, 29);
		contentPane.add(btnNewButton);
	}
	public void open() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

}
