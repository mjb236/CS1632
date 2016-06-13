//Michael Bowen
//CS1632 - Deliverable 2

//Test cases for the CitySim9001 class

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;

public class CitySim9001Test {
	
//******** CitySim9001 Class Tests ***********************
	
	//Ensure that the city setup created a valid array of locations
	//The array should not be null after the object is created.
	@Test
	public void citySetupTest1() {
		CitySim9001 cs = new CitySim9001();
		cs.setupCity();
		assertNotNull(cs.locations);
	}
	
	//Ensure that the city setup created a valid array of roads
	//The roads array should not be null after creation.
	@Test
	public void citySetupTest2() {
		CitySim9001 cs = new CitySim9001();
		cs.setupCity();
		assertNotNull(cs.roads);
	}
	
	//Ensure that the city setup created a valid array of locations.
	//The first location name should be Hotel
	@Test
	public void citySetupTest3() {
		CitySim9001 cs = new CitySim9001();
		cs.setupCity();
		assertEquals(cs.locations[0].getName(), "Hotel");
	}
	
	//Ensure that the city setup created a valid array of road
	//The second location name should be Diner
	@Test
	public void citySetupTest4() {
		CitySim9001 cs = new CitySim9001();
		cs.setupCity();
		assertEquals(cs.locations[1].getName(), "Diner");
	}
	
	//Ensure that the city setup created a valid array of locations
	//The third location name should be Library
	@Test
	public void citySetupTest5() {
		CitySim9001 cs = new CitySim9001();
		cs.setupCity();
		assertEquals(cs.locations[2].getName(), "Library");
	}
	
	//Ensure that the city setup created a valid array of locations
	//The last location should be named "Outside City"
	@Test
	public void citySetupTest6() {
		CitySim9001 cs = new CitySim9001();
		cs.setupCity();
		assertEquals(cs.locations[4].getName(), "Outside City");
	}
	
	//Ensure that the createDriver function creates an array of drivers
	//The array of drivers should not be null
	@Test
	public void createDriversTest() {
		CitySim9001 cs = new CitySim9001();
		RandomNumber rand = Mockito.mock(RandomNumber.class);
		cs.setupCity();
		cs.createDrivers(rand);
		assertNotNull(cs.drivers);
	}
	
	//Test the function chooseStartLocation using a stub for the 
	//random number generator
	//If the random number generator returns 0, you should get the first
	//location - hotel.
	@Test
	public void chooseStartTest() {
		CitySim9001 cs = new CitySim9001();
		cs.setupCity();
		RandomNumber rand = Mockito.mock(RandomNumber.class);
		when(rand.getInt(5)).thenReturn(0);
		assertEquals(cs.chooseStartLocation(rand).getName(), "Hotel");		
	}
	
	//Test the function chooseStartLocation using a stub for the 
	//random number generator
	//If the random number generator returns 4, you should get the last
	//location - Outside City.
	@Test
	public void chooseStartTest2() {
		CitySim9001 cs = new CitySim9001();
		cs.setupCity();
		RandomNumber rand = Mockito.mock(RandomNumber.class);
		when(rand.getInt(5)).thenReturn(4);
		assertEquals(cs.chooseStartLocation(rand).getName(), "Outside City");		
	}
	
//******** Ends CitySim9001 Class Tests ******************
}
