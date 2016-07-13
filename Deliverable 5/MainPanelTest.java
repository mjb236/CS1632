import static org.junit.Assert.*;

import org.junit.Test;


public class MainPanelTest {

	//Pinning test to ensure that convertToInt works as expected
	//I created a public method that will simply get the returned
	//value of the private convertToInt method so that tests could
	//be applied.
	@Test
	public void convertToIntPin1() {
		MainPanel mp = new MainPanel(15);
		assertEquals(mp.intConvert(8), 8);
	}
	
	//Pinning test to make sure that convertToInt works with the value 0
	@Test
	public void convertToIntPin2() {
		MainPanel mp = new MainPanel(15);
		assertEquals(mp.intConvert(0), 0);
	}
	
	//Pinning test to make sure that convertToInt works with MAXINT
	@Test
	public void convertToIntPin3() {
		MainPanel mp = new MainPanel(15);
		assertEquals(mp.intConvert(Integer.MAX_VALUE), Integer.MAX_VALUE);
	}
	
	//Pinning test to ensure that backup retains its functionality
	//when refactored to not make new objects each call.
	@Test
	public void backupTest() {
		MainPanel mp = new MainPanel(15);
		Cell[][] c = new Cell[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				if((i+j) % 2 == 0) {
					c[i][j] = new Cell(true);
				} else {
					c[i][j] = new Cell(false);					
				}
			}
		}
		
		mp.setCells(c);
		mp.backup();
		assertTrue(mp.backupTest());
	}
	
	//Pinning test to ensure that backup retains its functionality
	//when refactored to not make new objects each call.
	@Test
	public void backupTest2() {
		MainPanel mp = new MainPanel(15);
		Cell[][] c = new Cell[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
                c[i][j] = new Cell(true);
			}
		}
		
		mp.setCells(c);
		mp.backup();
		assertTrue(mp.backupTest());
	}
	
	//Pinning test to ensure that backup retains its functionality
	//when refactored to not make new objects each call.
	@Test
	public void backupTest3() {
		MainPanel mp = new MainPanel(15);
		Cell[][] c = new Cell[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
			    c[i][j] = new Cell(false);
			}
		}
		c[0][0].setAlive(true);
		c[14][14].setAlive(true);
		
		mp.setCells(c);
		mp.backup();
		assertTrue(mp.backupTest());
	}
}