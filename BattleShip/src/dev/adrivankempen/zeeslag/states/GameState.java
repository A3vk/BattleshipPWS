package dev.adrivankempen.zeeslag.states;

import java.awt.Font;
import java.awt.Graphics;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.bord.SpelerBord;
import dev.adrivankempen.zeeslag.game.bord.TegenstanderBord;

/**de state die de game zelf regelt*/
public class GameState extends State {
	String turn = "";
	
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
		if(handler.getTurnP1()) {
			turn = "Speler 1 is aan de beurt";
		} else if(handler.getTurnP2()) {
			turn = "Speler 2 is aan de beurt";
		}
	}

	@Override
	public void render(Graphics g) {
		Font fnt0 = new Font("Arial", Font.PLAIN, 12);
		g.setFont(fnt0);
		g.drawString(turn, 390, 10);
		spelerBord.render(g);
		tegenstanderBord.render(g);
	}
	
}
