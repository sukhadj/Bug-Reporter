package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import dbClient.DBClient;
public class ChangeStatus extends JFrame{

	private String bug_status;
	private String bug_title;
	private int bug_id;
	private JFrame context;
	
	private JLabel currentStatus;
	private JLabel holdCurrentStatus;
	private JLabel changeStatusTo;
	private Font standardFont;
	
	private JButton workingButton;
	private JButton pendingButton;
	private JButton resolvedButton;
	
	DBClient client;
	
	public ChangeStatus(JFrame context,String bug_status, String bug_title,int bug_id) {
		this.bug_status = bug_status;
		this.bug_title = bug_title;
		this.bug_id = bug_id;
		this.context = context;
		
		standardFont = new Font("Arial", Font.BOLD, 15);
	
		currentStatus = new JLabel("Current Status: ");
		currentStatus.setBounds(50, 50, 200, 30);
		currentStatus.setFont(standardFont);
		
		changeStatusTo = new JLabel("Change Status to : ");
		changeStatusTo.setBounds(50, 100, 250, 30);
		changeStatusTo.setFont(standardFont);
		
		holdCurrentStatus = new JLabel(bug_status);
		holdCurrentStatus.setBounds(250,50,200,30);
		holdCurrentStatus.setForeground(get_color());
		holdCurrentStatus.setFont(standardFont);
		
		
		this.setTitle(bug_title);
		this.add(currentStatus);
		this.add(holdCurrentStatus);
		this.add(changeStatusTo);
		this.setTitle(bug_title);
		this.setLayout(null);
		this.setSize(400,400);
		display();
		this.setVisible(true);
		
		
	}
	
	public void display() {
		String status = this.bug_status;
		client = new DBClient();
		
		if(status.equals("Pending")) {
			workingButton = new JButton("Working");
			workingButton.setBounds(50, 150, 100, 30);
			this.add(workingButton);
			
			workingButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					// db function to change status to working
					client.change_bug_status(bug_id,"w");
					dispose();
					context.dispose();
				}
				
			});
			
			resolvedButton = new JButton("Resolved");
			resolvedButton.setBounds(250, 150, 100, 30);
			this.add(resolvedButton);
			
			resolvedButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					// db function to change status to resolved
					client.change_bug_status(bug_id,"r");
					dispose();
					context.dispose();
					
				}
				
			});
			
			
		}
		else if(status.equals("Working")) {
			
			resolvedButton = new JButton("Resolved");
			resolvedButton.setBounds(50, 150, 100, 30);
			this.add(resolvedButton);
			
			resolvedButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					// db function to change status to resolved
					client.change_bug_status(bug_id,"r");
					dispose();
					context.dispose();
				}
				
			});
			
			pendingButton = new JButton("Pending");
			pendingButton.setBounds(250, 150, 100, 30);
			this.add(pendingButton);
			
			pendingButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					// db function to change status to pending
					client.change_bug_status(bug_id,"p");
					dispose();
					context.dispose();
				}
				
			});
			
		}
		else {
		
			pendingButton = new JButton("Pending");
			pendingButton.setBounds(50, 150, 100, 30);
			this.add(pendingButton);
			
			pendingButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					// db function to change status to pending
					client.change_bug_status(bug_id,"p");
					dispose();
					context.dispose();
				}
				
			});
			
			workingButton = new JButton("Working");
			workingButton.setBounds(250, 150, 100, 30);
			this.add(workingButton);
			
			workingButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					// db function to change status to pending
					client.change_bug_status(bug_id,"w");
					dispose();
					context.dispose();
				}
				
			});
			
			
		}
	}
	private Color get_color() {
		if (this.bug_status.equals("Pending"))
			return Color.red;
		else if (this.bug_status.equals("Working"))
			return Color.blue;
		else
			return new Color(0,128,0);
	}
	
	
}

