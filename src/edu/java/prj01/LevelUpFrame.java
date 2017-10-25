package edu.java.prj01;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LevelUpFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textChoice;
	
	private int a;
	private WordDAOImple dao=WordDAOImple.getInstance();		//이렇게 해도 될까? 아 이렇게 하면 메소드 사용안해도 바로 자리 만들어버리는거? 근데어차피 static getInstance자나
	private JLabel lblChoice;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelUpFrame frame = new LevelUpFrame();
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
	public LevelUpFrame() {
		User.clevel++;		////레벨업
		User.test_status=0;//test 상태 초기화
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 524);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLevelUp = new JLabel("Level Up!!!");
		lblLevelUp.setOpaque(true);
		lblLevelUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevelUp.setFont(new Font("휴먼편지체", Font.BOLD, 37));
		lblLevelUp.setBackground(new Color(255, 228, 225));
		lblLevelUp.setBounds(63, 31, 325, 62);
		contentPane.add(lblLevelUp);
		
		JLabel lblDisk = new JLabel("");
		lblDisk.setIcon(new ImageIcon("C:\\Users\\user\\Pictures\\돌림판4.PNG"));
		lblDisk.setBounds(47, 91, 351, 351);
		contentPane.add(lblDisk);
		

		lblChoice = new JLabel("1~6 까지의 숫자 중 하나를 선택하세요 기회는 한 번뿐!!");
		lblChoice.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		lblChoice.setBounds(12, 440, 308, 28);
		contentPane.add(lblChoice);
		
		JButton btnGo = new JButton("GO!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chuchum();
			}
		});
		btnGo.setFont(new Font("휴먼편지체", Font.BOLD, 11));
		btnGo.setBounds(369, 434, 54, 40);
		contentPane.add(btnGo);
		
		textChoice = new JTextField();
		textChoice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnGo.doClick();
					
			}
		});
		textChoice.setBounds(334, 435, 35, 39);
		contentPane.add(textChoice);
		textChoice.setColumns(10);
		textChoice.grabFocus();
		
	
		
	}//생성자
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	void chuchum(){
		String choice=textChoice.getText();
		if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6")){
			///////////////////////////////////equals로 비교하는거!!!!! 주의해야지!!!!샛캬
		a=Integer.parseInt(choice);
		a=(int)(a*Math.random()*10);
		Random r=new Random(a);
		a=r.nextInt(6)+1;	//그냥a재사용
	
		receiveReward(a);
		dispose();
		
		}else{
			lblChoice.setText("입력 is wrong 기회 is gone");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	}
	
	void receiveReward(int a){		//Item의 location을 select해와서 chickenFrame에 뿌리자
		
	
		Item chicken=dao.selectItem(a);
		
		
		class ChickenFrame extends JFrame {

			private JPanel contentPane;

			/**
			 * Create the frame.
			 */
			public ChickenFrame(String location, String kind) {
				setBackground(Color.WHITE);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setBounds(100, 100, 384, 464);
				contentPane = new JPanel();
				contentPane.setBackground(new Color(255, 255, 255));
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JLabel lblChicken = new JLabel("");
				lblChicken.setHorizontalAlignment(SwingConstants.CENTER);
				lblChicken.setIcon(new ImageIcon(location));
				lblChicken.setBounds(39, 10, 294, 347);
				contentPane.add(lblChicken);
				
				JLabel lblNewLabel = new JLabel(kind+"에 당첨되셨습니다☆");
				lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
				lblNewLabel.setBounds(12, 373, 344, 42);
				contentPane.add(lblNewLabel);
				
				JButton btnNewButton = new JButton("확인");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						StudyFrame frame = new StudyFrame();
						frame.setVisible(true);
						
					}
				});
				btnNewButton.setFont(new Font("함초롬돋움", Font.BOLD, 13));
				btnNewButton.setBounds(293, 373, 63, 42);
				contentPane.add(btnNewButton);
			}//내부클래스의 생성자
		}//내부 local 클래스
		
		ChickenFrame chickFrame=new ChickenFrame(chicken.getLocation(),   chicken.getKind());
		chickFrame.setVisible(true);


	}//receiveReward()
}//class
