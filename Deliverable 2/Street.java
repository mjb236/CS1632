//Michael Bowen
//CS1632 - Deliverable 2

//Represents a Street within the city. Assumes that in this City, all 
//Streets are two-way roads

import java.util.LinkedList;

public class Street extends Road{
	LinkedList<Location> locations;
	
	//Constructor
	public Street(String name, LinkedList<Location> locations) {
		super(name + " St.");
		
		//make sure the linked list isn't null
		if(locations != null) {
			this.locations = new LinkedList<Location>(locations);	
		}
		else {
			this.locations = new LinkedList<Location>();
		}			
	}
	
	//function that will effectively use this road by returning the
	//next location that the driver can move to
	//NOTE - this function would need modified if the city were ever
	//to be expanded and this street had more than two locations. Function
	//will work for two locations as is however.
	public Location useRoad(Location start) {
		//if no locations exist on this street, return null
		if(locations.size() == 0) {
			System.out.println("No locations exist on this street.");
			return null;
		}	
		
		//if only one location, inform user that they cannot use the street
		if(locations.size() == 1) {
			System.out.println("Only one location on this street, you are stuck.");
			return start;
		}
		
		int index = locations.indexOf(start);
		
		if(index == locations.size() - 1) {
			//we are at end of street - since this is a two way road
			//we want to return the previous location
			return locations.get(index - 1);
		}
		else {
			//we are not at the end of the street = return the next location
			return locations.get(index + 1);
		}
	}	
}