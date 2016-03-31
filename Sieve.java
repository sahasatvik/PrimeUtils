import java.text.NumberFormat;

class Sieve {
	
	int size;
	int primeCount;
	boolean[] sieve;
		
	Sieve (int size) {
		this.size = size;
		sieve = new boolean[size+1];					// Create boolean array with each index representing a number. 
		resetSieve ();
		sievePrimes();
	}
	void resetSieve () {							// Mark all 'numbers' with true, representing 'not composite'
		for (int i = 1; i <= size; i++) {
			sieve[i] = true;	
		}
	}
	void sievePrimes () {							// 'Sieve' out all primes, which will remain marked true.
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
	void displayPrimes (boolean count, boolean countOnly) {			// Display all primes : numbers marked true
		primeCount = 0;
		for (int i = 2; i <= size; i++) {
			if (sieve[i]) {
				primeCount++;
				if (!countOnly) { 
					System.out.printf("%d ", i);
				}
			}
		}
		if (countOnly) {
			System.out.printf("%d", primeCount);
		} else if (count) {
			System.out.printf("%n");
			System.out.printf("%nPrime Count : %d%n", primeCount);
		} else {
			System.out.printf("%n");
		
		}
	}
	
	static int argBase;
	static int numberOfArgs;
	static boolean help;
	static boolean count;
	static boolean countOnly;

	public static boolean hasMoreArgs () {
	    return numberOfArgs > argBase;
	}
	
	public static void displayHelp () {
		System.out.println("\nSieve - a simple program to display primes less than 'maxNumber' using the sieve of Eratosthenes.");
		System.out.println("Usage :");
		System.out.println("\tjava Sieve [-h] [-c | -C] maxNumber\n");
		System.out.println("\t\t-h or --help        :   Displays this text");
		System.out.println("\t\t-c or --count       :   Displays number of primes detected");
		System.out.println("\t\t-C or --count-only  :   Only outputs number of primes detected");
		System.out.println("\t\tmaxnumber           :   Number below which primes are to be found\n");
	}

	public static void main (String[] args) {
	
		argBase = 0;
		numberOfArgs = args.length;
		
		try {
			help = !hasMoreArgs() || args[argBase].equals("-h") || args[argBase].equals("--help");
			if (help) {
				displayHelp();
				argBase++;
			}
			if (hasMoreArgs()) {
				count = args[argBase].equals("-c") || args[argBase].equals("--count");
				if (count) {
					argBase++;
				} else {
					countOnly = args[argBase].equals("-C") || args[argBase].equals("--count-only");
					if (countOnly) {
   						argBase++;
   					}
				}
				if (hasMoreArgs()) {
					int size = Integer.parseInt(args[argBase]);
					if (size > 1) {
						Sieve eratosthenes = new Sieve(size);
						eratosthenes.displayPrimes(count, countOnly);
					} else {
						System.out.println("maxNumber is too less ! Try keeping maxNumber above 1");
					}
				} else {
					System.out.println("Too few arguments !");
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Incorrect usage ! Type in    java Sieve -h    to see correct usage");
		} catch (Exception e) {
			System.out.println("Exception !" + e.getMessage());
		}
	}
}
