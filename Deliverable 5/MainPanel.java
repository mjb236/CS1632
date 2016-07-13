import java.awt.*;

import javax.swing.*;

import java.util.*;


public class MainPanel extends JPanel {

    // Current configuration
    private Cell[][] _cells;

    // Backup configuration
    private Cell[][] _backupCells;

    private int _size = 0;

    private int _maxCount = 10000;

    public int _r = 1000;
    
    private boolean _running = false;

    public int getCellsSize() {
	return _size;
    }

    public void setCells(Cell[][] cells) {
	_cells = cells;
    }
    
    public Cell[][] getCells() {
	return _cells;
    }

    private int convertToInt(int x) {
    	//removed unnecessary code
    	//parameter is already an int - does not need to be converted.
    	//Simply returns the value - left the method to avoid possibility of other issues.
    	return x;
    }
    
    //debugging method that allows pinning tests for convertToInt to function.
    public int intConvert(int x) {
    	return convertToInt(x);
    }
    
    private int getNumNeighbors(int x, int y) {
	int size = _size;
	int leftX = (x - 1) % size;
	int rightX = (x + 1) % size;
	int upY = (y - 1) % size;
	int downY = (y + 1) % size;

	if (leftX == -1) { leftX = size - 1; }
	if (rightX == -1) { rightX = size - 1; }
	if (upY == -1) { upY = size - 1; }
	if (downY == -1) { downY = size - 1; }
		
	int numNeighbors = 0;

	if (_cells[leftX][upY].getAlive())    { numNeighbors++; }
	if (_cells[leftX][downY].getAlive())  { numNeighbors++; }
	if (_cells[leftX][y].getAlive())      { numNeighbors++; }
	if (_cells[rightX][upY].getAlive())   { numNeighbors++; }
	if (_cells[rightX][downY].getAlive()) { numNeighbors++; }
	if (_cells[rightX][y].getAlive())     { numNeighbors++; }
	if (_cells[x][upY].getAlive())        { numNeighbors++; }
	if (_cells[x][downY].getAlive())      { numNeighbors++; }
	    
	return convertToInt(numNeighbors);

    }

    private boolean iterateCell(int x, int y) {
	boolean toReturn = false;
	boolean alive = _cells[x][y].getAlive();
	int numNeighbors = getNumNeighbors(x, y);
	if (alive) {
	    if (numNeighbors < 2 || numNeighbors > 3) {
		toReturn = false;
	    } else {
		toReturn = true;
	    }
	} else {
	    if (numNeighbors == 3) {
		toReturn = true;
	    } else {
		toReturn = false;
	    }
	}
	return toReturn;

    }

