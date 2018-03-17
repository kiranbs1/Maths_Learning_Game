package Maths_Learning_Game.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Utils {

	public static String LoadfileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");	//   \n is a new line
			
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();		
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);	//parseInt() converts a string to an integer
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public static int RandomNum(int num, int mod) {
	Random rand = new Random();
	int rndNum = 0;
	rndNum = rand.nextInt(num) + mod;
	return rndNum;
	}
	
	public static String RemoveFinalChar(String string, int pos) {
		if(string.length() == 0)
			return string;
		return string.substring(0, pos - 1);
	}
	

}
