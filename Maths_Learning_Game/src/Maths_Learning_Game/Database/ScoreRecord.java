package Maths_Learning_Game.Database;


public class ScoreRecord {
	private String Scores[];
	private int ID, StudentID;
	
	public ScoreRecord(int ID, int StudentID, String score1, String score2, String score3) {
		Scores = new String [3];
		this.ID = ID;
		this.StudentID = StudentID;
		Scores[0] = score1;
		Scores[1] = score2;
		Scores[2] = score3;
	}

	
	public String[] getScores() {
		return Scores;
	}

	public void setScores(String[] scores) {
		Scores = scores;
	}

	public int getID() {
		return ID;
	}

	public int getStudentID() {
		return StudentID;
	}
	
}
