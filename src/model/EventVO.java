package model;

public class EventVO {
	
	private String restaurant_id;
	private String event;
	private String menu;
	private String dis_price;
	private String update_date;
	public EventVO(String restaurant_id) {
		super();
		this.restaurant_id = restaurant_id;
	}
	public EventVO(String restaurant_id, String event, String menu, String dis_price, String update_date) {
		super();
		this.restaurant_id = restaurant_id;
		this.event = event;
		this.menu = menu;
		this.dis_price = dis_price;
		this.update_date = update_date;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getDis_price() {
		return dis_price;
	}
	public void setDis_price(String dis_price) {
		this.dis_price = dis_price;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	

}
