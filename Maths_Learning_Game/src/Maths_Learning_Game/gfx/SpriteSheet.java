package Maths_Learning_Game.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;	//created from a import
	 
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;  
		//sets the SpriteSheet class variable sheet equal to the sheet passed in through Asset.init()
		
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);	
		//this runs the crop() function for each of the Assets passed through the Asset.init() method
		//the crop function will cut out a part of the image passed through with the size and position being specified
		//by the parameters passed through
	}
	  
}
