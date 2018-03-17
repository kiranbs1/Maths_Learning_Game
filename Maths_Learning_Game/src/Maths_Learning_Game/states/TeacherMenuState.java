package Maths_Learning_Game.states;

import java.awt.Color;
import java.awt.Graphics;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.Text;
import Maths_Learning_Game.ui.ClickListener;
import Maths_Learning_Game.ui.UIImageButton;
import Maths_Learning_Game.ui.UIManager;

public class TeacherMenuState extends State {

	private UIManager uiManager;
	
	public TeacherMenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManagager(uiManager);
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) - 96, (handler.getGame().getHeight()/2) + 96, 128, 64, "Alter Students", new ClickListener() {		

			@Override
			public void onClick() {
				
				handler.getGame().TeacherMenuState = null;
				handler.getGame().AddRemoveStudent = new AddRemoveStudent(handler);
				State.setState(handler.getGame().AddRemoveStudent);
			}
	}));
	
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()) - 128, 0, 128, 64, "Back", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getGame().TeacherMenuState = null;
				handler.getGame().LoginState = new LoginState(handler);
				State.setState(handler.getGame().LoginState);
			}
	}));
	
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 128, 32);
		g.fillRect(0, handler.getHeight()/ 2, 160, handler.getHeight()/ 2);
		Text.drawString(g, handler.getAllDataRecords().getTeacher().getUserName(), 64, 16, true, Color.black, Asset.font20);
		
		for(int count = 0;count < handler.getAllDataRecords().getStudents().size(); count++) {
			Text.drawString(g, handler.getAllDataRecords().getStudents().get(count).getUserName() , 5, handler.getHeight()/ 2 + (count * 15 + 15) , false, Color.black, Asset.font20);
			Text.drawString(g, handler.getAllDataRecords().getStudents().get(count).getScore().getScores()[0] , 90, handler.getHeight()/ 2 + (count * 15 + 15) , false, Color.black, Asset.font20);
			Text.drawString(g, handler.getAllDataRecords().getStudents().get(count).getScore().getScores()[1] , 115, handler.getHeight()/ 2 + (count * 15 + 15) , false, Color.black, Asset.font20);
			Text.drawString(g, handler.getAllDataRecords().getStudents().get(count).getScore().getScores()[2] , 140, handler.getHeight()/ 2 + (count * 15 + 15) , false, Color.black, Asset.font20);

		}

	}

}
