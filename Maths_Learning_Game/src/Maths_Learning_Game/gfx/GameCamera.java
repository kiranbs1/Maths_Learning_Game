package Maths_Learning_Game.gfx;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.entities.Entity;
import Maths_Learning_Game.tiles.Tile;

public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset;
		
	public GameCamera(Handler handler ,float xOffset, float yOffset) {  //run in Game.init()
			
		this.handler = handler;	
		this.xOffset = xOffset;		//0
		this.yOffset = yOffset;		//0
			
	}
	
	public void CheckBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		}else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = (handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth());
		}
		
		if (yOffset < 0) {
			yOffset = 0;
		}else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = (handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight());
		}
		
		
		
		
	}	
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		//X position of player - 320 + 32
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		//Y position of player - 250 + 32
		CheckBlankSpace();
	}
		
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		CheckBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	
}
