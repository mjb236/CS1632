import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;


public class LocationTest {

//******** Location Class Tests ********************

	//Ensure that the object is constructed as expected
	@Test
	public void locationConstructionTest() {
		Location loc = new Location(0, null, 2);
		assertNotNull(loc);
	}
	
	//Ensure that the location's getId function returns the expected
	//value of the ID given upon object construction.
	//The id should return 10 as was given in creation.
	@Test
	public void locationGetIdTest() {
		Location loc = new Location(10, null, 2);
		assertEquals(10, loc.getId());
	}
	
	//Ensure that the location's getName function returns the expected
	//string given during object construction.
	@Test
	public void locationGetNameTest() {
		String name = "Sennot Square";
		Location loc = new Location(0, name, 2);
		assertSame(loc.getName(), name);
	}
	
	//Ensure that if a location is created with a null name, a default
	//name is given.
	@Test
	public void locationGetNameNullTest() {
		Location loc = new Location(0, null, 2);
		assertNotNull(loc.getName());
	}
	
	//Ensure that if a location is created with a null name, a default
	//name is given.
	@Test
	public void locationGetNameNullTest2() {
		Location loc = new Location(0, null, 2);
		assertTrue(loc.getName().equals("Default"));
	}
	
	//Ensure that the location's getNumRoads function returns the 
	//expected number of roads added to the location
	@Test
	public void locationGetNumRoadsTest1() {
		Location loc = new Location(0, "Test", 2);
		assertEquals(loc.getNumRoads(), 0);
	}
	
	//Ensure that the location's getNumRoads function returns the 
	//expected number of roads added to the location
	@Test
	public void locationGetNumRoadsTest2() {
		Location loc = new Location(0, "Test", 2);
		Road road = Mockito.mock(Road.class);
		loc.addRoad(road);
		assertEquals(loc.getNumRoads(), 1);
	}
	
	//Ensure that the location's getNumRoads function returns the 
	//expected number of roads added to the location
	@Test
	public void locationGetNumRoadsTest3() {
		Location loc = new Location(0, "Test", 2);
		Road road = Mockito.mock(Road.class);
		Road road2 = Mockito.mock(Road.class);
		loc.addRoad(road);
		loc.addRoad(road2);
		assertEquals(loc.getNumRoads(), 2);
	}
	
	//Ensure that the location's getNumRoads function returns the 
	//expected number of roads when streets are added
	@Test
	public void locationGetNumRoadsTest4() {
		Location loc = new Location(0, "Test", 2);
		Street street = Mockito.mock(Street.class);
		Street street2 = Mockito.mock(Street.class);
		loc.addRoad(street);
		loc.addRoad(street2);
		assertEquals(loc.getNumRoads(), 2);
	}
	
	//Ensure that the location's getNumRoads function returns the 
	//expected number of roads when avenues are added
	@Test
	public void locationGetNumRoadsTest5() {
		Location loc = new Location(0, "Test", 2);
		Avenue ave = Mockito.mock(Avenue.class);
		Avenue ave2 = Mockito.mock(Avenue.class);
		loc.addRoad(ave);
		loc.addRoad(ave2);
		assertEquals(loc.getNumRoads(), 2);
	}
	
	//Ensure that the getRoad function returns the expected value
	//when there are two avenues added
	@Test
	public void locationGetRoadTest1() {
		Location loc = new Location(0, "Test", 2);
		Avenue ave = Mockito.mock(Avenue.class);
		Avenue ave2 = Mockito.mock(Avenue.class);
		loc.addRoad(ave);
		loc.addRoad(ave2);
		assertSame(loc.getRoad(0), ave);
	}
	
	//Ensure that the getRoad function returns the expected value
	//when there are two avenues added
	@Test
	public void locationGetRoadTest2() {
		Location loc = new Location(0, "Test", 2);
		Avenue ave = Mockito.mock(Avenue.class);
		Avenue ave2 = Mockito.mock(Avenue.class);
		loc.addRoad(ave);
		loc.addRoad(ave2);
		assertSame(loc.getRoad(1), ave2);
	}
	
	//Ensure that the getRoad function returns the expected value
	//when there are two streets added
	@Test
	public void locationGetRoadTest3() {
		Location loc = new Location(0, "Test", 2);
		Street st = Mockito.mock(Street.class);
		Street st2 = Mockito.mock(Street.class);
		loc.addRoad(st);
		loc.addRoad(st2);
		assertSame(loc.getRoad(0), st);
	}
	
	//Ensure that the getRoad function returns the expected value
	//when there are two streets added
	@Test
	public void locationGetRoadTest4() {
		Location loc = new Location(0, "Test", 2);
		Street st = Mockito.mock(Street.class);
		Street st2 = Mockito.mock(Street.class);
		loc.addRoad(st);
		loc.addRoad(st2);
		assertSame(loc.getRoad(1), st2);
	}
	
	//Ensure that the getRoad function returns the expected value
	//when there are no streets added
	@Test
	public void locationGetRoadTest5() {
		Location loc = new Location(0, "Test", 2);
		assertNull(loc.getRoad(0));
	}

//******** End Location Class Tests ***************

}
