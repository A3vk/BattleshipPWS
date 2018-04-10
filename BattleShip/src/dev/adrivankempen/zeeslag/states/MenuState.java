package dev.adrivankempen.zeeslag.states;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.gfx.Assets;
import dev.adrivankempen.zeeslag.gfx.ImageLoader;

/**
 * de state waarbij je het spel kan beginnen (en instellingen aan kan passen)
 */
public class MenuState extends State {
	int playW = 80;
	int playH = 40;
	int playX = handler.getGame().getWidth() / 2 - (playW / 2);
	int playY = handler.getGame().getHeight() / 2 - (playH / 2) + 150;

	public MenuState(Handler handler) {
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
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ImageLoader.loadImage("/textures/bg.png"), 0, 0, null);
		g.drawImage(Assets.play, playX, playY, null);
	}

}
