package model;

public class UserVO {
	
	private String id;
	private String pw;
	private String sex;
	private int age;
	private String create_date; 
	private String update_date; 
	private String login_check;
	private int plan;
	private int oneday;
	private int hint_type;
	private String hint_ans;
	private String hp;
	private String name;
	private int firstplan;
	
	
	public UserVO(String id) {
		super();
		this.id = id;
	}
	

	public UserVO(String id, String pw, String sex, int age,int hint_type, String hint_ans, String hp,
			String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.sex = sex;
		this.age = age;
		this.hint_type = hint_type;
		this.hint_ans = hint_ans;
		this.hp = hp;
		this.name = name;
	}


	public UserVO(String id, int hint_type, String hint_ans) {
		super();
		this.id = id;
		this.hint_type = hint_type;
		this.hint_ans = hint_ans;
	}


	public UserVO(String id, String pw, String sex, int age, int hint_type, String hint_ans, String hp) {
		super();
		this.id = id;
		this.pw = pw;
		this.sex = sex;
		this.age = age;
		this.hint_type = hint_type;
		this.hint_ans = hint_ans;
		this.hp = hp;
	}

	public UserVO(String id, String pw, String sex, int age, String create_date, String update_date, String login_check,
			int plan, int oneday, int hint_type, String hint_ans, String hp, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.sex = sex;
		this.age = age;
		this.create_date = create_date;
		this.update_date = update_date;
		this.login_check = login_check;
		this.plan = plan;
		this.oneday = oneday;
		this.hint_type = hint_type;
		this.hint_ans = hint_ans;
		this.hp = hp;
		this.name = name;
	}
	
	
	public UserVO(String id, String pw, String sex, int age, String create_date, String update_date, String login_check,
			int plan, int oneday, int hint_type, String hint_ans, String hp, String name, int firstplan) {
		super();
		this.id = id;
		this.pw = pw;
		this.sex = sex;
		this.age = age;
		this.create_date = create_date;
		this.update_date = update_date;
		this.login_check = login_check;
		this.plan = plan;
		this.oneday = oneday;
		this.hint_type = hint_type;
		this.hint_ans = hint_ans;
		this.hp = hp;
		this.name = name;
		this.firstplan = firstplan;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getLogin_check() {
		return login_check;
	}
	public void setLogin_check(String login_check) {
		this.login_check = login_check;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
	public int getOneday() {
		return oneday;
	}
	public void setOneday(int oneday) {
		this.oneday = oneday;
	}
	public int getHint_type() {
		return hint_type;
	}
	public void setHint_type(int hint_type) {
		this.hint_type = hint_type;
	}
	public String getHint_ans() {
		return hint_ans;
	}
	public void setHint_ans(String hint_ans) {
		this.hint_ans = hint_ans;
	}


	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}


	public int getFirstplan() {
		return firstplan;
	}


	public void setFirstplan(int firstplan) {
		this.firstplan = firstplan;
	}
	

}
