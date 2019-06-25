package pongV1_3;

//red paddle (right)


import java.awt.Color;
import java.awt.Graphics;

public class PaddleA implements Paddle{
	
	double y; //Position of the paddle.
	int x; //don't need double, will be static

	double yVelocity;
	
	boolean upAcceleration;
	boolean downAcceleration;
	
	int player;		//player 1 or 2
	final double FRICTION = 0.9;
	
	public PaddleA(int player) {
		upAcceleration = false;
		downAcceleration = false;
		y = 240;  	//given by (BackGround HEIGHT - paddle height) divided by 2 (because top left corner)
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
			yVelocity -= 1; 		//might look strange but recall that 0,0 exists in top left
		}
		else if(downAcceleration) {
			yVelocity += 1;		// same concept
		}
		else if(!upAcceleration && !downAcceleration) {		//implement friction so paddle slows down
			yVelocity *= FRICTION;
		}
		if(yVelocity >= 8)	//caps speed
			yVelocity =8;
		else if (yVelocity <= -8)
			yVelocity = -8;
			
		y += yVelocity; 	//so we can actually move
		
		if (y<0) 		//top limit
			y=0;
		else if (y>480)		//bottom limit
			y=480;
			//yVelocity *= -1; wanted some sort of bounce effect. come back to it later
	}
	
	public void setUpAcceleration(boolean input) { 		//sets up accel
		upAcceleration = input;
	}

	public void setDownAcceleration(boolean input) {		//sets down accel
		downAcceleration = input;
	}
	public int getY() {
		return (int)y; //return int value of y
	}

}
