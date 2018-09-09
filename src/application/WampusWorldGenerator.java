package application;

import Util.util;
import java.util.ArrayList;
import java.util.Random;

public class WampusWorldGenerator {
	
	private ArrayList< ArrayList< WorldCell > > wamWorld = new ArrayList<>();
		
	public ArrayList< ArrayList< WorldCell > >  getWampusWorld() {
		
		initializeWampusWorld();
		
		insertElement(util.NUMBER_OF_GOLD,util.GOLD);
		
		insertElement(util.NUMBER_OF_WAMPUS,util.WAMPUS);
		
		insertElement(util.NUMBER_OF_PIT,util.PIT);
		
		insertEffect(util.WAMPUS, util.STENCH);
		
		insertEffect(util.PIT, util.BREEZE);
		
		return wamWorld;
		
	}
	
	private void insertEffect(String element,String effect) {
		
		for(int i=0;i<util.ROW;i++) {
			
			for(int j=0;j<util.COL;j++) {
				
				WorldCell cell = wamWorld.get(i).get(j);
				
				if(cell.getCellElement().equals(element)) wampusEffect(i,j,effect);
				
			}
			
		}
		
		
		
	}

	private void wampusEffect(int row, int col, String effect) {
		
		int currentx,currenty;
		
		for(int i=0;i<util.DIRECTION;i++) {
			
			currentx = row + util.x[i];
			
			currenty = col + util.y[i];
			
			if(!isValidIndex(currentx, currenty) ) {
				
				WorldCell cell = wamWorld.get(currentx).get(currenty);
				
				if(cell.getCellMessage().equals(util.EMPTY)) // cell.setCellMessage(util.STENCH);
				
					cell.setCellMessage(effect.equals(util.STENCH ) ? util.STENCH : util.BREEZE);
					
				
				else if ( ! cell.getCellMessage().equals(effect) && cell.getCellMessage().length()==6) 
					
					cell.setCellMessage(cell.getCellMessage()+"\n"+ effect);
					
					//cell.setCellMessage(cell.getCellMessage()+"\n"+ util.STENCH);
				
			}
			
		}
		
	}
	
	private boolean isValidIndex(int currentXCoordinate,int currentYCoordinate) {
		
		return ( currentXCoordinate < 0 || currentYCoordinate <0 
				|| currentXCoordinate> (util.COL-1) || currentYCoordinate > (util.ROW-1)  );
	}

	private void insertElement(int numberOfElement, String elementName) {
		
		int row, col, i = 0;
		
		WorldCell cell;
		
		while(i<numberOfElement) {

			row = getRandomNumber(util.ROW-1);
			
			col = getRandomNumber(util.COL-1);
			
			cell = wamWorld.get(row).get(col);
			
			if(cell.getCellElement().equals(util.EMPTY)) {
				
				cell.setCellElement(elementName);
				
				i++;
				
			}
			
		}
		
	}

	
	private void initializeWampusWorld() {
		
		for(int i=0;i<util.ROW;i++) {
			
			ArrayList< WorldCell > rowList = new ArrayList<>();
			
			for(int j=0;j<util.COL;j++)
				
				rowList.add(new WorldCell(i, j, util.EMPTY, util.EMPTY));
				
			wamWorld.add(rowList);
			
		}
		
	}
	
	
	private int getRandomNumber(int limit) {
		
		Random rand = new Random();

		int  n = rand.nextInt(limit) + 1;
		
		return n;
		
	}

}
