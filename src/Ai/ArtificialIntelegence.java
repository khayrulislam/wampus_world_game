package Ai;

import java.io.IOException;
import java.util.ArrayList;

import Agent.Agent;
import Controller.UserBoardController;
import Util.util;
import application.Action;

public class ArtificialIntelegence {
	
	//private UserBoardController ubc;
	
	public static Cell [][] aiBoard = new Cell[util.ROW][util.COL];
	
	private Agent agent;
	
	public static int count=0;
	
	public ArtificialIntelegence() {
		// TODO Auto-generated constructor stub
		//this.ubc = ubc;
		agent = Agent.getAgentInstance();
		intializeBoard();
	}
	
	public Action getAction() {
		
		int nx,ny;
		
		updateAdjacentCell();
		
		
		
		
		Cell cell = aiBoard[agent.getCuPosX()][agent.getCuPosY()];

		nx = agent.getCuPosX() + agent.getChangeX();
		ny = agent.getCuPosY() + agent.getChangeY();
		
		
		
		if(cell.isContainGold()) return Action.GRAB_GOLD;
		
		if(nx>=0 && nx<util.ROW && ny>=0 && ny<util.COL) {
			
			Cell NextCell = aiBoard[agent.getCuPosX()+agent.getChangeX()][agent.getCuPosY()+agent.getChangeY()];
			
			System.out.println(count +"    dddddddddddddddddddddddddddd");

			//if(!NextCell.isContainPit() && !NextCell.isContainWampus() && NextCell.isExplore() ) return Action.TURN_LEFT;
			
			/*if(NextCell.isExplore()  && ( cell.isContainBreeze() || cell.isContainStench() ) ) {
				 return Action.GO_FORWARD;
			}*/
			//if(cell.isExplore() && NextCell.isExplore())  return Action.TURN_LEFT;
			if(!NextCell.isContainPit() && !NextCell.isContainWampus() && !NextCell.isExplore()) return Action.GO_FORWARD;
			
			else if(count>2 && NextCell.isExplore() ) {
				count = 0;
				 return Action.GO_FORWARD;
			}
			
			else {
				
				System.out.println("             else            ");
				count++;
				return Action.TURN_LEFT;
			}
			
		}
		count++;
		return Action.TURN_LEFT;
	}
	
	
	
	
	public void updateAdjacentCell() {
		// TODO Auto-generated method stub
		Cell cell = aiBoard[agent.getCuPosX()][agent.getCuPosY()];
		
		System.out.println(agent.getCuPosX()   + "        "+agent.getCuPosY());
		
		if(!cell.isContainBreeze()) {
			
			int ax,ay;
			cell.setContainPit(false);
			
			for(int i=0;i<4;i++) {
				
				ax = agent.getCuPosX() + util.x[i];
				ay = agent.getCuPosY() + util.y[i];
				
				if( ax>=0 && ax<util.ROW && ay>=0 && ay<util.COL ) {
					
					if(aiBoard[ax][ay].isContainPit()) aiBoard[ax][ay].setContainPit(false);
					
					///System.out.println(  "  bre = "+aiBoard[ax][ay].isContainBreeze() + "  sten = "+ aiBoard[ax][ay].isContainStench()+"    wam= "+aiBoard[ax][ay].isContainWampus() +"  pit = "+aiBoard[ax][ay].isContainPit());

				}
				
			}
		}
		
		if(!cell.isContainStench()) {
			
			int ax,ay;
			
			for(int i=0;i<4;i++) {
				
				ax = agent.getCuPosX() + util.x[i];
				ay = agent.getCuPosY() + util.y[i];
				
				if( ax>=0 && ax<util.ROW && ay>=0 && ay<util.COL ) {
					
					if(aiBoard[ax][ay].isContainWampus())aiBoard[ax][ay].setContainWampus(false);
					
				}
			}
			
		}
	}

	private void intializeBoard() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<util.ROW;i++) {
			
			for(int j=0;j<util.COL;j++) {
				aiBoard[i][j] = new Cell(util.UNSAFE, false);
				
				//System.out.print(  "  bre = "+aiBoard[i][j].isContainBreeze() + "  sten = "+ aiBoard[i][j].isContainStench()+"    wam= "+aiBoard[i][j].isContainWampus() +"  pit = "+aiBoard[i][j].isContainPit());
			}
			
			//System.out.println();
		}
		
		aiBoard[0][0].setContainPit(false);
		aiBoard[0][0].setContainWampus(false);
		
		//aiBoard[agent.getCuPosX()][agent.getCuPosY()] = ubc.getAiCell();
		
	}
	

}





























/*private String getAdjacentCellInfo(int x,int y) {
		
		if(x>=0 && x<util.ROW && y>=0 && y<util.COL) {
			
			Cell cell = aiBoard[x][y];
			
			if(cell.isExplore() ) {
				
				if(cell.isContainStench() && cell.isContainBreeze() ) return util.BOTH;
				
				if(cell.isContainBreeze()) return util.BREEZE;
				
				if(cell.isContainStench() ) return util.STENCH;
				
				else return util.EMPTY;
				
			}
			
			else return util.UNDEFIEND;
			
		}
		return util.UNDEFIEND;
		
	}
	
	

	private boolean checkForwardActionSafe() {
		// TODO Auto-generated method stub
		
		int nx,ny,ax1,ay1,ax2,ay2,breezeCount = 0,stenchCount = 0;
		String a1Result,a2Result;
		
		ArrayList<String> result = new ArrayList<>();
		
		nx = agent.getCuPosX() + agent.getChangeX();
		ny = agent.getCuPosY() + agent.getChangeY();
		
		if(nx>=0 && nx<util.ROW &&ny>=0 && ny<util.COL) {
			
			for(int i=0;i<4;i++) {
				
				ax1 = nx + util.ax[i];
				ay1 = nx + util.ay[i];
				
				ax2 = nx + util.ax[i+1];
				ay2 = nx + util.ay[i+1];
				
				a1Result = getAdjacentCellInfo(ax1,ay1);
				a2Result = getAdjacentCellInfo(ax2,ay2);
				
				if(a1Result.equals(util.EMPTY)  || a2Result.equals(util.EMPTY)) return true;
				
				if(a1Result.equals(util.BREEZE)  && a2Result.equals(util.STENCH)) return true;
				
				if(a1Result.equals(util.STENCH)  && a2Result.equals(util.BREEZE)) return true;
				
				else {
					result.add(a1Result);
				}
				
				
			}
			
			if(  )
				
				
			
		}
		
		return false;
	}
	
	*/
