//Michael Bowen
//CS1632 - Deliverable 2

//Class to represent a Driver that can drive around the city.

public class Driver {
	private int id;
	private int moves;
	private Location location;
	private Road prevRoad;
	
	//Constructor
	public Driver(int id, Location location) {
		this.id = id;
		this.location = location;
		this.prevRoad = null;
		moves = 0;
	}
	
	//returns the id number for this driver
	public int getId() {
		return id;
	}
	
	//increment the number of moves
	public String move(RandomNumber rand) {
		Location prevLoc = location;
		int index = rand.getInt(location.getNumRoads());
		Road roadToUse = location.getRoad(index);
		
		//cast road to appropriate subclass
		if(roadToUse.isOneWay()) {
			location = ((Avenue) roadToUse).useRoad(location);
		}
		else {
			location = ((Street) roadToUse).useRoad(location);
		}
		
		prevRoad = roadToUse;
		
		moves++;
		
		return getMoveDescription(prevLoc);
	}
	
	//helper function that returns a description of the move the driver
	//just executed
	private String getMoveDescription(Location prevLoc) {
		String toReturn = "Driver " + id + " heading from " + prevLoc.getName() + " to " + 
							location.getName() + " via " + prevRoad.getName() + "\n";
		
		if(hasLeftCity()) {
			if(prevRoad.getName().equals("Fourth Ave.")) {
				toReturn +=  "Driver " + id + " has gone to Philadelphia.\n";
			}
			else {
				toReturn += "Driver " + id + " has gone to Cleveland.\n";
			}
		}
		
		return toReturn;
	}
	
	//returns the number of moves made by the driver.
	public int getMoves() {
		return moves;
	}
	
	//get the driver's current location
	public Location getLocation() {
		return location;
	}
	
	//set the driver's current location
	public void setLocation(Location location) {
		this.location = location;
	}
	
	//get the last road traversed
	public Road getPrevRoad() {
		return prevRoad;
	}
	
	//set the last road traversed
	public void setPrevRoad(Road prevRoad) {
		this.prevRoad = prevRoad;
	}	
	
	//determine whether or not driver has left the city
	public boolean hasLeftCity() {
		return (location.getName().equals("Outside City") && moves != 0);
	}
}