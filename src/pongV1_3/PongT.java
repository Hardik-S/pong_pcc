/**
 * UNIT TESTING FILE
 */
package pongV1_3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PongT {
	
	pongA myPong = new pongA();
	Puck myPuck = new Puck();


	@Test

	void testAdd() {
		int expected = 9;
		int actual = myPuck.add(4,5);
		System.out.println("from PongT: "+actual);
		Assert.assertEquals(expected, actual, 0);
	}

}
