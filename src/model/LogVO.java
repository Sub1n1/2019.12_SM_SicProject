package model;

public class LogVO {
	
	private String user_id;
	private String use_date;
	private String usage;
	private int price;
	private int leftover;
	
	
	public LogVO(String user_id) {
		super();
		this.user_id = user_id;
	}

	public LogVO(String user_id, String use_date, String usage, int price, int leftover) {
		super();
		this.user_id = user_id;
		this.use_date = use_date;
		this.usage = usage;
		this.price = price;
		this.leftover = leftover;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUse_date() {
		return use_date;
	}

	public void setUse_date(String use_date) {
		this.use_date = use_date;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getLeftover() {
		return leftover;
	}

	public void setLeftover(int leftover) {
		this.leftover = leftover;
	}
	

}
