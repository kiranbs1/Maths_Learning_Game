package Maths_Learning_Game.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.sound.sampled.Clip;

import Maths_Learning_Game.Audio.Audio;
import Maths_Learning_Game.Database.AllDatabaseDataRecord;
import Maths_Learning_Game.Database.AllSettings;
import Maths_Learning_Game.Database.DatabaseCommands;
import Maths_Learning_Game.display.Display;
import Maths_Learning_Game.gfx.Asset;
import Maths_Learning_Game.gfx.GameCamera;
import Maths_Learning_Game.input.KeyManager;
import Maths_Learning_Game.input.MouseManager;
import Maths_Learning_Game.states.State;
import Maths_Learning_Game.states.TorS;

public class Game implements Runnable {

	private Display display;  			
	private int width, height;			
	public String title;				
	private Thread thread;				
	private boolean running = false;	
	private BufferStrategy bs;			
	private Graphics g;					
	private int rTimer;					
	//user
	private boolean teacher = true;
	//player
	private int CurrentScore;				
	//Background sound
	private Clip clip;					
	//state
	public State gamestate;			
	public State menustate;			
	public State settingsstate;		
	public State PauseState;
	public State LoginState;
	public State TeacherMenuState;
	public State TorS;
	public State AddRemoveStudent;
	public State StudentMenuState;
	//Data
	private AllDatabaseDataRecord Data;
	//Input
	private KeyManager keymangager;		//created from class
	private MouseManager mousemanager;
	
	//camera
	private GameCamera gamecamera;		//created from class
	
	//Handler
	private Handler handler;		    //created from class
	//other
	private char Difficulty;
	private AllSettings AllSettings;
	//Game
	
	public Game(String title, int width, int height){
		this.width = width; 	//640
		this.height = height;	//500
		this.title = title; 
		keymangager = new KeyManager();
		mousemanager = new MouseManager();
		
	} 
	
	private void init() { 
		display = new Display (title,width,height);	
		display.getframe().addKeyListener(keymangager);
		display.getcanvas().addMouseListener(mousemanager);
		display.getcanvas().addMouseMotionListener(mousemanager);		
		Asset.init();  
		handler = new Handler(this);		
		gamecamera = new GameCamera(handler, 0, 0);
		
		Difficulty = 'E';
		AllSettings = new AllSettings(handler);
		
		TorS = new TorS(handler);
		State.setState(TorS);
		clip = Audio.BackgroundMusic(AllSettings.getVolume());
		clip.start(); 
		rTimer = 60;
		CurrentScore = 0;
									
	}
	
	
	private void tick() { 
		keymangager.tick();
		
		if (State.getState() != null) {	
		State.getState().tick(); 
		}

	}
	
	private void render() {  //used in run()
		
		bs = display.getcanvas().getBufferStrategy(); 
		if(bs == null) { 
			display.getcanvas().createBufferStrategy(3); 
			
	    	return;			
		}
		g = bs.getDrawGraphics();	
		g.clearRect(0, 0, width, height); 

		if (State.getState() != null)	
		    State.getState().render(g);	
		
		g.setColor(Color.red);
		g.fillRect(handler.getMouseManager().getMouseX() - 2, 
				handler.getMouseManager().getMouseY() - 2,
				4, 4);

		bs.show();	
		g.dispose();
	}
	
	
	public void run(){  //this is run after start.
		init();	//this runs the init() method
		//will only run once
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				delta--;
			}
		}
	}
	
	public void EndOfGame() {
		try {
			Data.getStudent().getScore().getScores()[2] = Data.getStudent().getScore().getScores()[1];
			Data.getStudent().getScore().getScores()[1] = Data.getStudent().getScore().getScores()[0];
			Data.getStudent().getScore().getScores()[0] = Integer.toString(CurrentScore) + Difficulty;
			
			DatabaseCommands.replaceScore(Data.getStudent().getScore().getID(), 
										  Data.getStudent().getScore().getScores()[0], 
										  Data.getStudent().getScore().getScores()[1], 
										  Data.getStudent().getScore().getScores()[2]);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void restart() {
		System.gc();
		rTimer = 60;
	}
	
	public synchronized void start() {
		if (running)	
			return;
		running = true;  
		thread = new Thread(this); 
		thread.start();	
	}

	public synchronized void stop() {
		if(!running) 
			return;
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//Getters and setters
	
	public KeyManager getkeymanager() {
		return keymangager;
	}
	
	public MouseManager getMouseManager() {
		return mousemanager;
	}
	
	public GameCamera getgamecamera() {
		return gamecamera;
	}
	
	public int getWidth() {
		return width;  //640
	}

	public int getHeight() {
		return height;  //500
	}

	public int getrTimer() {
		return rTimer;
	}

	public void setrTimer(int rTimer) {
		this.rTimer = rTimer;
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}
	
	public int getCurrentScore() {
		return CurrentScore;
	}

	public void setCurrentScore(int CurrentScore) {
		this.CurrentScore = CurrentScore;
	}

	public boolean getTeacher() {
		return teacher;
	}

	public void setTeacher(boolean teacher) {

		this.teacher = teacher;
		
	}
	
	public AllDatabaseDataRecord getData() {
		return Data;
	}

	public void setData(AllDatabaseDataRecord allDatabaseDataRecord) {
		Data = allDatabaseDataRecord;
	}

	public char getDifficulty() {
		return Difficulty;
	}

	public void setDifficulty(char difficulty) {
		Difficulty = difficulty;
	}

	public AllSettings getAllSettings() {
		return AllSettings;
	}

	public void setAllSettings(AllSettings allSettings) {
		AllSettings = allSettings;
	}
	
	
}

