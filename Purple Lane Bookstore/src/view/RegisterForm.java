package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.*;

import connect.Connect;

public class RegisterForm extends JFrame {

	JPanel top, mid, bot;
	JLabel header, labelEmail, labelUsername, labelPassword;
	JTextField txtEmail, txtUsername, txtPassword;
	JButton registerButton;
	Connect connect = new Connect();
	
	public RegisterForm() {	
		initialize();	
		addComponent();
		buttonListener();
		setSize(700, 200);	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(3, 3));
		bot = new JPanel();	
		header = new JLabel("Register Form");
		
		labelEmail = new JLabel("Email: ");
		labelUsername = new JLabel("Username: ");
		labelPassword = new JLabel("Password: ");
		
		txtEmail = new JTextField();
		txtUsername = new JTextField();
		txtPassword = new JTextField();
		
		registerButton = new JButton("Register");
	}

	private void addComponent() {
		top.add(header);
		
		mid.add(labelEmail);
		mid.add(txtEmail);
		mid.add(labelUsername);
		mid.add(txtUsername);
		mid.add(labelPassword);
		mid.add(txtPassword);
		
		bot.add(registerButton);

		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}

	public void buttonListener() {
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				
				String query = String.format("Insert INTO users VALUES(null, ?, ?, ?)");
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				try {
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, username);
					preparedStatement.setString(3, password);
					preparedStatement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Register Successfull!!");
					new LoginForm();
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}

}
