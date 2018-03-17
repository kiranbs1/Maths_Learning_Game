package Maths_Learning_Game.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Maths_Learning_Game.Audio.Audio;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.Text;


public class UIImageButton extends UIObject {
	
	private BufferedImage[] images;
	private ClickListener clicker;
	private String text;
	private Boolean TooLong = false;

	public UIImageButton(float x, float y, int width, int height,String Text, ClickListener clicker){
		super(x, y, width, height); 
		images = Asset.Button;
		this.clicker = clicker;
		this.text = Text;
		if(text.length() >= 10){
			TooLong = true;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(images[0], (int) x, (int) y, width, height, null);
		if(TooLong) {
			if(hovering) {
				Text.drawString(g, text,(int) x + 64, (int) y + 36, true, Color.white, Asset.font18);
			}else {
				Text.drawString(g, text,(int) x + 64, (int) y + 36, true, Color.black, Asset.font18);
			}
		}else {
			if(hovering) {
				Text.drawString(g, text,(int) x + 64, (int) y + 36, true, Color.white, Asset.font28);
			}else {
				Text.drawString(g, text,(int) x + 64, (int) y + 36, true, Color.black, Asset.font28);
			}
		}
	}

	@Override
	public void onClick() {	//look at MenuState
		clicker.onClick();
		Audio.ButtonSound(-5.0f);
	}

	
}
