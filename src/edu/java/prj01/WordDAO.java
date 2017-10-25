package edu.java.prj01;

import java.util.ArrayList;

public interface WordDAO {

	WordVO[] Level_init();
	
	void updateUser();
	void selectUser();//유저 선택해서 초기화
	

	Item selectItem(int rd);
	void updateItem(Item item);
	
	int insertHistory();//종료직전(히스토리넣고, 유저정보 넘기고, 그다음 종료)
	
	int myHistory();
	int myItem();
	 
	ArrayList<Item> bringMyItem();
	int bringMyHistory(String today, String tomorrow);//			 MM/yy  형태로 줄 것
	ArrayList<WordVO> bringHistoryWords(String today, String tomorrow);
	
	ArrayList<WordVO> bringOptions(WordVO answer); 
	
	void setWrong(int cid);
	
}
