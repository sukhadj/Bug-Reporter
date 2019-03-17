package model;

public class BugPair {

	private int bug_id;
	private String bug_title;
	
	public BugPair(int bug_id, String bug_title) {
		super();
		this.bug_id = bug_id;
		this.bug_title = bug_title;
	}


	public int getBug_id() {
		return bug_id;
	}


	public void setBug_id(int bug_id) {
		this.bug_id = bug_id;
	}


	public String getBug_title() {
		return bug_title;
	}


	public void setBug_title(String bug_title) {
		this.bug_title = bug_title;
	}
	
	
	
	
	
}
