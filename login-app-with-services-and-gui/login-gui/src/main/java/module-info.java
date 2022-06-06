module login.gui {

	requires login.api;
	
	requires javafx.controls;
	requires javafx.fxml;
	
	// javafx.graphics module needs reflection access to LoginApplication
	// javafx.fxml module needs reflection access to "LoginController" as it's referenced in "Login.fxml"
	opens modules.workshop.login.gui to javafx.graphics, javafx.fxml;

	// needs at least one module which provides an implementation of "Login" interface (e.g. "login-service-impl") 
	// [this totally decouple the "Login" implementation from the GUI] 
	uses modules.workshop.login.api.Login;

}