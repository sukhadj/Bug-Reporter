package model;

public class BugContainer {
	
	private String bug_title;
	private String bug_description;
	private String bug_os;
	private String bug_browser;
	private String bug_status;
	
	
	public BugContainer(String bug_title, String bug_description, String bug_os, String bug_browser,String bug_status) {
		this.bug_title = bug_title;
		this.bug_description = bug_description;
		this.bug_os = bug_os;
		this.bug_browser = bug_browser;
		this.bug_status = bug_status;
	}
	
	public BugContainer() {
		
	}

	public String getBug_title() {
		return bug_title;
	}

	public void setBug_title(String bug_title) {
		this.bug_title = bug_title;
	}

	public String getBug_description() {
		return bug_description;
	}

	public void setBug_description(String bug_description) {
		this.bug_description = bug_description;
	}

	public String getBug_os() {
		return bug_os;
	}

	public void setBug_os(String bug_os) {
		this.bug_os = bug_os;
	}

	public String getBug_browser() {
		return bug_browser;
	}

	public void setBug_browser(String bug_browser) {
		this.bug_browser = bug_browser;
	}

	public String getBug_status() {
		if(bug_status.equals("p"))
			return "Pending";
		else if(bug_status.equals("w"))
			return "Working";
		else
			return "Resolved";
	}

	public void setBug_status(String bug_status) {
		this.bug_status = bug_status;
	}

	

}

