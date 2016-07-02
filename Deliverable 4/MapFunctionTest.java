//Michael Bowen
//CS1632 - Deliverable 4
//Map function and property testing

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;

public class MapFunctionTest {
	private final static int NUM_TESTS = 10000;
	private Random rand = new Random();

	//helper function that will generate an array of a random size
	//filled with integers from 1 - 100 inclusive
	private int[] generateArray() {
		//array length from 1 - 100 inclusive
		int len = rand.nextInt(100) + 1;
		int[] array = new int[len];
		
		//fill array with random numbers between 1 - 100 inclusive
		for(int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(100) + 1;
		}
		
		return array;
	}
	
	//Property Test One - Positive Integer property
	//Because the array returned by laboonify contains only squares and
	//the sum of those squares, and the original array is assumed to have no 
	//zeroes, no number in the returned array should ever be negative or zero.	
	//Test is run NUM_TESTS times.
	@Test
	public void positiveIntegerTest() {
		for(int i = 0; i < NUM_TESTS; i++) {
			int[] a = MapFunction.laboonify(generateArray());
			for(int j = 0; j < a.length; j++) {
				if(a[j] <= 0) { 
					fail();
				}
			}
		}
	}
	
	//Property Test Two - Array Size property
	//The array returned by laboonify shall always have a length
	//that is one greater than the length of the original array.
	//Test is run NUM_TESTS times
	@Test
	public void arraySizeTest() {
		for(int i = 0; i < NUM_TESTS; i++) {
			int[] a = generateArray();
			int[] laboon = MapFunction.laboonify(a);
			if(laboon.length != (a.length + 1)) {
				fail();
			}
		}
	}
	
	//Property Test Three - Sum Magnitude property
	//The final value of the array returned by the laboonify function
	//shall never be less than any number in either the original 
	//array or any preceding number of the laboonified array.
	//Test is run NUM_TESTS times
	@Test
	public void sumMagnitudeTest() {
		for(int i = 0; i < NUM_TESTS; i++) {
			int[] a = generateArray();
			int[] laboon = MapFunction.laboonify(a);
			
			for(int j = 0; j < a.length; j++) {
				if(laboon[a.length] < a[j] || laboon[a.length] < laboon[j]) {
					fail();
				}
			}
		}
	}
	
	//Property Test Four - Laboonified Array Upper Limit property
	//The initial values of the array are defined as being 1 - 100 inclusive.
	//Also, the max number of elements in the initial array is defined as being
	//100. As such, the only number returned in the laboonified array that may 
	//be larger than 10,000 is the sum, and the sum can be no larger than 1,000,000.
	//Test is run NUM_TESTS times
	@Test
	public void upperLimitsTest() {
		for(int i = 0; i < NUM_TESTS; i++) {
			int[] laboon = MapFunction.laboonify(generateArray());
			if(laboon[laboon.length - 1] > 1000000) {
				fail();
			}
			for(int j = 0; j < laboon.length - 1; j++) {
				if(laboon[j] > 10000) {
					fail();
				}
			}
		}
	}
}