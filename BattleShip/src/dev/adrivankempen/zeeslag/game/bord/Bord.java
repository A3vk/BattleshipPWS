package dev.adrivankempen.zeeslag.game.bord;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.ship.Fregat;
import dev.adrivankempen.zeeslag.game.ship.Kruiser;
import dev.adrivankempen.zeeslag.game.ship.Mijnveger;
import dev.adrivankempen.zeeslag.game.ship.Slagschip;
import dev.adrivankempen.zeeslag.gfx.Assets;
import dev.adrivankempen.zeeslag.gfx.ImageLoader;

public class Bord {
	/*
	 * SIZE: de grote van het bord in de lengte en breedte
	 * bordX, bordY: de x en y positie van het bord
	 * direction: de richting waarop de schepen op dit moment geplaatst worden (0 = horizontaal & 1 = verticaal)
	 * currentShip: de richting van het schip dat nu geplaatst wordt
	 * currentLength: de lengte van het schip dat nu geplaatst wordt
	 * oldD, oldL, oldX, oldY: de oude informatie om de preview weg te halen
	 * shipsLeft: hoeveel schepen er nog over zijn
	 * 
	 * slagschip: een 5 lang schip, bewaard de schip class zodat die opnieuw opgeroepen kan worden
	 * kruiser: een 4 lang schip, bewaard de schip class zodat die opnieuw opgeroepen kan worden
	 * fregat1: een 3 lang schip, bewaard de schip class zodat die opnieuw opgeroepen kan worden
	 * fregat2: een 3 lang schip, bewaard de schip class zodat die opnieuw opgeroepen kan worden
	 * mijnveger: een 2 lang schip, bewaard de schip class zodat die opnieuw opgeroepen kan worden
	 * 
	 * visible: bepaald ofdat de schepen die op dit moment geplaatst worden zichtbaar zijn
	 * 
	 * tiles: het coördinaten systeem van het bord
	 * handler: algemenen variabelen en de inputs
	 * tempImg: het array dat gebruikt wordt om de preview te laten werken
	 */
	private final int SIZE = 10;
	private int bordX, bordY;
	protected int direction = 0;
	private int currentShip = 0;
	private int currentLength = 5;
	private int oldD, oldL, oldX, oldY;
	private int shipsLeft = 5;
	
	private Slagschip slagschip;
	private Kruiser kruiser;
	private Fregat fregat1, fregat2;
	private Mijnveger mijnveger;
	
	private boolean visible = false;
	
	protected Tile[][] tiles;
	protected Handler handler;
	private BufferedImage[] tempImg = {Assets.leeg, Assets.leeg, Assets.leeg, Assets.leeg, Assets.leeg};
	
