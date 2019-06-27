/**
 * PaddleB
 * The annotated paddle
 * ~ready for final commit~
 */
package pongV1_3;

//blue paddle (blue)

import java.awt.Color;
import java.awt.Graphics;

public class PaddleB implements Paddle{
	
	double yB;									 //Position of paddle B.
	int xB;    								   	//don't need double, will be static

	double yVelocityB;							
	
	boolean upAccelerationB;
	boolean downAccelerationB;
	
	int player;									//player 1 or 2
	final double FRICTION = 0.9;				//creates a sort of gliding motion for the paddle after release
	
	public PaddleB(int player) {
		upAccelerationB = false;
		downAccelerationB = false;
		yB = 240;  								//given by (BackGround HEIGHT - paddle height) divided by 2 (because top left corner)
		yVelocityB = 0;
		if (player==2)
			xB = 20;							//similar process here. I could've done this using formulae, but just simpler to do it in my mind. 
												//However, in a future implementation if the user wanted to change the applet size, all of these dependent vars could be linked.
		else
			xB = 1160;
	}
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(xB, (int)yB, 20, 120); 		//y value has to be made int for fillRect
	}

	public void move() {
		if(upAccelerationB) {
			yVelocityB -= 1; 					//might look strange but recall that 0,0 exists in top left
		}
		else if(downAccelerationB) {
			yVelocityB += 1;					//same concept
		}
		else if(!upAccelerationB && !downAccelerationB) {		//implement friction so paddle slows down after release
			yVelocityB *= FRICTION;
		}
		if(yVelocityB >= 8)						//caps speed at 8
			yVelocityB =8;
		else if (yVelocityB <= -8)
			yVelocityB = -8;
			
		yB += yVelocityB; 						//allows for movement after ensuring we wont go too fast
		
		if (yB<0) 								//top limit so paddle doesn't exit screen
			yB=0;
		else if (yB>480)						//bottom limit
			yB=480;
	}
	
	public void setUpAcceleration(boolean input) { 		//just true/false. should we move up or not?
		upAccelerationB = input;
	}

	public void setDownAcceleration(boolean input) {		//downAccel. go down?
		downAccelerationB = input;
	}
	public int getY() {							//for pongA
		return (int)yB; //return int value of y
	}

}
