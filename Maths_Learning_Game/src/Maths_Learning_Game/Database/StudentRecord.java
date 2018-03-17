package Maths_Learning_Game.Database;

public class StudentRecord {

	private String UserName, Password;
	private int ID, TeacherID;
	private ScoreRecord Score;
	
	public StudentRecord(String UserName, String Password, int ID, int TeacherID) {
		this.UserName = UserName;
		this.Password = Password;
		this.ID = ID;
		this.TeacherID = TeacherID;
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

	public int getTeacherID() {
		return TeacherID;
	}

	public ScoreRecord getScore() {
		return Score;
	}

	public void setScore(int ID, int StudentID, String score1, String score2, String score3) {
		Score = new ScoreRecord(ID, StudentID, score1, score2, score3);
	}	
}
