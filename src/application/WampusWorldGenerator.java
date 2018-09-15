package application;

import Util.util;
import java.util.ArrayList;
import java.util.Random;

public class WampusWorldGenerator {
	
	private ArrayList< ArrayList< WorldCell > > wamWorld = new ArrayList<>();
		
	public ArrayList< ArrayList< WorldCell > >  getWampusWorld() {
		
		initializeWampusWorld();
		
		insertElement(util.NUMBER_OF_GOLD,util.GOLD,util.GLITTER);
		
		insertElement(util.NUMBER_OF_WAMPUS,util.WAMPUS,util.STENCH);
		
		insertElement(util.NUMBER_OF_PIT,util.PIT,util.BREEZE);
		
		return wamWorld;
		
	}
	
	private void initializeWampusWorld() {
		
		for(int i=0;i<util.ROW;i++) {
			
			ArrayList< WorldCell > rowList = new ArrayList<>();
			
			for(int j=0;j<util.COL;j++)
				
				rowList.add(new WorldCell(i, j, util.EMPTY ));
				
			wamWorld.add(rowList);
			
		}
		
	}
	
	private int getRandomNumber(int limit) {
		
		Random rand = new Random();

		int  n = rand.nextInt(limit) + 1;
		
		return n;
		
	}
	
	private void insertElement(int numberOfElement, String elementName,String effect) {
		
		int row, col, i = 0;
		
		WorldCell cell;
		
		while(i<numberOfElement) {

			row = getRandomNumber(util.ROW-1);
			
			col = getRandomNumber(util.COL-1);
			
			cell = wamWorld.get(row).get(col);
			
			if(cell.getCellElement().equals(util.EMPTY)) {
				
				cell.setCellElement(elementName);
				
				if(elementName==util.GOLD) cell.setCellEffect(effect);
				
				else insertCellEffect(row, col, effect);
				
				i++;
				
			}
			
		}
		
	}


	private void insertCellEffect(int row, int col, String effect) {
		
		int currentx,currenty;
		
		for(int i=0;i<util.DIRECTION;i++) {
			
			currentx = row + util.x[i];
			
			currenty = col + util.y[i];
			
			if(!isValidIndex(currentx, currenty) ) {
				
				WorldCell cell = wamWorld.get(currentx).get(currenty);
				
				cell.setCellEffect(effect);
				
			}
			
		}
		
	}
	
	private boolean isValidIndex(int currentXCoordinate,int currentYCoordinate) {
		
		return ( currentXCoordinate < 0 || currentYCoordinate <0 
				|| currentXCoordinate> (util.COL-1) || currentYCoordinate > (util.ROW-1)  );
	}


}
