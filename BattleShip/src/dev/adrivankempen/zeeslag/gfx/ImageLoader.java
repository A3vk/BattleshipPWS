package dev.adrivankempen.zeeslag.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**laad een afbeelding in zodat je de afbeelding vervolgens kan renderen*/
public class ImageLoader {
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			return null;
		}
	}
}
