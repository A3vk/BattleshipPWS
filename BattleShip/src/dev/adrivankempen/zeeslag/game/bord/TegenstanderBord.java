package dev.adrivankempen.zeeslag.game.bord;

import dev.adrivankempen.zeeslag.Handler;

public class TegenstanderBord extends Bord{	
	public TegenstanderBord(int x, int y, Handler handler) {
		super(x, y, handler);
	}
	
	@Override
	public void tick() {
		if(handler.getAttackFase()) {
			if(handler.getMouseManager().getLeftPressed()) {
				handler.getMouseManager().setLeftPressed();
				getTile(click()).attack();
			}
		}
	}
}