    private void displayIteration(boolean[][] nextIter) {
	System.out.println("\tDisplaying...");
	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size;  k++) {
		_cells[j][k].setAlive(nextIter[j][k]);
	    }
	}
	setVisible(true);
    }

    /**
     * For each of the cells, calculate what their
     * state will be for the next iteration.
     */
    
    private void calculateNextIteration() {
	System.out.println("\tCalculating..");
	boolean[][] nextIter = new boolean[_size][_size];
	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size; k++) {
		nextIter[j][k] = iterateCell(j, k);
	    }
	}

	displayIteration(nextIter);
    }

    /**
     * Make a copy of the current cells and put
     * the copy in the backup cells.
     */
    
    public void backup() {
    	//instead of creating new objects each time, simply
    	//update the backupCell with the active cell's getAlive()
    	//function
	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size; k++) {
		_backupCells[j][k].setAlive(_cells[j][k].getAlive());
	    }
	}
    }
    
    //debugging method that allows to pin the functionality of backup
    public boolean backupTest() {
    	for(int i = 0; i < _size; i++) {
    		for(int j = 0; j < _size; j++) {
    			if(_backupCells[i][j].getAlive() != _cells[i][j].getAlive()) {
    				return false;
    			}
    		}
    	}
    	return true;
    }

    /**
     * This is for debug use.  It will display
     * the state of cells in a convenient format.
     * First it will display backup cells and then
     * the current cells.  Backup cells are what
     * you revert to when you press Undo.
     */
    
    public void debugPrint() {
	System.out.println("Backup cells");

	try {
	    for (int j = 0; j < _size; j++) {
		for (int k = 0; k < _size; k++) {

		    if (_backupCells[j][k].getAlive()) {
			System.out.print("X");
		    } else {
			System.out.print(".");
		    }
		}
		System.out.println("");
	    }

	    System.out.println("Current cells:");

	    for (int j = 0; j < _size; j++) {
		for (int k = 0; k < _size; k++) {

		    if (_cells[j][k].getAlive()) {
			System.out.print("X");
		    } else {
			System.out.print(".");
		    }
		}
		System.out.println("");
	    }
	} catch (Exception ex) {
	    System.out.println("Nothin' yet");
	}
					   
	
    }

    /**
     * Convert the Main Panel into a String
     * which can be written to a file.
     */
    
    public String toString() {

	// Loop through all of the cells, and
	// if they are alive, add an "X" to
	// the String, if dead, a ".".

	String toWrite = "";
	
	for (int j = 0; j < _size; j++) {
	    for(int k = 0; k < _size; k++) {
		if (_cells[j][k].getAlive()) {
		    toWrite += _cells[j][k].toString();
		} else {
		    toWrite += _cells[j][k].toString();
		}
		    
	    }
	    toWrite += "\n";
	}
	return toWrite;
    }

    /**
     * Run one iteration of the Game of Life
     */
    
    public void run() {
	backup();
	calculateNextIteration();
    }

    /**
     * Run the system continuously.
     */

    public void runContinuous() {
	_running = true;
	while (_running) {
		//removed the extraneous loop in this function. All it was doing was changing
		//the value of _r and then, after the loop, reseting it to its original value
		//There are no pinning tests for this method since the function takes no
		//input and produces no output. This function will eat a lot of CPU time because
		//of its nature, but removal of the extra loop will mean more actual game cycles
		//per second.
		
	    System.out.println("Running...");
	    try {
		Thread.sleep(20);
	    } catch (InterruptedException iex) { }
	    backup();
	    calculateNextIteration();
	}
    }

    /**
     * Stop a continuously running system.
     */
    
    public void stop() {
	_running = false;
    }
   

    /**
     * Convert the array of Cell objects into an 
     * array of booleans.
     */
    
    public boolean[][] convertToBoolean(Cell[][] cells) {

	// 2-D array to return.  Remember everything
	// is false by default for boolean arrays!
	
	boolean[][] toReturn = new boolean[_size][_size];

	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size; k++) {
		if (cells[j][k].getAlive()) {
		    toReturn[j][k] = true;
		} else {
		    // Nothing to do!  Already
		    // set to false by default.
		    // toReturn[j][k] = false;
		}
	    }
	}
	return toReturn;
	
    }

    /**
     * Revert back to the previous iteration,
     * which we have saved in _backupCells.
     */
    
    public void undo() {
	displayIteration(convertToBoolean(_backupCells));
    }

    /**
     * Loop through the entire array and reset
     * each of the Cells in the MainPanel.
     */
    
    public void clear() {
	for (int j = 0; j < _size; j++) {
	    for (int k = 0; k < _size; k++) {
		_cells[j][k].reset();
	    }
	}
	// Need to call setVisible() since
	// we did not do a displayIteration()
	// call.
	setVisible(true);
    }

    /**
     * Load in a previously saved Game of Life
     * configuration.
     */
    
    public void load(ArrayList<String> lines) {
	boolean[][] loaded = new boolean[_size][_size];

	
	for (int j = 0; j < _size; j++) {
	    String l = lines.get(j);
	    for (int k = 0; k < _size; k++) {

		// Reset the "been alive" count
		_cells[j][k].resetBeenAlive();

		// For each line, get each character.
		// If it's a '.', the cell stays
		// dead.  Otherwise, the cell is alive.
		// We could specifically check for
		// an 'X' for alive and throw an
		// error if we get an unexpected char.
		if (l.charAt(k) == '.') {		    
		    _cells[j][k].setAlive(false);
		    loaded[j][k] = false;
		} else {
		    _cells[j][k].setAlive(true);
		    loaded[j][k] = true;
		}
	    }
	}

	// Now that we have set the Cells to what
	// we expect, display the iteration.
	displayIteration(loaded);
	// debugPrint();
	
    }
    

    public MainPanel(int size) {
	super();
	_size = size;
	setLayout(new GridLayout(size, size));
	
	//initialize the backupCell array here and fill with new
	//cells. that way we don't need to make all new cells
	//for each backup
	_backupCells = new Cell[size][size];
	_cells = new Cell[size][size];
	for (int j = 0; j < size; j++) {
	    for (int k = 0; k < size; k++) {
	    	_backupCells[j][k] = new Cell();
		_cells[j][k] = new Cell();
		this.add(_cells[j][k]);
		_cells[j][k].setAlive(false);
	    }
	}

    }
	
}
