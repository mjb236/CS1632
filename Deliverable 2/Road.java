//Michael Bowen
//CS1632 - Deliverable 2

//Class to represent a Road within the city.

public class Road {
	private String name;
	private boolean oneWay;
	
	//Constructor for default roads
	public Road(String name) {
		if(name == null) {
			this.name = "Default";
		}
		else {
			this.name = name;
		}
		oneWay = false;		//assume default roads are not oneWay
	}
	
	//Constructor for oneway roads
	public Road(String name, boolean oneWay) {
		if(name == null) {
			this.name = "Default";
		}
		else {
			this.name = name;
		}
		this.oneWay = oneWay;
	}
	
	//returns the name of the road
	public String getName() {
		return name;
	}
	
	//returns whether or not the road is oneway
	public boolean isOneWay() {
		return oneWay;
	}
}