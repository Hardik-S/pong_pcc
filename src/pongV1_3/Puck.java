package pongV1_3;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;

public class Puck {
	double xVelocity;
	double yVelocity;
	double x;
	double y;
	double randomSpeed;
	int rally;
	boolean noobPlayer;
	boolean matchPoint;
	
	Random myRandom = new Random();		
	String pop;
	Graphics gfx;
	Image myImage;
	
	public int add(int a, int b) {		//testing unit testing take out later
		return a+b;
	}
	
	public void init() {
		gfx = myImage.getGraphics();
	}
	
	public void Beginner() {
		noobPlayer = true;
		System.out.println("Game started in beginner mode");
	}
	
	public Puck() {
		x = 600;
		y = 300;
		if (noobPlayer) {
			xVelocity =0.6;
			yVelocity =0.8;
		}
		else {
			xVelocity = getRandomSpeed() * getRandomDirection();
			System.out.println("from puck: getRandomSpeed: "+randomSpeed);

			yVelocity = getRandomSpeed() * getRandomDirection();
			System.out.println("from puck 2: getRandomSpeed: "+randomSpeed);

			
			if (xVelocity<=1 || xVelocity >= -1) {
				xVelocity = xVelocity*2;
			}
			if (xVelocity>=6 || xVelocity <= -6) {
				xVelocity = xVelocity/2;
			}
			
			if (yVelocity<=1 || yVelocity >= -1) {
				yVelocity = yVelocity*2;
			}	
			if (yVelocity>=4 || yVelocity <= -4) {
				yVelocity = yVelocity/2;
			}
		}
		
		System.out.println("og xVelocity: "+xVelocity+" og yVelocity: "+yVelocity);
		
	}
	
	public double getRandomSpeed() {
		randomSpeed = (Math.random()*3+1);
		return randomSpeed;
	}
	
	public int getRandomDirection() {
		int myRand = (int)(Math.random()*2);	//50-50 chance of being rounded to 1 or 2
		if(myRand==1)
			return 1;
		else
			return -1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.cyan);						//i want ball radius to be 30
		g.fillOval(((int)x-15), ((int)y-15), 30, 30); //radius is 15. top left corner effect gets neutralized by -15.
		g.drawString(("Rally: "+String.valueOf(rally/2)), 1125 , 50);
	}
	
	public void slowDown() { 		
		xVelocity =1;
	}
	
	public void resetPuck() {
		x=600;
		y=300;
		rally=0;
		if (noobPlayer) {
			System.out.println("got here (reset)");
			xVelocity = -1.5;
			yVelocity = -0.8;
		}
		else {
			xVelocity = 2*xVelocity;
			if (xVelocity<=1 || xVelocity >= -1) {
				xVelocity = xVelocity*2;
			}
			if (xVelocity>=6 || xVelocity <= -6) {
				xVelocity = xVelocity/2;
			}
			
			if (yVelocity<=1 || yVelocity >= -1) {
				yVelocity = yVelocity*2;
			}	
			if (yVelocity>=4 || yVelocity <= -4) {
				yVelocity = yVelocity/2;
			}
		}
		System.out.println("new xVelocity: "+xVelocity+" new yVelocity: "+yVelocity);	
	}
	
	public void checkPaddleCollision(Paddle PaddleA, Paddle PaddleB) {
		if (x<=55 && x >=20) {		//blue paddle hit
			if(y >= PaddleB.getY() && y <= PaddleB.getY()+120) {	//on left side
				xVelocity = -1.1*xVelocity;		//gets faster each rally
				yVelocity =.9*yVelocity;
				System.out.println("Blue hits it ");
				System.out.println("new xVelocity: "+xVelocity+" new yVelocity: "+yVelocity);
				if (x>0)
					rally++;
					x = x+20;

			}
		}
		else if (x>=1145 && x<=1180) {
			if (y >= PaddleA.getY() && y <= PaddleA.getY()+120) {	//right side
				xVelocity = -1.1*xVelocity;
				yVelocity =.9*yVelocity;

				System.out.println("Red hits it ");
				System.out.println("new xVelocity: "+xVelocity+" new yVelocity: "+yVelocity);
				if (x<1200) {
					rally++;
					x = x-20;
			}	
		}				
		}		
	}
	
	public void move() {
		x += xVelocity;		//update movement
		y += yVelocity;
		
		if(y<20) 	//hits top edge
			yVelocity = -yVelocity;
		if(y>580)	//hits bottom edge
			yVelocity = -yVelocity;
		if (xVelocity>8)
			xVelocity=8;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}

	
	public void lastPoint(Graphics g) {
		System.out.println("SCORE IS 4-4!");
		matchPoint = true;
		
	}
}
