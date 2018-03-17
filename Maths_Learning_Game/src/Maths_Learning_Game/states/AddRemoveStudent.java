package Maths_Learning_Game.states;

import java.awt.Color;
import java.awt.Graphics;

import Maths_Learning_Game.Database.DatabaseCommands;
import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.Text;
import Maths_Learning_Game.ui.ClickListener;
import Maths_Learning_Game.ui.UIImageButton;
import Maths_Learning_Game.ui.UIManager;

public class AddRemoveStudent extends State{

	private UIManager uiManager;
	private String StudentName, StudentPassword;
	private boolean enterUserName, enterPassword;
	public AddRemoveStudent(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManagager(uiManager);
		enterUserName = false;
		enterPassword = false;
		StudentName = "";
		StudentPassword = "";		
		
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
		//Password
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2) - 192, (handler.getGame().getHeight()/2) + 96, 128, 64, "Add Students", new ClickListener() {		

			@Override
			public void onClick() {
				try {
					DatabaseCommands.createStudent(StudentName, StudentPassword, handler.getAllDataRecords().getTeacher().getID());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}));
		//Add Student
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()/2), (handler.getGame().getHeight()/2) + 96, 128, 64, "Remove Students", new ClickListener() {		

			@Override
			public void onClick() {
				try {
					for(int count = 0; count < handler.getAllDataRecords().getStudents().size(); count++) {
						if((StudentName.equals(handler.getAllDataRecords().getStudents().get(count).getUserName()) &&
							StudentPassword.equals(handler.getAllDataRecords().getStudents().get(count).getPassword()))) {
							System.out.println("Deleting Student");
							DatabaseCommands.DeleteStudent(handler.getAllDataRecords().getStudents().get(count).getID());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}));
		//Remove Student		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth()) - 128, 0, 128, 64, "Back", new ClickListener() {		

			@Override
			public void onClick() {
				handler.getAllDataRecords().ReloadStudents();
				handler.getGame().AddRemoveStudent = null;
				handler.getGame().TeacherMenuState = new TeacherMenuState(handler);
				State.setState(handler.getGame().TeacherMenuState);
			}
	}));
		//back
		
	}
	
	public void AddStudent(){
		try {
			DatabaseCommands.createStudent("HI", "123", handler.getAllDataRecords().getTeacher().getID());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteStudent(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void tick() {
		if(handler.getMouseManager().isLeftPressed()) {
			enterUserName = false;
			enterPassword = false;
		}		
		if (enterUserName) {
			StudentName = handler.getKeyManager().getInput(StudentName);
		}else if (enterPassword) {
			StudentPassword = handler.getKeyManager().getInput(StudentPassword);		
		}
	}

	public void render(Graphics g) {
		uiManager.render(g);
		
		g.setColor(Color.WHITE);
		g.fillRect((handler.getGame().getWidth()/2) - 256, (handler.getGame().getHeight()/2) - 30, 128, 32);
		Text.drawString(g, StudentName , (handler.getGame().getWidth()/2 - 196), (handler.getGame().getHeight()/2) - 14, true, Color.BLACK, Asset.font20);
		//UserName
		g.setColor(Color.WHITE);
		g.fillRect((handler.getGame().getWidth()/2), (handler.getGame().getHeight()/2) - 30, 128, 32);
		Text.drawString(g, StudentPassword , (handler.getGame().getWidth()/2 + 64), (handler.getGame().getHeight()/2) - 14, true, Color.BLACK, Asset.font20);
		//password
	}

}