	/**creëer het bord*/
	public Bord(int x, int y, Handler handler) {
		this.bordX = x;
		this.bordY = y;
		this.handler = handler;
		
		//stel de groote van het bord in
		tiles = new Tile[SIZE][SIZE];
		
		//creëer voor elke positie in Tile[][] een Tile
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[i][j] = new Tile(i, j, x, y, handler);
			}
		}
	}
	
	/**algemene tick functie*/
	public void tick() {}
	
	/**algemene render functie */
	public void render(Graphics g) {
		//render de buiten rand van het bord
		g.drawImage(ImageLoader.loadImage("/textures/Bord.png"), bordX, bordY, null);
		
		//render alle Tiles
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				tiles[i][j].render(g);
			}
		}
	}
	
	/**return de tegel waar op dit moment overheen bewogen wordt*/
	protected String hover() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(tiles[i][j].hover() != null) {
					return tiles[i][j].hover();
				}
			}
		}
		return null;
	}	
	
	/**regelt het plaatsen en creëren van de schepen*/
	public void placeShip(Bord bord, Tile tile, int d) {
		int x = tile.getCoorX();
		int y = tile.getCoorY();
		
		//controleer welk schip geplaatst moet worden
		if(currentShip == 0) {
			//controleer ofdat het schip geplaatst kan worden
			if(checkPosition(x, y, d)) {
				//maak een niew schip
				slagschip = new Slagschip(d, tile, visible, bord);
				//pas de lengte aan van het volgende schip en ga naar het vogende schip
				currentLength--;
				currentShip++;
			}
		} else if(currentShip == 1) {
			if(checkPosition(x, y, d)) {
				kruiser = new Kruiser(d, tile, visible, bord);
				currentLength--;
				currentShip++;
			}
		} else if(currentShip == 2) {
			if(checkPosition(x, y, d)) {
				fregat1 = new Fregat(d, tile, visible, bord);
				currentShip++;
			}
		} else if(currentShip == 3) {
			if(checkPosition(x, y, d)) {
				fregat2 = new Fregat(d, tile, visible, bord);
				currentLength--;
				currentShip++;
			}
		} else if(currentShip == 4) {
			if(checkPosition(x, y, d)) {
				mijnveger = new Mijnveger(d, tile, visible, bord);
				currentShip++;
				if(handler.getTurnP2()) {
					handler.switchFase();
				}
				handler.switchTurn();
			}
		}
	}
	
	/**creëert een preview op het bord waar de muis nu is*/
	protected void preview(int startX, int startY, Bord bord) {
		//controleer de richting van de vorige preview
		if(oldD == 0) {
			//loop door de Tiles van de vorige preview
			for(int i = 0; i < oldL; i++) {
				try {
					//contoleer ofdat het schip net is geplaatst haal anders de preview weg
					if(!getTiles()[oldX + i][oldY].getHasShip())
						getTiles()[oldX + i][oldY].setImg(tempImg[i]);
				} catch(ArrayIndexOutOfBoundsException e) {}
			} 
		} else if(oldD == 1) {
			for(int i = 0; i < oldL; i++) {
				try {
					if(!getTiles()[oldX][oldY + i].getHasShip())
						getTiles()[oldX][oldY + i].setImg(tempImg[i]);
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
		
		//update de oude variabelen
		oldX = startX;
		oldY = startY;
		oldD = direction;
		oldL = currentLength;
		
		//controleer ofdat er een preview geplaatst kan worden
		if(checkPosition(oldX, oldY, oldD)) {
			//controleer de richting waaarin de preview geplaatst moet worden
			if(direction == 0){
				//loop door de Tiles waar de preview geplaatst moet worden
				for(int i = 0; i < currentLength; i++) {
					//maak een back-up van de tegels waarop de preview geplaatst wordt
					if(currentLength != 5) {
						tempImg[i] = getTiles()[oldX + i][oldY].getImg();
					}
					//plaats het schip
					if(i == 0) {
						getTiles()[oldX + i][oldY].setImg(Assets.shipBW);
					} else if(i > 0 && i < currentLength - 1) {
						getTiles()[oldX + i][oldY].setImg(Assets.shipMH);
					} else if(i == currentLength - 1) {
						getTiles()[oldX + i][oldY].setImg(Assets.shipBO);
					}
				}
			} else if(direction == 1) {
				for(int i = 0; i < currentLength; i++) {
					tempImg[i] = getTiles()[oldX][oldY + i].getImg();
					if(i == 0) {
						getTiles()[oldX][oldY + i].setImg(Assets.shipBN);
					} else if(i > 0 && i < currentLength - 1) {
						getTiles()[oldX][oldY + i].setImg(Assets.shipMV);
					} else if(i == currentLength - 1) {
						getTiles()[oldX ][oldY + i].setImg(Assets.shipBZ);
					}
				}
			}
		}
	}

	/**controleer ofdat er een schip geplaatst kan worden op de huidige positie*/
	private boolean checkPosition(int x, int y, int d) {
		//controleer de richting waarin het schip geplaatst wordt
		if(d == 0) {
			//kijk op de tegels van en rondom het schip
			for(int i = -1; i < currentLength + 1; i++) {
				for(int j = -1; j <= 1; j++) {
					try {
						//contoleer ofdat er nog geen schip staat
						if(!getTiles()[x + i][y + j].getCanPlace()) {
							return false;
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						//controleer ofdat de muis binnen het bord is
						if(x < 0 && y < 0 || x + currentLength - 1 > 9 || y > 9) {
							return false;
						}
					}
				}
			}
		} else if(d == 1) {
			for(int i = -1; i < currentLength + 1; i++) {
				for(int j = -1; j <= 1; j++) {
					try {
						if(!getTiles()[x + j][y + i].getCanPlace()) {
							return false;
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						if(x < 0 && y < 0 || x > 9 || y + currentLength - 1 > 9) {
							return false;
						}
					}
				}
			}
		}
		//als er niet eerder false terugegeven wordt kan er een schip geplaatst worden
		return true;
	}
	
	/**controleer ofdat een schip na de aanval gezonken is*/
	protected int updateShips() {
		//update het schip
		if(slagschip.update())
			//als het schip gezonken is haal 1 van shipsLeft af
			shipsLeft--;
		else if(kruiser.update())
			shipsLeft--;
		else if(fregat1.update())
			shipsLeft--;
		else if(fregat2.update())
			shipsLeft--;
		else if(mijnveger.update())
			shipsLeft--;
		
		//return het aantal schepen dat nog niet gezonken is
		return shipsLeft;
	}
	
	/**krijg een Tile door middel van een Id*/
	public Tile getTile(String Id) {	
		//loop door alle Tiles heen
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				//controleer of dat een Id overeenkomt zo ja return die Tile
				if (tiles[i][j].getId().equals(Id)) {
					return tiles[i][j];
				}
			}
		}
		//als er geen Tile is met het opgegeven Id return null
		return null;
	}
	
	public Tile translate(Tile tile, int x, int y) {
		return tiles[tile.getCoorX() + x][tile.getCoorY() + y];
	}
	
	public void changeImg(String Id, BufferedImage img) {
		getTile(Id).setImg(img);
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
}
