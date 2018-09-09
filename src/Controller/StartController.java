package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class StartController implements Initializable{
	
	@FXML
	private Button bbb;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void nextWindow(ActionEvent event) throws IOException {
	
		Parent root = FXMLLoader.load(getClass().getResource("/View/Choice.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		
		Main.window.setScene(scene);
		Main.window.centerOnScreen();
		Main.window.show();

	}
}
