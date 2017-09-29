package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.bord.Bord;

public class Kruiser extends Ship {
	private static final int length = 4;

	public Kruiser(int d, int startX, int startY, Bord bord) {
		super(length, d, startX, startY, bord);
	}
}
