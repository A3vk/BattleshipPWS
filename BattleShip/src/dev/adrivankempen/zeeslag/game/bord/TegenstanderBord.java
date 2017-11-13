package dev.adrivankempen.zeeslag.game.bord;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.AI.AI;

public class TegenstanderBord extends Bord{	
	private AI ai;
	
	public TegenstanderBord(int x, int y, Handler handler) {
		super(x, y, handler);
		
		ai = new AI(this);
	}
	
	@Override
	public void tick() {
		if(handler.getSetupFase()) {
			setup();
		}else if(handler.getAttackFase()) {
			attack();
		}
	}
	
	/**regelt de tick() van de setup voor P2*/
	private void setup() {
		if(handler.getTurnP2()) {
			placeShip(this, ai.randomTile(), ai.randomDirection());
		}
	}
	
	/**regelt de tick() van de aanval voor P1*/
	private void attack() {
		//controleer of er met de linker muisknop gedrukt word
		if(handler.getMouseManager().getLeftPressed()) {
			handler.getMouseManager().setLeftPressed();
			try{
				getTile(hover()).attack();
				if(updateShips() == 0)
					System.out.println("P1 heeft gewonnen");
			} catch(NullPointerException e) {}
		}
	}
}
