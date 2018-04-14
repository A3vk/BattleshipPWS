package dev.adrivankempen.zeeslag.states;

import java.awt.Font;
import java.awt.Graphics;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.bord.SpelerBord;
import dev.adrivankempen.zeeslag.game.bord.TegenstanderBord;
import dev.adrivankempen.zeeslag.gfx.ImageLoader;

/** de state die de game zelf regelt */
public class GameState extends State {
	String turn = "";

	public GameState(Handler handler) {
		super(handler);
	}

	// de twee borden uit het spel
	private SpelerBord spelerBord = new SpelerBord(20, 20, handler);
	private TegenstanderBord tegenstanderBord = new TegenstanderBord(460, 20, handler);

	@Override
	public void tick() {
		spelerBord.tick();
		tegenstanderBord.tick();
		if (handler.getTurnP1()) {
			turn = "Je bent aan de beurt";
		} else if (handler.getTurnP2()) {
			turn = "De computer is aan de beurt";
		}
		if (handler.getRestart()) {
			spelerBord.restart();
			tegenstanderBord.restart();
			handler.restart();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ImageLoader.loadImage("/textures/bg.png"), 0, 0, null);
		Font fnt0 = new Font("Arial", Font.PLAIN, 12);
		g.setFont(fnt0);
		g.drawString(turn, 390, 10);
		spelerBord.render(g);
		tegenstanderBord.render(g);
	}

}
