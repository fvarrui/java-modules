module login.gui {

	requires login.api;
	
	requires javafx.controls;
	requires javafx.fxml;
	
	// javafx.graphics module needs reflection accesss to LoginApplication
	// javafx.fxml module needs reflection accesss to LoginController
	opens modules.workshop.login.gui to javafx.graphics, javafx.fxml;

	// 
	uses modules.workshop.login.api.Login;

}