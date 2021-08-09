package model;

public class RestaurantVO {
	
	private String id;
	private String pw;
	private String address;
	private String tel;
	private String login_check;
	private String create_date;
	private String update_date;
	private String name;
	private String image;
	
	public RestaurantVO(String id) {
		super();
		this.id = id;
	}
	

	public RestaurantVO(String id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}


	public RestaurantVO(String id, String pw, String address, String tel, String login_check, String create_date,
			String update_date, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.address = address;
		this.tel = tel;
		this.login_check = login_check;
		this.create_date = create_date;
		this.update_date = update_date;
		this.name = name;
	}

	

	public RestaurantVO(String id, String pw, String address, String tel, String login_check, String create_date,
			String update_date, String name, String image) {
		super();
		this.id = id;
		this.pw = pw;
		this.address = address;
		this.tel = tel;
		this.login_check = login_check;
		this.create_date = create_date;
		this.update_date = update_date;
		this.name = name;
		this.image = image;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLogin_check() {
		return login_check;
	}

	public void setLogin_check(String login_check) {
		this.login_check = login_check;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	

}
