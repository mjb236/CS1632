import static org.junit.Assert.*;

import org.junit.Test;


public class RoadTest {

//******** Road Class Tests ***********************

	//Ensure that the object is constructed as expected
	@Test
	public void roadConstructionTest() {
		Road road = new Road("Ridge");
		assertNotNull(road);
	}
	
	//Ensure that the object is constructed as expected
	//using the second constructor
	@Test
	public void roadConstructionTest2() {
		Road road = new Road("Ridge", true);
		assertNotNull(road);
	}
	
	//Ensure that Road's getOneWay function returns the expected
	//value after object construction
	//The road is not one way by default.
	@Test
	public void roadOneWayTest() {
		Road road = new Road("Ridge");
		assertFalse(road.isOneWay());
	}
	
	//Ensure that Road's getOneWay function returns the expected
	//value after object construction using the second constructor
	//Should return true since it is specified by construction
	@Test
	public void roadOneWayTest2() {
		Road road = new Road("Ridge", true);
		assertTrue(road.isOneWay());
	}
	
	//Ensure that Road's getName function returns the expected
	//reference after object construction.
	@Test
	public void roadGetNameTest() {
		String name = "Ridge";
		Road road = new Road(name);
		assertSame(road.getName(), name);
	}
	
	//Ensure that a Road given a null name set to having a default
	//name to prevent null pointer possibilities
	@Test
	public void roadNullNameTest1() {
		Road road = new Road(null);
		assertNotNull(road.getName());
	}
	
	//Ensure that a Road given a null name set to having a default
	//name to prevent null pointer possibilities - uses second constructor
	@Test
	public void roadNullNameTest2() {
		Road road = new Road(null, true);
		assertNotNull(road.getName());
	}


//******** End Road Class Tests ***********************

}
