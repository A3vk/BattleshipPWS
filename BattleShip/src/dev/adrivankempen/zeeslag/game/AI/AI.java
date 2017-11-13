package dev.adrivankempen.zeeslag.game.AI;

import java.util.Random;

import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.bord.Bord;

public class AI {
	private Bord bord;
	private Tile returnTile;
	
	private int x, y, d;
	
	private Random random = new Random();
	
	public AI(Bord bord) {
		this.bord = bord;
	}
	
	public Tile randomTile() {
		x = random.nextInt(10);
		y = random.nextInt(10);
		
		returnTile = bord.getTiles()[x][y];
		
		return returnTile;
	}
	
	public int randomDirection() {
		d = random.nextInt(2);
		
		return d;
	}
}
