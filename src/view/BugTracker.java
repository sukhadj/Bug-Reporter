package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dbClient.DBClient;
import security.SHA256;

public class BugTracker extends JFrame {

	private JLabel welcomeHeading;
	private ImageIcon icon;
	
	private JLabel iconHolder;
	private JLabel loginHeader;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private JButton loginButton;
	private JLabel registrationLabel;
	private JButton goToRegisterButton;
	
	private Font standardFont;
	private Font headerFont;
	
	private static DBClient client;
	
	public BugTracker() {
		client = new DBClient();
		headerFont = new Font("Arial", Font.BOLD, 25);
		standardFont = new Font("Arial", Font.BOLD,15);
		
		//Icon
		
		icon = new ImageIcon("/home/sukhad/Workspace/Java/SDLProject_VFinal1/res/image.png");
		iconHolder = new JLabel();
		iconHolder.setIcon(icon);
		iconHolder.setBounds(50, 50, 170, 170);
		//Welcome header
		
		welcomeHeading = new JLabel("Welcome to BugTracker!");
		welcomeHeading.setBounds(220,50, 400, 170);
		welcomeHeading.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeHeading.setBackground(Color.WHITE);
		welcomeHeading.setFont(headerFont);
		welcomeHeading.setOpaque(true);
		
		loginHeader = new JLabel("Existing users, log in here");
		loginHeader.setBounds(100, 250, 400, 40);
		loginHeader.setFont(headerFont);
		
		usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(100, 310, 150, 30);
		usernameLabel.setFont(standardFont);
		
		usernameField = new JTextField();
		usernameField.setBounds(270, 310, 200,30);
		usernameField.setFont(standardFont);
		
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(100, 360, 150, 30);
		passwordLabel.setFont(standardFont);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(270, 360, 200, 30);
		passwordField.setFont(standardFont);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(100, 410, 150, 30);
		loginButton.setFont(standardFont);
		
		JFrame frame = this;
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText().toString();
				String password = passwordField.getText().toString();

				SHA256 sha = new SHA256();
				String pass_hash = sha.getSHA256Hash(password);

				String user_id = client.UserValidate(username, pass_hash);
				if (user_id != null) {
					ProjectCards pCards = new ProjectCards(frame,Integer.parseInt(user_id)); 
					pCards.display();
					

				} else {
					JOptionPane.showMessageDialog(frame, "Incorrect username or password", "Error",
							JOptionPane.ERROR_MESSAGE);
				
				}

			}
			
		});
		
		registrationLabel = new JLabel("New User? ");
		registrationLabel.setBounds(100,480,200,30);
		registrationLabel.setFont(headerFont);
		
		goToRegisterButton = new JButton("Go to Registration form");
		goToRegisterButton.setBounds(100, 550, 200, 40);
		goToRegisterButton.setFont(standardFont);
		
		goToRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrationForm form = new RegistrationForm();
				form.setVisible(true);
			}
		});
		this.add(iconHolder);
		this.add(welcomeHeading);
		this.add(loginHeader);
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(loginButton);
		this.add(registrationLabel);
		this.add(goToRegisterButton);
		this.setTitle("Bug Tracker");
		this.setLayout(null);
		this.setSize(700,700);
		this.setVisible(true);
	}
	
}

