package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.bord.Bord;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Ship {
	private int length, direction, startX, startY;
	private Bord bord;
	
	private boolean CONTROL = true;
	
	public Ship(int l, int d, int startX, int startY, Bord bord) {
		length = l;
		direction = d; //d = 0 horizontaal & d = 1 verticaal
		this.startX = startX;
		this.startY = startY;
		this.bord = bord;
		
		placeShip();
	}
	
	private void placeShip() {
		if(direction == 0) {
			for(int i = -1; i < length + 1; i++) {
				for(int j = -1; j <= 1; j++) {
					try {
						if(bord.getTiles()[startX + i][startY + j].getCanPlace() == false) {
							CONTROL = false;
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						if(startX <= 0 && startY <= 0 && startX >= 9 - length && startY >= 9 - length) {
							CONTROL = false;
						}
					}
				}
			}
			if(CONTROL) {
				for(int i = -1; i < length + 1; i++) {
					for(int j = -1; j <= 1; j++) {
						try {
							bord.getTiles()[startX + i][startY + j].switchCanPlace(false);
							if(i == 0) 
								bord.getTiles()[startX + i][startY].setImg(Assets.shipBW);
							else if(i > 0 && i < length - 1)
								bord.getTiles()[startX + i][startY].setImg(Assets.shipMH);
							else if(i == length - 1)
								bord.getTiles()[startX + i][startY].setImg(Assets.shipBO);
						} catch(ArrayIndexOutOfBoundsException e) {}
					}
				}
			}
		} else if(direction == 1) {
			for(int i = -1; i < length + 1; i++) {
				for(int j = -1; j <= 1; j++) {
					try {
						if(bord.getTiles()[startX + j][startY + i].getCanPlace() == false) {
							CONTROL = false;
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						if(startX <= 0 && startY <= 0 && startX >= 9 - length && startY >= 9 - length) {
							CONTROL = false;
						}
					}
				}
			}
			if(CONTROL) {
				for(int i = -1; i < length + 1; i++) {
					for(int j = -1; j <= 1; j++) {
						try {
							bord.getTiles()[startX + j][startY + i].switchCanPlace(false);
							if(i == 0)
								bord.getTiles()[startX][startY + i].setImg(Assets.shipBN);
							else if(i > 0 && i < length - 1)
								bord.getTiles()[startX][startY + i].setImg(Assets.shipMV);
							else if(i == length - 1)
								bord.getTiles()[startX][startY + i].setImg(Assets.shipBZ);
						} catch(ArrayIndexOutOfBoundsException e) {}
					}
				}
			}
		}
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
}
