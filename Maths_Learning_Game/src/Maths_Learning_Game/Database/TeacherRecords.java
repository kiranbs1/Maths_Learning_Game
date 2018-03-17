package Maths_Learning_Game.Database;

public class TeacherRecords {

	private String UserName, Password;
	private int ID;
	
	public TeacherRecords(int ID,String UserName,String Password) {
		this.ID = ID;
		this.UserName = UserName;
		this.Password = Password;
	}

	public String getUserName() {
		return UserName;
	}

	public String getPassword() {
		return Password;
	}

	public int getID() {
		return ID;
	}
	
}
