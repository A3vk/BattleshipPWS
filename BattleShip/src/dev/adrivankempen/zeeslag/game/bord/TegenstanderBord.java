package dev.adrivankempen.zeeslag.game.bord;

import dev.adrivankempen.zeeslag.Handler;

public class TegenstanderBord extends Bord{	
	public TegenstanderBord(int x, int y, Handler handler) {
		super(x, y, handler);
	}
	
	@Override
	public void tick() {
		//Als de aanval fase begint kan op dit scherm aangevallen worden door P1
		if(handler.getAttackFase()) {
			//controleer of er met de linker muisknop gedrukt word
			if(handler.getMouseManager().getLeftPressed()) {
				handler.getMouseManager().setLeftPressed();
				try{
					getTile(hover()).attack();
				} catch(NullPointerException e) {}
			}
		}
	}
}
