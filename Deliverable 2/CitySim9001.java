//Michael Bowen
//CS1632 - Deliverable 2

//Simulates a small city with roads and locations
//Drivers will navigate the city in a random fashion
//until they exit the city.

import java.util.LinkedList;

public class CitySim9001 {
	private final int MAX_DRIVERS = 5;
	private final int TOTAL_LOCS = 5;
	private final int TOTAL_ROADS = 4;
	private final int ROADS_PER_LOC = 2;
	public Driver[] drivers;
	public Location[] locations;
	public Road[] roads;

	//create the city by creating locations and roads and linking them together
	public void setupCity() {
		createLocations();
		createRoads();
		linkRoads();
	}
	
	//helper function that creates the locations
	private void createLocations() {
		//create the locations
		locations = new Location[TOTAL_LOCS];
		locations[0] = new Location(0, "Hotel", ROADS_PER_LOC);
		locations[1] = new Location(1, "Diner", ROADS_PER_LOC);
		locations[2] = new Location(2, "Library", ROADS_PER_LOC);
		locations[3] = new Location(3, "College", ROADS_PER_LOC);
		locations[4] = new Location(4, "Outside City", ROADS_PER_LOC);
	}
	
	//helper function that creates the locations
	private void createRoads() {
		//create the roads
		roads = new Road[TOTAL_ROADS];
		LinkedList<Location> locs = new LinkedList<Location>();
		locs.add(locations[4]);
		locs.add(locations[0]);
		locs.add(locations[1]);
		locs.add(locations[4]);
		roads[0] = new Avenue("Fourth", locs);
		locs = new LinkedList<Location>();
		locs.add(locations[4]);
		locs.add(locations[3]);
		locs.add(locations[2]);
		locs.add(locations[4]);
		roads[1] = new Avenue("Fifth", locs);
		locs = new LinkedList<Location>();
		locs.add(locations[0]);
		locs.add(locations[2]);
		roads[2] = new Street("Bill", locs);
		locs = new LinkedList<Location>();
		locs.add(locations[1]);
		locs.add(locations[3]);
		roads[3] = new Street("Phil", locs);
	}
	
	//helper function to link the roads to the locations
	private void linkRoads() {
		//link roads to the locations
		locations[0].addRoad(roads[0]);
		locations[0].addRoad(roads[2]);
		locations[1].addRoad(roads[0]);
		locations[1].addRoad(roads[3]);
		locations[2].addRoad(roads[1]);
		locations[2].addRoad(roads[2]);
		locations[3].addRoad(roads[1]);
		locations[3].addRoad(roads[3]);
		locations[4].addRoad(roads[0]);
		locations[4].addRoad(roads[1]);
	}
	
	//returns a random location from the locations array
	public Location chooseStartLocation(RandomNumber rand) {
		return locations[rand.getInt(TOTAL_LOCS)];
	}
	
	//create the drivers
	public void createDrivers(RandomNumber rand) {
		drivers = new Driver[MAX_DRIVERS];
		for(int i = 0; i < MAX_DRIVERS; i++) {
			drivers[i] = new Driver(i, chooseStartLocation(rand));
		}
	}
	
	//run the simulation
	public void runSim(RandomNumber rand) {
		for(int i = 0; i < MAX_DRIVERS; i++) {
			while(!drivers[i].hasLeftCity()) {
				System.out.print(drivers[i].move(rand));
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		//check number or arguments
		if(args.length != 1) {
			System.out.println("Please provide one, and only one, integer argument to seed the random number generator.\n");
			System.exit(1);
		}
		
		//attempt to parse the argument
		int seed = 0;
		try {
			seed = Integer.parseInt(args[0]);
		} catch(NumberFormatException e) {
			System.out.println("Please provide one, and only one, integer argument to seed the random number generator.\n");
			System.exit(1);
		}	
		
		//create the random number generator
		if(seed > Integer.MAX_VALUE || seed < Integer.MIN_VALUE) {
			System.out.println("Please provide an integer within the the range: " + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ".");
			System.exit(1);
		}
		RandomNumber rand = new RandomNumber(seed);
		
		//create the simulation
		CitySim9001 sim = new CitySim9001();
		sim.setupCity();
		sim.createDrivers(rand);
		sim.runSim(rand);
	}

}
