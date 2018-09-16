package application;

import java.util.HashSet;

import Util.util;


public class WorldCell {

	private int x;
	
	private int y;
	
	private int stenchCount=0;
	
	private boolean isVisited;
	
	private String cellElement;
	
	public HashSet<String> cellEffectList = new HashSet<>();
	
	public WorldCell(int x, int y, String cellElement) {
		
		this.x = x;
		this.y = y;
		this.cellElement = cellElement;
		
	}
	
	
	public void setIsVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
	public boolean getIsVisited() {
		return this.isVisited;
	}
	
	
	public void setCellEffect(String effect) {
		this.cellEffectList.add(effect);
		if(util.STENCH.equals(effect)) stenchCount++;
	}

	public void setCellElement(String cellElement) {
		this.cellElement = cellElement;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public String getCellElement() {
		return cellElement;
	}

	public String getCellEffect() {
		
		String cellEffect = "";
		
		for(String effect: cellEffectList) if(!effect.equals(util.GLITTER))cellEffect += effect+"\n";
		
		return cellEffect;
	
	}
	
	public void removeCellEffect() {
		
		if(cellEffectList.contains(util.STENCH)) {
			stenchCount--;
			if(stenchCount==0) cellEffectList.remove(util.STENCH);
			
		}
		
	}
	
}
