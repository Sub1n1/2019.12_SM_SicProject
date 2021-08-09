package model;

public class MenuVO {
	
	private String restaurant_id;
	private String menu_name;
	private int price;
	private String image;
	private int sum;
	
	public MenuVO(String restaurant_id) {
		super();
		this.restaurant_id = restaurant_id;
	}
	
	public MenuVO(String restaurant_id, String menu_name, int price) {
		super();
		this.restaurant_id = restaurant_id;
		this.menu_name = menu_name;
		this.price = price;
	}
	
	public MenuVO(String restaurant_id, String menu_name, int price, String image) {
		super();
		this.restaurant_id = restaurant_id;
		this.menu_name = menu_name;
		this.price = price;
		this.image = image;
	}
	
	
	
	public MenuVO(String restaurant_id, String menu_name, int price, String image, int sum) {
		super();
		this.restaurant_id = restaurant_id;
		this.menu_name = menu_name;
		this.price = price;
		this.image = image;
		this.sum = sum;
	}

	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
