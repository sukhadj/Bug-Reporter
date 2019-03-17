package view;

import javax.swing.*;

import dbClient.DBClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddBug extends JFrame implements ActionListener{

	private String project_title;
	private int project_id;
	private int user_id;
	
//	private JLabel 
	private JLabel projectTitle;
	private JLabel holdProjectTitle;
	
	private JLabel titleLabel;
	private JTextField titleField;
	private JLabel osLabel;
	private JComboBox osList;
	
	private JLabel browserLabel;
	private JComboBox browserList;
	private JLabel descriptionLabel;
	private JTextPane descriptionField;
	
	private JButton addButton;
	private JButton cancelButton;
	
	private Font standardFont;
	
	private static final String OS[] = {"Windows 10", "Windows 8", "Windows 7", "Ubuntu 18.04", "Ubuntu 16.04","Fedora 20"};
	private static final String BROWSER[] = {"Firefox", "Chrome", "Opera","NA"};
	
	
	private static DBClient client;
	@Override
	public void actionPerformed(ActionEvent e) {
		//Do stuff	
	}
	
	public AddBug(String project_title,int user_id,int project_id)
	{
		this();
		this.project_title = project_title;
		this.user_id = user_id;
		this.project_id = project_id;
		
//		System.out.println(this.project_id);
		
		//project_id = client.get_project_id(this.project_title);
		//System.out.println(project_id);
		
		projectTitle = new JLabel("Name of project: ");
		projectTitle.setFont(new Font("Arial", Font.BOLD, 15));
		projectTitle.setBounds(50, 50, 160, 30);
		
		holdProjectTitle = new JLabel(this.project_title);
		holdProjectTitle.setFont(new Font("Arial", Font.BOLD, 15));
		holdProjectTitle.setBounds(220, 50, 160, 30);
		
		this.add(projectTitle);
		this.add(holdProjectTitle);
	}

	public AddBug() {
		client = new DBClient();
		standardFont = new Font("Arial", Font.BOLD, 15);
		
		titleLabel = new JLabel("Title of the bug: ");
		titleLabel.setFont(standardFont);
		titleLabel.setBounds(50, 90, 160, 30);
		
		titleField = new JTextField();
		titleField.setBounds(220, 90, 250, 30);
		titleField.setFont(standardFont);
		
		osLabel = new JLabel("Select OS: ");
		osLabel.setBounds(50, 140, 160, 30);
		osLabel.setFont(standardFont);
		
		osList = new JComboBox(OS);
		osList.setFont(standardFont);
		osList.setBounds(220, 140, 160, 30);
		
		browserLabel = new JLabel("Select browser: ");
		browserLabel.setFont(standardFont);
		browserLabel.setBounds(50, 180, 160, 30);
		
		browserList = new JComboBox(BROWSER);
		browserList.setFont(standardFont);
		browserList.setBounds(220, 180, 160, 30);
		
		descriptionLabel = new JLabel("Tell us what is happening:");
		descriptionLabel.setFont(standardFont);
		descriptionLabel.setBounds(50, 230, 300, 30);
		
		descriptionField = new JTextPane();
		descriptionField.setBounds(50, 280, 500, 250);
		
		addButton = new JButton("Add Bug");
		addButton.setBounds(150,550,160,35);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(320,550,160,35);

		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Get projectid from project name, from welcome window
				String title = titleField.getText().toString().trim();
				String description = descriptionField.getText().toString().trim();
				String os = osList.getSelectedItem().toString();
				String browser = browserList.getSelectedItem().toString();
				
				//Logic to add timestamp also	
				//call a function to add a bug
			
				
				client.post_bug(title, os, browser, description, user_id, project_id);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
				dispose();
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		this.add(titleLabel);
		this.add(titleField);
		this.add(osLabel);
		this.add(osList);
		this.add(browserLabel);
		this.add(browserList);
		this.add(descriptionLabel);
		this.add(descriptionField);
		this.add(addButton);
		this.add(cancelButton);
		this.setSize(600, 650);
		this.setLayout(null);
		this.setTitle("Add a new bug");
		this.setVisible(true);
	}	
}