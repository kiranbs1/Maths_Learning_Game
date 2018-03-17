package Maths_Learning_Game.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.entities.EntityManager;
import Maths_Learning_Game.entities.Player;
import Maths_Learning_Game.entities.Zombie;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.Text;
import Maths_Learning_Game.states.PauseState;
import Maths_Learning_Game.states.State;
import Maths_Learning_Game.states.StudentMenu;
import Maths_Learning_Game.tiles.Tile;
import Maths_Learning_Game.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;  //20
	private int [][] W_tiles;
	private int worldWidthpxl, worldHeightpxl;
	//Entities
	private EntityManager entityManager;
	//timer
	private int ticks = 0;

	
	public World(Handler handler ,String path) {  //run in GameState constructor
		this.handler = handler;	
		
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		//World
		loadworld(path);	//runs loadworld() and passes through path 
		
		//Spawn of entities
		worldWidthpxl = width * Tile.TILEWIDTH; 
		worldHeightpxl = height * Tile.TILEHEIGHT;
		//Moving entities
		entityManager.addEntity(new Zombie(handler, GenerateRandomX(),GenerateRandomY()));
		entityManager.addEntity(new Zombie(handler, GenerateRandomX(),GenerateRandomY()));
		entityManager.addEntity(new Zombie(handler, GenerateRandomX(),GenerateRandomY()));
		entityManager.addEntity(new Zombie(handler, GenerateRandomX(),GenerateRandomY()));

	}
	
	public void tick() {  //used in GameState.tick()
		if(handler.getGame().getrTimer() > 0) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
				handler.getGame().PauseState = new PauseState(handler);
				State.setState(handler.getGame().PauseState);
			}		
				entityManager.tick();
				ticks++;
				if(ticks >= 60) {
					handler.getGame().setrTimer(handler.getGame().getrTimer() - 1);
					ticks = 0;
				}	
			
		}else {
			handler.getGame().EndOfGame();
			handler.getGame().gamestate = null;
			handler.getGame().StudentMenuState = new StudentMenu(handler);
			State.setState(handler.getGame().StudentMenuState);
		}
		
	}

	public void render(Graphics g) {  //used in GameState.render()
		if(handler.getGame().getrTimer() > 0) {
			
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		//xStart = the larger of    0   and   (X - 320 + 32) / 64
		//max is needed so that nothing is rendered outside of the map (to the left)
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth() ) / Tile.TILEWIDTH + 1); 
		//xEnd = the smaller of     20   and   (((x - 320 + 32) + 640) / 64) + 1
		//min is required so that no rendering is done off map (to the right)
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		//same as x just use 250 instead of 320 and 500 instead of 640
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		//This is where what is rendered is determined based on position of player
		for(int y = yStart; y< yEnd; y++) {
			for(int x = xStart; x< xEnd; x++) {
				getTile(x, y).render(g, (int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset())
						, (int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				
			}
			
		}
	//	itemManager.render(g);
		entityManager.render(g);
		//Timer
		g.setColor(Color.white);
		g.fillRect(290, 5, 60, 30);	//timer
		g.fillRect(290, 45, 60, 30);
		Text.drawString(g, Integer.toString(handler.getGame().getrTimer()) , (int) 320, 20, true, Color.BLACK, Asset.font28);	//timer
		Text.drawString(g, Integer.toString(handler.getGame().getCurrentScore()) , (int) 320, 60, true, Color.BLACK, Asset.font28);
		}
	}
	
	
	public Tile getTile(int x, int y) {
		
		Tile t = Tile.tiles[W_tiles[x][y]];
		//tiles are from Tile class. W_Tiles look at loadworld()
		//this sets the tiles[] variable equal to the tile id saved in the world file
		//this also instantiates the Tile object
		if(t == null) 
			return Tile.dirtTile;
		return t;
		
	}
	
	private void loadworld(String path) {	//runs in World constructor
		String file = Utils.LoadfileAsString(path); //Runs LoadfileAsString() passing through path and stores it in file
		String[] tokens = file.split("\\s+");
		//tokens[] will hold the the map data. each member of the array holding 1 piece of data. 
		//file.split() will seperate the file whereever it see whatever is inside of it's parameters
		// \\s+ is a space so a file with 1 2 4 2 3. will create 5 tokens each with 1 number.
		//The number is represents the id of a tile
		//every token is a string.
		width = Utils.parseInt(tokens[0]);	//the first value of the world document
		height = Utils.parseInt(tokens[1]);	//the second value of the world document
		W_tiles = new int[width][height];  //this is a 2d array
		for(int y = 0;y < height;y++) {	//creates the first loop of the nested loop
			for(int x = 0;x <width;x++) {  //creates the second loop of the nested loop
				W_tiles[x][y] = Utils.parseInt(tokens[(x + (y *width)) + 4]);
				//W_tiles[0][0] = tokens[(0 + (0*20)) + 4  (= 4)]
				//W_tiles[17][6] = tokens[((17 + (6*20)+ + 4 (= 141)]
				//W_tiles takes in the ID of the tile saved in the world file
			}				
		}	
			
		
		
	}
	
	public int GenerateRandomX() {
		int rndX = 0;
		rndX = Utils.RandomNum(worldWidthpxl, 0);
		if(rndX > worldWidthpxl - 100 || rndX < 100) //so that entity doesn't spawn on outer edge
			rndX = GenerateRandomX();
		return rndX;
	}
	
	public int GenerateRandomY() {
		int rndY = 0;
		rndY = Utils.RandomNum(worldHeightpxl, 0);
		if(rndY > worldHeightpxl - 100 || rndY < 100) //so that entity doesn't spawn on outer edge
			rndY = GenerateRandomX();
		
		return rndY;
	}
	
	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public int getWidth() {
			return width;
			
	}
	
	public int getHeight() {
		return height;
		
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public int getWorldWidthpxl() {
		return worldWidthpxl;
	}

	public int getWorldHeightpxl() {
		return worldHeightpxl;
	}
	
	
}
