import static org.junit.Assert.*;

import org.junit.Test;


public class SieveTest {

	//Make sure the convertResults test returns the expected results
	//both before and after refactoring based on FindBugs suggestions
	@Test
	public void convertResultsTest1() {
		int[] results = new int[10];
		boolean[] prime = new boolean[10];
		for(int i = 0; i < results.length; i++) {
			results[i] = i;
			prime[i] = true;
		}
		
		int[] toReturn = Sieve.convertResults(results, prime);
		
		for(int i = 0; i < toReturn.length; i++) {
			if(toReturn[i] != (i+1)) {
				fail();
			}
		}
	}
	
	@Test
	public void convertResultsTest2() {
		int[] results = new int[10];
		boolean[] prime = new boolean[10];
		for(int i = 0; i < results.length; i++) {
			results[i] = i;
			prime[i] = false;
		}
		
		prime[9] = true;
		
		int[] toReturn = Sieve.convertResults(results, prime);
		
		//only one value in array, and it is 10 (9  + 1)
		assertTrue(toReturn.length == 1);
		assertTrue(toReturn[0] == 10);
	}
	
	@Test
	public void convertResultsTest3() {
		int[] results = new int[10];
		boolean[] prime = new boolean[10];
		for(int i = 0; i < results.length; i++) {
			results[i] = i;
			prime[i] = false;
		}
		
		int[] toReturn = Sieve.convertResults(results, prime);
		
		//no value returned since there is no prime
		assertTrue(toReturn.length == 0);
	}
}
