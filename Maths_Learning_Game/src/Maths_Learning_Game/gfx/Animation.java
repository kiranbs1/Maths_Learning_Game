package Maths_Learning_Game.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed, index;
	private long lastTime, Timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		Timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		Timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(Timer > speed) {
			index++;
			Timer = 0;
			if (index >= frames.length) {
				index = 0;
			}
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

}
