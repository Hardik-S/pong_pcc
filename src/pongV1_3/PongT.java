/**
 * UNIT TESTING FILE
 * pongT for pong Testing
 * ~ready for final commit~
 */
package pongV1_3;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PongT {
	
	pongA myPong = new pongA();
	Puck myPuck = new Puck();

	@Test
	//just a practice test
	void testAdd() {
		int expected = 9;
		int actual = myPuck.add(4,5);
		System.out.println("from PongT, the sum is : "+actual);
		Assert.assertEquals(expected, actual, 0);
	}
	
	@Test
	//this one can't mess up
	void testSlowDown() {
		int expected = 1;
		double actual = myPuck.slowDown();
		System.out.println("from PongT the new speed is : "+actual);
		Assert.assertEquals(expected, actual, 0);
	}
	
	//Please Read:
	
	
	//first real test
	//its a fail, unfortunately I did not take into account unit testing when developing the code.
	//due to how I passed a class instead of a normal variable, I was unable to unit test the function checkPaddleCollision.
	//in the future, I will take caution when making complex methods that pass classes by keeping unit testing in mind.
	//going into this project, I didn't know what unit tests were. However, I will always code with them in consideration moving forward.
	
/**
  	void testCheckPaddleCollision() {
 	abstract class testPaddleA implements Paddle{				Tried playing around with abstract classes - failed.
		}
		abstract class testPaddleB implements Paddle{
			
		
		
		PaddleA testPaddleA = new PaddleA();					Tried playing around with MoreUnit to mock a class for unit testing, failed.
		new Expectations() {{
			mockPuck
		
		
		String xyVelocityExpected = (String.valueOf(-1.1*myPuck.getXvel())+String.valueOf(.9*myPuck.getYvel()));
		String[] xyVelocityActual = myPuck.checkPaddleCollision(testPaddleA, testPaddleB);
		System.out.println("from PongT the new xyVelocity is : "+xyVelocityActual);
		Assert.assertEquals(xyVelocityExpected, xyVelocityActual, 0);
		
		NOTE: The logic itself is sound - just unlucky that I just assumed I could pass a class from here.
	}
 */
		
		
	@Test
	//tests move
	void testMove() {
		double expected = 8.0;
		double actual = myPuck.move(12.453423);
		Assert.assertEquals(expected, actual, 0);
		System.out.println("from PongT the new xVelocity is : "+actual);
	}
	
}
