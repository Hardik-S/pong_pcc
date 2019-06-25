package pongV1_3;
//Java 1.8.0 u211
//Hardik Shrestha June 27, 2019


/**
 * Java 1.8.0 u211
 * @author hardik shrestha
 * **
 * 6/27/2019
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class pongA extends Applet implements Runnable, KeyListener{ 	//this will be a runnable, constantly listening applet
	final int WIDTH = 1200, HEIGHT = 600; 	//constant (final int) defines background size
	Thread myThread;	//our chosen path of execution >:D
	PaddleA playerOne;
	PaddleB playerTwo;
	Puck myPuck;
	boolean gameStart;
	Graphics gfx; 		//buffer to combat blinking
	Image myImage;
	int redPoints;
	int bluePoints;
	
	
	public void init() {
		this.resize(WIDTH,HEIGHT);
		gameStart = false;
		redPoints = 0;
		bluePoints = 0;
		
		this.addKeyListener(this); 		//pongA is the key listener
		
		
		playerOne = new PaddleA(1);		//first player here -- not sure if i need to create a new paddleB or can just make a player 2
		playerTwo = new PaddleB(2);		//decides which side
		myPuck = new Puck();
		
		myImage = createImage(WIDTH, HEIGHT);
		gfx = myImage.getGraphics();
		
		myThread = new Thread(this);	//runnable Obj 
		myThread.start();
		
		
	}
	
	public void paint(Graphics g) {
		
		gfx.setColor(Color.black);
		gfx.fillRect(0,0,WIDTH,HEIGHT);
		
		
		if(!gameStart) {
			gfx.setColor(Color.white);
			gfx.drawString("W E L C O M E   T O   P O N G !", (WIDTH/2)-85, (HEIGHT/2)-200);
			gfx.drawString("H I T   S P A C E   T O   S T A R T !", (WIDTH/2)-90, (HEIGHT/2)-170);
			gfx.drawString("C O N T R O L S :", (WIDTH/2)-45, (HEIGHT/2)-140);
			gfx.drawString("W / S          ↑ / ↓", (WIDTH/2)-40, (HEIGHT/2)-110);

		}
		
		if(myPuck.getX()<=0) { 	//if red scores render this	
			myPuck.slowDown();
			gfx.setColor(Color.red);
			gfx.drawString("Red Scores!", (WIDTH/2)+270, (HEIGHT/2));
			redPoints++;
			myPuck.resetPuck();
		}
		else if(myPuck.getX()>=1200) { 	//if blue scores render this
			myPuck.slowDown();
			gfx.setColor(Color.blue);
			gfx.drawString("Blue Scores!", (WIDTH/2)-330, (HEIGHT/2));
			bluePoints++; 
			myPuck.resetPuck();
		}
				
		else		//default, just render the puck and paddles
			playerOne.draw(gfx);
			playerTwo.draw(gfx);
			if(gameStart) {		//wait game start before puck and halfway line
				myPuck.draw(gfx);
				gfx.setColor(Color.white);
				gfx.fillRect(599, 0, 2, HEIGHT);
				gfx.setColor(Color.blue);
				gfx.drawString(""+bluePoints, 587, 50);
				gfx.setColor(Color.red);			
				gfx.drawString(""+redPoints, 605, 50);
			}
		g.drawImage(myImage,0,0,this);		//renders next frame before it happens
	}	
	
	public void update(Graphics g) {
		paint(g);
		
	}

	public void run() {
		while(true) {
			if (gameStart) {
				playerOne.move();
				playerTwo.move();
			
				myPuck.move();
				myPuck.checkPaddleCollision(playerOne, playerTwo);
				
			
			}	
			
			repaint();		//repaints before pause creating infinite loop

			try {
				Thread.sleep(10); 	//sleeps for 10 milliseconds at the end every run
			} catch (InterruptedException e) { 	//catch interrupted exception when sleeping
				e.printStackTrace(); 	//lmk 
			}
		}
	}

	
	public void keyPressed(KeyEvent e) {		
		if(e.getKeyCode()==KeyEvent.VK_UP){		//self-explanatory
			playerOne.setUpAcceleration(true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			playerOne.setDownAcceleration(true); 	//if pressed down go fast!!
		}
		else if(e.getKeyCode()==KeyEvent.VK_W) {	//p2 movement
			playerTwo.setUpAcceleration(true);	
		}
		else if(e.getKeyCode()==KeyEvent.VK_S) {
			playerTwo.setDownAcceleration(true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			gameStart = true;
		}
	}

	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			playerOne.setUpAcceleration(false); //let go -> stop accelerate
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			playerOne.setDownAcceleration(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_W) {
			playerTwo.setUpAcceleration(false);	
		}
		else if(e.getKeyCode()==KeyEvent.VK_S) {
			playerTwo.setDownAcceleration(false);
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

}
