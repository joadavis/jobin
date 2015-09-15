package programming.icpc2013.probf;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Simple class to track the inventory of machines and batteries and help make
 * the best battery to machine assignments
 * 
 * @author joadavis
 *
 */
public class InventoryCalc {
	private final static Logger log = Logger.getLogger(InventoryCalc.class.getName());

	int n, k;
	List<Integer> remainingBatts;
	List<TwoChipMachine> machines;
	int worstD;

	// shortcut to keep track of slots here rather than parsing through all the
	// machines to check
	int availableSlots;

	/**
	 * This is the main logic of this solution
	 * 
	 * @param n
	 *            Number of machines
	 * @param k
	 *            Number of batteries required for each chip
	 * @param battsarr
	 *            Array of battery power output values
	 * @return The highest difference in the lowest power batteries across all
	 *         machines
	 */
	public int calculateLowPower(int n, int k, int[] battsarr) {
		// remainingBatts = new ArrayList<int>(Arrays.asList(battsarr));
		// I admit, this seems silly since we had it as an IntStream already
		remainingBatts = new ArrayList<Integer>(IntStream.of(battsarr).boxed().sorted().collect(Collectors.toList()));

		// one quick input validation
		if (n * k * 2 != remainingBatts.size())
			throw new IllegalArgumentException("Wrong number of batteries");

		// see the list
		for (Integer b : remainingBatts) {
			System.out.print(b);
			System.out.print(" ");
		}
		System.out.println(" ");

		machines = new ArrayList<TwoChipMachine>(n);

		// pick the first two sorted batts for the first machine
		log.finest(String.valueOf(remainingBatts.get(0)));
		TwoChipMachine firstMach = new TwoChipMachine(remainingBatts.get(0), remainingBatts.get(1), k);
		remainingBatts.remove(1); // remove the higher number first so it
									// doesn't become 0
		remainingBatts.remove(0);

		machines.add(firstMach);
		availableSlots = firstMach.getRemainingBattSlots();

		log.fine("Available Slots: " + availableSlots);

		// int worstD = 1000000000 + 1; // worse than the allowed range of
		// values
		int worstD = firstMach.getD();

		while (machines.size() < n) {
			log.fine("worst " + worstD + " of " + machines.size());
			//for (TwoChipMachine m : machines) {
			//	log.fine(m.toString());
			//}

			// inline solution
			// lowest d will be between pairs of batteries adjacent in sorted
			// list
			int workingD = 1000000001;

			// determine number of available slots for hiding - numHidingSlots
			// int numHidingSlots = machines.stream().mapToInt(m -> m.getD()).sum();
			// its a shame, that was kind of elegant. But there is a simpler way

			int workingIndex = 0;
			// look at the next numHidingSlots pairs, pick the best one
			// for (int i = 0; i < numHidingSlots; i++) {
			for (int i = 0; i < availableSlots; i++) {
				if (remainingBatts.get(i + 1) - remainingBatts.get(i) < workingD) {
					workingD = remainingBatts.get(i + 1) - remainingBatts.get(i);
					workingIndex = i;
				}
				// else ignore it and will hide it later
			}

			// hide up to the best index
			// by claiming the available slots needed
			availableSlots = availableSlots - workingIndex;

			log.fine("Skipped " + workingIndex + " = " + remainingBatts.subList(0, workingIndex)
					+ ", Available Slots: " + availableSlots);

			TwoChipMachine workingMachine = new TwoChipMachine(remainingBatts.get(workingIndex),
					remainingBatts.get(workingIndex + 1), k);
			// remove the workingIndex + 2 batts
			remainingBatts = remainingBatts.subList(workingIndex + 2, remainingBatts.size());
			machines.add(workingMachine);
			// new capacity coming online
			availableSlots = availableSlots + workingMachine.getRemainingBattSlots();

			log.fine("Available Slots: " + availableSlots);

			if (workingMachine.getD() > worstD)
				worstD = workingMachine.getD();
		}

		// Sanity check, should be no leftovers
		log.fine("Final batch " + availableSlots + " = " + remainingBatts.size());

		log.fine("Final worst " + worstD + " of " + machines.size());
		for (TwoChipMachine m : machines) {
			log.fine(m.toString());
		}

		// the final answer
		return worstD;
	}

}
