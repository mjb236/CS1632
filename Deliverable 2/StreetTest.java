import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;
import org.mockito.Mockito;


public class StreetTest {

//******** Street Class Tests ***********************
	//Ensure that the object is constructed as expected
	@Test
	public void streetConstructionTest() {
		Street street = new Street("Ridge", null);
		assertNotNull(street);
	}
	
	//Ensure that Road's getOneWay function returns the expected
	//value after object construction
	//Streets should be two way roads
	@Test
	public void streetOneWayTest() {
		Street street = new Street("Ridge", null);
		assertFalse(street.isOneWay());
	}
	
	//Ensure that Road's getName function returns the expected
	//reference after object construction.
	//getName should return the name provided concatenated with "St."
	@Test
	public void streetGetNameTest() {
		String name = "Ridge";
		String expectedName = "Ridge St.";
		Street street = new Street(name, null);
		
		//assertSame will not work here as with Road since we are adding
		//a suffix onto the name
		assertEquals(street.getName(), expectedName);
	}
	
	//Ensure that the useRoad function returns the second location if
	//user starts at the first location
	//Using the road from the first location should return the second location
	@Test
	public void streetUseRoadTest1() {
		LinkedList<Location> locs = new LinkedList<Location>();
		Location loc1 = Mockito.mock(Location.class);
		Location loc2 = Mockito.mock(Location.class);
		locs.add(loc1);
		locs.add(loc2);
		Street street = new Street("Test", locs);
		assertSame(street.useRoad(loc1), loc2);
	}
	
	//Ensure that the useRoad function returns the first location if
	//user starts at the second location
	//Using the road from the second location should return the first location
	@Test
	public void streetUseRoadTest2() {
		LinkedList<Location> locs = new LinkedList<Location>();
		Location loc1 = Mockito.mock(Location.class);
		Location loc2 = Mockito.mock(Location.class);
		locs.add(loc1);
		locs.add(loc2);
		Street street = new Street("Test", locs);
		assertSame(street.useRoad(loc2), loc1);
	}

//******** End Street Class Tests ***********************

}
