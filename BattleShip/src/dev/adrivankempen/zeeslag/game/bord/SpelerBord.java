package dev.adrivankempen.zeeslag.game.bord;

import dev.adrivankempen.zeeslag.game.ship.Ship;

public class SpelerBord extends Bord {
	private Ship ship1;
	private Ship ship2;
	private Ship ship3;
	private Ship ship4;
	
	public SpelerBord(int x, int y) {
		super(x, y);
		
		ship3 = new Ship(4, 1, 5, 0, this);
		ship2 = new Ship(6, 0, 4, 8, this);
		ship1 = new Ship(4, 1, 5, 3, this);
		ship4 = new Ship(4, 0, 4, 4, this);
		
		
	}
	
	public Ship getShip1() {
		return ship1;
	}
	
	public Ship getShip2() {
		return ship2;
	}
	
	public Ship getShip3() {
		return ship3;
	}
	
	public Ship getShip4() {
		return ship4;
	}

}
