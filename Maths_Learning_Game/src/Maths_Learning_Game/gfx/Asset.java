package Maths_Learning_Game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Asset {
	
	
	public static Font font14,font18, font20, font28, font36, font48;
	
	private static final int width = 32, height = 32;	// Unchangeable variables. (Constants)
	public static BufferedImage dirt, grass, stone, tree; 
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] Button;
	

	public static void init() {  //Runs in Game.init()
		font14 = FontLoader.LoadFont("res/fonts/Magnificent.ttf", 14);
		font18 = FontLoader.LoadFont("res/fonts/Magnificent.ttf", 18);		
		font20 = FontLoader.LoadFont("res/fonts/Magnificent.ttf", 20);		
		font28 = FontLoader.LoadFont("res/fonts/Magnificent.ttf", 28); //28 is font size
		font36 = FontLoader.LoadFont("res/fonts/Magnificent.ttf", 36);
		font48 = FontLoader.LoadFont("res/fonts/Magnificent.ttf", 48);
		SpriteSheet sheet = new SpriteSheet(ImageLoarder.LoadImage("/textures/sheet.png")); 
		
		//created a object of SpriteSheet and passes through a image
		//this declares, where to start cropping(x), same(y), where to end cropping(x), same (y)
		
		//Button 
		//start
		Button = new BufferedImage[1];
		Button[0] = sheet.crop(4*width, 4*height, 2*width, height);
		//player
		player_down = new BufferedImage[2];
		player_down[0] = sheet.crop(4*width, 0, width, height);
		player_down[1] = sheet.crop(5*width, 0, width, height);
		
		player_up = new BufferedImage[2];
		player_up[0] = sheet.crop(6*width, 0, width, height);
		player_up[1] = sheet.crop(7*width, 0, width, height);

		player_right = new BufferedImage[2];
		player_right[0] = sheet.crop(4*width, height, width, height);
		player_right[1] = sheet.crop(5*width, height, width, height);
		
		player_left = new BufferedImage[2];
		player_left[0] = sheet.crop(6*width, height, width, height);
		player_left[1] = sheet.crop(7*width, height, width, height);
			
	
		
		//zombie
		zombie_down = new BufferedImage[2];
		zombie_down[0] = sheet.crop(4*width, height * 2, width, height);
		zombie_down[1] = sheet.crop(5*width, height * 2, width, height);	
		
		zombie_up = new BufferedImage[2];
		zombie_up[0] = sheet.crop(6*width, height * 2, width, height);
		zombie_up[1] = sheet.crop(7*width, height * 2, width, height);

		zombie_right = new BufferedImage[2];
		zombie_right[0] = sheet.crop(4*width, height * 3, width, height);
		zombie_right[1] = sheet.crop(5*width, height * 3, width, height);
		
		zombie_left = new BufferedImage[2];
		zombie_left[0] = sheet.crop(6*width, height * 3, width, height);
		zombie_left[1] = sheet.crop(7*width, height * 3, width, height);
		

		//tiles
		stone = sheet.crop(0, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(2*width, 0, width, height);
	
		sheet = null;
	}
	
}
