package Agent;

import Util.util;
import application.Direction;

public class Agent {

	private static Agent agent= null;
	
	private Direction currentDirection;
	
	private String imagePath;
	
	private int cuPosX, cuPosY, preX,preY,changeX,changeY,nextX,nextY;
	
	
	private Agent() {
		
		this.currentDirection = Direction.EAST;
		this.cuPosX = 0;
		this.cuPosY = 0;

		changeUpdatingCoordinate();
		
	}
	
	public static Agent getAgentInstance() {
		
		if(agent==null) {
			agent = new Agent();
		}
		
		return agent;	
	}
	
	public void changeDirectionToLeft() {
		
		switch (this.currentDirection) {
			
			case NORTH:
				this.currentDirection =  Direction.WEST;
				break;
				
			case EAST:
				this.currentDirection =  Direction.NORTH;
				break;
				
			case SOUTH:
				this.currentDirection =  Direction.EAST;
				break;
				
			default:
				this.currentDirection =  Direction.SOUTH;
				break;
				
		}
		
		changeUpdatingCoordinate();
		
	}
	
	public void changeDirectionToRight() {
		
		switch (this.currentDirection) {
			
			case NORTH:
				this.currentDirection =  Direction.EAST;
				break;
		
			case EAST:
				this.currentDirection =  Direction.SOUTH;
				break;
				
			case SOUTH:
				this.currentDirection =  Direction.WEST;
				break;
				
			default:
				this.currentDirection =  Direction.NORTH;
				break;
		}
		
		changeUpdatingCoordinate();
		
	}
	
	
	private void changeUpdatingCoordinate() {
		
		switch (this.currentDirection) {
			
			case NORTH:
				this.changeX = -1;
				this.changeY = 0;
				this.imagePath = util.NORTH_DIR_IMAGE;
				break;
				
			case EAST:
				this.changeX = 0;
				this.changeY = 1;
				this.imagePath = util.EAST_DIR_IMAGE;
				break;
				
			case SOUTH:
				this.changeX = 1;
				this.changeY = 0;
				this.imagePath = util.SOUTH_DIR_IMAGE;
				break;
				
			case WEST:
				this.changeX = 0;
				this.changeY = -1;
				this.imagePath = util.WEST_DRI_IMAGE;
				break;
				
			default:
				break;
		
		}
		
		
	}
	
/*	private void calculateNext() {
		// TODO Auto-generated method stub
		
		this.nextX = this.cuPosX + this.changeX;
		this.nextY = this.cuPosY + this.changeY;
		
		if()
		
	}*/

	public boolean CheckIndexValidity() {
		
		if(  ( this.cuPosX + this.changeX ) >=0 && ( this.cuPosX + this.changeX ) < util.ROW && ( this.cuPosY + this.changeY ) >=0 && ( this.cuPosY + this.changeY ) < util.COL ) return true;
		
		else return false;
		
	}
	
	
	public boolean agentMoveForward() {
		
		
		if(CheckIndexValidity()) {

			this.preX = this.cuPosX;
			this.preY = this.cuPosY;
			
			this.cuPosX += this.changeX;
			this.cuPosY += this.changeY;
			
			System.out.println(cuPosX+"          "+cuPosY+"       "+changeX+"         "+changeY+"            "+currentDirection);
			
			return true;
		}
		
		else return false;
		
	}

	
	public int getChangeX() {
		return this.changeX;
	}

	public int getChangeY() {
		return this.changeY;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public int getPreX() {
		return this.preX;
	}

	public int getPreY() {
		return this.preY;
	}

	public int getCuPosX() {
		return this.cuPosX;
	}

	public int getCuPosY() {
		return this.cuPosY;
	}
	
}
