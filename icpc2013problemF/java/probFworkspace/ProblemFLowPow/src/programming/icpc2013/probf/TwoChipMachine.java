package programming.icpc2013.probf;

import java.util.logging.Logger;

/**
 * Class for representing each Machine and operations we want on it
 * Each Machine has two chips and each chip can be assigned k batteries
 * For this problem, we really only care about the lowest power batt on each chip, so to save space we don't track all the batts
 * 
 * @author joadavis
 *
 */
public class TwoChipMachine {
	private final static Logger log = Logger.getLogger(TwoChipMachine.class.getName());
	
	private int chipAbatt1;
	private int chipBbatt1;
	private int k; // total number of batteries needed per chip
	private int remainingBattSlots; // between both chips
	private int d; // difference in power output between the two lowest batteries

	
	/**
	 * Representation of a machine with two chips and k slots for batteries each
	 * 
	 * @param chipAbatt1 Power output of first Battery on chip A
	 * @param chipBbatt1 Power output of first Battery on chip B
	 * @param k Number of Battery slots per chip
	 */
	public TwoChipMachine(int chipAbatt1, int chipBbatt1, int k) {
		//super();
		this.chipAbatt1 = chipAbatt1;
		this.chipBbatt1 = chipBbatt1;
		this.k = k;
		
		this.d = chipBbatt1 - chipAbatt1; // knowing we have sorted the list, then B > A
		log.finer("B " + chipBbatt1 + " - A " + chipAbatt1 + " = " + this.d);
		
		this.remainingBattSlots = k * 2 - 2;  // number of batts per chip x 2 chips - the two batts provided
	}
	
	/**
	 * Use up one of the available battery slots
	 * 
	 * if we were tracking all battery values, would do this differently
	 * @return number of remaining slots
	 */
	public int claimBattSlot() {
		if (remainingBattSlots > 0)
			remainingBattSlots--;
		else
			throw new IndexOutOfBoundsException("All battery slots have been claimed.");
		return remainingBattSlots;
	}
	
	/**
	 * Determine the number of battery slots available
	 * @return number of remaining slots
	 */
	public int getRemainingBattSlots() {
		return remainingBattSlots;
	}
	
	/**
	 * Get the difference in power output between the lowest batteries on each chip
	 * @return Difference in power output
	 */
	public int getD() {
		return d;
	}

	@Override
	public String toString() {
		return "Machine [chipAbatt1=" + chipAbatt1 + ", chipBbatt1=" + chipBbatt1 + ", k=" + k + ", remainingBattSlots="
				+ remainingBattSlots + ", d=" + d + "]";
	}
	
	
}
