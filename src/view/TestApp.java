package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbClient.DBClient;
import security.SHA256;

import javax.swing.JButton;

public class TestApp {

	private JFrame frame;
	private JLabel header;
	private JLabel registerNow;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton registerButton;

	private static DBClient client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestApp window = new TestApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestApp() {
		initialize();
		client = new DBClient();
		header = new JLabel("Welcome to BugTracker!");
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setForeground(new Color(153, 50, 204));
		header.setBounds(50, 25, 300, 50);
		// this.setBackground(bgColor);

		registerNow = new JLabel("New User? Register Here");
		registerNow.setFont(new Font("Arial", Font.BOLD, 20));
		registerNow.setBounds(50, 276, 300, 50);

		frame.getContentPane().add(header);
		frame.getContentPane().add(registerNow);
		frame.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(67, 105, 96, 15);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(67, 160, 96, 15);
		frame.getContentPane().add(lblPassword);

		usernameField = new JTextField();
		usernameField.setBounds(181, 103, 169, 25);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(181, 158, 169, 25);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(62, 212, 117, 25);
		frame.getContentPane().add(btnLogin);

	
		btnLogin.addActionListener(new ActionListener() {
			
			
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

		registerButton = new JButton("Register");
		registerButton.setBounds(67, 352, 117, 25);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RegistrationForm form = new RegistrationForm();
				form.setVisible(true);
			}

		});
		frame.getContentPane().add(registerButton);
		// frame.setSize(600,600);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
