package pongV1_3;

import java.awt.Graphics;

public interface Paddle {
	public void draw(Graphics g);
	public void move();
	public int getY(); 		//has to return int
}
