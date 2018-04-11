package dev.adrivankempen.zeeslag.states;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.gfx.Assets;
import dev.adrivankempen.zeeslag.gfx.ImageLoader;

public class EndGameState extends State {
	int playW = 80;
	int playH = 40;
	int playX = handler.getGame().getWidth() / 2 - (playW / 2);
	int playY = handler.getGame().getHeight() / 2 - (playH / 2) + 150;

	public EndGameState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		if (handler.getMouseManager().getLeftPressed()) {
			handler.getMouseManager().setLeftPressed();
			if (handler.getMouseManager().getMouseX() >= playX && handler.getMouseManager().getMouseX() <= playX + playW
					&& handler.getMouseManager().getMouseY() >= playY
					&& handler.getMouseManager().getMouseY() <= playY + playH) {
				State.setState(handler.getGame().getGameState());
				handler.setRestart(true);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		Font fnt0 = new Font("Arial", Font.BOLD, 50);
		g.setFont(fnt0);
		if (handler.getWinP1()) {
			FontMetrics fm = g.getFontMetrics();
			String s = "Je hebt gewonnen";
			int x = (handler.getGame().getWidth() - fm.stringWidth(s)) / 2;
			int y = (fm.getAscent() + (handler.getGame().getHeight() - (fm.getAscent() + fm.getDescent())) / 2) - 200;
			g.drawImage(ImageLoader.loadImage("/textures/bgW.png"), 0, 0, null);
			g.drawString(s, x, y);
		} else if (handler.getWinP2()) {
			FontMetrics fm = g.getFontMetrics();
			String s = "De computer heeft gewonnen";
			int x = (handler.getGame().getWidth() - fm.stringWidth(s)) / 2;
			int y = (fm.getAscent() + (handler.getGame().getHeight() - (fm.getAscent() + fm.getDescent())) / 2) - 200;
			g.drawImage(ImageLoader.loadImage("/textures/bgV.png"), 0, 0, null);
			g.drawString(s, x, y);
		}
		g.drawImage(Assets.play, playX, playY, null);
	}

}
