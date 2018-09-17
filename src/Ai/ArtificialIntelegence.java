package Ai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import Agent.Agent;
import Controller.UserBoardController;
import Util.util;
import application.Action;
import application.Direction;

public class ArtificialIntelegence {
	
	public static Cell [][] aiBoard = new Cell[util.ROW][util.COL];
	
	private Agent agent;
	
	public static int count=0;
	
	public ArtificialIntelegence() {
		
		agent = Agent.getAgentInstance();
		intializeBoard();
		
	}
	
	public Action getAction() {
		
		int nx,ny;
		
		updateAdjacentCell();
		
		
		Cell cell = aiBoard[agent.getCuPosX()][agent.getCuPosY()];

		nx = agent.getCuPosX() + agent.getChangeX();
		ny = agent.getCuPosY() + agent.getChangeY();
		
		
		
		if(cell.isContainGold()) {
			cell.removeGold();
			return Action.GRAB_GOLD;
		}
		
		
		Action ac = sendAction();
		
		/*if(nx>=0 && nx<util.ROW && ny>=0 && ny<util.COL) {
			
			Cell NextCell = aiBoard[agent.getCuPosX()+agent.getChangeX()][agent.getCuPosY()+agent.getChangeY()];
			
			System.out.println(count +"    dddddddddddddddddddddddddddd");

			//if(!NextCell.isContainPit() && !NextCell.isContainWampus() && NextCell.isExplore() ) return Action.TURN_LEFT;
			
			if(NextCell.isExplore()  && ( cell.isContainBreeze() || cell.isContainStench() ) ) {
				 return Action.GO_FORWARD;
			}
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
			
		}*/
		count++;
		return ac;
	}
	
	
	
	
	public void updateAdjacentCell() {
		
		Cell cell = aiBoard[agent.getCuPosX()][agent.getCuPosY()];
		
		//System.out.println(agent.getCuPosX()   + "        "+agent.getCuPosY());
		
		if(!cell.isContainBreeze()) {
			
			int ax,ay;
			//cell.setContainPit(false);
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	public Action sendAction() {
		
		double front = getForwardValue()+1;
		double left = getLeftValue();
		double right = getRightValue();
		
		
		
		
		TreeMap<Double, Action> actionMap = new TreeMap<>();
		
		if(front>-1)actionMap.put(front, Action.GO_FORWARD);
		actionMap.put(left, Action.TURN_LEFT);
		actionMap.put(right, Action.TURN_RIGHT);
		
		//System.out.println(front+ "     ");
		
		for(double k: actionMap.keySet() ) System.out.println(k+ "     "+actionMap.get(k));
		
		
		double key = actionMap.lastKey();
		
		return actionMap.get(key);
		
	}

	private double getForwardValue() {
		// TODO Auto-generated method stub
		
		int nx = agent.getCuPosX()+agent.getChangeX();
		int ny = agent.getCuPosY()+agent.getChangeY();
		
		if( nx>=0 && nx<util.ROW && ny>=0 && ny<util.COL ) {
			
			Cell cell = aiBoard[nx][ny];
			
			if(cell.isExplore()) return (double) 200/cell.getCount();
			
			else if(!cell.isExplore()) {
				
				if(!cell.isContainPit() && !cell.isContainWampus()) return 500;
				
				else return -200;
				
			}
			
		}
		
		return -100;
	}
	
	public double getLeftValue() {
		
		int changeX = 0,changeY=0;
		Direction direction;
		
		switch (agent.currentDirection) {
		
		case NORTH:
			direction =  Direction.WEST;
			break;
			
		case EAST:
			direction =  Direction.NORTH;
			break;
			
		case SOUTH:
			direction =  Direction.EAST;
			break;
			
		default:
			direction =  Direction.SOUTH;
			break;
		}
		
		switch (direction) {
		
		case NORTH:
			changeX = -1;
			changeY = 0;
			
			break;
			
		case EAST:
			changeX = 0;
			changeY = 1;
			
			break;
			
		case SOUTH:
			changeX = 1;
			changeY = 0;
			
			break;
			
		case WEST:
			changeX = 0;
			changeY = -1;
			
			break;
			
		default:
			break;
	
		}
		
		int nx = agent.getCuPosX()+changeX;
		int ny = agent.getCuPosY()+changeY;
		
		if( nx>=0 && nx<util.ROW && ny>=0 && ny<util.COL ) {
			
			Cell cell = aiBoard[nx][ny];
			
			if(cell.isExplore()) return (double)200/cell.getCount();
			
			else if(!cell.isExplore()) {
				
				if(!cell.isContainPit() && !cell.isContainWampus()) return 500;
				
				else return -200;
				
			}
			
		}
		
		return -100;
		
	}
	
	
	public double getRightValue() {
		
		int changeX = 0,changeY=0;
		Direction direction;
		
		switch (agent.currentDirection) {
		
		case NORTH:
			direction =  Direction.EAST;
			break;
	
		case EAST:
			direction =  Direction.SOUTH;
			break;
			
		case SOUTH:
			direction =  Direction.WEST;
			break;
			
		default:
			direction =  Direction.NORTH;
			break;
		}
		
		switch (direction) {
		
		case NORTH:
			changeX = -1;
			changeY = 0;
			
			break;
			
		case EAST:
			changeX = 0;
			changeY = 1;
			
			break;
			
		case SOUTH:
			changeX = 1;
			changeY = 0;
			
			break;
			
		case WEST:
			changeX = 0;
			changeY = -1;
			
			break;
			
		default:
			break;
	
		}
		
		int nx = agent.getCuPosX()+changeX;
		int ny = agent.getCuPosY()+changeY;
		
		if( nx>=0 && nx<util.ROW && ny>=0 && ny<util.COL ) {
			
			Cell cell = aiBoard[nx][ny];
			
			if(cell.isExplore()) return (double) 200/cell.getCount();
			
			else if(!cell.isExplore()) {
				
				if(!cell.isContainPit() && !cell.isContainWampus()) return 500;
				
				else return -200;
				
			}
			
		}
		
		return -100;
		
		
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
