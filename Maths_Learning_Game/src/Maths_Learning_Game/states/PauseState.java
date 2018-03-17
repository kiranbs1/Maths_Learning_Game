package Maths_Learning_Game.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Maths_Learning_Game.Audio.Audio;
import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.ui.ClickListener;
import Maths_Learning_Game.ui.UIImageButton;
import Maths_Learning_Game.ui.UIManager;

public class PauseState extends State {
	
	private UIManager uiManager;
	
	public PauseState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManagager(uiManager);
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) - 64, (handler.getGame().getHeight()/2) - 32 , 128, 64, "Resume", new ClickListener() {		

			@Override 
			public void onClick() {
				handler.getGame().PauseState = null;
				State.setState(handler.getGame().gamestate);			
				Audio.ButtonSound(+5.0f);
			}
	}));
		//continue
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) - 64, (handler.getGame().getHeight()/2) + 128 , 128, 64, "Go To Menu", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().PauseState = null;
				handler.getGame().StudentMenuState = new StudentMenu(handler);
				State.setState(handler.getGame().StudentMenuState);
			}
	}));	
		//menu
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
			State.setState(handler.getGame().gamestate);
		}

	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}