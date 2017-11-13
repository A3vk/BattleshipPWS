package dev.adrivankempen.zeeslag.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	//De afmetingen van een sprite op het spritesheet
	private static final int width = 40, height = 40;
	
	//H staat voor hit & S staat voor sunken
	public static BufferedImage shipBN, shipBO, shipBZ, shipBW, shipMH, shipMV, HshipBN, HshipBO, HshipBZ, HshipBW, HshipMH, HshipMV, SshipBN, SshipBO, SshipBZ, SshipBW, SshipMH, SshipMV, leeg, mis1, mis2, mis3, full;
	
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
		HshipBN = sheet.crop(280, 0, width, height);
		HshipBO = sheet.crop(320, 0, width, height);
		HshipBZ = sheet.crop(360, 0, width, height);
		HshipBW = sheet.crop(0, 40, width, height);
		HshipMH = sheet.crop(40, 40, width, height);
		HshipMV = sheet.crop(80, 40, width, height);
		SshipBN = sheet.crop(120, 40, width, height);
		SshipBO = sheet.crop(160, 40, width, height);
		SshipBZ = sheet.crop(200, 40, width, height);
		SshipBW = sheet.crop(240, 40, width, height);
		SshipMH = sheet.crop(280, 40, width, height);
		SshipMV = sheet.crop(320, 40, width, height);
		mis1 = sheet.crop(360, 40, width, height);
		mis2 = sheet.crop(0, 80, width, height);
		mis3 = sheet.crop(40, 80, width, height);
		full = sheet.crop(80, 80, width, height);
	}
}
