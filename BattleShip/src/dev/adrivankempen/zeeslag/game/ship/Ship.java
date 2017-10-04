package dev.adrivankempen.zeeslag.game.ship;

import java.awt.image.BufferedImage;

import dev.adrivankempen.zeeslag.game.bord.Bord;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Ship {
	private int length, direction, startX, startY;
	private boolean preview = false;
	private BufferedImage[] tempImg = new BufferedImage[5];
	private Bord bord;
	
	public Ship(int l, int d, int startX, int startY, Bord bord) {
		length = l;
		direction = d; //d = 0 horizontaal & d = 1 verticaal
		this.startX = startX;
		this.startY = startY;
		this.bord = bord;
		
		for(int i = 0; i < tempImg.length; i++) {
			tempImg[i] = Assets.leeg;
		}
		
		placeShip();
	}
	
	public boolean checkPosition(int d, int l, int x, int y, Bord b) {
		if(d == 0) {
			for(int i = -1; i < l + 1; i++) {
				for(int j = -1; j <= 1; j++) {
					try {
						if(b.getTiles()[x + i][y + j].getCanPlace() == false) {
							return false;
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						if((x < 0 && y < 0) || (x + l - 1 > 9 || y > 9)) {
							return false;
						}
					}
				}
			}
		} else if(d == 1) {
			for(int i = -1; i < l + 1; i++) {
				for(int j = -1; j <= 1; j++) {
					try {
						if(b.getTiles()[x + j][y + i].getCanPlace() == false) {
							return false;
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						if((x < 0 && y < 0) || (x > 9 || y + l - 1 > 9)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void placeShip() {
		if(direction == 0) {
			if(checkPosition(direction, length, startX, startY, bord)) {
				for(int i = 0; i < length; i++) {
					try {
						if(preview == false) {
							bord.getTiles()[startX + i][startY].setCanPlace(false);
						}
						if(i == 0) 
							bord.getTiles()[startX + i][startY].setImg(Assets.shipBW);
						else if(i > 0 && i < length - 1)
							bord.getTiles()[startX + i][startY].setImg(Assets.shipMH);
						else if(i == length - 1)
							bord.getTiles()[startX + i][startY].setImg(Assets.shipBO);
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
			}
		} else if(direction == 1) {
			if(checkPosition(direction, length, startX, startY, bord)) {
				for(int i = 0; i < length; i++) {
					try {
						if(preview == false) {
							bord.getTiles()[startX][startY + i].setCanPlace(false);
						}
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
	
	public void preview(int d, int x, int y) {		
		if(d != startX || x != startX || y != startX) {
			if(direction == 0){
				for(int i = 0; i < length; i++) {
					try {
						bord.getTiles()[startX + i][startY].setImg(tempImg[i]);
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
			} else if(direction == 1) {
				for(int i = 0; i < length; i++) {
					try {
						bord.getTiles()[startX][startY + i].setImg(tempImg[i]);
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
			}
			
			direction = d;
			startX = x;
			startY = y;
		
			if(direction == 0){
				for(int i = 0; i < length; i++) {
					try {
						tempImg[i] = bord.getTiles()[startX + i][startY].getImg();
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
			} else if(direction == 1) {
				for(int i = 0; i < length; i++) {
					try {
						tempImg[i] = bord.getTiles()[startX][startY + i].getImg();
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
		preview = true;
		placeShip();
		preview = false;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
}