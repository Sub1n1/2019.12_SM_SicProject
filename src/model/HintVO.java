package model;

public class HintVO {
	
	private int hint_type;
	private String hint_question;
	
	public HintVO(int hint_type, String hint_question) {
		super();
		this.hint_type = hint_type;
		this.hint_question = hint_question;
	}

	public int getHint_type() {
		return hint_type;
	}

	public String getHint_question() {
		return hint_question;
	}

}
