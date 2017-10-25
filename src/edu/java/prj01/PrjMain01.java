package edu.java.prj01;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class PrjMain01 {

	public JFrame frame;
	WordDAOImple dao=WordDAOImple.getInstance();//dao의 메소드 쓰려고
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrjMain01 window = new PrjMain01();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrjMain01() {
		//확인용
				dao.selectUser(); // initialize 밖에쓰고안에쓰고 하는것..~~
				initialize();				
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(107, 142, 35));
		frame.setBounds(100, 100, 451, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("다너를 위한 시간");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("휴먼편지체", Font.BOLD, 38));
		lblTitle.setBackground(new Color(255, 228, 225));
		lblTitle.setBounds(56, 128, 325, 62);
		frame.getContentPane().add(lblTitle);
		
		JButton btnStudy = new JButton("시작");
		btnStudy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	frame.dispose();// 필드변수 frame 닫고
				StudyFrame frame = new StudyFrame();
				frame.setVisible(true);
			}
			
		});
		btnStudy.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		btnStudy.setBorder(new LineBorder(new Color(0, 128, 128), 2, true));
		btnStudy.setBackground(new Color(250, 235, 215));
		btnStudy.setBounds(125, 224, 178, 47);
		frame.getContentPane().add(btnStudy);
		
		JButton btnUserInfo = new JButton("사용자 정보");
		btnUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				UserInfoFrame frame = new UserInfoFrame();
				frame.setVisible(true);
				
			}
		});
		btnUserInfo.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		btnUserInfo.setBorder(new LineBorder(new Color(0, 128, 128), 2, true));
		btnUserInfo.setBackground(new Color(255, 239, 213));
		btnUserInfo.setBounds(125, 307, 178, 47);
		frame.getContentPane().add(btnUserInfo);
		
		JButton btnGameOver = new JButton("종료");
		btnGameOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameOver();
			}
		});
		btnGameOver.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		btnGameOver.setBorder(new LineBorder(new Color(0, 128, 128), 2, true));
		btnGameOver.setBackground(new Color(255, 239, 213));
		btnGameOver.setBounds(125, 392, 178, 47);
		frame.getContentPane().add(btnGameOver);
		
		JLabel lblShorlock = new JLabel("");
		lblShorlock.setIcon(new ImageIcon("C:\\Users\\user\\Pictures\\셜록5.png"));
		lblShorlock.setVerticalAlignment(SwingConstants.TOP);
		lblShorlock.setBounds(0, 0, 435, 202);
		frame.getContentPane().add(lblShorlock);
		
		
	}//initialize
	
	
	void gameOver(){
		dao.insertHistory();
	//	System.out.println("종료 직전의:"+User.status);
		dao.updateUser();
		System.exit(0);
		
	}
}//class
