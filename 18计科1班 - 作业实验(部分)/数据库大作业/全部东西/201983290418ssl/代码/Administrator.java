package administrator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class Administrator extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator frame = new Administrator();
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
	public Administrator() {
		setTitle("\u6559\u52A1\u5904\u7BA1\u7406\u5458");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 882, 597);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//选课审批
		JButton btnNewButton = new JButton("\u9009\u8BFE\u5BA1\u6279");
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 22));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ApproveCourse approveCourse=new ApproveCourse();
				approveCourse.open();
			}
		});
		btnNewButton.setBounds(339, 114, 165, 73);
		contentPane.add(btnNewButton);
		
		//成绩单显示
		JButton btnNewButton_1 = new JButton("\u5B66\u751F\u6210\u7EE9\u5355");
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 22));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GradeResult gradeResult=new GradeResult();
				gradeResult.open();
			}
		});
		btnNewButton_1.setBounds(339, 292, 165, 73);
		contentPane.add(btnNewButton_1);
	}
	public void open(){
		this.setVisible(true);
		
	}
}
