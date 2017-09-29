package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Tile {	
	private final int WIDTH = 40;
	private final int RAND = 20;
	private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	
	private int coorX, coorY;
	private int x, y;
	private String Id;
	private boolean canPlace = true;
	
	private BufferedImage img = Assets.leeg;
	private BufferedImage tempImg = img;
	
	private Handler handler;
	
	private boolean clicked = false;
	
	public Tile(int x, int y, int startX, int startY , Handler handler) {
		coorX = x;
		coorY = y;
		
		this.x = RAND + WIDTH * x + startX;
		this.y = RAND + WIDTH * y + startY;
		
		this.handler = handler;
		
		Id = createId();
	}
	
	public void tick() {
		hover();
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

	public void setCanPlace(boolean bool) {
		setImg(Assets.full);
		canPlace = bool;
	}

	public BufferedImage getTempImg() {
		return tempImg;
	}

	public void setTempImg(BufferedImage tempImg) {
		this.tempImg = tempImg;
	}
}
