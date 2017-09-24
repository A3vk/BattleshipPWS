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
		
		setTiles(new Tile[SIZE][SIZE]);
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				getTiles()[i][j] = new Tile(i, j, x, y);
			}
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(ImageLoader.loadImage("/textures/Bord.png"), x, y, null);
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				getTiles()[i][j].render(g);
			}
		}
	}
	
	public Tile getTile(String Id) {		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if (getTiles()[i][j].getId().equals(Id)) {
					return getTiles()[i][j];
				}
			}
		}
		return null;
	}
	
	public void changeImg(String Id, BufferedImage img) {
		getTile(Id).setImg(img);
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
}
