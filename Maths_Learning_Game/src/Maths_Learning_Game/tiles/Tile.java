package Maths_Learning_Game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//Static stuff here
	
	public static Tile[] tiles = new Tile[256];
	public static Tile dirtTile = new dirt(0);  //parameter is id and is passed into dirt.
	public static Tile grassTile = new GrassTile(1);
	public static Tile stoneTile = new Stone(2);
	
	
	//class	
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

	protected BufferedImage texture;
	protected final int id;
	
	
	public Tile(BufferedImage texture, int id) {
		tiles[id] = this;
		
		this.texture = texture;
		this.id = id;		
		
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);		
	}
	
	public boolean isSolid() {
		return false;
		
	}
	
	public int getId() {
		return id;		
	}
	
	
	
}
