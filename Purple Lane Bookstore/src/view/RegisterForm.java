package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import connect.Connect;
import controller.UserController;

public class RegisterForm extends View {

	JPanel top, mid, bot;
	JLabel header, labelEmail, labelUsername, labelPassword;
	JTextField txtEmail, txtUsername, txtPassword;
	JButton registerButton;
	Connect connect = new Connect();
	
	public RegisterForm() {	
		super();
		this.width = 700;
		this.height = 200;
	}

	@Override
	public void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(3, 3));
		bot = new JPanel();	
		header = new JLabel("Register Form");
		
		labelEmail = new JLabel("Email: ");
		labelUsername = new JLabel("Username: ");
		labelPassword = new JLabel("Password: ");
		
		txtEmail = new JTextField();
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		
		registerButton = new JButton("Register");
		
	}

	@Override
	public void addComponent() {
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

	@Override
	public void addListener() {
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				UserController.getInstance().insert(email, username, password);
				}
		});
	}
	
}


