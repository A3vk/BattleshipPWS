package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.gfx.ImageLoader;

public class Bord {
	private final int SIZE = 10;
	private final int RAND = 20;
	
	private int x, y;
	private Tile[][] tiles;
	private int startX, startY;
	
	public Bord(int x, int y) {
		this.x = x;
		this.y = y;
		
		startX = x + RAND;
		startY = y + RAND;
		
		tiles = new Tile[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[i][j] = new Tile(startX + 40 * i, startY + 40 * j);
			}
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(ImageLoader.loadImage("/textures/Bord.png"), x, y, null);
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[i][j].render(g);
			}
		}
	}
}
