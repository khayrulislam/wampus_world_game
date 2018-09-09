package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import application.*;
import Util.util;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.AmbientLight;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;

public class UserBoardController implements Initializable {

	@FXML
	private GridPane grid;
	
	@FXML
	private Button upButton;

	@FXML
	private Button rightButton;
	
	@FXML
	private Button leftButton;
	
	@FXML
	private Button downButton;
	
	@FXML
	private Button spaceButton;
	
	@FXML
	private Button enterButton;
	
	@FXML 
	private Label gold;
	
	@FXML
	private Label arrow;
	
	
	private int cuPosX, cuPosY, preX,preY, facing, goldCount,arrowCount;
	
	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		WampusWorldGenerator wwg = new WampusWorldGenerator();
		
		cuPosX = 0; cuPosY = 0; facing = 1; goldCount = util.NUMBER_OF_GOLD; arrowCount = util.NUMBER_OF_ARROW;
		
		gold.setText(Integer.toString(goldCount));
		
		arrow.setText(Integer.toString(arrowCount));
		
		ArrayList< ArrayList< WorldCell > > wamWorld = wwg.getWampusWorld();
		
		
		for(int i=0;i<util.ROW;i++) {
			
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
				/*
				grid.setRowIndex(sp, i);
				grid.setColumnIndex(sp, j);
				
				grid.getChildren().add(sp);*/
				
				/*sp.getProperties().put("row", i);
				sp.getProperties().put("col", j);
				
				grid.setConstraints(sp, j, i);*/
				
				sp.getProperties().put("info", cell);
				grid.add(sp, i, j);
				
			}
			
		}
		
	}
	
	
	private ImageView getImageView(String path) {
		
		Image image = new Image(path);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(58);
		imageView.setFitWidth(58);
		
		return imageView;
	}
	
	
	public void downAction(ActionEvent event) throws IOException {
		
		downActionExecute();
		
	}


	private void downActionExecute() throws IOException {
		// TODO Auto-generated method stub
		buttonDisable(true);
		
		preX = cuPosX; preY = cuPosY;
		
		if(facing!=1) {
			
			rotateAgent(cuPosX,cuPosY,"/image/player_facing_to_down.png");
			
			facing = 1;
		}
		
		else if(cuPosX+1 < util.ROW ) {
			
			cuPosX +=1;
			
			makeTransition(cuPosX,cuPosY,"/image/player_facing_to_down.png");
			
		}
		
		buttonDisable(false);
		
	}


	public void leftAction(ActionEvent event) throws IOException {
		
		leftActionExecute();
			
	}
	
	
	private void leftActionExecute() throws IOException {
		// TODO Auto-generated method stub
		buttonDisable(true);
		
		preX = cuPosX; preY = cuPosY;
		
		if(facing!=2) {
			
			rotateAgent(cuPosX, cuPosY, "/image/player_facing_to_left.png");
			
			facing = 2;
		}
		
		else if(cuPosY-1 > -1 ) {
			
			cuPosY -=1;
			
			makeTransition(cuPosX, cuPosY, "/image/player_facing_to_left.png");
			
		}
		
		buttonDisable(false);
	}


	public void upAction(ActionEvent event) throws IOException {
		
		upActionExecute();
			
	}
	
	
	private void upActionExecute() throws IOException {
		// TODO Auto-generated method stub
		buttonDisable(true);
		
		preX = cuPosX; preY = cuPosY;
		
		if(facing!=3) {
			
			rotateAgent(cuPosX, cuPosY, "/image/player_facing_to_up.png");
			
			facing = 3;
		}
		
		else if(cuPosX-1 > -1 ) {
			
			cuPosX -=1;
			
			makeTransition(cuPosX, cuPosY, "/image/player_facing_to_up.png");
			
		}
		
		buttonDisable(false);
	}


	public void rightAction(ActionEvent event) throws IOException {
		
		rightActionExecute();
		
	}
	
	
	private void rightActionExecute() throws IOException {
		// TODO Auto-generated method stub
		buttonDisable(true);
		
		preX = cuPosX; preY = cuPosY;
		
		if(facing!=4) {
			
			rotateAgent(cuPosX, cuPosY, "/image/player_facing_to_right.png");
			
			facing = 4;
		}
		
		else if(cuPosY+1 < util.COL ) {
			
			cuPosY +=1;
			
			makeTransition(cuPosX, cuPosY, "/image/player_facing_to_right.png");
			
		}
		
		buttonDisable(false);
	}


	public void enterAction(ActionEvent event) {
		
		enterActionExecute();
		
	}
	
	
	private void enterActionExecute() {
		// TODO Auto-generated method stub

		buttonDisable(true);
		
		StackPane curCellView;
		
		curCellView = getCellView(cuPosX, cuPosY);
		
		WorldCell cell = (WorldCell) curCellView.getProperties().get("info");
		
		if(cell.getCellElement().equals(util.GOLD)) {
			
			curCellView.getChildren().remove(curCellView.getChildren().size()-2);
			
			cell.setCellElement(util.EMPTY);
			
			goldCount--;
			
			gold.setText(Integer.toString(goldCount));
			
		}
		
		buttonDisable(false);
		
	}


	public void spaceAction(ActionEvent event) {
		
		spaceActionExecute();
		
	}
	
	private void spaceActionExecute() {
		
		buttonDisable(true);
		
		StackPane attackingCellView = null;
		
		if(facing==1) {
			
			attackingCellView = getCellView(cuPosX+1, cuPosY);
			
		}
		
		else if (facing==2) {
			
			attackingCellView = getCellView(cuPosX, cuPosY-1);
			
		}
		
		else if (facing==3) {
			
			attackingCellView = getCellView(cuPosX-1, cuPosY);
			
		}
		
		else if (facing==4) {
			
			attackingCellView = getCellView(cuPosX, cuPosY+1);
			
		}
		
		WorldCell cell = (WorldCell) attackingCellView.getProperties().get("info");
		
		buttonDisable(false);
		
	}


	public void keyPressedAction(KeyEvent event) throws IOException {
		
		switch (event.getCode()) {
        case UP:    
        	upActionExecute();
        	break;
        case DOWN:  
        	downActionExecute();
        	break;
        case LEFT:  
        	leftActionExecute();
        	break;
        case RIGHT: 
        	rightActionExecute();
        	break;	
        case ENTER: 
        	enterActionExecute();
        	break;
		}
    }
		
	
	
	private void rotateAgent(int cuPosX, int cuPosY, String path) {
		
		StackPane curCellView;
		
		curCellView = getCellView(cuPosX, cuPosY);
		
		curCellView.getChildren().remove(curCellView.getChildren().size()-1);
		
		curCellView.getChildren().add(getImageView(path));
	}


	private void makeTransition(int cuPosX, int cuPosY,String path) throws IOException {
		
		StackPane curCellView,preCellView;
		
		preCellView = getCellView(preX, preY);
		
		preCellView.getChildren().remove(preCellView.getChildren().size()-1);
		
		curCellView = getCellView(cuPosX, cuPosY);
		
		WorldCell cell = (WorldCell) curCellView.getProperties().get("info");
		
		if(cell.getCellHide().equals(util.HIDE)) {
			
			curCellView.getChildren().remove(curCellView.getChildren().size()-1);
			
			if(cell.getCellElement().equals(util.WAMPUS) || cell.getCellElement().equals(util.PIT) ) terminateGame();
			
			cell.setCellHide(util.OPEN);
			
		}
		
		curCellView.getChildren().add(getImageView(path));
		
	}
	
	
	private void terminateGame() throws IOException {
		// TODO Auto-generated method stub
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("The Game is over");
		alert.setContentText("Do you want to play again?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){

			Parent root = FXMLLoader.load(getClass().getResource("/View/Choice.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			Main.window.setScene(scene);
			Main.window.centerOnScreen();
			Main.window.show();	
			


		} else {

			Platform.exit();
			
		}
		
	}


	private void buttonDisable(boolean value) {
		
		downButton.setDisable(value);
		leftButton.setDisable(value);
		upButton.setDisable(value);
		rightButton.setDisable(value);
		enterButton.setDisable(value);
		spaceButton.setDisable(value);
		
	}
	
	private StackPane getCellView(int row,int col) {
		
		Node result = null;
		
		for(Node node : grid.getChildren()) {
			
			Integer r = grid.getRowIndex(node);
			Integer c = grid.getColumnIndex(node);
			
			if(r==null && c==null) continue;
			
			else if(r==row && c==col) return (StackPane) node;
			
		}
		return null;

	}
	
	
	
	

}
