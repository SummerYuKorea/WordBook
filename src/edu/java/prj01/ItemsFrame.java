package edu.java.prj01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTable;

public class ItemsFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private WordDAOImple dao=WordDAOImple.getInstance();
	private JScrollPane scrollPane;
	
	ArrayList<Item> items= new ArrayList<>();
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemsFrame frame = new ItemsFrame();
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
	public ItemsFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 26, 389, 210);
		contentPane.add(scrollPane);
		
		showItems();//table set
		
		/////////////////////////////////////////////////////////////////////////////////////////
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int row=table.getSelectedRow();//사용자가 선택한 행 			    
				Item chicken=items.get(row);
				
				ChickenFrame chickFrame=new ChickenFrame(chicken.getLocation(),   chicken.getKind());
				chickFrame.setVisible(true);
			 
			}
		});
	}//생성자
	
	private void showItems(){
		
		items=dao.bringMyItem();
		int total_num=dao.myItem();
		
		String top[]={"종류","날짜"};
		String content[][]=new String[total_num][2];
		for(int i=0;i<total_num;i++){
			content[i][0]=items.get(i).getKind();
			 content[i][1]=items.get(i).getDate();

		}
		
		table=new JTable(content,top);
		table.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
	}//showItems()
}//class




//////////////////////chickenFrame//////////////////////////////

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
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("함초롬돋움", Font.BOLD, 13));
		btnNewButton.setBounds(293, 373, 63, 42);
		contentPane.add(btnNewButton);
	}//chickenFrame의 생성자
}//내부 local 클래스