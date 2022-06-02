package modules.workshop.login.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApplication extends Application {
	
	public static Stage primaryStage;


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoginApplication.primaryStage = primaryStage;

		FXMLLoader loader = new FXMLLoader(LoginApplication.class.getResource("/fxml/Login.fxml"));
		loader.load();

		primaryStage.setTitle("Login application");
		primaryStage.setScene(new Scene(loader.getRoot()));
		primaryStage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
