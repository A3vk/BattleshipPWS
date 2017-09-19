package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.gfx.Assets;
import dev.adrivankempen.zeeslag.gfx.ImageLoader;

public class Bord {
	private Object[][] tiles = new Object[10][10];
	private int x, y;
	private int rand = 20;
	private int size = 40;
	
	public Bord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.drawImage(ImageLoader.loadImage("/textures/Bord.png"), x, y, null);
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles.length; j++) {
				tiles[i][j] = g.drawImage(Assets.leeg, (x + rand + i * size), (y + rand + j * size), null);
			}
		}
	}
}
