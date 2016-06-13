import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;


public class DriverTest {

//******* Driver Class Tests ****************	

	//Ensure that the object is constructed as expected
	@Test
	public void driverConstructionTest() {
		Driver driver = new Driver(0, null);
		assertNotNull(driver);
	}
	
	//Ensure that the Driver class's getId method returns the 
	//correct value after Driver creation.
	//The id should be the value passed in on construction - 15
	@Test
	public void driverGetIdTest() {
		Driver driver = new Driver(15, null);
		assertEquals(15, driver.getId());
	}
	
	//Ensure that the Driver class's getMoves method returns the
	//correct number of moves after Driver creation.
	//The driver has not yet moved, so getMoves should return 0
	@Test
	public void driverGetMovesTest() {
		Driver driver = new Driver(15, null);
		assertEquals(0, driver.getMoves());
	}
	
	//Ensure that the Driver class's move method increments the
	//number of moves correctly.
	//This test is a bit sloppy with all the mocks, since move() is where all the
	//different classes meet, but the number of moves should be 10
	@Test
	public void driverMoveTest() {
		Location loc = Mockito.mock(Location.class);
		Driver driver = new Driver(15, loc);
		RandomNumber rand = Mockito.mock(RandomNumber.class);
		Avenue road = Mockito.mock(Avenue.class);
		
		when(loc.getName()).thenReturn("Test Loc");
		when(road.getName()).thenReturn("Test Road");
		when(loc.getNumRoads()).thenReturn(0);
		when(loc.getRoad(0)).thenReturn(road);
		when(rand.getInt(0)).thenReturn(0);
		when(road.isOneWay()).thenReturn(true);
		when(road.useRoad(loc)).thenReturn(loc);
				
		driver.setLocation(loc);
		for(int i = 0; i < 10; i++) {
			driver.move(rand);
		}
		assertEquals(10, driver.getMoves());
	}
	
	//Ensure that the Driver's getLocation method returns the same location
	//that is used to construct the object - same reference should be returned.
	@Test
	public void driverSetupLocationTest() {
		Location loc = Mockito.mock(Location.class);
		Driver driver = new Driver(15, loc);
		assertSame(loc, driver.getLocation());
	}
	
	//Ensure that a new Driver that starts with the outside city location name
	//is not considered to have already left the city
	@Test
	public void driverHasLeftCityTest1() {
		Location loc = Mockito.mock(Location.class);
		when(loc.getName()).thenReturn("Outside City");
		Driver driver = new Driver(0, loc);
		assertFalse(driver.hasLeftCity());
	}
	
	//Ensure that a new Driver that has moved and is at the outside city location
	//is considered to have left the city.
	//Once again this test is sloppy, but once you get through the mocks, the 
	//hasLeftCity() function should return true if the curr location is outside city
	//and moves is greater than 0 - driver has moved TO an outside city
	@Test
	public void driverHasLeftCityTest2() {
		Location loc = Mockito.mock(Location.class);
		Driver driver = new Driver(15, loc);
		RandomNumber rand = Mockito.mock(RandomNumber.class);
		Avenue road = Mockito.mock(Avenue.class);
		
		when(loc.getName()).thenReturn("Outside City");
		when(road.getName()).thenReturn("Test Road");
		when(loc.getNumRoads()).thenReturn(0);
		when(loc.getRoad(0)).thenReturn(road);
		when(rand.getInt(0)).thenReturn(0);
		when(road.isOneWay()).thenReturn(true);
		when(road.useRoad(loc)).thenReturn(loc);
		driver.move(Mockito.mock(RandomNumber.class));
		assertTrue(driver.hasLeftCity());
	}
	
	//Ensure that the getPrevRoad returns null when the object
	//is first created.
	@Test
	public void driverGetPrevRoadTest() {
		Driver driver = new Driver(15, null);
		assertNull(driver.getPrevRoad());
	}
	
//******** End Driver Class Tests ******************

}
