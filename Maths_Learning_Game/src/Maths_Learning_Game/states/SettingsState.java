package Maths_Learning_Game.states;

import java.awt.Color;
import java.awt.Graphics;

import Maths_Learning_Game.Audio.Audio;
import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.Text;
import Maths_Learning_Game.ui.ClickListener;
import Maths_Learning_Game.ui.UIImageButton;
import Maths_Learning_Game.ui.UIManager;

public class SettingsState extends State {
 
	private UIManager uiManager;
	
	public SettingsState(Handler handler) {
		super(handler);

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManagager(uiManager);
	
		
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()) - 128, 0, 128, 64, "BACK", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().settingsstate = null;
				handler.getGame().StudentMenuState = new StudentMenu(handler);
				State.setState(handler.getGame().StudentMenuState);	
			}
	}));
		//back
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()) - 256, 0, 128, 64, "Save Settings", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().getAllSettings().WriteOverSettings();	
			}
	}));
		//save settings
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) + 190, (handler.getGame().getHeight()/2) - 110, 128, 64, "Volume Up", new ClickListener() {		

			@Override
			public void onClick() {
				Audio.adjustVolume(Audio.getVol(handler.getGame().getClip()) + 5.0f,handler.getGame().getClip());
				handler.getGame().getAllSettings().setVolume(handler.getGame().getAllSettings().getVolume() + 5.0f);
			}
	}));
		//volume up
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) + 190, (handler.getGame().getHeight()/2) - 14, 128, 64, "Volume Down", new ClickListener() {		

			@Override
			public void onClick() {
				Audio.adjustVolume(Audio.getVol(handler.getGame().getClip()) - 5.0f,handler.getGame().getClip());
				handler.getGame().getAllSettings().setVolume(handler.getGame().getAllSettings().getVolume() - 5.0f);
				
			}
	}));
		//volume down
		
		uiManager.addObject(new UIImageButton(0, (handler.getGame().getHeight()/2) - 96, 128, 64, "EASY", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().setDifficulty('E');
			}
	}));
		//Set difficulty Easy
		uiManager.addObject(new UIImageButton(0, (handler.getGame().getHeight()/2), 128, 64, "MEDIUM", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().setDifficulty('M');
			}
	}));
		//Set difficulty Medium
		uiManager.addObject(new UIImageButton(0, (handler.getGame().getHeight()/2) + 96, 128, 64, "HARD", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().setDifficulty('H');
			}
	}));
		//Set difficulty Hard
	
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		uiManager.render(g);
		Text.drawString(g, "Volume up" , (handler.getGame().getWidth()/2) + 190, (handler.getGame().getHeight()/2) - 110 - 14, false, Color.BLACK, Asset.font20);
		//volume up
		Text.drawString(g, "Volume down" , (handler.getGame().getWidth()/2) + 160, (handler.getGame().getHeight()/2) - 20, false, Color.BLACK, Asset.font20);
		//volume down
		
	
	}
}