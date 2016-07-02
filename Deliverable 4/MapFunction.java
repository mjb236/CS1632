//Michael Bowen
//CS1632 - Deliverable 4
//Map function and property testing

public class MapFunction {
	
	//returns an array that contains the squares of the argument array
	//and an extra element that is the sum of those squares
	public static int[] laboonify(int[] x) {
		//declare new array
		int[] newArray = new int[x.length + 1];
		
		//fill array with squares and sum squares
		int sum = 0;
		for(int i = 0; i < x.length; i++) {
			newArray[i] = square(x[i]);
			sum += newArray[i];
		}
		newArray[x.length] = sum;		
		
		return newArray;
	}
	
	//helper function that will square integer given
	//returns num squared
	private static int square(int num) {
		return num * num;
	}
}
