package Maths_Learning_Game.states;

import java.awt.Graphics;

import Maths_Learning_Game.Main.Handler;

public abstract class State {

	
	
	private static State currentstate = null;
	
	public static void setState(State state) {
		currentstate = state;
	}
	
	public static State getState() {
		return currentstate;
	}
			
			
	//class
	
	protected Handler handler;
	
	public State(Handler handler) {		//constructor
		this.handler = handler;	//passed through from GameState constructor
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	

}
