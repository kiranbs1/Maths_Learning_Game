package Maths_Learning_Game.Main;

import Maths_Learning_Game.Database.AllDatabaseDataRecord;
import Maths_Learning_Game.gfx.GameCamera;
import Maths_Learning_Game.input.KeyManager;
import Maths_Learning_Game.input.MouseManager;
import Maths_Learning_Game.worlds.World;

public class Handler {

	private Game game;
	private World world;
	//Handler	
	
	public Handler (Game game) {	//run in Game.init()
		this.game = game;	//Sets the game object in the class equal to the passed in game object?
		
	}

	public GameCamera getGameCamera() {
		return game.getgamecamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getkeymanager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}

	public AllDatabaseDataRecord getAllDataRecords() {
		return game.getData();
	}	
}
