package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dbClient.DBClient;
import model.BugContainer;
import model.Pair;
public class BugView extends JFrame{

	private int user_id;
	private int project_id;
	private int bug_id;
	private String user_role;
	
	
	private String bug_title;
	private String bug_description;
	private String bug_os;
	private String bug_browser;
	private String bug_status;
	
	private static DBClient client;
	
	private JLabel titleLabel;
	private JLabel holdBugTitle;
	private JLabel descriptionLabel;
	private JTextArea holdBugDescription;
	private JLabel osLabel;
	private JLabel holdBugOs;
	private JLabel browserLabel;
	private JLabel holdBugBrowser;
	private JLabel bugStatus;
	private JLabel holdBugStatus;
	
	private Font standardFont;
	private Font bugDescriptionFont;
	private Color skyBlue;
	
	private JButton closeButton;
	private JButton changeStatusButton;
	
	public BugView(int user_id, int project_id, int bug_id) {
		client = new DBClient();
		this.standardFont = new Font("Arial", Font.BOLD, 15);
		this.bugDescriptionFont = new Font("Arial", Font.PLAIN, 14);
		this.skyBlue = new Color(176,226,255);
		
		this.user_id = user_id;
		this.project_id = project_id;
		this.bug_id = bug_id;
		this.user_role = client.get_user_role(user_id);
		
		// System.out.println(user_role);
		
		BugContainer container = client.get_bug_data(bug_id);
		this.bug_title = container.getBug_title();
		this.bug_description = container.getBug_description();
		this.bug_os = container.getBug_os();
		this.bug_browser = container.getBug_browser();
		this.bug_status = container.getBug_status();
		
		titleLabel = new JLabel("Title of Bug: ");
		titleLabel.setBounds(50, 50, 150, 30);
		titleLabel.setFont(standardFont);
		titleLabel.setBackground(skyBlue);
		titleLabel.setOpaque(true);
		
		holdBugTitle = new JLabel(bug_title);
		holdBugTitle.setBounds(200, 50, 300, 30);
		holdBugTitle.setFont(standardFont);
		holdBugTitle.setBackground(skyBlue);
		holdBugTitle.setOpaque(true);

		osLabel = new JLabel("Operating System:");
		osLabel.setBounds(50, 100, 200, 30);
		osLabel.setFont(standardFont);
		osLabel.setBackground(skyBlue);
		osLabel.setOpaque(true);

		holdBugOs = new JLabel(bug_os);
		holdBugOs.setBounds(250, 100, 150, 30);
		holdBugOs.setFont(standardFont);
		holdBugOs.setBackground(skyBlue);
		holdBugOs.setOpaque(true);

		browserLabel = new JLabel("Browser:");
		browserLabel.setBounds(50, 150, 150, 30);
		browserLabel.setFont(standardFont);
		browserLabel.setBackground(skyBlue);
		browserLabel.setOpaque(true);
		
		holdBugBrowser = new JLabel(bug_browser);
		holdBugBrowser.setBounds(200, 150, 150, 30);
		holdBugBrowser.setFont(standardFont);
		holdBugBrowser.setBackground(skyBlue);
		holdBugBrowser.setOpaque(true);
		
		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setBounds(50, 200, 150, 30);
		descriptionLabel.setFont(standardFont);
		descriptionLabel.setBackground(skyBlue);
		descriptionLabel.setOpaque(true);
		
		holdBugDescription = new JTextArea(bug_description);
		holdBugDescription.setBounds(50, 250, 450, 250);
		holdBugDescription.setFont(bugDescriptionFont);
		holdBugDescription.setBackground(new Color(255,253,211));
		holdBugDescription.setEditable(false);
		holdBugDescription.setLineWrap(true);
		
		bugStatus = new JLabel("Status :");
		bugStatus.setBounds(50, 525, 100, 30);
		bugStatus.setFont(standardFont);
		bugStatus.setBackground(skyBlue);
		bugStatus.setOpaque(true);
		
		holdBugStatus = new JLabel(bug_status);
		holdBugStatus.setBounds(150, 525, 150, 30);
		holdBugStatus.setFont(standardFont);
		holdBugStatus.setForeground(get_color());
		holdBugStatus.setBackground(skyBlue);
		holdBugStatus.setOpaque(true);
		
		changeStatusButton = new JButton("Change the status");
		changeStatusButton.setBounds(350, 525, 250, 30);
		changeStatusButton.setFont(standardFont);
		changeStatusButton.addActionListener(new ActionListener() {

		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ChangeStatus changeStatus = new ChangeStatus(BugView.this,bug_status,bug_title,bug_id);
			}
			
		});

		
		
		closeButton = new JButton("Close");
		closeButton.setBounds(50, 600, 120, 30);
		closeButton.setFont(standardFont);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//dispose();
				dispose();
			}
			
		});
		this.add(titleLabel);
		this.add(holdBugTitle);
		this.add(osLabel);
		this.add(holdBugOs);
		this.add(browserLabel);
		this.add(holdBugBrowser);
		this.add(descriptionLabel);
		this.add(holdBugDescription);
		this.add(bugStatus);
		this.add(holdBugStatus);
		this.check_status();
		this.add(closeButton);
		this.setSize(650, 650);
		this.setLayout(null);
		this.setTitle("Bug Description");
		this.setVisible(true);
		
	}

	private Color get_color()
	{
		if(this.bug_status.equals("Pending"))
			return Color.red;
		else if(this.bug_status.equals("Working"))
			return Color.blue;
		else
			return new Color(0,128,0);
	}
	
	private void check_status()
	{
		if(this.user_role.equals("d"))
		{
			this.add(changeStatusButton);
		}
		else
		{
			// dont add
		}
	}
	
}