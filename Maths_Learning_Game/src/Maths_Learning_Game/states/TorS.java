package Maths_Learning_Game.states;

import java.awt.Graphics;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.ui.ClickListener;
import Maths_Learning_Game.ui.UIImageButton;
import Maths_Learning_Game.ui.UIManager;

public class TorS extends State{

	private UIManager uiManager;
	
	public TorS(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManagager(uiManager);
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) + 64, (handler.getGame().getHeight()/2) - 110, 128, 64, "Teacher", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().setTeacher(true);
				handler.getGame().TorS = null;
				handler.getGame().LoginState = new LoginState(handler);
				State.setState(handler.getGame().LoginState);
			}
	}));	
		//Teacher
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) - 192, (handler.getGame().getHeight()/2) - 110, 128, 64, "Student", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().setTeacher(false);
				handler.getGame().TorS = null;
				handler.getGame().LoginState = new LoginState(handler);
				State.setState(handler.getGame().LoginState);

			}
	}));
		//Teacher
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
