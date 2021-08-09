package model;

public class RstrName {
	private String rstrName;
	private String imgName;

	public RstrName() {
		super();
	}

	public RstrName(String rstrName, String imgName) {
		super();
		this.rstrName = rstrName;
		this.imgName = imgName;
	}

	public String getRstrName() {
		return rstrName;
	}

	public void setRstrName(String rstrName) {
		this.rstrName = rstrName;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
}