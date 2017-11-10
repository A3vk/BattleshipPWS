package dev.adrivankempen.zeeslag.game.bord;

import dev.adrivankempen.zeeslag.Handler;

public class SpelerBord extends Bord {	
	public SpelerBord(int x, int y, Handler handler) {
		super(x, y, handler);
	}
	
	@Override
	public void tick() {
		//controleer ofdat de aanval fase niet begonnen is
		if(!handler.getAttackFase()) {
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
				handler.getMouseManager().setLeftPressed();
				//plaats een schip
				if(hover() != null) {
					placeShip(this);
				}
				
			} else {
				try{
					//laat een preview van het schip zien
					preview(getTile(hover()).getCoorX(), getTile(hover()).getCoorY(), this);
				} catch(NullPointerException e) {}
			}
		}
	}
}