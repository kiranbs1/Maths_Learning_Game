package Maths_Learning_Game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoarder {

	public static BufferedImage LoadImage(String path) {
		try {
			return ImageIO.read(ImageLoarder.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
		
	}
	
}
