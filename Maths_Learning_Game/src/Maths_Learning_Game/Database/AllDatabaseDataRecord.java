package Maths_Learning_Game.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.states.State;
import Maths_Learning_Game.states.StudentMenu;
import Maths_Learning_Game.states.TeacherMenuState;
import Maths_Learning_Game.utils.Utils;

public class AllDatabaseDataRecord {
	private Handler handler;
	private ResultSet results;
	private StudentRecord student;
	private ArrayList<StudentRecord> Students;
	private ArrayList<TeacherRecords> Teachers;
	private TeacherRecords teacher;
	
	public AllDatabaseDataRecord(Handler handler){
		this.handler = handler;
		
		Teachers = new ArrayList<>();
		Students = new ArrayList<>();
		if(handler.getGame().getTeacher()) {
			SetTeacherResults();
		}else {
			SetStudentResults();
			SetScoreResults();
		}
	}

	
	public void SetTeacherResults() {

		
			try {
				results = DatabaseCommands.getTeacherResults();
				results.beforeFirst();
				while (results.next()) {
					Teachers.add(new TeacherRecords(Utils.parseInt(results.getString("ID")),
													results.getString("TUserName").toUpperCase(), 
												    results.getString("TUserPassword").toUpperCase()));	
			}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	

	}
	
	public void SetStudentResults()  {
		try {
			results = DatabaseCommands.getStudentResults();
			results.beforeFirst();
				while (results.next()) {
						Students.add(new StudentRecord(results.getString("UserName").toUpperCase(), 
													   results.getString("UserPassword").toUpperCase(),
													   Utils.parseInt(results.getString("ID")), 
													   Utils.parseInt(results.getString("TeacherID"))));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SetScoreResults() {
		try {
			results = DatabaseCommands.getScoreResults();
			results.beforeFirst();
			while (results.next()) {	
				for(int count = 0;count < Students.size() ; count ++) {
					if(Students.get(count).getID() == results.getInt("StudentID")) {
						Students.get(count).setScore(Utils.parseInt(results.getString("ID")),
									 		 	 Utils.parseInt(results.getString("StudentID")),
									 		 	 results.getString("Score1"),
									 		 	 results.getString("Score2"),
									 		 	 results.getString("Score3"));	
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ReloadStudents() {
		Students.clear();
		SetStudentResults();
		SetScoreResults();
		for (int count = 0; count < Students.size(); count++) {
			if(Students.get(count).getTeacherID() != teacher.getID()){
				Students.remove(count);
				count--;
			}
		}
	}
	
	
	public void StudentLogin(String UserName,String Password) {
		for(int count = 0; count <= Students.size();count++) {
			if((UserName.equals(Students.get(count).getUserName())) &&
			   (Password.equals(Students.get(count).getPassword()))) {
				
				student = Students.get(count);			
				Students.clear();
				
				
				handler.getGame().LoginState = null;
				handler.getGame().StudentMenuState = new StudentMenu(handler);
				State.setState(handler.getGame().StudentMenuState);
				return;
			}
		}	
	}
	
	public void TeacherLogin(String UserName,String Password) {

		for(int count = 0; count < Teachers.size();count++) {
				if((UserName.equals(Teachers.get(count).getUserName())) &&
				   (Password.equals(Teachers.get(count).getPassword()))) {
					
							teacher = Teachers.get(count);
							Teachers.clear();
							
							ReloadStudents();
							handler.getGame().LoginState = null;
							handler.getGame().TeacherMenuState = new TeacherMenuState(handler);
							State.setState(handler.getGame().TeacherMenuState);
					return;
				}
				
			}
	}

	public int ResultSetLength(ResultSet results) {
		int count = 0;
		try {
			while(results.next()) {
				count++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public StudentRecord getStudent() {
		return student;
	}

	public ArrayList<StudentRecord> getStudents() {
		return Students;
	}

	public TeacherRecords getTeacher() {
		return teacher;
	}
	

}