import static org.junit.Assert.*;

import org.junit.Test;


public class CellTest {

	//Pinning test to make sure that the toString method returns 
	//the expected value with an alive cell.
	@Test
	public void aliveCellToStringPin() {
		Cell c = new Cell();
		c.setAlive(true);
		assertTrue(c.toString().equals("X"));
	}
	
	//Pinning test to make sure that the toString method returns 
	//the expected value with a dead cell.
	@Test
	public void deadCellToStringPin() {
		Cell c = new Cell();
		c.setAlive(false);
		assertTrue(c.toString().equals("."));
	}
	
	//Pinning test to make sure that the toString method returns 
	//the expected value with a dead cell that was previously alive.
	@Test
	public void prevAliveDeadCellToStringPin() {
		Cell c = new Cell();
		c.setAlive(true);
		c.setAlive(false);
		assertTrue(c.toString().equals("."));
	}

}
