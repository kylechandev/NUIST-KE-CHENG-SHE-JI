package teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tools.ConnectionFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;

public class AddCourse extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Window frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
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
	public AddCourse() {
		setTitle("\u6DFB\u52A0\u8BFE\u7A0B");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 733, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		label.setBounds(83, 52, 113, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("宋体", Font.PLAIN, 24));
		label_1.setBounds(83, 111, 113, 21);
		contentPane.add(label_1);
		
		JLabel label_1_1 = new JLabel("\u5B66  \u5206\uFF1A");
		label_1_1.setForeground(new Color(0, 0, 128));
		label_1_1.setFont(new Font("宋体", Font.PLAIN, 24));
		label_1_1.setBounds(83, 234, 113, 21);
		contentPane.add(label_1_1);
		
		textField = new JTextField();
		textField.setBounds(214, 46, 140, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(214, 105, 140, 27);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(214, 234, 140, 27);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("\u8BFE  \u65F6\uFF1A");
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("宋体", Font.PLAIN, 24));
		label_2.setBounds(83, 175, 113, 21);
		contentPane.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(214, 175, 140, 27);
		contentPane.add(textField_3);
		
		JLabel label_2_1 = new JLabel("\u5F00\u8BFE\u5B66\u671F\uFF1A");
		label_2_1.setForeground(new Color(0, 0, 128));
		label_2_1.setFont(new Font("宋体", Font.PLAIN, 24));
		label_2_1.setBounds(83, 302, 140, 21);
		contentPane.add(label_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(214, 302, 140, 27);
		contentPane.add(textField_4);
		
		JLabel label_2_1_1 = new JLabel("\u8BFE\u7A0B\u7B80\u4ECB\uFF1A");
		label_2_1_1.setForeground(new Color(0, 0, 128));
		label_2_1_1.setFont(new Font("宋体", Font.PLAIN, 24));
		label_2_1_1.setBounds(83, 373, 140, 21);
		contentPane.add(label_2_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(214, 373, 140, 27);
		contentPane.add(textField_5);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A\u6DFB\u52A0");
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
					Connection connection=ConnectionFactory.getConnection();
				    Statement statement=connection.createStatement();
				    ResultSet resultSet1=statement.executeQuery("SELECT * FROM Course WHERE 课程号='"+textField.getText()+"';");
			        if(resultSet1.next()==true)
		            {
			        	System.out.println(textField_1.getText());
						JOptionPane.showMessageDialog(frame,"该课程已存在,请重新输入!");
		            }
			        else {
		            String kch=textField.getText();
		            String kcm=textField_1.getText();
		            String xf1=textField_2.getText();
		            int xf=Integer.parseInt(xf1);
		            String ks1=textField_3.getText();
		            int ks=Integer.parseInt(ks1);
		            String kkxq=textField_4.getText();
		            String kcjj=textField_5.getText();
		            if(kch.equals("")||kcm.equals("")||xf1.equals("")||ks1.equals("")||kkxq.equals("")||kcjj.equals(""))
			        {
		            	JOptionPane.showMessageDialog(frame,"您还有必填项没有填写！");
			        }else{
		            String sql="INSERT INTO Course VALUES('"+kch+"','" +kcm+ "','" +xf+"','"+ks+"','"+kkxq+"','"+kcjj+"','False')";
			        PreparedStatement pst=null;
		            System.out.println(sql);
		            pst = connection.prepareStatement(sql);
		            pst.executeUpdate();
					System.out.println("成功加载驱动，成功连接服务器");
					System.out.println("执行完毕");
					System.out.println("收工");
			        JOptionPane.showMessageDialog(frame,"添加成功!");//添加成功
			        setVisible(false);
			        }
			      }
				}catch(Exception ee)
				{ 
					ee.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(527, 341, 140, 66);
		contentPane.add(btnNewButton);
	}

	public void open() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

}
