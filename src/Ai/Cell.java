package Ai;

import java.util.ArrayList;

import Util.util;

public class Cell {
	
	private String currentStatus;
	
	private boolean isExplore;
	
	private ArrayList<String> cellEffect = new ArrayList<>();
	
	public Cell(String currentStatus,boolean isExplore) {
		this.currentStatus = currentStatus;
		this.isExplore = isExplore;
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
	
	public boolean isContainStench() {
		return cellEffect.contains(util.STENCH);
	}

	public boolean isContainBreeze() {
		return cellEffect.contains(util.BREEZE);
	}

}
