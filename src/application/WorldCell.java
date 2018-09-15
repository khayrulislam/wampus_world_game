package application;

import java.util.HashSet;

import Util.util;


public class WorldCell {

	private int x,y,count=0;
	
	private boolean isVisited;
	
	private String cellElement;
	
	private HashSet<String> cellEffectList = new HashSet<>();
	
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
		if(util.STENCH.equals(effect)) count++;
	}

	public void setCellElement(String cellElement) {
		this.cellElement = cellElement;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getCellElement() {
		return cellElement;
	}

	public String getCellEffect() {
		
		String cellEffect = "";
		
		for(String effect: cellEffectList) if(!effect.equals(util.GLITTER))cellEffect += effect+"\n";
		
		return cellEffect;
	
	}
	
	public boolean removeCellEffect(String effect) {
		
		if(cellEffectList.contains(effect)) {
			count--;
			if(count==0)return cellEffectList.remove(effect);
			else return true;
		}
		else return false;
		
	}
	
}
