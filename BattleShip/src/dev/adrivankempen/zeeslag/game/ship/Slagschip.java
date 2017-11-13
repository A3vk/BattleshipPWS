package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.bord.Bord;

public class Slagschip extends Ship {
	//De lengte van het schip
	private static final int length = 5;

	public Slagschip(int d, Tile tile, boolean visible, Bord bord) {
		super(length, d, tile, visible, bord);
	}
}
