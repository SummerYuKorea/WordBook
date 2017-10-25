package edu.java.prj01;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class TestFrame extends JFrame {

	private JPanel contentPane;
	private WordVO[] list =null;//100해야될것같지만 null이라해보자
	private WordDAOImple dao=WordDAOImple.getInstance();
	private WordVO word=null;
	
	public static final int WORD20=5;
	private JLabel lblOpt1;
	private JLabel lblOpt2;
	private JLabel lblOpt3;
	private JLabel lblOpt4;
	
	int status_in_test=0;
	int grade=0;
	private JLabel lblWord_class;
	private JLabel lblGrade;
	private JLabel lblwrong;
	private JLabel lblShorlock;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
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
	public TestFrame() {
		
		list= dao.Level_init();///음~
		//System.out.println("User.staus:"+User.status);
		status_in_test=(User.status-1)% WordDAOImple.WORD100- (WORD20-1); //0~9( WORD100-1까지)
		//System.out.println("2:: "+status_in_test);
		word=list[status_in_test];
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 524);
		contentPane = new JPanel();
		contentPane.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(Color.WHITE, 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTest = new JLabel("TEST");
		lblTest.setOpaque(true);
		lblTest.setBackground(new Color(255, 228, 225));
		lblTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblTest.setFont(new Font("휴먼편지체", Font.BOLD, 37));
		lblTest.setBounds(35, 26, 154, 52);
		contentPane.add(lblTest);
		
		JLabel lblWord = new JLabel(word.getWord());
		lblWord.setHorizontalAlignment(SwingConstants.LEFT);
		lblWord.setFont(new Font("함초롬돋움", Font.PLAIN, 29));
		lblWord.setBounds(67, 146, 277, 52);
		contentPane.add(lblWord);
		
		JButton btnRight = new JButton("▷");
		btnRight.setBackground(new Color(70, 130, 180));
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (status_in_test < (WORD20*(User.test_status+1))-1) {
					if (status_in_test == (WORD20*(User.test_status+1))-2) {
						btnRight.setText("S");
						btnRight.setForeground(Color.WHITE);
					}
					status_in_test++;
					word = list[status_in_test];
					lblWord.setText(word.getWord());
					lblWord_class.setText(word.getWord_class() + ".");
					setOptions(word);
				}else{
					User.test_status++;
					User.status++;
					if (User.test_status < ((WordDAOImple.WORD100) / WORD20)) {
						dispose();// test끝내고
						StudyFrame frame = new StudyFrame();
						frame.setVisible(true);// study 다시 시작
					}else{  //test_status가 5(100개/20개)면 level up!  이경우는 2개
						dispose();
						LevelUpFrame frame = new LevelUpFrame();
					frame.setVisible(true);}
				}
			}
		});
		btnRight.setFont(new Font("굴림", Font.BOLD, 28));
		btnRight.setBounds(356, 146, 67, 71);
		contentPane.add(btnRight);
		
		lblOpt1 = new JLabel();
		lblOpt1.setBackground(new Color(255, 255, 255));
		lblOpt1.setOpaque(true);
		lblOpt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rightOrWrong( lblOpt1,  lblOpt1.getText().substring( 8,  lblOpt1.getText().length()));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpt1.setBackground(new Color(173, 216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpt1.setBackground(new Color(255, 255, 255));
			}
		});
		lblOpt1.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lblOpt1.setBounds(35, 210, 322, 48);
		contentPane.add(lblOpt1);
		
		lblOpt2 = new JLabel("");
		lblOpt2.setBackground(new Color(255, 255, 255));
		lblOpt2.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lblOpt2.setBounds(35, 272, 339, 46);
		lblOpt2.setOpaque(true);
		lblOpt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rightOrWrong(lblOpt2,  lblOpt2.getText().substring( 8,  lblOpt2.getText().length()));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpt2.setBackground(new Color(173, 216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpt2.setBackground(new Color(255, 255, 255));
			}
		});
		contentPane.add(lblOpt2);
		
		lblOpt3 = new JLabel("");
		lblOpt3.setBackground(new Color(255, 255, 255));
		lblOpt3.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lblOpt3.setBounds(35, 335, 339, 46);
		lblOpt3.setOpaque(true);
		lblOpt3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rightOrWrong(lblOpt3,  lblOpt3.getText().substring( 8,  lblOpt3.getText().length()));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpt3.setBackground(new Color(173, 216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpt3.setBackground(new Color(255, 255, 255));
			}
		});
		contentPane.add(lblOpt3);
		
		lblOpt4 = new JLabel("");
		lblOpt4.setBackground(new Color(255, 255, 255));
		lblOpt4.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lblOpt4.setBounds(35, 396, 339, 46);
		lblOpt4.setOpaque(true);
		lblOpt4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rightOrWrong( lblOpt4,  lblOpt4.getText().substring( 8,  lblOpt4.getText().length()));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpt4.setBackground(new Color(173, 216, 230));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpt4.setBackground(new Color(255, 255, 255));
			}
		});
		contentPane.add(lblOpt4);
		
		lblWord_class = new JLabel(word.getWord_class()+".");
		lblWord_class.setForeground(new Color(0, 0, 255));
		lblWord_class.setFont(new Font("함초롬돋움", Font.BOLD, 24));
		lblWord_class.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWord_class.setBounds(12, 152, 46, 46);
		contentPane.add(lblWord_class);
		
		lblGrade = new JLabel("");
		lblGrade.setForeground(new Color(178, 34, 34));
		lblGrade.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrade.setBounds(196, 26, 98, 55);
		contentPane.add(lblGrade);
		
		lblwrong = new JLabel();
		lblwrong.setForeground(new Color(255, 255, 255));
		lblwrong.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		lblwrong.setBounds(281, 86, 99, 46);
		contentPane.add(lblwrong);
		
		lblShorlock = new JLabel("");
	//	lblShorlock.setIcon(new ImageIcon("C:\\Users\\stu\\Pictures\\셜록3.PNG"));
		lblShorlock.setBounds(281, 0, 154, 143);
		contentPane.add(lblShorlock);
		
		setOptions(word);
	}//생성자
	
	private void setOptions(WordVO word){
		ArrayList<WordVO>options = dao.bringOptions(word);
		Collections.shuffle(options);
		
		lblOpt1.setText("   Ⓐ    "+options.get(0).getMeaning());
		lblOpt2.setText("   Ⓑ    "+options.get(1).getMeaning());
		lblOpt3.setText("   Ⓒ    "+options.get(2).getMeaning());
		lblOpt4.setText("   Ⓓ    "+options.get(3).getMeaning());
		
		lblOpt1.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lblOpt1.setForeground(new Color(0, 0, 0));
		lblOpt2.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lblOpt2.setForeground(new Color(0, 0, 0));
		lblOpt3.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lblOpt3.setForeground(new Color(0, 0, 0));
		lblOpt4.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lblOpt4.setForeground(new Color(0, 0, 0));
		
		lblwrong.setText("");
	}//setOptions()
	
	private void rightOrWrong(JLabel lbl, String choice){
		lbl.setForeground(new Color(0, 0, 205));
		
		if(choice.equals(word.getMeaning())){
			grade++;
			lbl.setFont(new Font("함초롬돋움", Font.BOLD, 20));
			lblwrong.setText("RIGHT♥");
			lblShorlock.setIcon(new ImageIcon("C:\\Users\\user\\Pictures\\셜록3.PNG"));
		}else{
			dao.setWrong(word.getCid());;//이 단어 내가 틀렷던 단어라고 단어장에 알려준다
			lblShorlock.setIcon(new ImageIcon("C:\\Users\\user\\Pictures\\셜록.PNG"));
			if(lblOpt1.getText().substring( 8,  lblOpt1.getText().length()).equals(word.getMeaning())){
				lblOpt1.setFont(new Font("함초롬돋움", Font.BOLD, 20));
				lblOpt1.setForeground(new Color(220, 20, 60));
			}else if(lblOpt2.getText().substring( 8,  lblOpt2.getText().length()).equals(word.getMeaning())){
				lblOpt2.setFont(new Font("함초롬돋움", Font.BOLD, 20));
				lblOpt2.setForeground(new Color(220, 20, 60));
			}else if(lblOpt3.getText().substring( 8,  lblOpt3.getText().length()).equals(word.getMeaning())){
				lblOpt3.setFont(new Font("함초롬돋움", Font.BOLD, 20));
				lblOpt3.setForeground(new Color(220, 20, 60));
			}else if(lblOpt4.getText().substring( 8,  lblOpt4.getText().length()).equals(word.getMeaning())){
				lblOpt4.setFont(new Font("함초롬돋움", Font.BOLD, 20));
				lblOpt4.setForeground(new Color(220, 20, 60));
			}
			lblwrong.setText("WRONG!!!");
		}//else 끝!
		lblGrade.setText(grade+" / "+WORD20);
		
		
	}// rightOrWrong()
}//class
