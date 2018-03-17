package Maths_Learning_Game.states;

import java.awt.Graphics;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.worlds.World;

public class GameState extends State {

	private World world;

	//GameState
	
	public GameState(Handler handler) {
		super(handler);	//passes handler through to the State class as state is the super class
		world = new World(handler, "res/Worlds/World1.txt");	//created from world class
		handler.setWorld(world);
		
	}

	public void tick() {  //used in Game.tick()
		world.tick();
		//TIMER		
	}

	public void render(Graphics g) {  //used in Game.render(). and it passes through g which holds the graphics
			world.render(g);
	}
}
