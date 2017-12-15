package dev.adrivankempen.zeeslag.states;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import dev.adrivankempen.zeeslag.Handler;

public class EndGameState extends State {

	public EndGameState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		Font fnt0 = new Font("Arial", Font.BOLD, 50);
		g.setFont(fnt0);
		if(handler.getWinP1()) {
			FontMetrics fm = g.getFontMetrics();
			String s = "Speler 1 heeft gewonnen";
			int x = (handler.getGame().getWidth() - fm.stringWidth(s)) / 2;
			int y = (fm.getAscent() + (handler.getGame().getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
			g.drawString(s, x, y);
		} else if(handler.getWinP2()) {
			FontMetrics fm = g.getFontMetrics();
			String s = "Speler 2 heeft gewonnen";
			int x = (handler.getGame().getWidth() - fm.stringWidth(s)) / 2;
			int y = fm.getAscent() + (handler.getGame().getHeight() - (fm.getAscent() + fm.getDescent())) / 2;
			g.drawString(s, x, y);
		}
	}

}
