package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.gfx.Assets;
import dev.adrivankempen.zeeslag.gfx.ImageLoader;

public class Bord {
	@SuppressWarnings("unused")
	private Object[][] tiles;
	@SuppressWarnings("unused")
	private int x, y;
	private int rand = 20;
	private int size = 40;
	
	public Bord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.drawImage(ImageLoader.loadImage("/textures/Bord.png"), 0, 0, null);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				g.drawImage(Assets.leeg, (rand + i * size), (rand + j * size), null);
			}
		}
	}
}
