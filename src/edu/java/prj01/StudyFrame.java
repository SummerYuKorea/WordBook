package edu.java.prj01;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudyFrame extends JFrame {

	private JPanel contentPane;

	private WordVO[] list =null;//100해야될것같지만 null이라해보자
	private WordDAOImple dao=WordDAOImple.getInstance();
	private WordVO word=null;
	private JLabel lbl_example;
	private JButton btn_former;
	private JButton btn_latter;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { StudyFrame frame = new
	 * StudyFrame(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public StudyFrame() {
		
		list= dao.Level_init();///음~
		word=list[User.status% WordDAOImple.WORD100-1];
		//System.out.println("스터디시작할때:"+User.status);
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 524);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lbl_mean = new JLabel(word.getMeaning());
		lbl_mean.setHorizontalTextPosition(SwingConstants.LEADING);
		lbl_mean.setFont(new Font("함초롬돋움", Font.PLAIN, 19));
		lbl_mean.setBounds(188, 223, 235, 58);
		contentPane.add(lbl_mean);
		
		JLabel lbl_pum = new JLabel(word.getWord_class()+".");
		lbl_pum.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_pum.setForeground(Color.BLUE);
		lbl_pum.setFont(new Font("함초롬돋움", Font.BOLD, 23));
		lbl_pum.setBounds(114, 221, 62, 58);
		contentPane.add(lbl_pum);
		
		JLabel lbl_word = new JLabel(word.getWord());
		//JLabel lbl_word = new JLabel("be comparable\n to+명");
		lbl_word.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_word.setFont(new Font("함초롬돋움", Font.PLAIN, 27));
		lbl_word.setBounds(80, 123, 277, 58);
		contentPane.add(lbl_word);
		
		lbl_example = new JLabel(word.getExample());
		lbl_example.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_example.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_example.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
		lbl_example.setBounds(12, 302, 411, 58);
		contentPane.add(lbl_example);
		
		
		JLabel lblUserInfo = new JLabel("lev"+User.clevel+"   W: "+(User.status)+"   T: "+User.test_status+"/"+(WordDAOImple.WORD100/TestFrame.WORD20));
		lblUserInfo.setForeground(new Color(47, 79, 79));
		lblUserInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserInfo.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		lblUserInfo.setBounds(188, 11, 139, 46);
		contentPane.add(lblUserInfo);
		
		JLabel lblNewLabel = new JLabel(User.name);
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(94, 13, 76, 40);
		contentPane.add(lblNewLabel);
		/////////////////////////////////////////////////////////////////////////////

		btn_former = new JButton("◁");
		btn_former.setBackground(new Color(255, 160, 122));
		btn_former.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (User.status % WordDAOImple.WORD100 != 1&&  
						(User.status%WordDAOImple.WORD100)>(User.test_status*TestFrame.WORD20+1)) {
					//test 전의 단어들로 못돌아감
					User.status--;
					word = list[(User.status) % WordDAOImple.WORD100];// list에서 해당 WordVO를 인스턴스에  저장해서 사용하자
														
					lbl_word.setText(word.getWord());
					lbl_pum.setText(word.getWord_class()+".");
					lbl_mean.setText(word.getMeaning());
					lbl_example.setText(word.getExample());
					btn_latter.setText("▷");
					lblUserInfo.setText("lev"+User.clevel+"   W: "+(User.status)+"   T: "+User.test_status+"/"+(WordDAOImple.WORD100/TestFrame.WORD20));
				}// 이전으로 돌아가기
			}
		});
		btn_former.setFont(new Font("굴림", Font.BOLD, 27));
		btn_former.setBounds(12, 114, 64, 88);
		contentPane.add(btn_former);
		
		btn_latter = new JButton("▷");
		btn_latter.setBackground(new Color(70, 130, 180));
		btn_latter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((User.status%  WordDAOImple.WORD100)%TestFrame.WORD20  !=  0){ //100으로 나눈 나머지가 99냐 아니냐
					
					if((User.status%  WordDAOImple.WORD100)%TestFrame.WORD20  ==  TestFrame.WORD20-1){
						btn_latter.setText("T");
						btn_latter.setForeground(Color.WHITE);
					}//작은 if

					User.status++;
					word=list[(User.status-1)%  WordDAOImple.WORD100];//list에서 해당 WordVO를 인스턴스에 저장해서 사용하자
					//System.out.println("공부중:"+User.status);
				
					lbl_word.setText(word.getWord());
					lbl_pum.setText(word.getWord_class()+".");
					lbl_mean.setText(word.getMeaning());
					lbl_example.setText(word.getExample());
				
					lblUserInfo.setText("lev"+User.clevel+"   W: "+(User.status)+"   T: "+User.test_status+"/"+(WordDAOImple.WORD100/TestFrame.WORD20));
				
					//큰if끝
				}else {
					dispose();
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				}
				
			}
		});
		btn_latter.setFont(new Font("굴림", Font.BOLD, 27));
		btn_latter.setBounds(359, 114, 64, 88);
		contentPane.add(btn_latter);
		
		JLabel lblStop = new JLabel("공부 그만 ▶");
		lblStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			//	System.out.println("공부그만시: "+User.status);
			//	PrjMain01 window = new PrjMain01();
			//	window.frame.setVisible(true);
			}
		});
		lblStop.setOpaque(true);
		lblStop.setBackground(new Color(255, 215, 0));
		lblStop.setForeground(new Color(0, 0, 0));
		lblStop.setFont(new Font("휴먼편지체", Font.BOLD, 18));
		lblStop.setBounds(315, 430, 108, 31);
		contentPane.add(lblStop);
		

		
		
	}// 생성자
}// class
