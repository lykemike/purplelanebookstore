package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Homepage extends JFrame {

	JPanel top, bot;
	JLabel header;
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
		top = new JPanel();
		bot = new JPanel(new GridLayout(1,1));
		
		header = new JLabel("Purple Lane Bookstore");
		
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
	}
	
	private void addComponent() {
		top.add(header);
		
		bot.add(loginButton);
		bot.add(registerButton);
		
		add(top, BorderLayout.NORTH);
		add(bot, BorderLayout.CENTER);
	}
	
	private void buttonListener() {
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginForm();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RegisterForm();
			}
		});
	}
}
