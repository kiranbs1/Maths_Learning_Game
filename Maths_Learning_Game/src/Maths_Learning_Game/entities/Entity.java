package Maths_Learning_Game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.tiles.Tile;

public abstract class Entity {

	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	public static final float DEFUALT_SPEED = 6.0f;
	public static final int DEFUALT_WIDTH = 64,
							DEFUALT_HEIGHT = 64;

	
	protected float speed;  
	protected float xmove, ymove;
	
	public Entity(Handler handler ,float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;		//64
		this.height = height;	//64
		speed = DEFUALT_SPEED;
		xmove = 0;
		ymove = 0;
		bounds = new Rectangle(0, 0, width, height);
		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void postRender(Graphics g);

	public void move() {  //used in player.Tick()
		if (!CheckEntityCollisions(xmove, 0f)) {
		moveX();  
		}
		if (!CheckEntityCollisions(0f, ymove)) {
		moveY();
		}
		
		//runs moveX() and moveY()
	}
	
	public Rectangle getCollisionBounds(float xOffset,float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
		//gets the bounding rectangle
	}
	
	public boolean CheckEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) //Makes the entity not check for itself
				continue;
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				//checks if their bounding box overlaps
				return true;
			}
		}
		return false;		
	}
	public void moveX() {  //used in move()
		if(xmove > 0) { //right 
			
			int tx = (int) (x + xmove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			//this this finds the the pixel the player is moving to.
			// /tile.tilewidth allows it to find the tile instead.
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
			   !collisionWithTile(tx, (int) (y + bounds.y + bounds.getHeight()) / Tile.TILEHEIGHT)){				
				x += xmove; 
				//this lets u move as long as you're top right
				//corner isn't touching a solid tile
			}else { //stops the gap between tile and bounding box
				
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
				//makes it so go right next to the tile bar 1 pixel
		//		HitAWallX = true;
			}
			
		}else if(xmove < 0) { //left 
			
			int tx = (int) (x + xmove + bounds.x ) / Tile.TILEWIDTH;		
			// getting rid of + bounds.width is the only change
			//and allows for checking of left corners
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
			   !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){						
					x += xmove;						

		}else {
			x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
		//	HitAWallX = true;
		    }
	    }
	
	} //end x movement	
		
	public void moveY(){  //used in move()
		if(ymove < 0){//Up
			int ty = (int) (y + ymove + bounds.y) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
			   !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
				
				y += ymove;
			}else {
				
				y = ty* Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
		//		HitAWallY = true;
			}
		}else if(ymove > 0){//Down
			int ty = (int) (y + ymove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
			   !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
				
				y += ymove;
			}else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
		//		HitAWallY = true;
			}
		}
	}  //end of moveY()

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x,y).isSolid();
	} 	//checks if tile can be passed through

	
	//getters and setters
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	public float getXmove() {
		return xmove;
	}

	public void setXmove(float xmove) {
		this.xmove = xmove;
	}

	public float getYmove() {
		return ymove;
	}

	public void setYmove(float ymove) {
		this.ymove = ymove;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	

}
