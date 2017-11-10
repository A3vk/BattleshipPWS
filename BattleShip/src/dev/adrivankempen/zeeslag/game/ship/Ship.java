package dev.adrivankempen.zeeslag.game.ship;

import dev.adrivankempen.zeeslag.game.bord.Bord;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Ship {
	private int length, direction, startX, startY;
	private Bord bord;
	
	/*
	 * length is de lengte van het schip
	 * direction is de richting het schip wordt geplaatst (0 voor horizontaal & 1 voor verticaal
	 * startX en startY geven de begin positie van het schip aan
	 * bord geeft aan op welk van de 2 borden het schip geplaatst wordt
	 */
	public Ship(int l, int d, int startX, int startY, Bord bord) {
		length = l;
		direction = d;
		this.startX = startX;
		this.startY = startY;
		this.bord = bord;
		
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
					if(i == 0) 
						bord.getTiles()[startX + i][startY].setImg(Assets.shipBW);
					else if(i > 0 && i < length - 1)
						bord.getTiles()[startX + i][startY].setImg(Assets.shipMH);
					else if(i == length - 1)
						bord.getTiles()[startX + i][startY].setImg(Assets.shipBO);
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
					if(i == 0)
						bord.getTiles()[startX][startY + i].setImg(Assets.shipBN);
					else if(i > 0 && i < length - 1)
						bord.getTiles()[startX][startY + i].setImg(Assets.shipMV);
					else if(i == length - 1)
						bord.getTiles()[startX][startY + i].setImg(Assets.shipBZ);
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
	}
	
	/**return de X positie van het schip */
	public int getStartX() {
		return startX;
	}
	
	/**return de Y positie van het schip*/
	public int getStartY() {
		return startY;
	}
}