package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.bord.Bord;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Ship {
	/*
	 * length: de lengte van het schip
	 * direction: de richting van het schip
	 * startX: de x coördinaat van het schip
	 * startY: de Y coördinaat van het schip
	 * shipLeft: hoeveel tegels zijn er nog van het schip over
	 * 
	 * tile: de 'start' tegel van het schip
	 * 
	 * visible: is het schip zichtbaar
	 * N, O, Z, W, H1, H2, H3, V1, V2, V3: de Tegels van een schip --> veranderd per richting/lengte
	 * 
	 * bord: een instantie van het bord 
	 */
	private int length, direction, startX, startY;
	private int shipLeft;
	
	private Tile tile;
	
	private boolean visible;
	private boolean N = true, O = true, Z = true, W = true, H1 = true, H2 = true, H3 = true, V1 = true, V2 = true, V3 = true;
	
	private Bord bord;
	
	public Ship(int l, int d, Tile tile, boolean visible, Bord bord) {
		length = l;
		direction = d;
		this.tile = tile;
		this.visible = visible;
		this.bord = bord;
		
		startX = tile.getCoorX();
		startY = tile.getCoorY();
		
		shipLeft = length;
		
		//pas de controle variabelen aan ten opzichte van de lengte
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
	
	/**controleer of dat een deel van het schip is geraakt*/
	public boolean update() {
		//controleer of dat het schip nog niet gezonken is
		if(shipLeft != 0) {
			//controleer de richting
			if(direction == 0) {
				//loop door de lengte
				for(int i = 0; i < length; i++) {
					if(i == 0) {
						//controleer ofdat het schip nog niet is geraakt
						if(bord.getTiles()[startX + i][startY].getImg() == Assets.HshipBW)
							//controleer ofdat de variabelen nog niet is aangepast
							if(W) {
								//pas de variabelen aan
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
			
			//als er geen tegels meer over zijn in het schip laat dan het schip zinken
			if(shipLeft == 0) {
				sink();
				return true;
			}
		}
		return false;
	}
	
	/**laat het schip zinken als er geen tegels meer ongeraakt zijn*/
	private void sink() {
		//controleer de richting
		if(direction == 0) {
			//loop door de lengte
			for(int i = 0; i < length; i++) {
					if(i == 0) {
						//pas de afbeelding van de tegel aan
						bord.getTiles()[startX + i][startY].setImg(Assets.SshipBW);
						//pas het variabelen aan
						bord.getTiles()[startX + i][startY].setIsSunken();
					}else if(i > 0 && i < length - 1) {
						bord.getTiles()[startX + i][startY].setImg(Assets.SshipMH);
						bord.getTiles()[startX + i][startY].setIsSunken();
					}else if(i == length - 1) {
						bord.getTiles()[startX + i][startY].setImg(Assets.SshipBO);
						bord.getTiles()[startX + i][startY].setIsSunken();
					}
			}
		} else if(direction == 1) {
			for(int i = 0; i < length; i++) {
				if(i == 0) {
					bord.getTiles()[startX][startY + i].setImg(Assets.SshipBN);
					bord.getTiles()[startX][startY + i].setIsSunken();
				}else if(i > 0 && i < length - 1) {
						bord.getTiles()[startX][startY + i].setImg(Assets.SshipMV);
						bord.getTiles()[startX][startY + i].setIsSunken();
				}else if(i == length - 1) {
						bord.getTiles()[startX][startY + i].setImg(Assets.SshipBZ);
						bord.getTiles()[startX][startY + i].setIsSunken();
				}
			}
		}
	}
	
	/**return de X positie van het schip */
	public Tile getStartTile(){
		return tile;
	}
}