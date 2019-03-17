package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import dbClient.DBClient;
import security.SHA256;

public class RegistrationForm extends JFrame implements ActionListener {

	private JLabel welcomeLabel;

	private JLabel userNameLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JLabel confirmPasswordLabel;
	private JLabel contactLabel;

	private JTextField userNameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField contactField;

	private JButton registerButton;
	private static DBClient client;

	public RegistrationForm() {
		client = new DBClient();

		// change the title
		welcomeLabel = new JLabel("Register Here");
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
		welcomeLabel.setForeground(new Color(153, 50, 204));
		welcomeLabel.setBounds(200, 10, 200, 50);

		userNameLabel = new JLabel("User Name");
		userNameLabel.setBounds(100, 75, 150, 50);

		emailLabel = new JLabel("Email");
		emailLabel.setBounds(100, 115, 150, 50);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 155, 150, 50);

		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setBounds(100, 195, 150, 50);

		contactLabel = new JLabel("Contact No.");
		contactLabel.setBounds(100, 235, 150, 50);

		userNameField = new JTextField();
		userNameField.setBounds(250, 90, 150, 25);

		emailField = new JTextField();
		emailField.setBounds(250, 130, 150, 25);

		passwordField = new JPasswordField();
		passwordField.setBounds(250, 170, 150, 25);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(250, 210, 150, 25);

		contactField = new JTextField();
		contactField.setBounds(250, 250, 150, 25);

		registerButton = new JButton("Register");
		registerButton.setBounds(200, 330, 200, 30);

		registerButton.addActionListener(this);

		this.setSize(600, 450);
		this.setTitle("Registration Form");
		this.setLayout(null);
		this.add(welcomeLabel);
		this.add(userNameLabel);
		this.add(emailLabel);
		this.add(passwordLabel);
		this.add(confirmPasswordLabel);
		this.add(contactLabel);
//        
		this.add(userNameField);
		this.add(emailField);
		this.add(passwordField);
		this.add(confirmPasswordField);
		this.add(contactField);
		this.add(registerButton);
		setVisible(true);
	}

	public void actionPerformed(java.awt.event.ActionEvent ev) {
		String error_message = "No error";
		String username = userNameField.getText().toString().trim();
		String email = emailField.getText().toString().trim();
		String password = passwordField.getText().toString().trim();
		String confirmPassword = confirmPasswordField.getText().toString().trim();
		int contact_number = 0;
		String contact = contactField.getText().toString().trim();
		// Will have to validate later on
		error_message = validate(username, email, password, confirmPassword, contact);
		// System.out.println("Validate function's response: " + flag );
		if (!error_message.equals("Null")) {
			JOptionPane.showMessageDialog(this, error_message, "Error", JOptionPane.ERROR_MESSAGE);

		} else {

			contact_number = Integer.parseInt(contact);

			SHA256 sha = new SHA256();
			String pass_hash = sha.getSHA256Hash(password);

			boolean success = client.UserRegister(username, pass_hash, email, contact_number);

			if (success) {
					dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	public String validate(String username, String email, String password, String confirmPassword, String contact) {
		String error_message = "Null";
		// Any field is not null
		if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
				|| contact.isEmpty()) {

			error_message = "No fields must be empty";
			return error_message;
		}

		// username is in all small like your dick
		if (!isLowerCase(username)) {
			error_message = "Username should be only in lower case";
			return error_message;
		}

		// check for unique username
		if (!client.unique_username(username)) {
			error_message = "Username already exists.Choose another username";
			return error_message;
		}

		if (!password.equals(confirmPassword)) {
			// Throw an error dialog box saying that email and password dont match
			error_message = "Passwords don't match";
			return error_message;
		}

		// Password length check
		if (password.length() < 3) {
			error_message = "Password must be of atleast 3 characters";
			return error_message;
		}

		// email validator

		// contact number
		try {
			Integer.parseInt(contact);
		} catch (Exception e) {
			error_message = "Not a valid contact number -- Parse error";
			return error_message;
		}

		// contact number of 10 digits
		if (contact.length() != 5) {
			error_message = "Not a valid contact number";
			return error_message;
		}

		return error_message;
	}

	private boolean isLowerCase(String string) {
		return string.toLowerCase().equals(string);

	}

}