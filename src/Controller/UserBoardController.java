package Controller;

import application.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import Agent.Agent;
import Ai.ArtificialIntelegence;
import Ai.Cell;
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
import javafx.scene.input.KeyCode;
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
	
	
	private int goldCount,changeX,changeY;
	
	
	public static int arrowCount; 
	//private Cell AiCell;
	
	private Agent agent;
	
	private Thread threah;
	
	boolean flag = true;
	
	int i=0;
	
	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		WampusWorldGenerator wwg = new WampusWorldGenerator();
		ArrayList< ArrayList< WorldCell > > wamWorld = wwg.getWampusWorld();
		
		ArtificialIntelegence ai = new ArtificialIntelegence();
		
		goldCount = util.NUMBER_OF_GOLD;
		changeGoldText();
		arrowCount = util.NUMBER_OF_ARROW;
		changeArrowText();
		
		agent = Agent.getAgentInstance();
		
		
		for(int i=0;i<util.ROW;i++) {
			
			for(int j=0;j<util.COL;j++) {
				
				WorldCell cell = wamWorld.get(i).get(j);
				
				StackPane sp  = getCellStack(cell);
				sp.getProperties().put("info", cell);
				grid.add(sp, i, j);
				
			}
			
		}
		
		try {
			updateCurrentAgent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		threah = new Thread(new Runnable() {
			
			
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(flag) {
					
					Platform.runLater(new Runnable()
					{
					    @Override
					    public void run()
					    {
					    	try {
					    		
					    //startAgent();
					    		
								Action  action = ai.getAction();
								
								if(action==Action.GO_FORWARD) forwardActionExecute();
								else if(action==Action.GRAB_GOLD) enterActionExecute();
								else if(action==Action.TURN_LEFT) leftActionExecute();
								else if(action==Action.TURN_RIGHT) rightActionExecute();
								else if(action==Action.SHOOT_ARROW) spaceActionExecute();
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							i++;

					    }

						/*private void startAgent() {
							// TODO Auto-generated method stub
							
							ai.updateAdjacentCell();
							
							for( int i=0;i<4;i++ ) {
							
								
								Cell cell = ArtificialIntelegence.aiBoard[agent.getCuPosX()][agent.getCuPosY()];


								int ax = agent.getCuPosX() + util.x[i];
								int ay = agent.getCuPosY() + util.y[i];
								
								if( ax>=0 && ax<util.ROW && ay>=0 && ay<util.COL ) {
									
									
									
								}
								
								
								
								
								
							}
							
						}
					    
					  */
					});
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
			}
		}); 
		
		threah.start();
		
	}
	
	
	private void changeArrowText() {
		// TODO Auto-generated method stub
		arrow.setText(Integer.toString(arrowCount));
	}


	private StackPane getCellStack(WorldCell worldCell) {
		// TODO Auto-generated method stub

		StackPane sp = new StackPane();
		
		sp.getChildren().add(getImageView(util.CELL_FLOOR_IMAGE));
		
		if( worldCell.getCellEffect()!= null && ! worldCell.getCellEffect().equals(util.GLITTER) ) {
			
			Label label = new Label( worldCell.getCellEffect() );
			label.setTextFill(Color.web("#ffffff"));
			sp.getChildren().add(label);
		}
		
		if(worldCell.getCellElement().equals(util.WAMPUS)) sp.getChildren().add(getImageView(util.WAMPUS_IMAGE));
		
		else if ( worldCell.getCellElement().equals(util.PIT) ) sp.getChildren().add(getImageView(util.PIT_IMAGE));
		
		else if ( worldCell.getCellElement().equals(util.GOLD) ) sp.getChildren().add(getImageView(util.GOLD_IMAGE));
		
		sp.getChildren().add(getImageView(util.WALL_IMAGE));
		
		return sp;
		
	}


	private ImageView getImageView(String path) {
		
		Image image = new Image(path);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(58);
		imageView.setFitWidth(58);
		
		return imageView;
	}
	

	public void rightAction(ActionEvent event) throws IOException {
		
		rightActionExecute();
		
	}
	
	
	private void rightActionExecute() throws IOException {
		// TODO Auto-generated method stub
		buttonDisable(true);
		
		agent.changeDirectionToRight();
		
		updateAgentRotation();
		
		buttonDisable(false);
		
	}


	private void updateCurrentAgent() throws IOException {
		// TODO Auto-generated method stub
		
		StackPane curCellView;
		
		curCellView = getCellView(agent.getCuPosX(), agent.getCuPosY());
		
		WorldCell cell = (WorldCell) curCellView.getProperties().get("info");
		
		//System.out.println( agent.getCuPosX()+"         "+agent.getCuPosY()  );
		//System.out.println( cell.getX()+"         "+cell.getY()  );
		
		if(!cell.getIsVisited()) {
			
			updateAiCell(cell);
			curCellView.getChildren().remove(curCellView.getChildren().size()-1);
			
			if(cell.getCellElement().equals(util.WAMPUS) || cell.getCellElement().equals(util.PIT) ) terminateGame();
			
			cell.setIsVisited(true);
			
		}
		
		//if(ArtificialIntelegence.aiBoard[agent.getCuPosX()][agent.getCuPosY()].isExplore()) ArtificialIntelegence.aiBoard[agent.getCuPosX()][agent.getCuPosY()].increment();
		curCellView.getChildren().add(getImageView(agent.getImagePath()));
		
	}
	
	private void updateAiCell(WorldCell worldCell) {
		
		Cell cell = new Cell(util.SAFE,true);
	
		for(String effect: worldCell.cellEffectList) cell.insertEffect(effect);
		
		if( !cell.isContainBreeze() && !cell.isContainStench() ) {
			
			cell.setContainWampus(false);
			cell.setContainPit(false);
					
		}
		
		ArtificialIntelegence.aiBoard[agent.getCuPosX()][agent.getCuPosY()] = cell;
		
		
		
	}
	

	public void leftAction(ActionEvent event) throws IOException {
		
		leftActionExecute();
			
	}
	
	

	public void leftActionExecute() throws IOException {
		// TODO Auto-generated method stub
		buttonDisable(true);
		
		agent.changeDirectionToLeft();
		
		updateAgentRotation();
				
		buttonDisable(false);
	}

	
	private void updateAgentRotation() {
		// TODO Auto-generated method stub

		StackPane curCellView;
		
		curCellView = getCellView(agent.getCuPosX(), agent.getCuPosY());
		
		WorldCell cell = (WorldCell) curCellView.getProperties().get("info");
		
		//System.out.println( agent.getCuPosX()+"         "+agent.getCuPosY()  );
		//System.out.println( cell.getX()+"         "+cell.getY()  );
		
		curCellView.getChildren().remove(curCellView.getChildren().size()-1);
		
		curCellView.getChildren().add(getImageView(agent.getImagePath()));
		
	}


	public void forwardAction(ActionEvent event) throws IOException {
		
		forwardActionExecute();
	}
	
	
	public void forwardActionExecute() throws IOException {
		// TODO Auto-generated method stub
		buttonDisable(true);
		
		System.out.println("forward action call");
		
		if(agent.agentMoveForward() ) makeTransition();
		
		buttonDisable(false);
		
	}

	public void enterAction(ActionEvent event) {
		
		enterActionExecute();
		
	}
	
	private void changeGoldText() {
		
		gold.setText(Integer.toString(goldCount));
		
	}
	
	
	private void enterActionExecute() {
		// TODO Auto-generated method stub

		buttonDisable(true);
		
		grabGold();
		
		buttonDisable(false);
		
	}


	public void grabGold() {
		// TODO Auto-generated method stub
		StackPane curCellView;
		
		curCellView = getCellView(agent.getCuPosX(), agent.getCuPosY());
		
		WorldCell cell = (WorldCell) curCellView.getProperties().get("info");
		
		if(cell.getCellElement().equals(util.GOLD)) {
			
			curCellView.getChildren().remove(curCellView.getChildren().size()-2);
			
			cell.setCellElement(util.EMPTY);
			
			goldCount--;
			
			changeGoldText();
			
		}
		
	}


	public void spaceAction(ActionEvent event) {
		
		spaceActionExecute();
		
	}
	
	private void spaceActionExecute() {
		
		buttonDisable(true);
		
		//System.out.println( agent.getCuPosX()+"         "+agent.getCuPosY()  );
		
		
		StackPane attackingCellView =  getCellView(  (agent.getCuPosX()+agent.getChangeX() ), (agent.getCuPosY() + agent.getChangeY()) );
		
		//Label lable = (Label) attackingCellView.getChildren().get(2);
		
		if(agent.CheckIndexValidity()) {
			
			
			
			WorldCell attackingCell = (WorldCell) attackingCellView.getProperties().get("info");
			
			//System.out.println( attackingCell.getX()+"         "+attackingCell.getY()  );
		
			if(attackingCell.getCellElement().equals(util.WAMPUS)) {
				
				attackingCellView.getChildren().remove(attackingCellView.getChildren().size()-2);
				
				attackingCell.setCellElement(util.EMPTY);
				
				ArtificialIntelegence.aiBoard[agent.getCuPosX()+agent.getChangeX()][agent.getCuPosY() + agent.getChangeY()].setContainWampus(false);
				
				removeAdjacentCellEffect(attackingCell);
				
				arrowCount--;
				
				changeArrowText();
				
			}
			
			
		}
		

		//System.out.println("dhbddddddddddhf"+attackingCell.getCellElement());
		
		
	
		
		buttonDisable(false);
		
	}


	private void removeAdjacentCellEffect(WorldCell AttackingCell) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<4;i++) {
			
			if (  (agent.getCuPosX()+agent.getChangeX() + util.x[i] ) >=0 &&
					 (agent.getCuPosX()+agent.getChangeX() + util.x[i] ) < util.ROW &&
					( (agent.getCuPosY() + agent.getChangeY()) + util.y[i] ) >=0 &&
					( (agent.getCuPosY() + agent.getChangeY())+ util.y[i] ) < util.COL ) {
				
				

				StackPane adjacentCellView = getCellView( (agent.getCuPosX()+agent.getChangeX() ) + util.x[i],
						(agent.getCuPosY() + agent.getChangeY()) + util.y[i] );
				
				WorldCell adjacentCell = (WorldCell) adjacentCellView.getProperties().get("info");
				
				//System.out.println(util.x[i]+"     "+util.y[i]+"         "+adjacentCellView.getChildren().size());
				
				adjacentCellView.getChildren().remove(1);
				
				adjacentCell.removeCellEffect();
				
				if( adjacentCell.getCellEffect()!= null && ! adjacentCell.getCellEffect().equals(util.GLITTER) ) {
					
					Label label = new Label( adjacentCell.getCellEffect() );
					label.setTextFill(Color.web("#ffffff"));
					adjacentCellView.getChildren().add(1,label);
				}
				
				
			}
			
			
			
		}
		
	}


	public void keyPressedAction(KeyEvent event) throws IOException {
		
		
		if(event.getCode() == KeyCode.UP) forwardActionExecute();
		else if (event.getCode() == KeyCode.LEFT) leftActionExecute();
		else if (event.getCode() == KeyCode.RIGHT) rightActionExecute();
		else if (event.getCode() == KeyCode.ENTER) enterActionExecute();
		else if (event.getCode() == KeyCode.SPACE) spaceActionExecute();
		
    }
		

	private void makeTransition() throws IOException {
		
		removePreviousAgent();
		
		updateCurrentAgent();
		
		if(ArtificialIntelegence.aiBoard[agent.getCuPosX()][agent.getCuPosY()].isExplore()) 
			ArtificialIntelegence.aiBoard[agent.getCuPosX()][agent.getCuPosY()].increment();
	}
	
	
	private void removePreviousAgent() {
		// TODO Auto-generated method stub
		StackPane preCellView;

		preCellView = getCellView(agent.getPreX(), agent.getPreY());
		
		preCellView.getChildren().remove(preCellView.getChildren().size()-1);
	}


	private void terminateGame() throws IOException {
		// TODO Auto-generated method stub
		flag = false;
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
