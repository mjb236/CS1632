//Michael Bowen
//CS1632 - Deliverable 2

//Class to represent a Location within the city

public class Location {
	private int id;
	private String name;
	private Road[] roads;
	private int numRoads;
	
	//constructor
	public Location(int id, String name, int numRoads) {
		this.id = id;
		if(name == null) {
			this.name = "Default";
		}
		else  {
			this.name = name;
		}
		roads = new Road[numRoads];
		this.numRoads = 0;
	}
	
	//return this locations id number
	public int getId() {
		return id;
	}
	
	//return this location's name
	public String getName() {
		return name;
	}
	
	//add a road to this locations list of roads
	public void addRoad(Road road) {
		this.roads[numRoads] = road;
		numRoads++;
	}
	
	//get the road at index
	public Road getRoad(int index) {
		if(roads == null) {
			return null;
		}
		else {
			return roads[index];	
		}
	}
	
	//get the number or roads linked to this location
	public int getNumRoads() {
		return numRoads;
	}
}