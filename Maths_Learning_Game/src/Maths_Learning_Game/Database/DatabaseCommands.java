package Maths_Learning_Game.Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseCommands {


	public static void createStudent(String Name, String Password, int TeacherID) throws Exception {
		try {
			Connection con = getsConnection();
			if(Name == "" || Password == "")
				return;
			PreparedStatement posted = con.prepareStatement("Insert into student(TeacherID,UserName, UserPassword) VALUES ('"+TeacherID+"', '"+Name+"','"+Password+"')");                   
			posted.executeUpdate();
			con = getsConnection();
			posted = con.prepareStatement("select ID from student where  id=(select max(ID) from student)");                   
			ResultSet results = posted.executeQuery();
			results.next();
			posted = con.prepareStatement("Insert into score(StudentID, Score1, Score2, Score3) VALUES ("+results.getInt("ID")+",'0','0','0')");
			posted.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e);
		}
		finally {
			System.out.println("insert completed");
		}
	}

	public static ResultSet getStudentResults() throws Exception{
		try {
			Connection con = getsConnection();
			PreparedStatement statement = con.prepareStatement("Select * from student");	

			ResultSet results = statement.executeQuery();
			System.out.println("all records selected");
			return results;
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;			
	}
	
	public static ResultSet getTeacherResults() throws Exception{
		try {
			Connection con = getsConnection();
			PreparedStatement statement = con.prepareStatement("Select * from teacher");	

			ResultSet results = statement.executeQuery();
			System.out.println("all records selected");
			return results;
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;			
	}
	
	public static ResultSet getScoreResults() throws Exception{
		try {
			Connection con = getsConnection();
			PreparedStatement statement = con.prepareStatement("Select * from score");	
			ResultSet results = statement.executeQuery();
			System.out.println("all records selected");
			return results;

		}catch(Exception e) {
			System.out.println(e);
		}
		return null;			
	}

	public static void DeleteStudent(int ID ) throws Exception{
		try {
			Connection con = getsConnection();
			
			PreparedStatement delete = con.prepareStatement("delete from score where StudentID = "+ ID);
			delete.executeUpdate();
			
			delete = con.prepareStatement("delete from student where ID = "+ ID);
			delete.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	

	public static void replaceScore(int ID, String score1, String score2, String score3) throws Exception{
		try {
		Connection con = getsConnection();
		PreparedStatement replace = con.prepareStatement("update score set Score1 = '"+score1+"', Score2 = '"+score2+"', Score3 = '"+score3+"'  where ID = '"+ID+"'");
		replace.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static Connection getsConnection() throws Exception {
		try {
			String Driver = "com.mysql.jdbc.Driver";
			String Url = "jdbc:mysql://127.0.0.1:3306/maths_learing_game";			
			String Username = "root";
			String Password = "Kirkirbs";
			Class.forName(Driver);
			
			Connection conn = DriverManager.getConnection(Url, Username, Password);
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}


}
