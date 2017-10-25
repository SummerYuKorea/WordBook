package edu.java.prj01;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

public class HistoryFrame extends JFrame {

	private JPanel contentPane;
	private WordDAOImple dao=WordDAOImple.getInstance();
	private Date date1=null;
	private Date date2=null;
	private Date date3=null;
	private Date date4=null;
	private Date date5=null;
	private Date date6=null;
	private Date date7=new Date();
	
	private Date date0 =null; //tomorow 만들어서 넘겨주기 위한
	
	private SimpleDateFormat sdf=new SimpleDateFormat(" MM/dd\r\n    EEE");
	private SimpleDateFormat sdf2=new SimpleDateFormat("MM/dd");
	private String dateString=null;
	
	private final long ONEDAY= 86400000;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JTextArea textArea3;
	private JTextArea textArea5;
	private JTextArea textArea4;
	private JTextArea textArea6;
	private JTextArea textArea7;
	private JProgressBar bar1;
	private JProgressBar bar2;
	private JProgressBar bar3;
	private JProgressBar bar4;
	private JProgressBar bar5;
	private JProgressBar bar6;
	private JProgressBar bar7;
	private JButton btnRight;
	private JButton btnLeft;
	private JLabel lblLeft;
	private JLabel lblRight;
	
	private int page=0;
	private JTextArea textWords;
	private JLabel lblnum1;
	private JLabel lblnum2;
	private JLabel lblnum3;
	private JLabel lblnum4;
	private JLabel lblnum5;
	private JLabel lblnum6;
	private JLabel lblnum7;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryFrame frame = new HistoryFrame();
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
	public HistoryFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750,461);
		//506,461
	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//////
		setBounds(100, 100, 740, 461);
		textWords = new JTextArea();
		textWords.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		textWords.setBounds(470, 42, 254, 334);
		
		contentPane.add(textWords);
		textWords.setVisible(true);
		///이렇게 적어놓으면 디자인탭에서 볼수잇고
		//그치만 마우가 exited되어있기 때문에 실행하면 안ㄷ보임
		
		lblLeft = new JLabel("◀");
		lblLeft.setForeground(new Color(30, 144, 255));
		lblLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnLeft.doClick();
			}
		});
		lblRight = new JLabel("▶");
		lblRight.setForeground(new Color(30, 144, 255));
		lblRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnRight.doClick();
			}
		});
		
		lblnum7 = new JLabel();
		lblnum7.setHorizontalAlignment(SwingConstants.CENTER);
		lblnum7.setForeground(Color.WHITE);
		lblnum7.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		lblnum7.setBounds(391, 315, 29, 32);
		contentPane.add(lblnum7);
		
		lblnum6 = new JLabel();
		lblnum6.setHorizontalAlignment(SwingConstants.CENTER);
		lblnum6.setForeground(Color.WHITE);
		lblnum6.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		lblnum6.setBounds(336, 315, 29, 32);
		contentPane.add(lblnum6);
		
		lblnum4 = new JLabel();
		lblnum4.setHorizontalAlignment(SwingConstants.CENTER);
		lblnum4.setForeground(Color.WHITE);
		lblnum4.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		lblnum4.setBounds(229, 315, 29, 32);
		contentPane.add(lblnum4);
		
		lblnum5 = new JLabel();
		lblnum5.setHorizontalAlignment(SwingConstants.CENTER);
		lblnum5.setForeground(Color.WHITE);
		lblnum5.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		lblnum5.setBounds(283, 315, 29, 32);
		contentPane.add(lblnum5);
		
		lblnum3 = new JLabel();
		lblnum3.setHorizontalAlignment(SwingConstants.CENTER);
		lblnum3.setForeground(Color.WHITE);
		lblnum3.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		lblnum3.setBounds(175, 315, 29, 32);
		contentPane.add(lblnum3);
		
		lblnum2 = new JLabel();
		lblnum2.setHorizontalAlignment(SwingConstants.CENTER);
		lblnum2.setForeground(Color.WHITE);
		lblnum2.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		lblnum2.setBounds(123, 315, 29, 32);
		contentPane.add(lblnum2);
		
		lblnum1 = new JLabel();
		lblnum1.setForeground(new Color(255, 255, 255));
		lblnum1.setHorizontalAlignment(SwingConstants.CENTER);
		lblnum1.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		lblnum1.setBounds(69, 315, 29, 32);
		contentPane.add(lblnum1);
		lblRight.setBackground(new Color(255, 255, 255));
		lblRight.setHorizontalAlignment(SwingConstants.CENTER);
		lblRight.setFont(new Font("굴림", Font.BOLD, 24));
		lblRight.setBounds(434, 350, 45, 45);
		contentPane.add(lblRight);
		lblRight.setVisible(false);
		lblLeft.setBackground(new Color(255, 255, 255));
		lblLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeft.setFont(new Font("굴림", Font.BOLD, 24));
		lblLeft.setBounds(10, 350, 45, 45);
		contentPane.add(lblLeft);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	
		
		textArea7 = new JTextArea();
		textArea7.setBackground(new Color(176, 224, 230));
		textArea7.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textArea7.setBounds(378, 360, 51, 39);
		contentPane.add(textArea7);
	
		bar7 = new JProgressBar();
		bar7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBounds(100, 100, 740, 461);
				contentPane.add(textWords);
				textWords.setVisible(true);
				showHistoryWords( date7);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textWords.setVisible(false);
				setBounds(100, 100, 506,461);
			}
		});
		bar7.setValue(30);
		bar7.setOrientation(SwingConstants.VERTICAL);
		bar7.setMaximum(35);
		bar7.setForeground(new Color(0, 128, 128));
		bar7.setBackground(Color.WHITE);
		bar7.setBounds(391, 42, 29, 308);
		contentPane.add(bar7);
	
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
		
		textArea6 = new JTextArea();
		textArea6.setBackground(new Color(176, 224, 230));
		textArea6.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textArea6.setBounds(324, 360, 51, 39);
		contentPane.add(textArea6);
		
		bar6 = new JProgressBar();
		bar6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBounds(100, 100, 740, 461);
				contentPane.add(textWords);
				textWords.setVisible(true);
				showHistoryWords( date6);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textWords.setVisible(false);
				setBounds(100, 100, 506,461);
			}
		});
		bar6.setValue(30);
		bar6.setOrientation(SwingConstants.VERTICAL);
		bar6.setMaximum(35);
		bar6.setForeground(new Color(0, 128, 128));
		bar6.setBackground(Color.WHITE);
		bar6.setBounds(336, 42, 29, 308);
		contentPane.add(bar6);
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		
		textArea5 = new JTextArea();
		textArea5.setBackground(new Color(176, 224, 230));
		textArea5.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textArea5.setBounds(271, 360, 51, 39);
		contentPane.add(textArea5);
		
		bar5 = new JProgressBar();
		bar5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBounds(100, 100, 740, 461);
				contentPane.add(textWords);
				textWords.setVisible(true);
				showHistoryWords( date5);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textWords.setVisible(false);
				setBounds(100, 100, 506,461);
			}
		});
		bar5.setValue(30);
		bar5.setOrientation(SwingConstants.VERTICAL);
		bar5.setMaximum(35);
		bar5.setForeground(new Color(0, 128, 128));
		bar5.setBackground(Color.WHITE);
		bar5.setBounds(283, 42, 29, 308);
		contentPane.add(bar5);
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		textArea4 = new JTextArea();
		textArea4.setBackground(new Color(176, 224, 230));
		textArea4.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textArea4.setBounds(218, 360, 51, 39);
		contentPane.add(textArea4);
		
		bar4 = new JProgressBar();
		bar4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBounds(100, 100, 740, 461);
				contentPane.add(textWords);
				textWords.setVisible(true);
				showHistoryWords( date4);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textWords.setVisible(false);
				setBounds(100, 100, 506,461);
			}
		});
		bar4.setValue(30);
		bar4.setOrientation(SwingConstants.VERTICAL);
		bar4.setMaximum(35);
		bar4.setForeground(new Color(0, 128, 128));
		bar4.setBackground(Color.WHITE);
		bar4.setBounds(229, 42, 29, 308);
		contentPane.add(bar4);
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		textArea3 = new JTextArea();
		textArea3.setBackground(new Color(176, 224, 230));
		textArea3.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textArea3.setBounds(165, 360, 51, 39);
		contentPane.add(textArea3);
		
		bar3 = new JProgressBar();
		bar3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBounds(100, 100, 740, 461);
				contentPane.add(textWords);
				textWords.setVisible(true);
				showHistoryWords( date3);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textWords.setVisible(false);
				setBounds(100, 100, 506,461);
			}
		});
		bar3.setValue(30);
		bar3.setOrientation(SwingConstants.VERTICAL);
		bar3.setMaximum(35);
		bar3.setForeground(new Color(0, 128, 128));
		bar3.setBackground(Color.WHITE);
		bar3.setBounds(175, 42, 29, 308);
		contentPane.add(bar3);
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		textArea2 = new JTextArea();
		textArea2.setBackground(new Color(176, 224, 230));
		textArea2.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textArea2.setBounds(112, 360, 51, 39);
		contentPane.add(textArea2);
		
		bar2 = new JProgressBar();
		bar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBounds(100, 100, 740, 461);
				contentPane.add(textWords);
				textWords.setVisible(true);
				showHistoryWords( date2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textWords.setVisible(false);
				setBounds(100, 100, 506,461);
			}
		});
		bar2.setValue(30);
		bar2.setOrientation(SwingConstants.VERTICAL);
		bar2.setMaximum(35);
		bar2.setForeground(new Color(0, 128, 128));
		bar2.setBackground(Color.WHITE);
		bar2.setBounds(123, 42, 29, 308);
		contentPane.add(bar2);
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		textArea1 = new JTextArea();
		textArea1.setBackground(new Color(176, 224, 230));
		textArea1.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textArea1.setBounds(59, 360, 51, 39);
		contentPane.add(textArea1);
		
		bar1 = new JProgressBar();
		bar1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBounds(100, 100, 740, 461);
				contentPane.add(textWords);
				textWords.setVisible(true);
				showHistoryWords( date1);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textWords.setVisible(false);
				setBounds(100, 100, 506,461);
			}
		});
		bar1.setBackground(new Color(255, 255, 255));
		bar1.setOrientation(SwingConstants.VERTICAL);
		bar1.setMaximum(35);
		bar1.setValue(5);
		bar1.setForeground(new Color(0, 128, 128));
		bar1.setBounds(69, 42, 29, 308);
		contentPane.add(bar1);
		
	
		
		
		
		
		
		////////////버튼~///////////////////////////////////////////////////////////////////////////////////////
		
		btnRight = new JButton("");
		btnRight.setBackground(new Color(255, 255, 255));
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page!=0){
					date7=new Date(date7.getTime()+ONEDAY*7);
					showAllHistory();
					page++;
					if(page==0)lblRight.setVisible(false);
				}
			}
		});
		
		btnLeft = new JButton("");
		btnLeft.setVisible(false);
		btnLeft.setBackground(new Color(255, 255, 255));
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date7=new Date(date7.getTime()-ONEDAY*7);
				showAllHistory();
				page--;
				if(page!=0)lblRight.setVisible(true);
				}
		});
		btnLeft.setFont(new Font("굴림", Font.PLAIN, 15));
		btnLeft.setBounds(10, 350, 45, 45);
	//	contentPane.add(btnLeft);
		btnRight.setFont(new Font("굴림", Font.PLAIN, 12));
		btnRight.setBounds(434, 350, 45, 45);
	//	contentPane.add(btnRight);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		showAllHistory();
	}//생성자
	
	private void showHistory(JTextArea textArea, JProgressBar bar, JLabel lblnum, Date date){
		int word_num;
		String today;
		String tomorrow;
		
		date0=new Date(date.getTime()+ONEDAY);
		dateString=sdf.format(date);
		textArea.setText(dateString);
		today=sdf2.format(date);
		tomorrow=sdf2.format(date0);
		word_num=dao.bringMyHistory(  today, tomorrow);
		if(word_num==0){
			lblnum.setForeground(new Color(255, 0, 0));
		   lblnum.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		}else{
			lblnum.setForeground(Color.WHITE);
		}
		bar.setValue(word_num);
		lblnum.setText(String.format("%d", word_num));
		
		/*date=new Date(date.getTime()-ONEDAY);
		date2=new Date(date.getTime()+ONEDAY);
		dateString=sdf.format(date);
		textArea6.setText(dateString);
		today=sdf2.format(date);
		tomorrow=sdf2.format(date2);
		word_num=dao.bringMyHistory(  today, tomorrow);
		bar6.setValue(word_num);
		
		date=new Date(date.getTime()-ONEDAY);
		date2=new Date(date.getTime()+ONEDAY);
		dateString=sdf.format(date);
		textArea5.setText(dateString);
		today=sdf2.format(date);
		tomorrow=sdf2.format(date2);
		word_num=dao.bringMyHistory(  today, tomorrow);
		bar5.setValue(word_num);
		
		date=new Date(date.getTime()-ONEDAY);
		date2=new Date(date.getTime()+ONEDAY);
		dateString=sdf.format(date);
		textArea4.setText(dateString);
		today=sdf2.format(date);
		tomorrow=sdf2.format(date2);
		word_num=dao.bringMyHistory(  today, tomorrow);
		bar4.setValue(word_num);
		
		date=new Date(date.getTime()-ONEDAY);
		date2=new Date(date.getTime()+ONEDAY);
		dateString=sdf.format(date);
		textArea3.setText(dateString);
		today=sdf2.format(date);
		tomorrow=sdf2.format(date2);
		word_num=dao.bringMyHistory(  today, tomorrow);
		bar3.setValue(word_num);
		
		date=new Date(date.getTime()-ONEDAY);
		date2=new Date(date.getTime()+ONEDAY);
		dateString=sdf.format(date);
		textArea2.setText(dateString);
		today=sdf2.format(date);
		tomorrow=sdf2.format(date2);
		word_num=dao.bringMyHistory(  today, tomorrow);
		bar2.setValue(word_num);
		
		date=new Date(date.getTime()-ONEDAY);
		date2=new Date(date.getTime()+ONEDAY);
		dateString=sdf.format(date);
		textArea1.setText(dateString);
		today=sdf2.format(date);
		tomorrow=sdf2.format(date2);
		word_num=dao.bringMyHistory(  today, tomorrow);
		bar1.setValue(word_num);
		
		*/
			
	}//showHistory
	
	private void showAllHistory(){
		showHistory(textArea7, bar7,lblnum7, date7);
		
		date6=new Date(date7.getTime()-ONEDAY);
		showHistory(textArea6, bar6,lblnum6, date6);
		
		date5=new Date(date6.getTime()-ONEDAY);
		showHistory(textArea5, bar5,lblnum5, date5);
		
		date4=new Date(date5.getTime()-ONEDAY);
		showHistory(textArea4, bar4,lblnum4, date4);
		
		date3=new Date(date4.getTime()-ONEDAY);
		showHistory(textArea3, bar3,lblnum3, date3);
		
		date2=new Date(date3.getTime()-ONEDAY);
		showHistory(textArea2, bar2,lblnum2, date2);
		
		date1=new Date(date2.getTime()-ONEDAY);
		showHistory(textArea1, bar1,lblnum1, date1);
	}//showAllHistory()
	
	private void showHistoryWords(Date date){
		ArrayList<WordVO> words= new ArrayList<>();
		String today;
		String tomorrow;
		String wordContent="";
		
		date2=new Date(date.getTime()+ONEDAY);
		today=sdf2.format(date);
		tomorrow=sdf2.format(date2);
		words=dao.bringHistoryWords(  today, tomorrow);
		
		for(WordVO vo :words){
			if(vo.getWrong()==0)	wordContent+= vo.getWord_class()+".  "+vo.getWord() +"  :   "+vo.getMeaning()+"\n";
			else if(vo.getWrong()==1)	wordContent+= vo.getWord_class()+".  "+vo.getWord() +"  :   "+vo.getMeaning()+"★★\n";
			
		}//for
		
		textWords.setText(today+"\n\n"+wordContent);
		
		
		
	}
}//class
