package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Homepage extends JFrame {

	JPanel top, bot;
	JLabel header, header2;
	JButton loginButton, registerButton;
	
	public Homepage() {
		intialize();
		addComponent();
		buttonListener();
		setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}

	private void intialize() {
		top = new JPanel(new GridLayout(2, 1));
		bot = new JPanel(new GridLayout(1,1));
		
		header = new JLabel("Welcome To", SwingConstants.CENTER);
		header2 = new JLabel("Purple Lane Bookstore", SwingConstants.CENTER);
		header.setFont(new Font("Verdana", Font.PLAIN, 20));
		header2.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
	}
	
	private void addComponent() {
		top.add(header);
		top.add(header2);
		
		bot.add(loginButton);
		bot.add(registerButton);
		
		add(top, BorderLayout.NORTH);
		add(bot, BorderLayout.CENTER);
	}
	
	private void buttonListener() {
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginForm();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterForm();
			}
		});
	}
}
