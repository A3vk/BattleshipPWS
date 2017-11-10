package dev.adrivankempen.zeeslag.gfx;

import java.awt.image.BufferedImage;

/**deze functie laat je de afbeelding van een spritesheet 'handmatig' splitsen*/
public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
