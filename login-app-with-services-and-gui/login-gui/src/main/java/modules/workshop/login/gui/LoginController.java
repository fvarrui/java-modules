package modules.workshop.login.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modules.workshop.login.api.Login;
import modules.workshop.login.api.LoginException;

public class LoginController implements Initializable {
	
	// logic
	
	private Login login;

	// model
	
	private StringProperty username = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	
	// view
	
    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField usernameText;

    @FXML
    void onLogin(ActionEvent event) {

		try {
			
			boolean valid = login.login(username.get(), password.get());
			
	    	if (valid) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.initOwner(LoginApplication.primaryStage);
	    		alert.setTitle("Login application");
	    		alert.setHeaderText("User " + username.get() + " logged in!");
	    		alert.show();
	    	} else {
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.initOwner(LoginApplication.primaryStage);
	    		alert.setTitle("Login application");
	    		alert.setHeaderText("Invalid credentials for user " + username.get() + "!");
	    		alert.show();
	    	}
			
		} catch (LoginException e) {
    		e.printStackTrace();
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.initOwner(LoginApplication.primaryStage);
    		alert.setTitle("Login application");
    		alert.setHeaderText("Error");
    		alert.setContentText(e.getMessage());
    		alert.show();
		}
    	
	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// sets bindings
    	username.bind(usernameText.textProperty());
    	password.bind(passwordText.textProperty());    	

		
		// loads login services
		Iterable<Login> loginServices = ServiceLoader.load(Login.class);
		
		// gets the first one
		if (loginServices.iterator().hasNext()) {
			login = loginServices.iterator().next();
			System.out.println("Login service implementation found: " + login.getClass().getCanonicalName());
		}
		
		
	}

	
	
	
}
