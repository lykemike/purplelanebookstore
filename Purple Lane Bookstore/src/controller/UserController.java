package controller;

import java.util.Vector;

import model.Model;
import model.UserModel;
import view.View;

public class UserController extends Controller {

	private UserModel userModel;
	private static UserController controller;
	
	public UserController() {
		userModel = new UserModel();
	}

	public static UserController getInstance() {
		return controller = (controller == null) ? new UserController() : controller;
	}
	
	public void insert(String email, String name, String password) {
		UserModel userModel = new UserModel();
		userModel.setEmail(email);
		userModel.setUsername(name);
		userModel.setPassword(password);
		userModel.insert();
	}
	
	public void getUserId(String email, String password) {
		UserModel userModel = new UserModel();
		userModel.setEmail(email);
		userModel.setPassword(password);
		userModel.getUserId();
	}
	
	public Integer getUserId(String email) {
		UserModel userModel = new UserModel();
		userModel.setEmail(email);
		return userModel.getUserId();
	}

	@Override
	public View view() {
		return null;
	}

	@Override
	public Vector<Model> getAll() {
		return null;
	}
	
}
