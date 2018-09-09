package application;

public class WorldCell {

	private int x,y;
	
	private String cellElement,cellMessage,cellHide;
	
	public WorldCell(int x, int y, String cellElement, String cellMessage) {
		
		this.x = x;
		this.y = y;
		this.cellElement = cellElement;
		this.cellMessage = cellMessage;
	}

	public String getCellHide() {
		return cellHide;
	}

	public void setCellHide(String cellHide) {
		this.cellHide = cellHide;
	}

	public void setCellElement(String cellElement) {
		this.cellElement = cellElement;
	}

	public void setCellMessage(String cellMessage) {
		this.cellMessage = cellMessage;
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

	public String getCellMessage() {
		return cellMessage;
	}
	
	
	
	
}
