package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.bord.Bord;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Ship {
	private int length, direction, startX, startY;
	private int shipLeft;
	private Tile tile;
	private boolean visible;
	private boolean N = true, O = true, Z = true, W = true, H1 = true, H2 = true, H3 = true, V1 = true, V2 = true, V3 = true;
	private Bord bord;
	
	/*
	 * length is de lengte van het schip
	 * direction is de richting het schip wordt geplaatst (0 voor horizontaal & 1 voor verticaal
	 * startX en startY geven de begin positie van het schip aan
	 * bord geeft aan op welk van de 2 borden het schip geplaatst wordt
	 */
	public Ship(int l, int d, Tile tile, boolean visible, Bord bord) {
		length = l;
		direction = d;
		this.tile = tile;
		this.visible = visible;
		this.bord = bord;
		
		startX = tile.getCoorX();
		startY = tile.getCoorY();
		
		shipLeft = length;
		
		if(length == 4) {
			H3 = false;
			V3 = false;
		} else if(length == 3) {
			H2 = false;
			H3 = false;
			V2 = false;
			V3 = false;
		} else if(length ==2) {
			H1 = false;
			H2 = false;
			H3 = false;
			V1 = false;
			V2 = false;
			V3 = false;
		}
		
		placeShip();
	}
	
	/**De placeShip() functie zorgt ervoor dat de variabelen in de Tile worden
	 * aangepast en dat de afbeeldingen in de Tile worden aangepast*/
	private void placeShip() {
		//controleer de richting
		if(direction == 0) {
			//loop door alle tegels waar het schip geplaatst moet worden
			for(int i = 0; i < length; i++) {
				try {
					//verander de variabelen in de Tile
					bord.getTiles()[startX + i][startY].setCanPlace(false);
					bord.getTiles()[startX + i][startY].setHasShip(true);
					//verander de afbeelding van de Tile
					if(i == 0) {
						bord.getTiles()[startX + i][startY].setPiece('W');
						if(visible)
							bord.getTiles()[startX + i][startY].setImg(Assets.shipBW);
					}else if(i > 0 && i < length - 1) {
						bord.getTiles()[startX + i][startY].setPiece('H');
						if(visible)
							bord.getTiles()[startX + i][startY].setImg(Assets.shipMH);
					}else if(i == length - 1) {
						bord.getTiles()[startX + i][startY].setPiece('O');
						if(visible)
							bord.getTiles()[startX + i][startY].setImg(Assets.shipBO);
					}
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		//controleer de richting
		} else if(direction == 1) {
			//loop door alle tegels waar het schip geplaatst moet worden
			for(int i = 0; i < length; i++) {
				try {
					//verander de variabelen in de Tile
					bord.getTiles()[startX][startY + i].setCanPlace(false);
					bord.getTiles()[startX][startY + i].setHasShip(true);
					//verander de afbeelding van de Tile
					if(i == 0) {
						bord.getTiles()[startX][startY + i].setPiece('N');
						if(visible)
							bord.getTiles()[startX][startY + i].setImg(Assets.shipBN);
					}else if(i > 0 && i < length - 1) {
						bord.getTiles()[startX][startY + i].setPiece('V');
						if(visible)
							bord.getTiles()[startX][startY + i].setImg(Assets.shipMV);
					}else if(i == length - 1) {
						bord.getTiles()[startX][startY + i].setPiece('Z');
						if(visible)
							bord.getTiles()[startX][startY + i].setImg(Assets.shipBZ);
					}
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
	}
	
	public boolean update() {
		if(shipLeft != 0) {
			if(direction == 0) {
				for(int i = 0; i < length; i++) {
					if(i == 0) {
						if(bord.getTiles()[startX + i][startY].getImg() == Assets.HshipBW)
							if(W) {
								W = false;
								shipLeft--;
							}
					}else if(i > 0 && i < length - 1) {
						if(bord.getTiles()[startX + i][startY].getImg() == Assets.HshipMH)
							if(H1) {
								H1 = false;
								shipLeft--;
							} else if(H2) {
								H2 = false;
								shipLeft--;
							} else if(H3) {
								H3 = false;
								shipLeft--;
							}
					}else if(i == length - 1) {
						if(bord.getTiles()[startX + i][startY].getImg() == Assets.HshipBO)
							if(O) {
								O = false;
								shipLeft--;
							}
					}
				}
			} else if(direction == 1) {
				for(int i = 0; i < length; i++) {
					if(i == 0) {
						if(bord.getTiles()[startX][startY + i].getImg() == Assets.HshipBN)
							if(N) {
								N = false;
								shipLeft--;
							}
					}else if(i > 0 && i < length - 1) {
						if(bord.getTiles()[startX][startY + i].getImg() == Assets.HshipMV)
							if(V1) {
								V1 = false;
								shipLeft--;
							} else if(V2) {
								V2 = false;
								shipLeft--;
							} else if(V3) {
								V3 = false;
								shipLeft--;
							}
					}else if(i == length - 1) {
						if(bord.getTiles()[startX][startY + i].getImg() == Assets.HshipBZ)
							if(Z) {
								Z = false;
								shipLeft--;
							}
					}
				}
			}
		
			if(shipLeft == 0) {
				sink();
				return true;
			}
		}
		return false;
	}
	
	private void sink() {
		if(direction == 0) {
			for(int i = 0; i < length; i++) {
					if(i == 0) {
						bord.getTiles()[startX + i][startY].setImg(Assets.SshipBW);
					}else if(i > 0 && i < length - 1) {
						bord.getTiles()[startX + i][startY].setImg(Assets.SshipMH);
					}else if(i == length - 1) {
						bord.getTiles()[startX + i][startY].setImg(Assets.SshipBO);
					}
			}
		} else if(direction == 1) {
			for(int i = 0; i < length; i++) {
				if(i == 0) {
					bord.getTiles()[startX][startY + i].setImg(Assets.SshipBN);
				}else if(i > 0 && i < length - 1) {
						bord.getTiles()[startX][startY + i].setImg(Assets.SshipMV);
				}else if(i == length - 1) {
						bord.getTiles()[startX][startY + i].setImg(Assets.SshipBZ);
				}
			}
		}
	}
	
	/**return de X positie van het schip */
	public Tile getStartTile(){
		return tile;
	}
}