package programming.icpc2013.probf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Logger;

public class LowPowerOutputCalculator {

	private final static Logger log = Logger.getLogger(LowPowerOutputCalculator.class.getName());
	
	/**
	 * Read two lines from standard input and write back out a result
	 */
	private static void processFromPrompt() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String nandkString;
		String sbatts;
		int n, k;
		InventoryCalc invC = new InventoryCalc();
		
		// make prompts optional, as problem description does not call for them
		boolean showPrompts = true;
		log.fine("Starting command prompt processing");
		
		if (showPrompts) {
			System.out.println("Please enter number of machines and number of batteries per machine (ie \"2 4\"):");
		}
		
		while ((nandkString = in.readLine()) != null) {
			nandkString = nandkString.trim();
			if (nandkString.isEmpty() || nandkString.equals("quit")) {
				log.info("All Done.");
				break;
			}

			
			// try something new in Java 8.  Might be picky about input format...
			int[] nkarr = Arrays.stream( nandkString.split(" ") ).mapToInt(Integer::parseInt).toArray();
			n = nkarr[0];
			k = nkarr[1];  //TODO: unnecessary assignment if just passing to a method
			
			// TODO: input validation, 2nk < 10^6
			if (n <= 0 || k <= 0) {
				throw new IllegalArgumentException("Cannot take values <= 0");
			}
			
			
			if (showPrompts) {
				System.out.println("Please enter battery output values (ie \"1 2 3 4 5 6 7 8\"):");
			}
			sbatts = in.readLine().trim();
			// TODO: input validation, 1 <= p <= 10^9
			
			// could easily add a .sorted() here, but want to keep all the functionality in the InventoryCalc class
			int[] battsarr = Arrays.stream(sbatts.split(" ")).mapToInt(Integer::parseInt).toArray();
			// and considered passing the IntStream, but want to keep a simple-to-test interface 
			
			// do the real work, from n and k and list of batts
			System.out.println(invC.calculateLowPower(nkarr[0], nkarr[1], battsarr));
			
			// prompt for next loop
			if (showPrompts) {
				System.out.println("\nPlease enter number of machines and number of batteries per machine (ie \"2 4\"):");
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		// either read the values from a file, or from the command prompt
		
		// processFromFiles(".");
		// OR
		processFromPrompt();
	}


}
