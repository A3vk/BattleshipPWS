package dev.adrivankempen.zeeslag.game.bord;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.gfx.ImageLoader;

public class Bord {
	private final int SIZE = 10;
	
	private int x, y;
	private Tile[][] tiles;
	
	public Bord(int x, int y) {
		this.x = x;
		this.y = y;
		
		tiles = new Tile[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[i][j] = new Tile(i, j, x, y);
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
	
	public Tile getTile(String Id) {		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if (tiles[i][j].getId().equals(Id)) {
					return tiles[i][j];
				}
			}
		}
		return null;
	}
	
	public void changeImg(String Id, BufferedImage img) {
		getTile(Id).setImg(img);
	}
}
