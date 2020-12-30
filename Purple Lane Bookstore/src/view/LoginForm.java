package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connect.Connect;

public class LoginForm extends JFrame {

	JPanel top, mid, bot;
	JLabel header, labelEmail, labelPassword;
	JTextField txtEmail, txtPassword;
	JButton loginButton;
	Connect connect = new Connect();
	
	public LoginForm() {
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
		mid = new JPanel(new GridLayout(2, 2));
		bot = new JPanel();
		
		header = new JLabel("Login");
		labelEmail = new JLabel("Email: ");
		labelPassword = new JLabel("Password: ");
		
		txtEmail = new JTextField();
		txtPassword = new JPasswordField();
		
		loginButton = new JButton("Login");
	}
	
	private void addComponent() {
		top.add(header);
		
		mid.add(labelEmail);
		mid.add(txtEmail);
		mid.add(labelPassword);
		mid.add(txtPassword);
		
		bot.add(loginButton);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
	
	private void buttonListener() {
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = String.format("SELECT * FROM users WHERE email = ? AND Password = ?");
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				ResultSet resultSet = null;
				try {
					preparedStatement.setString(1, txtEmail.getText());
					preparedStatement.setString(2, txtPassword.getText());
					resultSet = preparedStatement.executeQuery();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				try {
					if(resultSet.first()) {
						JOptionPane.showMessageDialog(null, "Login Success");
						new ProductForm();
						dispose();
					} 
					else if (resultSet.toString().equals("admin@gmail.com")) {
						JOptionPane.showMessageDialog(null, "Admin Login Success");
						new ProductForm();
					}
					else {
						JOptionPane.showMessageDialog(null, "Login Failed");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}
	
}
