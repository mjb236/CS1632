import static org.junit.Assert.*;

import org.junit.Test;


public class RandomNumberTest {

//******** RandomNumber Class Tests *********************

	//Ensure that the object gets constructed correctly.
	@Test
	public void randomNumberConstructionTest() {
		RandomNumber rand = new RandomNumber(0);
		assertNotNull(rand);
	}
	
	//Test that the number returned by the RandomNumber's getInt method
	//is within the expected range 1000 times. Should never return more
	//than the number passed in or less than 0
	@Test
	public void randomNumberGetIntTest() {
		RandomNumber rand = new RandomNumber(0);
		for(int i = 0; i < 1000; i++) {
			int num = rand.getInt(10);
			assertTrue(num >= 0 && num < 10);
		}
	}
	
	//Test that two RandomNumbers getInt method returns the same number
	//given the same seed.
	//The two generators should return the same value.
	@Test
	public void sameSeedTest() {
		RandomNumber rand1 = new RandomNumber(10);
		RandomNumber rand2 = new RandomNumber(10);
		
		assertEquals(rand1.getInt(500), rand2.getInt(500));
	}
	
//******** Ends RandomNumber Class Tests *****************

}
