//Michael Bowen
//CS1632 - Deliverable 2

//Wrapper class that will use Java's random number generator
//to return a random integer - will be used to determine starting point
//of the driver, as well as what road to use when moving.

import java.util.Random;

public class RandomNumber {
	Random rand;

	//constructor
	public RandomNumber(long seed) {
		rand = new Random(seed);
	}
	
	//function uses the Random's getDouble to calculate a pseudorandom
	//number between 0(inclusive) and upperBound(non inclusive)
	public int getInt(int upperBound) {
		return (int) (rand.nextDouble() * upperBound);
	}
}