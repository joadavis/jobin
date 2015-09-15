package programming.icpc2013.probf;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for determining the largest difference in power between two lowest power batteries per machine.
 * 
 * Remember, the number of batteries should be 2nk - 2 chips times n machines times k batteries per chip
 * 
 * Note: not thoroughly testing all error cases for bad input.
 * 
 * @author joadavis
 *
 */
public class InventoryCalcTest {
	
	@Test
	public void testCalculateLowPowerSimpleCase() {
		InventoryCalc invC = new InventoryCalc();
		
		// first simple case
		assertEquals(1, invC.calculateLowPower(2, 2, new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
	}

	
	@Test
	public void testCalculateLowPowerSample1() {
		InventoryCalc invC = new InventoryCalc();
		
		// Sample 1 from problem description
		assertEquals(1, invC.calculateLowPower(2, 3, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
	}
	
	@Test
	public void testCalculateLowPowerSample2() {
		InventoryCalc invC = new InventoryCalc();
		
		// Sample 2 from problem description
		assertEquals(2, invC.calculateLowPower(2, 2, new int[]{3,1,3,3,3,3,3,3}));
	}
	
	@Test
	public void testCalculateLowPowerEasyUnsorted() {
		InventoryCalc invC = new InventoryCalc();
		
		// simple result if sorted
		assertEquals(0, invC.calculateLowPower(3, 2, new int[]{1,3,5,8,8,8,1,3,5,8,8,8}));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateLowPowerNotEnoughBatts() {
		InventoryCalc invC = new InventoryCalc();
		
		invC.calculateLowPower(3, 2, new int[]{1});
	}
	
	
	@Test
	public void testCalculateLowPowerTooBad() {
		InventoryCalc invC = new InventoryCalc();
		
		// to bad 18 and 19 are too high to be lowest
		assertEquals(4, invC.calculateLowPower(2, 2, new int[]{1,1,2,6,10,14,18,19}));
	}
	
	
	@Test
	public void testCalculateLowPowerHidingHelps() {
		InventoryCalc invC = new InventoryCalc();
		
		// If hide the 2 then 6 and 9 are closer
		assertEquals(3, invC.calculateLowPower(2, 2, new int[]{1,1,2,6,9,13,17,21}));
	}
	
	@Test
	public void testCalculateLowPowerLotsOMachines() {
		InventoryCalc invC = new InventoryCalc();
		
		// test with more machines
		assertEquals(0, invC.calculateLowPower(10, 2, new int[]{
				1,1,1,1,1,1,1,1,1,1,
				1,1,1,1,2,6,9,13,17,21,
				22,22,22,22,22,22,22,22,22,22,
				22,22,22,22,22,22,22,22,22,22}));
	}
	
	
	
	@Test
	public void testCalculateLowPowerSomeHideSomeNot() {
		InventoryCalc invC = new InventoryCalc();
		
		// mix of good and bad
		assertEquals(2, invC.calculateLowPower(10, 2, new int[]{
				1,1,1,2,2,9,18,19,29,39,
				49,51,71,91,92,93,94,113,117,121,
				222,222,222,222,222,222,222,222,222,222,
				222,222,222,222,222,222,222,222,222,222}));
	}
	
	
	
	@Test
	public void testCalculateLowPowerWorstFirst() {
		InventoryCalc invC = new InventoryCalc();
		
		// the lowest 1 has to be matched to the relative biggest gap to 1000
		assertEquals(999, invC.calculateLowPower(10, 2, new int[]{
				1, 1000, 1800, 1700, 1600,1500,1400,1300,1200,1100,
				1149,1151,1171,1191,1192,1193,1194,1113,1117,1121,
				1222,1222,1222,1222,1222,1222,1222,1222,1222,1222,
				1222,1222,1222,1222,1222,1222,1222,1222,1222, 2222}));
	}
	
	@Test
	public void testCalculateLowPowerWorstFirstAgain() {
		InventoryCalc invC = new InventoryCalc();
		
		// the lowest 1 has to be matched to the relative biggest gap to 1000
		assertEquals(999, invC.calculateLowPower(5, 4, new int[]{
				1, 1000, 1800, 1700, 1600,1500,1400,1300,1200,1100,
				1149,1151,1171,1191,1192,1193,1194,1113,1117,1121,
				1222,1222,1222,1222,1222,1222,1222,1222,1222,1222,
				1222,1222,1222,1222,1222,1222,1222,1222,1222, 2222}));
	}
}
