package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.bord.Bord;

public class Fregat extends Ship {
	private static final int length = 3;

	public Fregat(int d, int startX, int startY, Bord bord) {
		super(length, d, startX, startY, bord);
	}
}
