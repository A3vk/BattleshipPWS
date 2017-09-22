package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {	
	private final int WIDTH = 40;
	private final int RAND = 20;
	private final char[] alphabet = "ABCDEFGHIJ".toCharArray();
	
	private int coorX, coorY;
	private int x, y;
	private String Id;
	
	private BufferedImage img;
	
	public Tile(int i, int j, int x, int y, BufferedImage img) {
		coorX = i;
		coorY = j;
		
		this.x = RAND + WIDTH * i + x;
		this.y = RAND + WIDTH * j + y;
		
		this.img = img;
		
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
		
		Id1 = Character.toString(alphabet[coorX]);
		Id2 = Integer.toString(coorY + 1);
		
		return (Id1 + Id2);
	}
	
	public String getId() {
		return Id;
	}
}
