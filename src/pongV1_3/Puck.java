package pongV1_3;

import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;

public class Puck {
	double xVelocity;
	double yVelocity;
	double x;
	double y;
	int rally;
	Random myRandom = new Random();		
	String pop;
	/**int puckRedPoints;
	int puckBluePoints;
	Graphics gfx;*/
	
	public Puck() {
		x = 600;
		y = 300;
		xVelocity = getRandomSpeed() * getRandomDirection();
		yVelocity = getRandomSpeed() * getRandomDirection();
		
	}
	
	public double getRandomSpeed() {
		return (Math.random()*3+1);
	}
	
	public int getRandomDirection() {
		int myRand = (int)(Math.random()*2);	//50-50 chance of being rounded to 1 or 2
		if(myRand==2)
			return 2;
		else
			return -2;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.cyan);
		g.fillOval(((int)x-15), ((int)y-15), 30, 30); //radius is 15. top left corner effect gets neutralized by -15.
		g.drawString(("Rally: "+String.valueOf(rally)), 1125 , 50);
	}
	
	public void slowDown() {
		xVelocity =1;
	}
	
	public void resetPuck() {
		x=600;
		y=300;
		rally=0;
		xVelocity = 2*xVelocity;
	}
		
	
	public void checkPaddleCollision(Paddle PaddleA, Paddle PaddleB) {
		if (x<=55 && x >=20) {
			if(y >= PaddleB.getY() && y <= PaddleB.getY()+120) {	//on left side
				xVelocity = -1.1*xVelocity;		//gets faster each rally
				if (x>0)
					rally++;
			}
		}
		else if (x>=1145 && x<=1180) {
			if (y >= PaddleA.getY() && y <= PaddleA.getY()+120) {	//right side
				xVelocity = -1.1*xVelocity;
				if (x<1200) {
					rally++;
				
				/**if(x<=-27) { 	//if red scores render this	
					gfx.setColor(Color.red);
					gfx.drawString("Red Scores!", 870, 300);
					puckRedPoints++;
				}
		else if(x>=1227) { 	//if blue scores render this
					gfx.setColor(Color.blue);
					gfx.drawString("Blue Scores!", 270, 300);
					puckBluePoints++;
			}
		gfx.setColor(Color.blue);
		gfx.drawString(""+puckBluePoints, 587, 50);
		gfx.setColor(Color.red);			
		gfx.drawString(""+puckRedPints, 605, 50);*/
			}	
		}				
		}		
	}
	
	
	public void move() {
		x += xVelocity;		//update movement
		y += yVelocity;
		
		if(y<20) 	//hits top edge
			yVelocity = -1*(int)yVelocity;
		if(y>580)	//hits bottom edge
			yVelocity = -1*(int)yVelocity;
		if (xVelocity>8)
			xVelocity=8;
		
		
		/**if(x<-30) {		//stops ball from continuing forever and taking memory
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x=600;
			y=300;
			rally=0;
			xVelocity = .5*xVelocity;
			//redPoints++;
		}
		if(x>1230) {	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x=600;
			y=300;
			rally=0;
			xVelocity = .5*xVelocity;

		}*/
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
}
