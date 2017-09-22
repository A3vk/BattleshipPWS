package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.adrivankempen.zeeslag.gfx.Assets;

public class Tile {	
	private final int WIDTH = 40;
	private final int RAND = 20;
	private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	
	private int coorX, coorY;
	private int x, y;
	private String Id;
	
	private BufferedImage img = Assets.leeg;
	
	public Tile(int x, int y, int startX, int startY) {
		coorX = x;
		coorY = y;
		
		this.x = RAND + WIDTH * x + startX;
		this.y = RAND + WIDTH * y + startY;
		
		Id = createId();
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	public String createId() {
		String Id1;
		String Id2;
		
		Id1 = alphabet[coorX];
		Id2 = Integer.toString(coorY + 1);
		
		return (Id1 + Id2);
	}
	
	public String getId() {
		return Id;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public void setImg(BufferedImage img) {
		this.img = img;
	}
}
