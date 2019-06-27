/**
 * PaddleA
 * The lesser annotated paddle
 * ~ready for final commit~
 */
package pongV1_3;

//red paddle (right)
/**
 * This is better commented in PaddleB.java
 * Please take a look at it instead.
 * The code is simply mirrored for both paddles, however I only documented PaddleB (Player 2/ Blue Paddle) extensively.
 */


import java.awt.Color;
import java.awt.Graphics;

public class PaddleA implements Paddle{
	
	double y; 
	int x; 

	double yVelocity;
	
	boolean upAcceleration;
	boolean downAcceleration;
	
	int player;		//player 1 or 2
	final double FRICTION = 0.9;
	
	public PaddleA(int player) {
		upAcceleration = false;
		downAcceleration = false;
		y = 240;  	
		yVelocity = 0;
		if (player==1)
			x = 1160;
		else
			x = 20;
	}
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, (int)y, 20, 120); 	//y value has to be made int 
	}

	public void move() {
		if(upAcceleration) {
			yVelocity -= 1; 		
		}
		else if(downAcceleration) {
			yVelocity += 1;		
		}
		else if(!upAcceleration && !downAcceleration) {		//implement friction so paddle slows down
			yVelocity *= FRICTION;
		}
		if(yVelocity >= 8)	
			yVelocity =8;
		else if (yVelocity <= -8)
			yVelocity = -8;
			
		y += yVelocity; 	//so we can actually move
		
		if (y<0) 		//top limit
			y=0;
		else if (y>480)		
			y=480;
	}
	
	public void setUpAcceleration(boolean input) { 		
		upAcceleration = input;
	}

	public void setDownAcceleration(boolean input) {		
		downAcceleration = input;
	}
	public int getY() {
		return (int)y;
	}

}
