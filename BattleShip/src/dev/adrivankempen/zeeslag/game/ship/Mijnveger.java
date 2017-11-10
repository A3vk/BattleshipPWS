package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.bord.Bord;

public class Mijnveger extends Ship {
	//De lengte van het schip
	private static final int length = 2;

	public Mijnveger(int d, int startX, int startY, Bord bord) {
		super(length, d, startX, startY, bord);
	}
}
