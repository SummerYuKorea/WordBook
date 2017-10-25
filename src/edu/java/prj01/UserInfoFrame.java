package edu.java.prj01;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInfoFrame extends JFrame {

	private JPanel contentPane;
	WordDAOImple dao=WordDAOImple.getInstance();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoFrame frame = new UserInfoFrame();
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
	public UserInfoFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 524);
		contentPane = new JPanel();
		contentPane.setAlignmentY(0.0f);
		contentPane.setAlignmentX(0.0f);
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("사용자 정보");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("휴먼편지체", Font.BOLD, 37));
		label.setBackground(new Color(255, 228, 225));
		label.setBounds(99, 63, 229, 62);
		contentPane.add(label);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		lblID.setBounds(113, 172, 57, 40);
		contentPane.add(lblID);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		lblLevel.setBounds(113, 228, 57, 40);
		contentPane.add(lblLevel);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//dispose();  //이렇게 닫는다. JFrame 상속했으므로
				ItemsFrame frame = new ItemsFrame();
				frame.setVisible(true);
			}
		});
		lblItem.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		lblItem.setBounds(113, 288, 57, 40);
		contentPane.add(lblItem);
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		lblHistory.setBounds(113, 347, 71, 40);
		contentPane.add(lblHistory);
		///////////////////////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblID2 = new JLabel(User.name);
		lblID2.setFont(new Font("함초롬돋움", Font.PLAIN, 19));
		lblID2.setBounds(192, 173, 71, 40);
		contentPane.add(lblID2);
		
		JLabel lblLevel2 = new JLabel("lv "+User.clevel);
		lblLevel2.setFont(new Font("함초롬돋움", Font.PLAIN, 19));
		lblLevel2.setBounds(192, 229, 71, 40);
		contentPane.add(lblLevel2);

		
		JLabel lblItem2 = new JLabel(dao.myItem()+" 개");
		lblItem2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItemsFrame frame = new ItemsFrame();
				frame.setVisible(true);
			}
		});
		lblItem2.setFont(new Font("함초롬돋움", Font.PLAIN, 19));
		lblItem2.setBounds(192, 289, 71, 40);
		contentPane.add(lblItem2);
		
		JLabel lblHistory2 = new JLabel(dao.myHistory()+" 단어");
		lblHistory2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HistoryFrame frame = new HistoryFrame();
				frame.setVisible(true);
			}
		});
		lblHistory2.setFont(new Font("함초롬돋움", Font.PLAIN, 19));
		lblHistory2.setBounds(192, 348, 71, 40);
		contentPane.add(lblHistory2);
	}

}
