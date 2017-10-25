package edu.java.prj01;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OChickenFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChickenFrame frame = new ChickenFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public OChickenFrame() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 464);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChicken = new JLabel("");
		lblChicken.setHorizontalAlignment(SwingConstants.CENTER);
		lblChicken.setIcon(new ImageIcon("C:\\Users\\stu\\Pictures\\꽝2.PNG"));
		lblChicken.setBounds(39, 10, 294, 347);
		contentPane.add(lblChicken);
		
		JLabel lblNewLabel = new JLabel("후라이드 치킨에 당첨되셨습니다☆");
		lblNewLabel.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		lblNewLabel.setBounds(27, 373, 294, 42);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		btnNewButton.setBounds(293, 373, 63, 42);
		contentPane.add(btnNewButton);
	}
}
