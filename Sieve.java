
import com.satvik.cli.args.ArgHandler;

public class Sieve {

	int size;
	int primeCount;
	boolean[] sieve;

	public Sieve (int size) {
		this.size = size;
		sieve = new boolean[size+1];					// Create boolean array with each index representing a number.
		resetSieve ();
		sievePrimes();
	}
	public void resetSieve () {						// Mark all 'numbers' with true, representing 'not composite'
		for (int i = 1; i <= size; i++) {
			sieve[i] = true;
		}
	}
	public void sievePrimes () {						// 'Sieve' out all primes, which will remain marked true.
		int p = 2;							// Start with 2.
		while ((p * p) <= size) {					// 'Cross out' (mark false) multiples of p, such that p^2 <= size
			int m = p * 2;						// Start by crossing out the second multiple of p ...
			while (m <= size) {					// ... continue until the multiples reach size
				sieve[m] = false;				// Cross out the multiple
				m += p;						// Jump to the next multiple
			} 
			while (!sieve[++p]);					// Increase p until it reaches an uncrossed number
		}
	}
	public void displayPrimes (boolean count, boolean countOnly) {		// Display all primes : numbers marked true
		primeCount = 0;
		for (int i = 2; i <= size; i++) {
			if (sieve[i]) {
				primeCount++;
				if (!countOnly) { 				// Check whether only the count is to be displayed
					System.out.printf("%d ", i);
				}
			}
		}
		if (countOnly) {						// Check whether only the count is to be displayed
			System.out.printf("%d", primeCount);			// Display the count
		} else if (count) {
			System.out.printf("%n");
			System.out.printf("%nPrime Count : %d%n", primeCount);  // Display the count verbosely
		} else {
			System.out.printf("%n");
		}
	}


	private static void displayHelp () {					// Display the helpText
		System.out.println("\nSieve - a simple program to display primes less than 'maxNumber' using the sieve of Eratosthenes.");
		System.out.println("Usage :");
		System.out.println("\tjava Sieve [-h] [-cC] maxNumber\n");
		System.out.println("\t\t-h or --help        :   Displays this text");
		System.out.println("\t\t-c or --count       :   Displays number of primes detected");
		System.out.println("\t\t-C or --count-only  :   Only outputs number of primes detected : overrides -c");
		System.out.println("\t\tmaxNumber           :   Number below which primes are to be found\n");
	}

	public static void main (String[] args) {

		Sieve eratosthenes;
		String[][] flagTable = {{"-h", "--help"},			// Create a table of accepted flags ...
					  {"-c", "--count"},			// ... to pass to the ArgHandler
					  {"-C", "--count-only"}};		// Strings in each row are synonimous, eg, '-h' is also recognized as '--help'
		ArgHandler a = new ArgHandler(args, flagTable, 1);		// Create an ArgHandler, pass to (String[] args, String[][] flagTable, int minArgs)

		if (a.checkFlag('h')) {						// Check whether the user passed '-h' or '--help'
			displayHelp();						// Display a help message
		} else if (a.hasUnknownFlag()) {				// Check whether the user passed an unrecognized flag
			System.out.println("Flags " + a.getUnknownFlags() + " not found !");
			System.out.println("Incorrect usage ! Type in    java Sieve -h    to see correct usage");
		} else if (a.hasTooFewArgs()) {					// Check whether the user passed too few arguments
			System.out.println("Too few arguments entered !");
			System.out.println("Incorrect usage ! Type in    java Sieve -h    to see correct usage");
		} else {

			boolean count = a.checkFlag('c');			// Fetch the flags for --count ...
			boolean countOnly = a.checkFlag('C');			// ... and --count-only
			int maxNumber = a.nextInt();				// Pop the first argument (parsed as an Integer)

			if (maxNumber != Integer.MIN_VALUE) {			// Check whether the user has actually entered an int
				eratosthenes = new Sieve(maxNumber);
				eratosthenes.displayPrimes(count, countOnly);	// Display the sieve, according to the user's options
			} else {
				System.out.println("Not a number !");
				System.out.println("Incorrect usage ! Type in    java Sieve -h    to see correct usage");
			}
		}
	}
}
