package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.gfx.Assets;

public class Tile {	
	@SuppressWarnings("unused")
	private final int WIDTH = 40;
	
	private int x, y;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.leeg, x, y, null);
	}
	
	public Tile getTile() {
		return(this);
	}
}
