package Ai;

import Agent.Agent;
import Controller.UserBoardController;
import Util.util;

public class ArtificialIntelegence {
	
	private UserBoardController ubc;
	
	private Cell [][] aiBoard = new Cell[util.ROW][util.COL];
	
	private Agent agent;
	
	public ArtificialIntelegence(UserBoardController ubc) {
		// TODO Auto-generated constructor stub
		this.ubc = ubc;
		agent = Agent.getAgentInstance();
	}
	
	public void startIntelegence() {
		
		intializeBoard();
		
		executGame();
	}

	private void executGame() {
		// TODO Auto-generated method stub
		
		tryToGrabGold();
		
		if(checkForwardActionSafe()) {
			
		}
		
	}

	private boolean checkForwardActionSafe() {
		// TODO Auto-generated method stub
		
		int nx,ny;
		
		nx = agent.getCuPosX() + agent.getChangeX();
		ny = agent.getCuPosY() + agent.getChangeY();
		
		if(nx>=0 && nx<util.ROW &&ny>=0 && ny<util.COL) {
			
			
			
		}
		
		
		return false;
	}

	private void tryToGrabGold() {
		// TODO Auto-generated method stub
		
		Cell cell = aiBoard[agent.getCuPosX()][agent.getCuPosY()];
		
		if(cell.isContainGold()) ubc.grabGold();
		
	}

	private void intializeBoard() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<util.ROW;i++) {
			
			for(int j=0;j<util.COL;j++) 
				aiBoard[i][j] = new Cell(util.UNSAFE, false);
		}
		
		aiBoard[agent.getCuPosX()][agent.getCuPosY()] = ubc.getAiCell();
		
	}
	

}
