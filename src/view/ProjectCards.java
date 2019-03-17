package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import dbClient.DBClient;
import model.Card;
import model.Pair;

public class ProjectCards extends JFrame{

	private Card card_list[];
	
	private ArrayList<Pair> data;
	private int user_id;
	private static DBClient client;
	private int card_count;
	
	
	public ProjectCards(JFrame context,int user_id) {
		
		context.dispose();
		client = new DBClient();
		
		this.user_id = user_id;
		data = client.get_projects(user_id);
		
		card_count = extract_data(data);		
		
	}
	
	public int extract_data(ArrayList<Pair> data) {
		
		int length = data.size();
		card_list = new Card[length];
		Pair project;
		String title;
		String description;
		
		for(int i = 0; i < length; i++) {
			project = data.get(i);
			title = project.getTitle();
			description = project.getDescription();
			String trimmedDescription = "";
			try {
				trimmedDescription = description.substring(0,101) +"\n\nRead More...";
			}
			catch(Exception e) {
				trimmedDescription = description + "\n\nRead More...";
				// e.printStackTrace();
			}
			finally {
				Card tempCard = new Card(this.user_id,title,trimmedDescription);
				card_list[i] = tempCard;
			}
			
		}
		
		return length;
	}
	
	public void display() {
		if(card_count <= 4) {
			this.setSize(700,700);
			if(card_count <= 2) {
				this.setSize(700,500);
				this.setLayout(new GridLayout(1,card_count));
			}
			else {
				this.setLayout(new GridLayout(2,2));
			}
		}
		else {
			this.setSize(2000,700);
			this.setLayout(new GridLayout(2,3));
		}
		
		for(int i = 0; i < card_count; i++) {
			this.add(card_list[i]);
		}
		this.setTitle("Your Projects");
		this.setVisible(true);
	}
	
	
}