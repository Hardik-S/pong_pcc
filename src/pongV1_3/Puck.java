/**
 * Puck Class
 * Where most of the logic is
  * ~ready for final commit~
 */

package pongV1_3;

import java.awt.Color;
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
	boolean noobPlayer;													//"noob" means new. As in first-time player.
	boolean matchPoint;
	
	Random myRandom = new Random();										
	Graphics gfx;
	Image myImage;
	
	public int add(int a, int b) {										//for practice unit testing 
		return a+b;														//would be deleted for shipping
	}
	
	public void init() {
		gfx = myImage.getGraphics();
	}
	
	public void Beginner() {											//if beginner mode is triggered this fires
		noobPlayer = true;
		System.out.println("Game started in beginner mode");
	}
	
	public Puck() {
		x = 600;
		y = 300;
		if (noobPlayer) {												//slow if new
			xVelocity =0.6;
			yVelocity =0.8;
		}
		else {															//fast else
			xVelocity = getRandomSpeed() * getRandomDirection();
			yVelocity = getRandomSpeed() * getRandomDirection();

			
			if (xVelocity<=1 || xVelocity >= -1) {						//these ifs exist to ensure its not TOO fast
				xVelocity = xVelocity*2;
			}
			if (xVelocity>=6 || xVelocity <= -6) {
				xVelocity = xVelocity/3;
			}
			
			if (yVelocity<=1 || yVelocity >= -1) {
				yVelocity = yVelocity*2;
			}	
			if (yVelocity>=4 || yVelocity <= -4) {
				yVelocity = yVelocity/3;
			}
		}
		
		System.out.println("og xVelocity: "+xVelocity+" og yVelocity: "+yVelocity); 	//og means original
		
	}
	
	public double getRandomSpeed() {
		randomSpeed = (Math.random()*3+1);
		return randomSpeed;
	}
	
	public int getRandomDirection() {
		int myRand = (int)(Math.random()*2);							//50-50 chance of being rounded to 1 or 2
		if(myRand==1)
			return 1;
		else
			return -1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.cyan);											//i want ball radius to be 30
		g.fillOval(((int)x-15), ((int)y-15), 30, 30); 					//radius is 15. top left corner effect gets neutralized by -15.
		g.drawString(("Rally: "+String.valueOf(rally/2)), 1125 , 50);
	}
	
	public double slowDown() { 		
		xVelocity = 1;
		return xVelocity;
	}
	
	public void resetPuck() {
		x=600;
		y=300;
		rally=0;
		if (noobPlayer) {
			System.out.println("got here (reset)");		//even if new, change it up a little bit
			xVelocity = -1.5;
			yVelocity = -0.8;
		}
		else {
			xVelocity = 2*xVelocity;					//else, just double speed after the reset. not too fast though.
			if (xVelocity<=1 || xVelocity >= -1) {
				xVelocity = xVelocity*2;
			}
			if (xVelocity>=6 || xVelocity <= -6) {
				xVelocity = xVelocity/3;
			}
			
			if (yVelocity<=1 || yVelocity >= -1) {
				yVelocity = yVelocity*2;
			}	
			if (yVelocity>=4 || yVelocity <= -4) {
				yVelocity = yVelocity/3;
			}
		}
		System.out.println("new xVelocity: "+xVelocity+" new yVelocity: "+yVelocity);	
	}
	
	public String[] checkPaddleCollision(Paddle PaddleA, Paddle PaddleB) {	//reason for string explained later
		if (x<=55 && x >=20) {												//blue paddle hit
			if(y >= PaddleB.getY() && y <= PaddleB.getY()+120) {			//on left side
				xVelocity = -1.1*xVelocity;									//gets faster each rally
				yVelocity =.9*yVelocity;
				System.out.println("Blue hits it ");
				System.out.println("new xVelocity: "+xVelocity+" new yVelocity: "+yVelocity);
				if (x>0)
					rally++;
					x = x+20;
			}
		}
		else if (x>=1145 && x<=1180) {
			if (y >= PaddleA.getY() && y <= PaddleA.getY()+120) {			//right side
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
		
		String xyVelocity[] = new String [2];				//combining both x and y velocities into a returnable string for unit testing
		xyVelocity[0] = String.valueOf(xVelocity);			//perhaps there's a better way to return two values, or maybe one should just make two separate methods for this in the future
		xyVelocity[1] = String.valueOf(yVelocity);			//this ends up not working out in Unit Testing
		
		return xyVelocity;		
	}
	
	public void move() {					//actual thing that gets called
		move(xVelocity);					//overloaded method :)
	}
	
	public double move(double xVelocity) {	//just for unit testing
		x += xVelocity;						//update movement
		y += yVelocity;
			
		if(y<20) 							//hits top edge
			yVelocity = -yVelocity;
		if(y>580)							//hits bottom edge
			yVelocity = -yVelocity;
		if (xVelocity>8)					//too fast due to speed multiplier 
			xVelocity=8;
		
		return xVelocity;
	}
	
	
	 
	
	public int getX() {						//for pongA / pongT to call
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public double getXvel() {
		return xVelocity;
	}
	
	public double getYvel() {
		return yVelocity;
	}

	
	public void lastPoint() {				//just for system, too awkward visually to implement into applet
		System.out.println("SCORE IS 4-4!");
		matchPoint = true;
		
	}
}
