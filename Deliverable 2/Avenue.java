//Michael Bowen
//CS1632 - Deliverable 2

//Represents a Avenue within the city. Assumes that in this City, all 
//Avenues are one-way roads

import java.util.LinkedList;

public class Avenue extends Road{
	LinkedList<Location> locations;
	
	//Constructor
	public Avenue(String name, LinkedList<Location> locations) {
		super(name + " Ave.", true);
		
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
	public Location useRoad(Location start) {
		//if no locations exist on this street, return null
		if(locations.size() == 0) {
			System.out.println("No locations exist on this avenue.\n");
			return null;
		}	
		
		//if only one location, inform user that they cannot use the avenue
		//this should not happen, but better safe than sorry
		if(locations.size() == 1) {
			System.out.println("Only one location on this avenue, you are stuck.\n");
			return start;
		}

		//there may be a potential problem here - outside city location will exist twice
		//in the avenue's list of locations. the logic in the driver's move function should
		//prevent this from being called when driver has arrived at an outside location, 
		//thus we can still use indexOf instead of lastIndexOf. Just making note in case
		//some weirdness occurs
		int index = locations.indexOf(start);
		
		if(index == locations.size() - 1) {
			//we are at end of street - inform user they cannot move forward
			//we want to return the current location
			//NOTE - this should not occur.
			System.out.println("You cannot move down this avenue any more.\n");
			return start;
		}
		else {
			//we are not at the end of the street = return the next location
			return locations.get(index + 1);
		}
	}
}