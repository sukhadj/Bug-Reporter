package model;

public class Pair {

	private String title;
	private String description;
	public Pair(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
	public Pair() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
