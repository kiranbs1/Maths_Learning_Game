package Maths_Learning_Game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.gfx.Animation;
import Maths_Learning_Game.gfx.Asset;
	
//inventory disabled
public class Player extends Entity {

	//Animations
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	//Stuck
	private boolean CantMove = false;

	
	public Player(Handler handler, float x, float y) {
		super(handler ,x, y, Entity.DEFUALT_WIDTH, Entity.DEFUALT_HEIGHT);
		//sets bounding box size
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 23;
		bounds.height = 23;
		//Animations
		animDown = new Animation(400, Asset.player_down);
		animUp = new Animation(500, Asset.player_up);
		animLeft = new Animation(500, Asset.player_left);
		animRight = new Animation(500, Asset.player_right);
	}

	public void tick() {  //Run in GameState.Tick()		

		if(!CantMove) {
			//Animations
			animDown.tick();
			animUp.tick();
			animLeft.tick();
			animRight.tick();
			//movement		
			getInput();	// runs get input method
			move();  //method is inside creature class
			//camera
			handler.getGameCamera().centerOnEntity(this);  //used to lock camera on player
		}

	
	}
	
	public void render(Graphics g) { //used in GameState.render()
		//player
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
			g.setColor(Color.red);  //sets the colour to red
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset())
		, (int) (y + bounds.y - handler.getGameCamera().getyOffset())
		, bounds.width, bounds.height);				
		//used to draw bounding box. useful in testing.	
	}
	
	public void postRender(Graphics g) {		
	}
	
	private void getInput() { //gets keyboard inputs
		
		xmove = 0;
		ymove = 0;

		if(handler.getKeyManager().up)  //up is a boolean. so checking if up = true
			ymove = -speed;	//speed found in creature
		if(handler.getKeyManager().down)
			ymove = +speed;
		if(handler.getKeyManager().left)
			xmove = -speed;
		if(handler.getKeyManager().right)
			xmove = +speed;
		
	}

	
	private BufferedImage getCurrentAnimationFrame() {
		if(xmove < 0) {
			return animLeft.getCurrentFrame();
		}else if (xmove > 0) {
			return animRight.getCurrentFrame();
		}else if (ymove < 0) {
			return animUp.getCurrentFrame();
		}else {
			return animDown.getCurrentFrame();

		}
		
	}
	

	//getters setters

	public boolean getCantMove() {
		return CantMove;
	}

	public void setCantMove(boolean cantMove) {
		CantMove = cantMove;
	}
}
