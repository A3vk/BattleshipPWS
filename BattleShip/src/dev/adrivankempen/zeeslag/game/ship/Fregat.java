package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.bord.Bord;

public class Fregat extends Ship {
	//De lengte van het schip
	private static final int length = 3;

	public Fregat(int d, Tile tile, boolean visible, Bord bord) {
		super(length, d, tile, visible, bord);
	}
}
