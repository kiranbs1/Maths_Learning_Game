package Maths_Learning_Game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Maths_Learning_Game.Main.Handler;
import Maths_Learning_Game.gfx.Animation;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.Text;
import Maths_Learning_Game.utils.Utils;

public class Zombie extends Entity {
	//Movement
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	//interaction with player
	private long lastAttackTimer, attackCoolDown = 400, attackTimer = attackCoolDown;
	//Question
	private int QP1,QP2,ans; //question part 1
	private String userAns = "" ;
	private boolean generated;
	private boolean isPlayerStuck = false;
	private char Operater = '+';
	
	public Zombie(Handler handler, float x, float y) {
		super(handler, x, y, Entity.DEFUALT_WIDTH, Entity.DEFUALT_HEIGHT);
		//bounds
		bounds.x = 20;
		bounds.y = 49;
		bounds.width = 30;
		bounds.height = 19;
		//stats
		setstats();
		//animations
		animDown = new Animation(600, Asset.zombie_down);
		animUp = new Animation(600, Asset.zombie_up);
		animLeft = new Animation(500, Asset.zombie_left);
		animRight = new Animation(500, Asset.zombie_right);
	}

	@Override
	public void tick() {
		if(!isPlayerStuck) {	//just used so multiple zombies can't ask questions at once
			move();
			FollowMove();
			animDown.tick();
			animUp.tick();
			animLeft.tick();
			animRight.tick();
			if(!handler.getWorld().getEntityManager().getPlayer().getCantMove())	//just used so multiple zombies can't ask questions at once
				checkIfHits();   
		}else {
			if(!generated) 
			generateQnA();
			UserAnswering();

		}
	}
	 
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public void postRender(Graphics g) {
		if(isPlayerStuck) {
			g.setColor(Color.WHITE);
			g.fillRect((int)(x - handler.getGameCamera().getxOffset()) - 20 ,(int) (y - handler.getGameCamera().getyOffset() + 60), 105, 20);
				Text.drawString(g, "" + QP1  + Operater + QP2 , (int) (x - handler.getGameCamera().getxOffset() + 32), (int) (y - handler.getGameCamera().getyOffset()) + 70, true, Color.BLACK, Asset.font20);
				
			g.setColor(Color.WHITE);
			g.fillRect((int)(x - handler.getGameCamera().getxOffset()) + 5, (int) (y - handler.getGameCamera().getyOffset() + 30), 50, 30);
			Text.drawString(g, userAns, (int) (x - handler.getGameCamera().getxOffset() + 30) , (int) (y - handler.getGameCamera().getyOffset()) + 45, true, Color.BLACK, Asset.font20);

		}
	}	
	
	public void FollowMove() {
		xmove = 0;
		ymove = 0;
		if(handler.getWorld().getEntityManager().getPlayer().getCantMove()) 
			return;
		
		if(handler.getWorld().getEntityManager().getPlayer().getX() > x ) {	//player is right
			xmove += speed;
		}else if(handler.getWorld().getEntityManager().getPlayer().getX() < x ) {	//player is left
			xmove -= speed;
		}
		if(handler.getWorld().getEntityManager().getPlayer().getY() > y ) {	//player is up
			ymove += speed;
		}else if(handler.getWorld().getEntityManager().getPlayer().getY() < y ) {	//player is down
			ymove -= speed;
		}
		
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
	public void setstats(){
		speed = Utils.RandomNum(30, 10) / 10;
	}
	
	public void checkIfHits() {
		
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCoolDown) {
			return;
		}
		attackTimer = 0;		
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(10, 10)) || handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(-10, -10))
				|| handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(-10, 10))|| handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(10, -10))) {
			    handler.getWorld().getEntityManager().getPlayer().setCantMove(true);
			    isPlayerStuck = true;
				return;
			}
		}	
	}
	
	public void generateQnA() {
		if(handler.getGame().getDifficulty() == 'E') {
			Operater = '+';
			ans = Utils.RandomNum(9, 1);
			QP1 = Utils.RandomNum(9, 1);
			QP2 = ans - QP1;	
			generated = true;
			
		}else if(handler.getGame().getDifficulty() == 'M') {
			Operater = '+';
			ans = Utils.RandomNum(19, 1);
			QP2 = Utils.RandomNum(21, -10);
			while(ans < QP2) {
				QP2 = Utils.RandomNum(21, -10);
			}
			QP1 = ans - QP2;	
			generated = true;
			if(QP1 > ans) {
				Operater = ' ';
			}
			
		}else if(handler.getGame().getDifficulty() == 'H'){
			if(Utils.RandomNum(2, 0) == 0){
				Operater = '+';
				ans = Utils.RandomNum(19, 1);
				QP1 = Utils.RandomNum(21, -10);
				QP2 = ans - QP1;	
				generated = true;
				if(QP1 > ans) {
					Operater = ' ';
				}
			}else {
				Operater = '*';
				QP1 = Utils.RandomNum(5, 1);
				QP2 = Utils.RandomNum(5, 1);
				ans = QP1 * QP2;	
				generated = true;
			}

		}
	}		
	
	public void UserAnswering() {
		userAns = handler.getKeyManager().getInput(userAns);
	
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			if(Utils.parseInt(userAns) == ans){	//correct
				handler.getGame().setCurrentScore(handler.getGame().getCurrentScore() + 1);
				System.out.println("Correct");
				handler.getWorld().getEntityManager().getPlayer().setCantMove(false);
				x = handler.getWorld().GenerateRandomX();
				y = handler.getWorld().GenerateRandomY();
				setstats();

			}else {	//incorrect
				System.out.println("Wrong");
				handler.getWorld().getEntityManager().getPlayer().setX(100);
				handler.getWorld().getEntityManager().getPlayer().setY(100); 
				handler.getWorld().getEntityManager().getPlayer().setCantMove(false);
				handler.getGame().setrTimer(handler.getGame().getrTimer() - 5);
			}
			isPlayerStuck = false;
			userAns = "";
			generateQnA();
		}
	}
	
//	public void 
}

