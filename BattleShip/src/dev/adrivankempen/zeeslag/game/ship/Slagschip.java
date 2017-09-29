package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.bord.Bord;

public class Slagschip extends Ship {
	private static final int length = 5;

	public Slagschip(int d, int startX, int startY, Bord bord) {
		super(length, d, startX, startY, bord);
	}
}