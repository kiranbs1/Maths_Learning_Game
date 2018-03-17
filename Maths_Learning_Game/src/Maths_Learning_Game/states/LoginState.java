package Maths_Learning_Game.states;

import java.awt.Color;
import java.awt.Graphics;

import Maths_Learning_Game.Database.AllDatabaseDataRecord;
import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.Text;
import Maths_Learning_Game.ui.ClickListener;
import Maths_Learning_Game.ui.UIImageButton;
import Maths_Learning_Game.ui.UIManager;

public class LoginState extends State {

	private UIManager uiManager;
	private Boolean enterUserName, enterPassword;
	private String Password, UserName;
	
	public LoginState(Handler handler) {
		super(handler);
		enterUserName = false;
		enterPassword = false;
		UserName = "DAVID";
		Password = "123456";
		handler.getGame().setData(new AllDatabaseDataRecord(handler));
		uiManager = new UIManager(handler);
		
		handler.getMouseManager().setUIManagager(uiManager);		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()) - 128, 0, 128, 64, "Back", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().LoginState = null;
				handler.getGame().TorS = new TorS(handler);
				State.setState(handler.getGame().TorS);
			}
	}));
		//back
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) - 256, (handler.getGame().getHeight()/2) - 110, 128, 64,"UserName", new ClickListener() {		

			@Override
			public void onClick() {
				enterUserName = true;
			}
	}));
		//UserName
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2), (handler.getGame().getHeight()/2) - 110, 128, 64, "Password", new ClickListener() {		

			@Override
			public void onClick() {
				enterPassword = true;		
			}
	}));
		//password
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) - 64, (handler.getGame().getHeight()/2) + 96, 128, 64, "Login", new ClickListener() {		

			@Override
			public void onClick() {
				if(!handler.getGame().getTeacher()) {
					handler.getAllDataRecords().StudentLogin(UserName, Password);
				}else {
					handler.getAllDataRecords().TeacherLogin(UserName, Password);
				}
			}
	}));	
		//login (to menu)
	
	}

	@Override
	public void tick() {
		if(handler.getMouseManager().isLeftPressed()) {
			enterUserName = false;
			enterPassword = false;
		}		
		if (enterUserName)
			UserName = handler.getKeyManager().getInput(UserName);
		else if (enterPassword)
			Password = handler.getKeyManager().getInput(Password);
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
		g.setColor(Color.WHITE);
		g.fillRect((handler.getGame().getWidth()/2) - 256, (handler.getGame().getHeight()/2) - 30, 128, 32);
		Text.drawString(g, UserName , (handler.getGame().getWidth()/2 - 196), (handler.getGame().getHeight()/2) - 14, true, Color.BLACK, Asset.font20);
		//UserName
		g.setColor(Color.WHITE);
		g.fillRect((handler.getGame().getWidth()/2), (handler.getGame().getHeight()/2) - 30, 128, 32);
		Text.drawString(g, Password , (handler.getGame().getWidth()/2 + 64), (handler.getGame().getHeight()/2) - 14, true, Color.BLACK, Asset.font20);
		//password
	}
}
