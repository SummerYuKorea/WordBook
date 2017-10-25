package edu.java.prj01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class WordDAOImple implements WordDAO {
	
	public static final int WORD100=15;
	
	
	public static final String URL="jdbc:oracle:thin:@192.168.11.5:1521:xe";
	public static final String USER="scott";
	public static final String PASSWD="tiger";
	///////////////SQL!/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//static final 왜 붙여야할까//cid없앨까?VO에서
	static final String SQL_SELECT100= "select * from WORDS where cid>? and  cid <=?";//레벨업시
	static final String SQL_SEL_USER="select * from USERS where cid=?";//시작시&종료시 전status확인할때
	static final String SQL_SEL_ITEM="select * from ITEMS where takenby=0 and cid=? ";//상품탈때
	static final String SQL_UPDATE_ITEM=	"update ITEMS set takenby =? , "
			+ "today=sysdate  where cid=?";
	static final String SQL_UPDATE_USER="update USERS "//종료시.
			+ "set name=?,  "
			+ "clevel=?, "
			+ "status=?, "
			+ "test_status=? "
			+ "where cid=?";
	
	
	static final String SQL_INSERT_HISTORY="insert into HISTORY values(?,?,?,sysdate)";
	//userid, how_many_word, today(현재시간넣기)
	
	//누구 아이템 가져올지 정해야함
	static final String SQL_ALL_MY_ITEMS=	" select count(cid) from items where takenby=?";
	static final String SQL_BRING_MY_ITEMS="select * from ITEMS where takenby =?  ";
	//누구 히스토리 가져올지
	static final String SQL_MY_HISTORY="select sum(how_many_word) from history where userid=? ";
	static final String SQL_BRING_MY_HISTORY=" select sum(how_many_word) from history "
			+ "where userid=? and  today  >= to_date(?) and today < to_date(?)";
			//오라클의 to_date라는 기능 이용해보자
	static final String SQL_BRING_HISTORY_STATUS="select (user_status-how_many_word), user_status from history "
			+ "where userid=? and today >= to_date(?) and today < to_date(?)";
	static final String SQL_BRING_HISTORY_WORDS=" select * from words where cid>=? and  cid<? ";
	
	static final String SQL_OPTIONS="select *  from "
			+ "(select * from words  where cid!=? and word_class=?"
			+ " order by dbms_random.value )"
			+ " where rownum<=3";
	
	static final String SQL_SET_WRONG="update words set wrong=1 where cid=?";
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	int former_status=1;
	
	//여기서 DB 연결 자시고~
	private Connection conn=null;
	private PreparedStatement ppst=null;
	private ResultSet rs=null;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//싱글턴 go  ==> 싱글턴 왜하더라? 할때마다 빡~와야지~
	private static WordDAOImple instance=new WordDAOImple();
	private WordDAOImple(){
		try {
			DriverManager.registerDriver(new OracleDriver() );  //import안하고적은것. register부분 부터 안되네?(메소드 바깥에서 해서.) oracledriver부터안됨
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static WordDAOImple getInstance(){
		return instance;
	}
	//잘햇는지 후에 확인ㄱ	

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public WordVO[] Level_init() {
		WordVO[] list = new WordVO[WORD100]; // ArrayList 안 사용 중
		WordVO vo=null;
		int i=0;
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWD);  //얘도 반복 되는 부분 
																																							//근데 다른메소드쓸때닫아버려서
			ppst = conn.prepareStatement(SQL_SELECT100);			//	닫혀있을수있기때문에 여기다매번하는게 나음
			ppst.setInt(1, (User.clevel -1) * WORD100);
			ppst.setInt(2, (User.clevel) * WORD100);
			rs = ppst.executeQuery();

			while (rs.next()) {
				int cid = rs.getInt(1);
				String word_class = rs.getString(2);
				String word = rs.getString(3);
				String meaning = rs.getString(4);
				String example = rs.getString(5);
				int wrong=rs.getInt(6);
				vo = new WordVO(cid, word_class, word, meaning, example,wrong);
				list[i] = vo;
				i++;
			} // while
		
		}catch(Exception e){}
				
		return list;

	}//Level_init
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override		//==========> 하다가. WordVO에서 싱글턴으로 하기로 결심함
	public void selectUser() {		//얘그냥 확 맨처음 생성자로 넘겨버려????!
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);  
			ppst=conn.prepareStatement(SQL_SEL_USER);
			ppst.setInt(1, 1);
			rs=ppst.executeQuery();
			
			rs.next();		///////// 한번 불러줘야지!!!!
			User.cid=rs.getInt(1);
			User.name=rs.getString(2);
			User.clevel=rs.getInt(3);
			User.status=rs.getInt(4);
			User.test_status=rs.getInt(5);
			
			
			former_status=rs.getInt(4);	//여기도 저장해놓는거야
			//System.out.println("former_status: "+former_status);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResources(conn, ppst, rs);
		}
	}
		
		
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@Override
	public Item selectItem(int a) {
		 Item item=null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);  
			ppst=conn.prepareStatement(SQL_SEL_ITEM);
			ppst.setInt(1, a);
			rs=ppst.executeQuery();
			rs.next();
			
			if(rs==null){//선택안되면 꽝 리턴
				ppst.setInt(1, 1);
				rs=ppst.executeQuery();
				rs.next();
				
			}
			item=new Item(rs.getInt("cid"),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
				//이거 컬럼 이름으로도 할 수 있다며?어떻게 하더라.  "cid" 로 함~
		    //int cid, String kind, int takenby, String location, String date
		    
			updateItem(item);				//선택된순간 update까지 한번에 ㄱ
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, ppst, rs);
		}
		return item;		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void updateItem(Item item) {
			

			try {
				conn = DriverManager.getConnection(URL, USER, PASSWD);  
				ppst=conn.prepareStatement(SQL_UPDATE_ITEM);
				if (item.getCid() != 1) {
					ppst.setInt(1, User.cid);
					ppst.setInt(2, item.getCid());
					ppst.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeResources(conn, ppst);
			}
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void updateUser() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);  
			ppst=conn.prepareStatement(SQL_UPDATE_USER);
			ppst.setString(1, User.name);
			ppst.setInt(2, User.clevel);
			ppst.setInt(3, User.status);
			ppst.setInt(4, User.test_status);
			ppst.setInt(5, User.cid);
		    		    
			ppst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, ppst);
		}
		
	}//updateUser
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int insertHistory() {//selectUser한 번 부른다음에 불러야하는 메소드!!( former_status 때문에)
		int result =0;
		//System.out.println("ll::"+User.status);
		if ((User.status - former_status) != 0) {
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWD);
				ppst = conn.prepareStatement(SQL_INSERT_HISTORY);
				ppst.setInt(1, User.cid);
				ppst.setInt(2, User.status - former_status);// 오늘 본단어갯수에서 전에 저장된
				ppst.setInt(3, User.status);
															// 단어갯수 뺌.
															// selectUser한 번
															// 부른다음에 불러야하는 메소드!!

				result = ppst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, ppst);
			}
		}
		return result;
		
	}//insertHistory
	
	@Override
	public int myHistory() {
		int history_num=0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);  
			ppst=conn.prepareStatement(SQL_MY_HISTORY);
			ppst.setInt(1, User.cid);
			rs=ppst.executeQuery();
		    rs.next();
		    
			history_num=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, ppst, rs);
		}
		return history_num;
	}
	@Override
	public int myItem() {
		int item_num=0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);  
			ppst=conn.prepareStatement(SQL_ALL_MY_ITEMS);
			ppst.setInt(1, User.cid);
			rs=ppst.executeQuery();
		    rs.next();
		    
			item_num=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, ppst, rs);
		}
		return item_num;
	}
	@Override
	public ArrayList<Item> bringMyItem() {
		ArrayList<Item> items=new ArrayList<>();
		Item item=null;
		int i=0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);  
			ppst=conn.prepareStatement(SQL_BRING_MY_ITEMS);
			ppst.setInt(1, User.cid);
			rs=ppst.executeQuery();
		    
			while(rs.next()){
				//cid, kind,takenby, location, date
				item=new Item(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
				items.add(item);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, ppst, rs);
		}
		return items;
	}
	@Override
	public int bringMyHistory(String today, String tomorrow) {  //해당 날짜마다 각자 불러서 그 날의 단어 갯수를 리턴
//		ArrayList<History> history_all=null;		
//		History history_oneday=null;

		int word_num=0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);  
			ppst=conn.prepareStatement(SQL_BRING_MY_HISTORY);
			ppst.setInt(1,User.cid);
			ppst.setString(2, "2017/"+today);
			ppst.setString(3, "2017/"+tomorrow);
		
			rs = ppst.executeQuery();
			
