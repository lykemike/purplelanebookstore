package model;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import view.ProductForm;

public class UserModel extends Model {

	private Integer id;
	private String email;
	private String username;
	private String password;
	
	public UserModel() {
		this.tableName = "Users";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void insert() {
		String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?)", tableName);
		java.sql.PreparedStatement preparedStatement = connect.prepareStatement(query);
		
		try {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getUserId() {
		String query = String.format("SELECT * FROM users WHERE email = ? AND Password = ?");
		java.sql.PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = null;
		try {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		try {
			if(resultSet.first()) {
				JOptionPane.showMessageDialog(null, "Login Success");
				new ProductForm();
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
		return null;
		
}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Model> getAll() {
		return null;
	}

}
