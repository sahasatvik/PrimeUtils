
public class PrimeTest {

	public static boolean test (long n) {
		if (n <= 1) {
			return false;
		} else if (n <= 3) {
			return true;
		} else if (((n & 1) == 0) || ((n % 3) == 0)) {
			return false;
		} else {
			int x = 5;
			while ((x * x) <= n) {
				if (((n % x) == 0) || ((n % (x + 2) == 0))) {
					return false;
				}
				x += 6;
			}
		}
		return true;
	}
	private static void displayHelp () {						// Display the helpText
		System.out.println("\nPrimeTest - a simple program to identify whether the number passed to it is prime or not.");
		System.out.println("Usage :");
		System.out.println("\tjava PrimeTest [-h] number\n");
		System.out.println("\t\t-h or --help        :   Displays this text");
		System.out.println("\t\tnumber              :   Number whose primality is to be tested\n");
	}
	public static void main (String[] args) {
		

		String[][] switchTable = {{"-h", "--help"}};				// Create a table of accepted options/switches to pass to the ArgHandler
		ArgHandler a = new ArgHandler(args, switchTable, 1);			// Create an ArgHandler
		
		if (a.isOn('h')) {							// Check whether the user passed '-h' or '--help'
			displayHelp();							// Display a help message
		} else if (a.optionNotFound()) {					// Check whether the user passed an unrecognized option
			System.out.println("Options " + a.getUnknownOptions() + " not found !");
			System.out.println("Incorrect usage ! Type in    java PrimeTest -h    to see correct usage");
		} else if (a.hasTooFewArgs()) {						// Check whether the user passed too few arguments
			System.out.println("Too few arguments entered !");
			System.out.println("Incorrect usage ! Type in    java PrimeTest -h    to see correct usage");
		} else {
			try {
				int n = Integer.parseInt(args[0]);
				System.out.println(PrimeTest.test(n));
			} catch (NumberFormatException e) {				// Catch exceptions
				System.out.println("Not a number !");		
				System.out.println("Incorrect usage ! Type in    java PrimeTest -h    to see correct usage");
			} catch (Exception e) {
				System.out.println("Unknown exception !");
			}		
		}
	}
} 
