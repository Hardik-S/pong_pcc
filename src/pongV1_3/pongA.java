package pongV1_3;
//Java 1.8.0 u211
//Hardik Shrestha June 27, 2019


/**
 * Java 1.8.0 u211
 * @author hardik shrestha
 * **
 * 6/27/2019
 * current errors:
 * none
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Timer;

public class pongA extends Applet implements Runnable, KeyListener{ 	//this will be a runnable, constantly listening applet
	final int WIDTH = 1200, HEIGHT = 600; 	//constant (final int) defines background size
	Thread myThread;	//our chosen path of execution >:D
	PaddleA playerOne;
	PaddleB playerTwo;
	Puck myPuck;
	Graphics gfx; 		//buffer to combat blinking
	Image myImage;
	int redPoints;
	int bluePoints;
	int delay;
	boolean gameStart;
	boolean gameEnd;
	int playTo;
	Scanner myScanner;
	
	public void init() {
		this.resize(WIDTH,HEIGHT);
		gameStart = false;
		gameEnd = false;
		playTo = 5;
		redPoints = 0;
		bluePoints = 0;
		delay = 1000;
		
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
			gfx.setFont(new Font("Courier New",Font.PLAIN,30)); //big font 
			gfx.drawString("W E L C O M E   T O   P O N G !", (WIDTH/2)-280, (HEIGHT/2)-200);
			gfx.drawString("F I R S T   T O   5   P O I N T S   W I N S!", (WIDTH/2)-380, (HEIGHT/2)-160);

			gfx.drawString("C O N T R O L S :", (WIDTH/2)-180, (HEIGHT/2)-120);
			gfx.drawString("W / S       ↑ / ↓", (WIDTH/2)-180, (HEIGHT/2)-80);
			
			gfx.drawString("H I T   S P A C E   T O   S T A R T !", (WIDTH/2)-330, (HEIGHT/2)-10);
			
			gfx.setFont(new Font("Courier New",Font.PLAIN,20)); //smaller font
			gfx.drawString("O R   P R E S S   ' B '   F O R   B E G I N N E R   M O D E", (WIDTH/2)-350, (HEIGHT/2)+20);

		}
		
		if(myPuck.getX()<=0) { 	//if red scores render this	
			myPuck.slowDown();
			gfx.setColor(Color.red);
			
			
			redPoints++;
			System.out.println("Red Scores "+redPoints);
			
			myPuck.resetPuck();
				if (redPoints==5) {
					//myPuck.redVictory(g);
					gfx.setFont(new Font("TimesRoman",Font.PLAIN,20));
					gfx.drawString("R e d   W i n s !", (WIDTH/2)+270, (HEIGHT/2));
					System.out.println("Red Wins "+redPoints+"-"+bluePoints);
					gameEnd=true;
					System.out.println("Game End");
				}
				else if (bluePoints==4 && redPoints==4) {
					myPuck.lastPoint(g);
				}
		}
		else if(myPuck.getX()>=1200) { 	//if blue scores render this
			myPuck.slowDown();
			gfx.setColor(Color.blue);
			
			
			bluePoints++; 
			System.out.println("Blue Scores "+bluePoints);

			myPuck.resetPuck();
				if (bluePoints==5) {
					//myPuck.blueVictory(g);
					gfx.setFont(new Font("TimesRoman",Font.PLAIN,20));
					gfx.drawString("B l u e   W i n s !", (WIDTH/2)-330, (HEIGHT/2));
					System.out.println("Blue Wins "+bluePoints+"-"+redPoints);
					gameEnd=true;
					System.out.println("Game End");
				}
				else if (bluePoints==4 && redPoints==4) {
					myPuck.lastPoint(g);
				}
		}
				
		else		//default, just render the puck and paddles
			playerOne.draw(gfx);
			playerTwo.draw(gfx);
			if(gameStart) {		//wait game start before drawing puck and halfway line
				myPuck.draw(gfx);
				gfx.setColor(Color.white);
				gfx.fillRect(599, 0, 2, HEIGHT);
				
				gfx.setFont(new Font("TimesRoman",Font.PLAIN,15));
				gfx.setColor(Color.blue);
				gfx.drawString(""+bluePoints, 587, 50);
				gfx.setColor(Color.red);			
				gfx.drawString(""+redPoints, 605, 50);
			}
		g.drawImage(myImage,0,0,this);		//renders next frame before it happens

	}	
	
	public void update(Graphics g) {
		if (!gameEnd) {
			paint(g);
		}
		else
			System.out.println("xd");
		
	}

	public void run() {
		while(true) {
			if (gameStart) {
				playerOne.move();
				playerTwo.move();
			
				myPuck.move();
				myPuck.checkPaddleCollision(playerOne, playerTwo);
				
			
			}	
			if (!gameEnd) {
				repaint();		//repaints before pause creating infinite loop
			}

			try {
				Thread.sleep(5); 	//sleeps for 5 milliseconds at the end every run
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
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			gameStart = true;
			System.out.println("Game Started");
		}
		else if(e.getKeyCode()==KeyEvent.VK_B) {
			myPuck.Beginner();
			gameStart = true;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
}
