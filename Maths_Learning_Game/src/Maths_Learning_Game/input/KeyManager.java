package Maths_Learning_Game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Maths_Learning_Game.utils.Utils;

public class KeyManager implements KeyListener {
	
	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left, right;
	public boolean aup, adown, aleft, aright;
	
	
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
		
	}
	
	public void tick() { //look at player.getinput()      

		for(int i = 0;i < keys.length;i++){
			if(cantPress[i] && !keys[i]){
				cantPress[i] = false;
			}else if(justPressed[i]){
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]){
				justPressed[i] = true;
			}
		}		
		
		up = keys[KeyEvent.VK_W]; 
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		aup = keys[KeyEvent.VK_UP]; 
		adown = keys[KeyEvent.VK_DOWN];
		aleft = keys[KeyEvent.VK_LEFT];
		aright = keys[KeyEvent.VK_RIGHT];
	}
	
	public String getInput(String string) {
		
		if(keyJustPressed(KeyEvent.VK_BACK_SPACE)) {
			string = Utils.RemoveFinalChar(string, string.length());
			return string;	//doesn't have to loop through everything
		}
		
		for (int count = 33; count <= 126; count++ ) {
			if(keyJustPressed(count)) {
				string = string + (char) count;
				return string;
			}
		}
		return string;
	}
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length) {
			return false;
		}
		return justPressed[keyCode];
	}		
	
	public void keyPressed(KeyEvent e) {	//this will run whenever a key is pressed. It doesn't have to be called
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) { 
			return;
		}
		keys[e.getKeyCode()] = true;
		
	}

	public void keyReleased(KeyEvent e) {	//This will run whenever a key is released(not be pressed anymore)
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) { 
			return;
		}
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
