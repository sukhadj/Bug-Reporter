package view;

import javax.swing.*;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.demo.charts.pie.PieChart01;


import java.awt.*;
import java.awt.event.*;
public class ViewProject extends JFrame implements ActionListener{

	private  int project_id;
	private  int user_id;
	
	private JLabel projectTitle;
	private JTextArea holdProjectTitle;
	private JLabel projectDescription;
	private JTextArea holdProjectDescription;
	private JButton closeButton;
	private JButton addBugButton;
	private JButton viewBugsButton;
	
	private JButton viewBugStats;
	
	//People working
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public ViewProject(int user_id,int project_id, String project_title, String project_description) {
		
		this.user_id = user_id;
		this.project_id = project_id;
		
		
		projectTitle = new JLabel("Project Title: ");
		projectTitle.setBounds(50,50,180,50);
		projectTitle.setFont(new Font("Arial",Font.BOLD, 20));
		projectTitle.setBackground(new Color(176,226,255));
		projectTitle.setOpaque(true);
		
		
		holdProjectTitle = new JTextArea(project_title);
		holdProjectTitle.setBounds(230,50,300,50);
		holdProjectTitle.setFont(new Font("Arial", Font.BOLD, 20));
		holdProjectTitle.setMargin(new Insets(10,0,0,0));
		holdProjectTitle.setBackground(new Color(176,226,255));
		holdProjectTitle.setEditable(false);
		
		projectDescription = new JLabel("Project Description: ");
		projectDescription.setFont(new Font("Arial",Font.BOLD, 17) );
		projectDescription.setBounds(50,110,250,40);
		
		
		holdProjectDescription = new JTextArea(project_description);
		holdProjectDescription.setLineWrap(true);
		holdProjectDescription.setEditable(false);
		holdProjectDescription.setFont(new Font("Arial",Font.PLAIN, 15));
		holdProjectDescription.setBounds(50,170,400,300);
		holdProjectDescription.setBackground(new Color(210,255,240));
		

		addBugButton = new JButton("Add Bug...");
		addBugButton.setBounds(50,540,120,30);
		addBugButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// call AddBug
				
				// System.out.println("Project id in ViewProject"+project_id);
				AddBug addBug = new AddBug(project_title, user_id,project_id);
			}
			
		});
		
		viewBugsButton = new JButton("View Bugs...");
		viewBugsButton.setBounds(190,540,130,30);
		viewBugsButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BugList bugList = new BugList(user_id,project_id);
			}
			
		});
		
		
		
		
		viewBugStats = new JButton("View Bug Stats");
		viewBugStats.setBounds(50,580,200,30);
		viewBugStats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExampleChart<PieChart> exampleChart = new BugReportPieChart(project_id);
			    PieChart chart = exampleChart.getChart();
			    Thread t = new Thread(new Runnable() {
			        @Override
			        public void run() {
			        	JFrame pieChart =new SwingWrapper<PieChart>(chart).displayChart();       
			        	pieChart.setSize(500, 500);
			        	pieChart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			        }

			       });
			    t.start();
			
			//    System.out.println("Pie chart created");
			}
			
		});
		
		closeButton = new JButton("Close");
		closeButton.setBounds(330,540,120,30);
		
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});

		this.setLayout(null);
		this.add(addBugButton);
		this.add(viewBugsButton);
		this.add(closeButton);
		this.add(viewBugStats);
		this.add(projectTitle);
		this.add(projectDescription);
		this.setSize(600, 650);
		this.add(holdProjectTitle);
		this.add(holdProjectDescription);
		this.setTitle("Project Details");
		this.setVisible(true);
	}

}