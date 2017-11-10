package dev.adrivankempen.zeeslag.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	//De afmetingen van een sprite op het spritesheet
	private static final int width = 40, height = 40;
	
	public static BufferedImage shipBN, shipBO, shipBZ, shipBW, shipMH, shipMV, leeg, mis1, mis2, mis3, full;
	
	/**De init() functie laad alle sprites uit de spritesheet in bij het opstarten van de game*/
	public static void init() {
		//laad het spritesheet in
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet.png"));
		
		//maak van alle apparte afbeeldingen in hun eigen variabele
		leeg = sheet.crop(0, 0, width, height);
		shipBN = sheet.crop(40, 0, width, height);
		shipBO = sheet.crop(80, 0, width, height);
		shipBZ = sheet.crop(120, 0, width, height);
		shipBW = sheet.crop(160, 0, width, height);
		shipMH = sheet.crop(200, 0, width, height);
		shipMV = sheet.crop(240, 0, width, height);
		mis1 = sheet.crop(280, 0, width, height);
		mis2 = sheet.crop(320, 0, width, height);
		mis3 = sheet.crop(360, 0, width, height);
		full = sheet.crop(0, 40, width, height);
	}
}
