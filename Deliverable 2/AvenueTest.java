import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;
import org.mockito.Mockito;


public class AvenueTest {

//******** Avenue Class Tests ***********************

	//Ensure that the object is constructed as expected
	@Test
	public void avenueConstructionTest() {
		Avenue avenue = new Avenue("Ridge", null);
		assertNotNull(avenue);
	}
	
	//Ensure that Avenue's getOneWay function returns the expected
	//value after object construction.
	//Should return true
	@Test
	public void avenueOneWayTest() {
		Avenue avenue = new Avenue("Ridge", null);
		assertTrue(avenue.isOneWay());
	}
	
	//Ensure that Avenue's getName function returns the expected
	//reference after object construction
	//Should return the given value concatenated with "Ave."
	@Test
	public void avenueGetNameTest() {
		String name = "Ridge";
		String expectedName = "Ridge Ave.";
		Avenue avenue = new Avenue(name, null);
		
		//assertSame will not work here as with Road since we are adding
		//a suffix onto the name
		assertEquals(avenue.getName(), expectedName);
	}
	
	//Ensure that the useRoad function returns the second location if
	//user starts at the first location
	//Using the road at the first location should return the second location.
	@Test
	public void avenueUseRoadTest() {
		LinkedList<Location> locs = new LinkedList<Location>();
		Location loc1 = Mockito.mock(Location.class);
		Location loc2 = Mockito.mock(Location.class);
		locs.add(loc1);
		locs.add(loc2);
		Avenue avenue = new Avenue("Test", locs);
		assertSame(avenue.useRoad(loc1), loc2);
	}
	
	//Ensure that the useRoad function returns the start location if
	//user starts at the last location on the avenue
	//Using the road from the second location should return that location
	//as there is no further location to which to travel.
	@Test
	public void avenueUseRoadTest2() {
		LinkedList<Location> locs = new LinkedList<Location>();
		Location loc1 = Mockito.mock(Location.class);
		Location loc2 = Mockito.mock(Location.class);
		locs.add(loc1);
		locs.add(loc2);
		Avenue avenue = new Avenue("Test", locs);
		assertSame(avenue.useRoad(loc2), loc2);
	}

//******** End Avenue Class Tests ***********************

}
