package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dbClient.DBClient;
import view.ViewProject;

public class Card extends JPanel {

	private JLabel titleField;
	private JTextArea descriptionField;
	private JButton viewButton;
	private int project_id;
	private int user_id;
	private String title;
	private String description;
	private static DBClient client;
	
	private Color skyBlue;
	public Card() {
		client = new DBClient();
	}
	
	public Card(int user_id,String title, String description) {
		client = new DBClient();
		this.skyBlue = new Color(176,226,255);
		this.title = title;
		this.description = description;
		
		project_id = client.get_project_id(title);
		
		titleField = new JLabel(title, SwingConstants.CENTER);
		
		titleField.setBackground(new Color(0,255,255));
		titleField.setBounds(20,20,200,25);
		titleField.setFont(new Font("Arial", Font.BOLD, 20));
		titleField.setBackground(skyBlue);
		
		titleField.setOpaque(true);
		
		descriptionField = new JTextArea(description);
		descriptionField.setBounds(20,50, 250,100);
		descriptionField.setEditable(false);
		descriptionField.setLineWrap(true);
		
		viewButton = new JButton("View Project...");
		viewButton.setBounds(20,20, 250,30);
		viewButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				ViewProject viewProject = new ViewProject(user_id,project_id,getTitle(),client.get_description(project_id));
//				context.dispose();
			}
			
			
		});
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 0;
		c1 = padTitle(c1);
		c1.ipady = 40;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 1;
		c2.ipady = 150;
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 2;
		c3.ipady = 20;
		c3.ipadx = 115;
		this.add(viewButton, c3);
		this.add(titleField,c1);
		this.add(descriptionField,c2);
		this.setSize(200,200);
		this.setVisible(true);
		
	}
	
	public GridBagConstraints padTitle(GridBagConstraints c) {
		String t = this.title;
		int difference = 0;
		if(t.length() < 25) {
			difference = 25 - t.length();
			c.ipadx = (difference*10);
		}
		return c;
	}
	
	
	
	
	public String getTitle() {
		return this.title;
	}
	
	
	
}