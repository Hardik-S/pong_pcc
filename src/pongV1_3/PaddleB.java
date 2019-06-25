package pongV1_3;

//blue paddle (blue)

import java.awt.Color;
import java.awt.Graphics;

public class PaddleB implements Paddle{
	
	double yB; //Position of paddle B.
	int xB; //don't need double, will be static

	double yVelocityB;
	
	boolean upAccelerationB;
	boolean downAccelerationB;
	
	int player;		//player 1 or 2
	final double FRICTION = 0.9;
	
	public PaddleB(int player) {
		upAccelerationB = false;
		downAccelerationB = false;
		yB = 240;  	//given by (BackGround HEIGHT - paddle height) divided by 2 (because top left corner)
		yVelocityB = 0;
		if (player==2)
			xB = 20;	//similar process here
		else
			xB = 1160;
	}
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(xB, (int)yB, 20, 120); 	//y value has to be made int 
	}

	public void move() {
		if(upAccelerationB) {
			yVelocityB -= 1; 		//might look strange but recall that 0,0 exists in top left
		}
		else if(downAccelerationB) {
			yVelocityB += 1;		// same concept
		}
		else if(!upAccelerationB && !downAccelerationB) {		//implement friction so paddle slows down
			yVelocityB *= FRICTION;
		}
		if(yVelocityB >= 8)	//caps speed
			yVelocityB =8;
		else if (yVelocityB <= -8)
			yVelocityB = -8;
			
		yB += yVelocityB; 	//so we can actually move
		
		if (yB<0) 		//top limit
			yB=0;
		else if (yB>480)		//bottom limit
			yB=480;
			//yVelocity *= -1; wanted some sort of bounce effect. come back to it later
	}
	
	public void setUpAcceleration(boolean input) { 		//sets upAccel
		upAccelerationB = input;
	}

	public void setDownAcceleration(boolean input) {		//sets downAccel
		downAccelerationB = input;
	}
	public int getY() {
		return (int)yB; //return int value of y
	}

}
