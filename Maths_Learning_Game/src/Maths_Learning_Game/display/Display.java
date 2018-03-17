package Maths_Learning_Game.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;		//Created from import. This displays the Canvas
	private  Canvas canvas;		//created from import. This gets drawn onto by render.
	
	
	//Display
	
	public Display(String title, int width, int height){  //display constructor. Run in Game.init()
		
		createDisplay(title, width, height);  //runs method createDisplay()
		
	}
	
	public void DisposeDisplay() {
		frame.setVisible(false);
		frame.dispose();
	}
	
	
	private void createDisplay(String title, int width, int height) {  //run in Display()
		frame = new JFrame (title);  //creates a new JFrame window called whatever the title variable is equal to
		frame.setSize(width,height); //sets the JFrame window to the Width and Height of the width and height variables 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //shuts down the program properly
		frame.setResizable(false);	//this makes it so that the user cannot resize the window
		frame.setLocationRelativeTo(null);  //can be used to set position of the Window. Only used here to stop potential errors
		frame.setVisible(true);  //makes the window visible to the user. 

		canvas = new Canvas();  //Creates a new Canvas. required in my method of rendering
		canvas.setPreferredSize(new Dimension(width,height));	//used to set width and height of canvas
		canvas.setMaximumSize(new Dimension(width, height));	//used to set width and height of canvas
		canvas.setMinimumSize(new Dimension(width, height));	//used to set width and height of canvas
		canvas.setFocusable(false);
		
		frame.add(canvas);  //This adds the canvas to the frame so that the rendered graphics can be displayed
		frame.pack();		//only useful if i set frame.setResizable() to true.
		
	}
	
	public Canvas getcanvas() {  //allows access to canvas
		return canvas;		
	}
	public JFrame getframe() {  //allows access to canvas
		return frame;
		
	}
	
}
