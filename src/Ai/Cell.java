package Ai;

import java.util.ArrayList;

import Util.util;

public class Cell {
	
	private String currentStatus;
	
	private boolean isExplore,containWampus,containPit;
	
	int count=1;
	
	public int getCount() {
		return count;
	}
	
	public void increment() {
		count++;
	}

	private ArrayList<String> cellEffect = new ArrayList<>();
	
	public Cell(String currentStatus,boolean isExplore) {
		this.currentStatus = currentStatus;
		this.isExplore = isExplore;
		containWampus = true;
		containPit = true;
	}
	

	public void setContainWampus(boolean containWampus) {
		this.containWampus = containWampus;
	}


	public void setContainPit(boolean containPit) {
		this.containPit = containPit;
	}


	public boolean isContainWampus() {
		return containWampus;
	}


	public boolean isContainPit() {
		return containPit;
	}


	public String getCurrentStatus() {
		return currentStatus;
	}

	public boolean isExplore() {
		return isExplore;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setExplore(boolean isExplore) {
		this.isExplore = isExplore;
	}
	
	public void insertEffect(String effect) {
		this.cellEffect.add(effect);
	}
	
	public boolean isContainGold() {
		return cellEffect.contains(util.GLITTER);
	}
	
	public void removeGold() {
		if(cellEffect.contains(util.GLITTER))cellEffect.remove(util.GLITTER);
	}
	
	public boolean isContainStench() {
		return cellEffect.contains(util.STENCH);
	}

	public boolean isContainBreeze() {
		return cellEffect.contains(util.BREEZE);
	}

}
