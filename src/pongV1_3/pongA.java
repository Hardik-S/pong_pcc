/**
 * Java 1.8.0 u211
 * @author hardik shrestha
 * **
 * 6/27/2019
 * pongA for pong Applet
 * ~ready for final commit~
 */

package pongV1_3;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class pongA extends Applet implements Runnable, KeyListener{ 				//this will be a runnable, constantly listening applet
	final int WIDTH = 1200, HEIGHT = 600; 											//constant (final int) defines background size
	Thread myThread;																//our chosen path of execution >:D
	PaddleA playerOne;																//red
	PaddleB playerTwo;																//blue
	Puck myPuck;
	Graphics gfx; 																	//buffer to combat blinking
	Image myImage;																	//paints before render
	int redPoints;
	int bluePoints;
	boolean gameStart;
	boolean gameEnd;
	int playTo;
	Scanner myScanner;																//for initial gamemode selection
	
	public void init() {
		this.resize(WIDTH,HEIGHT);
		gameStart = false;
		gameEnd = false;
		playTo = 5;										//in the future, this can easily be made a user input insead. However, it doesn't really go with the retro theme of the game
		redPoints = 0;
		bluePoints = 0;
		
		this.addKeyListener(this); 						//pongA is the key listener
		
		
		playerOne = new PaddleA(1);						//first player here 
		playerTwo = new PaddleB(2);						//decides which side
		myPuck = new Puck();
		
		myImage = createImage(WIDTH, HEIGHT);
		gfx = myImage.getGraphics();
		
		myThread = new Thread(this);					//execution begins 
		myThread.start();
	}
	
	
	
	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0,0,WIDTH,HEIGHT);
		
		if(!gameStart) {																			//before player starts game
			gfx.setColor(Color.white);																//introduction to game and instructions
			gfx.setFont(new Font("Courier New",Font.PLAIN,30)); 									//big font 
			gfx.drawString("W E L C O M E   T O               P O N G !", (WIDTH/2)-370, (HEIGHT/2)-200);
			
			gfx.setFont(new Font("Courier New",Font.BOLD,30)); 										//bold font 
			gfx.drawString("                      R E T R O            ", (WIDTH/2)-370, (HEIGHT/2)-200);
			
			gfx.setFont(new Font("Courier New",Font.PLAIN,30)); 									//back to plain  
			gfx.drawString("F I R S T   T O   5   P O I N T S   W I N S!", (WIDTH/2)-380, (HEIGHT/2)-160);

			gfx.drawString("C O N T R O L S :", (WIDTH/2)-180, (HEIGHT/2)-120);
			gfx.drawString("W / S       ↑ / ↓", (WIDTH/2)-180, (HEIGHT/2)-80);
			
			gfx.drawString("H I T               T O   S T A R T !", (WIDTH/2)-330, (HEIGHT/2)-10);
			gfx.setFont(new Font("Courier New",Font.BOLD,30)); 										//bold font 
			gfx.drawString("        S P A C E                    ", (WIDTH/2)-330, (HEIGHT/2)-10);
			
			gfx.setFont(new Font("Courier New",Font.PLAIN,20));										 //smaller font
			gfx.drawString("O R   P R E S S           F O R   B E G I N N E R   M O D E", (WIDTH/2)-350, (HEIGHT/2)+20);
			gfx.setFont(new Font("Courier New",Font.BOLD,20));										 //smaller font
			gfx.drawString("                  ' B '                                    ", (WIDTH/2)-350, (HEIGHT/2)+20);

		}
		
		if(myPuck.getX()<=0) { 								//if red scores render this	
			myPuck.slowDown();								//slow down after intense rally (puck is 1.1x speed after each hit)
			gfx.setColor(Color.red);
			redPoints++;									//increase red points by 1
			System.out.println("Red Scores "+redPoints);
			
			myPuck.resetPuck();								//reset puck to center
				if (redPoints==playTo) {					//check if red has won
					gfx.setFont(new Font("TimesRoman",Font.PLAIN,20));
					gfx.drawString("R e d   W i n s !", (WIDTH/2)+270, (HEIGHT/2));
					System.out.println("Red Wins "+redPoints+"-"+bluePoints);
					gameEnd=true;
					System.out.println("Game End");
				}
				else if (bluePoints==(playTo-1) && redPoints==(playTo-1)) {	//check for matchpoint
					myPuck.lastPoint();
				}
		}
		else if(myPuck.getX()>=1200) { 						//if blue scores render this
			myPuck.slowDown();								//slow down, but preserve direction
			gfx.setColor(Color.blue);
			bluePoints++; 									//increase blue points
			System.out.println("Blue Scores "+bluePoints);
			myPuck.resetPuck();								//reset puck to center
				if (bluePoints==playTo) {					//check for win
					gfx.setFont(new Font("TimesRoman",Font.PLAIN,20));
					gfx.drawString("B l u e   W i n s !", (WIDTH/2)-330, (HEIGHT/2));
					System.out.println("Blue Wins "+bluePoints+"-"+redPoints);
					gameEnd=true;
					System.out.println("Game End");
				}
				else if (bluePoints==(playTo-1) && redPoints==(playTo-1)) {	//check for matchpoint
					myPuck.lastPoint();
				}
		}
				
		else												//before game starts, just render the paddles
			playerOne.draw(gfx);
			playerTwo.draw(gfx);
			if(gameStart) {									//if game starts, draw the puck and halfway line
				myPuck.draw(gfx);
				gfx.setColor(Color.white);
				gfx.fillRect(599, 0, 2, HEIGHT);			//half way line
				gfx.setFont(new Font("TimesRoman",Font.PLAIN,15));
				gfx.setColor(Color.blue);
				gfx.drawString(""+bluePoints, 587, 50);		//blue points (center screen)
				gfx.setColor(Color.red);			
				gfx.drawString(""+redPoints, 605, 50);		//red points (center screen)
			}
		g.drawImage(myImage,0,0,this);						//renders next frame before it happens

	}	
	
	public void update(Graphics g) {						//change screen on game end
		if (!gameEnd) {
			paint(g);
		}
	}
	
	public void run() {										//the main loop
		while(true) {
			if (gameStart) {								//once game starts, everyone gets to start moving
				playerOne.move();
				playerTwo.move();
				myPuck.move();
				myPuck.checkPaddleCollision(playerOne, playerTwo);		//Collision theory is called
			}	
			if (!gameEnd) {					//between game start and game end
				repaint();					//repaints before pause creating infinite loop
			}
			
			try {
				Thread.sleep(5); 	//sleeps for 5 milliseconds at the end every run
			} catch (InterruptedException e) { 	//catch interrupted exception when sleeping
				e.printStackTrace(); 	
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {		
		if(e.getKeyCode()==KeyEvent.VK_UP){					//up arrow key input
			playerOne.setUpAcceleration(true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {			//down arrow key input
			playerOne.setDownAcceleration(true); 			
		}
		else if(e.getKeyCode()==KeyEvent.VK_W) {			//W key input
			playerTwo.setUpAcceleration(true);	
		}
		else if(e.getKeyCode()==KeyEvent.VK_S) {			//S key input
			playerTwo.setDownAcceleration(true);
		}	
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			playerOne.setUpAcceleration(false); 			//if player lets go of up arrow key -> stop moving up
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {			//if player lets go of down arrow key -> stop moving up
			playerOne.setDownAcceleration(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_W) {			//if player lets go of W key -> stop moving up
			playerTwo.setUpAcceleration(false);	
		}
		else if(e.getKeyCode()==KeyEvent.VK_S) {			//if player lets go of S key -> stop moving up
			playerTwo.setDownAcceleration(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) {		//game starts once player hits and releases spacebar
			gameStart = true;
			System.out.println("Game Started");
		}
		else if(e.getKeyCode()==KeyEvent.VK_B) {			//for new players, they can choose to play with a significantly slower puck and set direction of serve
			myPuck.Beginner();
			gameStart = true;
		}
	}



	@Override												//just auto created, doesn't seem to like when i get rid of it
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
