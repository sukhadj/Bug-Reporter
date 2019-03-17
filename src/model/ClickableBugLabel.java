package model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import view.AddBug;
import view.BugView;
public class ClickableBugLabel extends JLabel{

	int bug_id;
	String title;
	int user_id;
	int project_id;
	
	public ClickableBugLabel(String title,int bug_id,int user_id,int project_id) {
		
		super(title,SwingConstants.CENTER);
		
		this.title = title;
		this.bug_id = bug_id;
		
		this.addMouseListener(new MouseAdapter() { 
			//Create new bug view
			public void mouseClicked(MouseEvent me) {
	
				BugView bugView = new BugView(user_id,project_id,bug_id);
			}
			
		});
	}
	
}