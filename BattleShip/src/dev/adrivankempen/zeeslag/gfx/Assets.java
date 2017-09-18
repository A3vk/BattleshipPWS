package dev.adrivankempen.zeeslag.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 40, height = 40;
	
	public static BufferedImage shipBN, shipBO, shipBZ, shipBW, shipMH, shipMV, leeg;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet.png"));
		
		leeg = sheet.crop(0, 0, width, height);
		shipBN = sheet.crop(40, 0, width, height);
		shipBO = sheet.crop(80, 0, width, height);
		shipBZ = sheet.crop(120, 0, width, height);
		shipBW = sheet.crop(160, 0, width, height);
		shipMH = sheet.crop(200, 0, width, height);
		shipMV = sheet.crop(240, 0, width, height);
	}
}
