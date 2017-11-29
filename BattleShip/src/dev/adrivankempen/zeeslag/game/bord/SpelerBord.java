package dev.adrivankempen.zeeslag.game.bord;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.AI.AI;

public class SpelerBord extends Bord {
	/*
	 * ai: de class die alles van de AI regelt
	 * tile: de tegel die aangevallen wordt	
	 */
	
	private AI ai;
	private Tile tile;
	
	@SuppressWarnings("unused")
	private boolean specialAttack;
	@SuppressWarnings("unused")
	private Tile[] specialAttackTiles = new Tile[5];
	@SuppressWarnings("unused")
	private int currentIndex = 0;
	
	public SpelerBord(int x, int y, Handler handler) {
		super(x, y, handler);
		
		ai = new AI(this);
	}
	
	@Override
	public void tick() {
		//controleer welke fase het is
		if(handler.getSetupFase()) {
			//roep de setup op
			setup();
		} else if(handler.getAttackFase()) {
			//roep de attack op
			attack();
		}
	}
	
	/**regelt het aanvallen van de AI*/
	private void attack() {
		//is het de beurt van P2
		if(handler.getTurnP2()) {
			//creëer een random tegel
			tile = ai.randomTile();
			try{
				//voer de aanval uit
				tile.attack();
//				if(!specialAttack) {
//					if(!tile.getIsShot()) {
//						if(tile.attack()) {
//							specialAttackTiles[currentIndex] = tile;
//							currentIndex++;
//							specialAttack = true;
//						} else {
//							if(specialAttackTiles[0].getIsSunken()) {
//								
//							}
//						}
//					} else {
//						
//					}
					//als alle schepen gezonken zijn heeft P2 gewonnen
					if(updateShips() == 0)
						System.out.println("P2 heeft gewonnen");
					//wissel de beurt
					handler.switchTurn();
//				}
			} catch(NullPointerException e) {}
		}
	}
	
	/**regelt alles wat er in de setup op dit bord voor P1 gebeurt voor de tick()*/
	private void setup() {
		//controleer of er met de rechter muisknop gedrukt word
		if(handler.getMouseManager().getRightPressed()) {
			handler.getMouseManager().setRightPressed();
			//verander de richting
			if(direction == 0) {
				direction = 1;
			}
			else {
				direction = 0;
			}
		//controleer of er met de linker muisknop gedrukt word
		} else if(handler.getMouseManager().getLeftPressed()) {
			if(handler.getTurnP1()) {
				handler.getMouseManager().setLeftPressed();
				//plaats een schip
				if(hover() != null) {
					//plaats een schip voor player 1(0)
					placeShip(this, getTile(hover()), direction);
				}
			}				
		} else if(handler.getTurnP1()) {
			try{
				//laat een preview van het schip zien
				preview(getTile(hover()).getCoorX(), getTile(hover()).getCoorY(), this);
			} catch(NullPointerException e) {}
		}
	}
}