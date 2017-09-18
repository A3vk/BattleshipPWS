package dev.adrivankempen.zeeslag.states;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.game.Bord;

public class GameState extends State {
	private Bord bord = new Bord(0, 0);

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		bord.render(g);
	}
	
}
