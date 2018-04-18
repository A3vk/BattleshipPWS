package dev.adrivankempen.zeeslag.game.ship;

import java.util.Random;

import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.bord.Bord;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Ship {
	/*
	 * length: de lengte van het schip direction: de richting van het schip
	 * startX: de x coördinaat van het schip startY: de Y coördinaat van het
	 * schip shipLeft: hoeveel tegels zijn er nog van het schip over
	 * 
	 * tile: de 'start' tegel van het schip
	 * 
	 * visible: is het schip zichtbaar N, O, Z, W, H1, H2, H3, V1, V2, V3: de
	 * Tegels van een schip --> veranderd per richting/lengte
	 * 
	 * bord: een instantie van het bord
	 */
	private int length, direction;
	private int shipLeft;

	private Tile tile;

	private boolean visible;

	private Bord bord;
	
	private Random random = new Random();

	public Ship(int l, int d, Tile tile, boolean visible, Bord bord) {
		length = l;
		direction = d;
		this.tile = tile;
		this.visible = visible;
		this.bord = bord;

		shipLeft = length;

		placeShip();
	}

	/**
	 * De placeShip() functie zorgt ervoor dat de variabelen in de Tile worden
	 * aangepast en dat de afbeeldingen in de Tile worden aangepast
	 */
	private void placeShip() {
		// controleer de richting
		if (direction == 0) {
			// loop door alle tegels waar het schip geplaatst moet worden
			for (int i = 0; i < length; i++) {
				try {
					// verander de variabelen in de Tile
					bord.translate(tile, i, 0).setCanPlace(false);
					bord.translate(tile, i, 0).setHasShip(true);
					// verander de afbeelding van de Tile
					if (i == 0) {
						bord.translate(tile, i, 0).setPiece('W');
						if (visible)
							bord.translate(tile, i, 0).setImg(Assets.shipBW);
					} else if (i > 0 && i < length - 1) {
						bord.translate(tile, i, 0).setPiece('H');
						if (visible)
							bord.translate(tile, i, 0).setImg(Assets.shipMH);
					} else if (i == length - 1) {
						bord.translate(tile, i, 0).setPiece('O');
						if (visible)
							bord.translate(tile, i, 0).setImg(Assets.shipBO);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
			// controleer de richting
		} else if (direction == 1) {
			// loop door alle tegels waar het schip geplaatst moet worden
			for (int i = 0; i < length; i++) {
				try {
					// verander de variabelen in de Tile
					bord.translate(tile, 0, i).setCanPlace(false);
					bord.translate(tile, 0, i).setHasShip(true);
					// verander de afbeelding van de Tile
					if (i == 0) {
						bord.translate(tile, 0, i).setPiece('N');
						if (visible)
							bord.translate(tile, 0, i).setImg(Assets.shipBN);
					} else if (i > 0 && i < length - 1) {
						bord.translate(tile, 0, i).setPiece('V');
						if (visible)
							bord.translate(tile, 0, i).setImg(Assets.shipMV);
					} else if (i == length - 1) {
						bord.translate(tile, 0, i).setPiece('Z');
						if (visible)
							bord.translate(tile, 0, i).setImg(Assets.shipBZ);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
	}

	/** controleer of dat een deel van het schip is geraakt */
	public boolean update() {
		// controleer of dat het schip nog niet gezonken is
		if (shipLeft != 0) {
			// controleer de richting
			if (direction == 0) {
				// loop door de lengte
				for (int i = 0; i < length; i++) {
					if (bord.translate(tile, i, 0).getImg() == Assets.hit
							&& !bord.translate(tile, i, 0).getIsUpdated()) {
						bord.translate(tile, i, 0).setIsUpdated(true);
						shipLeft--;
					}
				}
			} else if (direction == 1) {
				for (int i = 0; i < length; i++) {
					if (bord.translate(tile, 0, i).getImg() == Assets.hit
							&& !bord.translate(tile, 0, i).getIsUpdated()) {
						bord.translate(tile, 0, i).setIsUpdated(true);
						shipLeft--;
					}
				}
			}

			// als er geen tegels meer over zijn in het schip laat dan het schip
			// zinken
			if (shipLeft == 0) {
				sink();
				return true;
			}
		}
		return false;
	}

	/** laat het schip zinken als er geen tegels meer ongeraakt zijn */
	private void sink() {
		// controleer de richting
		if (direction == 0) {
			// loop door de lengte
			for (int i = 0; i < length; i++) {
				if (i == 0) {
					// pas de afbeelding van de tegel aan
					bord.translate(tile, i, 0).setImg(Assets.SshipBW);
					// pas het variabelen aan
					bord.translate(tile, i, 0).setIsSunken(true);
				} else if (i > 0 && i < length - 1) {
					bord.translate(tile, i, 0).setImg(Assets.SshipMH);
					bord.translate(tile, i, 0).setIsSunken(true);
				} else if (i == length - 1) {
					bord.translate(tile, i, 0).setImg(Assets.SshipBO);
					bord.translate(tile, i, 0).setIsSunken(true);
				}
			}
		} else if (direction == 1) {
			for (int i = 0; i < length; i++) {
				if (i == 0) {
					bord.translate(tile, 0, i).setImg(Assets.SshipBN);
					bord.translate(tile, 0, i).setIsSunken(true);
				} else if (i > 0 && i < length - 1) {
					bord.translate(tile, 0, i).setImg(Assets.SshipMV);
					bord.translate(tile, 0, i).setIsSunken(true);
				} else if (i == length - 1) {
					bord.translate(tile, 0, i).setImg(Assets.SshipBZ);
					bord.translate(tile, 0, i).setIsSunken(true);
				}
			}
		}

		updateTiles();
	}

	private void updateTiles() {
		if (direction == 1) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= length; j++) {
					try {
						bord.translate(tile, i, j).setCanGetShot(false);
						bord.translate(tile, i, j).setIsShot(true);
						if (i == -1 || i == 1 || j == -1 || j == length) {
							int r = random.nextInt(3);
							if(r == 0) {
								bord.translate(tile, i, j).setImg(Assets.mis1);
							} else if(r == 1) {
								bord.translate(tile, i, j).setImg(Assets.mis2);
							} else if(r == 2) {
								bord.translate(tile, i, j).setImg(Assets.mis3);
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		} else if (direction == 0) {
			for (int i = -1; i <= length; i++) {
				for (int j = -1; j <= 1; j++) {
					try {
						bord.translate(tile, i, j).setCanGetShot(false);
						bord.translate(tile, i, j).setIsShot(true);
						if (i == -1 || i == length || j == -1 || j == 1) {
							int r = random.nextInt(3);
							if(r == 0) {
								bord.translate(tile, i, j).setImg(Assets.mis1);
							} else if(r == 1) {
								bord.translate(tile, i, j).setImg(Assets.mis2);
							} else if(r == 2) {
								bord.translate(tile, i, j).setImg(Assets.mis3);
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		}
	}

	/** return de X positie van het schip */
	public Tile getStartTile() {
		return tile;
	}
}