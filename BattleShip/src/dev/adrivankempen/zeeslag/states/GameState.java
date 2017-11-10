package dev.adrivankempen.zeeslag.states;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.bord.SpelerBord;
import dev.adrivankempen.zeeslag.game.bord.TegenstanderBord;

/**de state die de game zelf regelt*/
public class GameState extends State {
	public GameState(Handler handler) {
		super(handler);
	}

	//de twee borden uit het spel
	private SpelerBord spelerBord = new SpelerBord(20, 20, handler);
	private TegenstanderBord tegenstanderBord = new TegenstanderBord(460, 20, handler);
	
	@Override
	public void tick() {
		spelerBord.tick();
		tegenstanderBord.tick();
	}

	@Override
	public void render(Graphics g) {
		spelerBord.render(g);
		tegenstanderBord.render(g);
	}
	
}