//			rs.next();
//			word_num=rs.getInt(1);	System.out.println(rs.getInt(1));
			if(rs.next() ) {word_num=rs.getInt(1);	}//널일 경우 초기값 0으로 리턴되겠지.
			else {System.out.println("no records...");}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, ppst, rs);
		}
		return word_num;
	}//bringMyHistory
	
	@Override
	public ArrayList<WordVO> bringHistoryWords(String today, String tomorrow) {
		ArrayList<WordVO> words=new ArrayList<>();
		WordVO vo=null;
		
		int[][] status=new int[100][2]; //해당 history 데이타의 시작 status, 종료 status 넣을 것 //프로그램 하루에 백번이상 접속하면 히스토리에서 못읽어옴 it's okay
		int i=0; //index번호로쓸것.
		PreparedStatement ppst2 =null;
		ResultSet rs2=null; //words 받아올 rs
		
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			ppst=conn.prepareStatement(SQL_BRING_HISTORY_STATUS);
			ppst.setInt(1,User.cid);
			ppst.setString(2, "2017/"+today);
			ppst.setString(3, "2017/"+tomorrow);
			rs=ppst.executeQuery();
			
			while(rs.next()){
				status[i][0]=rs.getInt(1);	//시작status
				status[i][1]=rs.getInt(2);	//종료status
				
				ppst2=conn.prepareStatement(SQL_BRING_HISTORY_WORDS);
				ppst2.setInt(1, status[i][0]);
				ppst2.setInt(2, status[i][1]);
				rs2=ppst2.executeQuery();
				
				while(rs2.next()){
					vo=new WordVO(rs2.getInt("cid"), rs2.getString("word_class"),rs2.getString("word"), rs2.getString("meaning"), rs2.getString("example"), rs2.getInt("wrong"));
					words.add(vo);
					
				}//작은 while
			}//큰while
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, ppst, rs);
		}
		
		
		
		return words;
	}//bringHistoryWords()
	
	///////////////////////////////////
	@Override
	public ArrayList<WordVO> bringOptions(WordVO answer) {
		
		ArrayList<WordVO> options=new ArrayList<>();	//null이라고 초기화?  불가하지 뉴 어레이리스트 줘야지.배열아님
		WordVO vo=null;
		//System.out.println(answer.getCid());
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			ppst= conn.prepareStatement(SQL_OPTIONS);
			ppst.setInt(1, answer.getCid());
			ppst.setString(2, answer.getWord_class());
			rs=ppst.executeQuery();
			
			while(rs.next()){				
				vo=new WordVO(rs.getInt("cid"), rs.getString("word_class"), rs.getString("word"), rs.getString("meaning"), rs.getString("example"),rs.getInt("wrong"));
				options.add(vo);
			}
			
			options.add(answer);		//4번째자리에 정답 넣는중
			////////////////////////////////////////
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResources(conn, ppst, rs);
		}
			
		return options;
	}//bringOptions()
	
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setWrong(int cid){
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			ppst=conn.prepareStatement(SQL_SET_WRONG);
			ppst.setInt(1, cid);
			ppst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void closeResources(Connection conn, Statement stmt){///stmt 자리에 PreparedStatement도 받을 수 있다. 상속 관계
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}//close
	
	private void closeResources(Connection conn, Statement stmt,ResultSet rs){///stmt 자리에 PrepareStatement도 받을 수 있다. 상속 관계
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}//close
	
	private void closeResources(Connection conn, Statement stmt,
			Statement stmt2, ResultSet rs, ResultSet rs2){///stmt 자리에 PrepareStatement도 받을 수 있다. 상속 관계
		try {
			rs2.close();
			stmt2.close();
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}//close


}//class
