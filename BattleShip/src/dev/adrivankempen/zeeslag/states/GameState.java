package dev.adrivankempen.zeeslag.states;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.game.bord.SpelerBord;
import dev.adrivankempen.zeeslag.game.bord.TegenstanderBord;

public class GameState extends State {
	private SpelerBord spelerBord = new SpelerBord(20, 20);
	private TegenstanderBord tegenstanderBord = new TegenstanderBord(460, 20);
	
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
