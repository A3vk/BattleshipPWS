package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Tile {	
	private final int WIDTH = 40;
	private final int RAND = 20;
	private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	
	private int coorX, coorY;
	private int x, y;
	private int rand;
	
	private String Id;
	
	private boolean canPlace = true;
	private boolean hasShip = false;
	private boolean clicked = false;
	private boolean isShot = false;
	
	private BufferedImage img = Assets.leeg;
	private BufferedImage tempImg = img;
	
	private Random random = new Random();
	
	private Handler handler;
	
	public Tile(int x, int y, int startX, int startY , Handler handler) {
		coorX = x;
		coorY = y;
		
		this.x = RAND + WIDTH * x + startX;
		this.y = RAND + WIDTH * y + startY;
		
		this.handler = handler;
		
		Id = createId();
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	public String hover() {
		if(handler.getMouseManager().getMouseX() >= x &&
				handler.getMouseManager().getMouseX() <= x + WIDTH &&
				handler.getMouseManager().getMouseY() >= y &&
				handler.getMouseManager().getMouseY() <= y + WIDTH) {
			return Id;
		}
		return null;
	}
	
	public void attack() {
		if(!isShot) {
			rand = random.nextInt(3) + 1;
			if(rand == 1) {
				setImg(Assets.mis1);
			} else if(rand == 2) {
				setImg(Assets.mis2);
			} else if(rand == 3) {
				setImg(Assets.mis3);
			}
			isShot = true;
		}
	}
	
	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	private String createId() {
		String Id1;
		String Id2;
		
		Id1 = alphabet[coorX];
		Id2 = Integer.toString(coorY + 1);
		
		return (Id1 + Id2);
	}
	
	public int getCoorX() {
		return coorX;
	}
	
	public int getCoorY() {
		return coorY;
	}
	
	public String getId() {
		return Id;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public void setImg(BufferedImage img) {
		this.img = img;
		setTempImg(img);
	}

	public boolean getCanPlace() {
		return canPlace;
	}

	public void setCanPlace(boolean b) {
		canPlace = b;
	}
	
	public boolean getHasShip() {
		return hasShip;
	}

	public void setHasShip(boolean b) {
		hasShip = b;
	}
	
	public boolean getIsShot() {
		return isShot;
	}

	public void setIsShot(boolean b) {
		isShot = b;
	}

	public BufferedImage getTempImg() {
		return tempImg;
	}

	public void setTempImg(BufferedImage tempImg) {
		this.tempImg = tempImg;
	}
}
