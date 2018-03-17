package Maths_Learning_Game.Database;

import java.util.Formatter;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.utils.Utils;

public class AllSettings {

	private Handler handler;
	private float Volume;	
	private Formatter FileFormatter;
	
	public AllSettings(Handler handler) {
		this.handler = handler;
		Volume = 0.0f;
		GetAllSettings();
	}
	public void GetAllSettings() {
		String file = Utils.LoadfileAsString("res/settings/Settings.txt"); //Runs LoadfileAsString() passing through path and stores it in file
		String[] tokens = file.split("\\s+");
		handler.getGame().setDifficulty( tokens[0].toCharArray()[0]);
		Volume = Float.valueOf(tokens[1]);
		
	}
	
	public void WriteOverSettings() {
		System.out.println(handler.getGame().getDifficulty() + "HELLO");
		try {
			FileFormatter = new Formatter("res/settings/Settings.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		FileFormatter.format("%s%n%s", handler.getGame().getDifficulty(), Volume );
		FileFormatter.close();
	}
	
	public float getVolume() {
		return Volume;
	}
	public void setVolume(float volume) {
		Volume = volume;
	}
	
	
}
