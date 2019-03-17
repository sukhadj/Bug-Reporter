package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import dbClient.DBClient;
import model.ClickableBugLabel;
import model.BugPair;



public class BugList extends JFrame{

	private int user_id;
	private int project_id; 
	private ArrayList<BugPair> bugList;
	private ArrayList<ClickableBugLabel> labelList;
	
	
	private JButton addNewBug;
	private JLabel  windowTitle;
	private JPanel panel;
	private JScrollPane scroller;
	private Font standardFont;
	public BugList(int user_id,int project_id)
	{
		this();
		standardFont = new Font("Arial", Font.BOLD, 15);
		this.user_id = user_id;
		this.project_id = project_id;
	
		DBClient client = new DBClient();
		bugList = client.get_bug_list(this.project_id);
		
		
		
		labelList = new ArrayList<ClickableBugLabel>();
		setLabels();
		
		for(ClickableBugLabel label : labelList)
		{
			label.setFont(standardFont);
			this.add(label);
		}
		
		
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public BugList()
	{
		
		windowTitle = new JLabel("List of bugs",SwingConstants.CENTER);
		windowTitle.setBounds(50,10,400,30);
		windowTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		
		addNewBug = new JButton("Add a new Bug.."); 
		addNewBug.setBounds(150, 460, 200, 30);
		addNewBug.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddBug addBug = new AddBug(new DBClient().get_project_title(project_id), user_id,project_id);
				dispose();
			}
			
		});		
		
		this.add(windowTitle);
		this.add(addNewBug);
		this.setTitle("List of Bugs");
		this.setSize(500, 500);
		
	}
	
    void setLabels()
    {
    	int bug_id;
    	String bug_title;
    	ClickableBugLabel label;
    	
    	int i = 1;
    	
    	int x =50; 
    	int y = 40;
    	
    	for(BugPair bug : bugList)
    	{
    		bug_id = bug.getBug_id();
    		bug_title = bug.getBug_title();
    		
    		label = new ClickableBugLabel(Integer.toString(i)+". "+bug_title,bug_id,user_id,project_id);
    		label.setBounds(x, y, 400, 30);
    		label.setBackground(Color.cyan);
    		label.setOpaque(true);
    		labelList.add(label);
    		
    		y+=40;
    		i++;
    	}
    }
}
