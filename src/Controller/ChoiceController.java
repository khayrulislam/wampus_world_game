package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

import Ai.ArtificialIntelegence;
import Util.util;
import application.Main;
import application.WampusWorldGenerator;
import application.WorldCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ChoiceController implements Initializable{
	
	public static ArrayList< ArrayList< StackPane > >  board;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		/*
		WampusWorldGenerator wwg = new WampusWorldGenerator();
		
		//cuPosX = 0; cuPosY = 0; facing = 1; goldCount = util.NUMBER_OF_GOLD; arrowCount = util.NUMBER_OF_ARROW;
		
		//0gold.setText(Integer.toString(goldCount));
		
		//arrow.setText(Integer.toString(arrowCount));
		
		ArrayList< ArrayList< WorldCell > > wamWorld = wwg.getWampusWorld();
		
		board = new ArrayList<>();
		
		for(int i=0;i<util.ROW;i++) {
			
			ArrayList<StackPane>  temp = new ArrayList<>();
			
			for(int j=0;j<util.COL;j++) {
				
				WorldCell cell = wamWorld.get(i).get(j);
				
				StackPane sp = new StackPane();
				
				sp.getChildren().add(getImageView("/image/floor.png"));
				
				if( ! cell.getCellMessage().equals(util.EMPTY) ) {
					
					Label label = new Label(cell.getCellMessage());
					label.setTextFill(Color.web("#ffffff"));
					sp.getChildren().add(label);
				}
				
				if(cell.getCellElement().equals(util.WAMPUS)) sp.getChildren().add(getImageView("/image/wampus.png"));
				
				else if ( cell.getCellElement().equals(util.PIT) ) sp.getChildren().add(getImageView("/image/pit.png"));
				
				else if ( cell.getCellElement().equals(util.GOLD) ) sp.getChildren().add(getImageView("/image/gold.png"));
				
				if(i!=0 || j!=0) {
					
					sp.getChildren().add(getImageView("/image/wall.png"));
					
					cell.setCellHide(util.HIDE);
				}
				
				else {
					
					sp.getChildren().add(getImageView("/image/player_facing_to_down.png"));
					
					cell.setCellHide(util.OPEN);
				}
				
				sp.getProperties().put("info", cell);
				
				temp.add(sp);
				//grid.add(sp, i, j);
				
			}
			
			board.add(temp);
			
		}
		
		*/
		
	}
	
	private ImageView getImageView(String path) {
		
		Image image = new Image(path);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(58);
		imageView.setFitWidth(58);
		
		return imageView;
	}
	
	public void loadThird(ActionEvent event) throws IOException {
		
		FXMLLoader load = new FXMLLoader(getClass().getResource("/View/UserBoard.fxml"));
		Parent root = load.load();
		UserBoardController ubc = load.getController();
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		
		Main.window.setScene(scene);
		Main.window.centerOnScreen();
		Main.window.show();
		
		//ArtificialIntelegence ai = new ArtificialIntelegence(ubc);
		//ai.startIntelegence();
		
		/*
		Parent root = FXMLLoader.load(getClass().getResource("/View/UserBoard.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		
		Main.window.setScene(scene);
		Main.window.centerOnScreen();
		Main.window.show();*/
		
	}
}
