package Maths_Learning_Game.states;

import java.awt.Color;
import java.awt.Graphics;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.Text;
import Maths_Learning_Game.ui.ClickListener;
import Maths_Learning_Game.ui.UIImageButton;
import Maths_Learning_Game.ui.UIManager;

public class StudentMenu extends State {

	private UIManager uiManager;
	
	public StudentMenu(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManagager(uiManager);	
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()) - 128, 0, 128, 64, "Back", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().StudentMenuState = null;
				handler.getGame().LoginState = new LoginState(handler);
				State.setState(handler.getGame().LoginState);
			}
	}));
		//Back
		
		uiManager.addObject(new UIImageButton((handler.getWidth()/ 2) - 64 , (handler.getHeight()/2) - 32, 128, 64, "PLAY GAME!", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().StudentMenuState = null;
				handler.getGame().gamestate = new GameState(handler);
				State.setState(handler.getGame().gamestate);
			}
	}));
	//game
		uiManager.addObject(new UIImageButton(0, 32, 128, 64, "Settings", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().StudentMenuState = null;
				handler.getGame().settingsstate = new SettingsState(handler);
				State.setState(handler.getGame().settingsstate);
			}
	}));
		//settings
	}

	@Override
	public void tick() {
	
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 128, 32);	//name
		g.fillRect((handler.getWidth()/ 2) - 64, (handler.getHeight()/2) + 56, 128, 92);	//scores
		Text.drawString(g, handler.getAllDataRecords().getStudent().getUserName(), 64, 16, true, Color.black, Asset.font20);
		Text.drawString(g, "Previous Scores : ", (handler.getWidth()/ 2) - 132 , (handler.getHeight()/2) + 64, true, Color.black, Asset.font20);
		
		Text.drawString(g, handler.getAllDataRecords().getStudent().getScore().getScores()[0],(handler.getWidth()/ 2) - 50, (handler.getHeight()/2) + 70, true, Color.black, Asset.font20);
		Text.drawString(g, handler.getAllDataRecords().getStudent().getScore().getScores()[1], (handler.getWidth()/ 2) - 50, (handler.getHeight()/2) + 100, true, Color.black, Asset.font20);
		Text.drawString(g, handler.getAllDataRecords().getStudent().getScore().getScores()[2], (handler.getWidth()/ 2) - 50, (handler.getHeight()/2) + 130, true, Color.black, Asset.font20);

	}

}
