package Maths_Learning_Game.Audio;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Audio {
	
	public static Clip BackgroundMusic(float Volume) {
			Clip clip = null;
		try {
			File file = new File("res/Sound/arcade-music-loop.wav");
		    clip =AudioSystem.getClip();
		    clip.open(AudioSystem.getAudioInputStream(file));
		    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		    gainControl.setValue(Volume); // Reduce volume by 10 decibels.
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
		  } catch (Exception e) { 
		   System.out.println(e);
		  } 
		return clip;  
	}  
	
	public static void ButtonSound(float Level) {
		try {
		   Clip clip = AudioSystem.getClip();
		   File file = new File("res/Sound/button-16.wav");   
		   clip.open(AudioSystem.getAudioInputStream(file));
		   FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		   gainControl.setValue(Level);
		   clip.start();
		}catch (Exception e) {
		   System.err.println(e.getMessage());
		}
	}
	public static FloatControl adjustVolume(Float vol, Clip clip) {
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    gainControl.setValue(vol);
		return gainControl;
	} 
	
	public static Float getVol(Clip clip) {
		float level = 0.0f;
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    level = gainControl.getValue();
		return level;
	} 
}
