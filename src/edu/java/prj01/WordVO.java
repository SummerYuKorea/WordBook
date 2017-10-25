package edu.java.prj01;

public class WordVO {

	private int cid;
	private String word_class;
	private String word;
	private String meaning;
	private String example;
	private int wrong;
	
	public WordVO(){}
	public WordVO(int cid, String word_class, String word, String meaning, String example, int wrong) {
		super();
		this.cid = cid;
		this.word_class = word_class;
		this.word = word;
		this.meaning = meaning;
		this.example = example;
		this.wrong =wrong;
	}
	

	public int getCid() {
		return cid;
	}
	public String getWord_class() {
		return word_class;
	}
	public String getWord() {
		return word;
	}
	public String getMeaning() {
		return meaning;
	}
	public String getExample() {
		return example;
	}
	public int getWrong() {
		return wrong;
	}
	@Override
	public String toString() {			//나중에 println으로 확인용으로 쓰려고
		String str= String.format(""
				+ "cid: %d\n"
				+ "품사: %s"
				+ "단어: %s"
				+ "의미: %s"
				+ "예문: %s", cid,word_class, word, meaning, example);
		return str;
		}
}//class WordVO

class User{///근데 결국 하나만 만들거면 String name 자시고 필요없나?->ㅇㅇㅇ!!!그런듯!!! 이해쫌열심히 하길
	static int cid=1;
	static String name="한결";
	static int clevel=1;
	static int status=1;//진도 //총 몇 개  ->100되면 레벨업!
	static int test_status=0;//0~5. 5되면 레벨 up
//items, history는 user에 등록하지 않고
	//각각의 클래스에다가 user를 등록(걔속추가go)
	
	//////////////////////////이거는 싱글턴이 아닌거 같어?아냐 싱글턴맞. ? 단하나존재하긴 하는데 바꿀 수 있.
	//음 set 해서 바꿔줘야 안정한가?(encapsulation)
	private static User instance=
			new User(name,clevel,status,test_status);	
	
	private User (String name, int clevel, int status,int test_status){
		User.name=name;
		User.clevel=clevel;
		User.status=status;
		User.test_status=test_status;
	}
	
	public User getInstance(){
		return instance; //나중에 메인에서 받을때 me 라고 하자
	}
}


class Item{
	private int cid;  //primary key 아이템 고유의.
	private String kind;//Chicken,pizza,sushi,candy
	private int takenby;//0-> 배당 안 됨. 쓸 수 있.
	private String location;
	private String date;
	
	//생성자
	
public int getCid() {
		return cid;
	}	
public String getKind() {
		return kind;
	}
public int getTakenby() {
		return takenby;
	}
public String getLocation() {
		return location;
	}
public String getDate() {
		return date;
	}

public Item(int cid, String kind, int takenby, String location, String date) {
		this.cid = cid;	
		this.kind = kind;
		this.takenby= takenby;	
		this.location=location;
		this.date=date;
		
	}

}

class History{  //내부 클래스로 만들어도 될 것 같은데~(?)
	int userid;
	int how_many_word;
	int user_status;
	String date;
	

	//생성자
	History(int userid, int how_many_word, int user_status, String date){		
		this.userid=userid;
		this.how_many_word=how_many_word;
		this.user_status=user_status;
		this.date=date;
	}
	
	
	
}
