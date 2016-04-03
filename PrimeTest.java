
import com.satvik.cli.args.ArgHandler;

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


		String[][] flagTable = {{"-h", "--help"}};				// Create a table of accepted flags to pass to the ArgHandler
		ArgHandler a = new ArgHandler(args, flagTable, 1);			// Create an ArgHandler

		if (a.checkFlag('h')) {							// Check whether the user passed '-h' or '--help'
			displayHelp();							// Display a help message
		} else if (a.hasUnknownFlag()) {					// Check whether the user passed an unrecognized flag
			System.out.println("Flags " + a.getUnknownFlags() + " not found !");
			System.out.println("Incorrect usage ! Type in    java PrimeTest -h    to see correct usage");
		} else if (a.hasTooFewArgs()) {						// Check whether the user passed too few arguments
			System.out.println("Too few arguments entered !");
			System.out.println("Incorrect usage ! Type in    java PrimeTest -h    to see correct usage");
		} else {
			int n = a.nextInt();						// Pop the first argument (parsed as an Integer)
			if (n != Integer.MIN_VALUE) {					// Check whether the user has actually entered an int
				System.out.println(PrimeTest.test(n));
			} else {
				System.out.println("Not a number !");
				System.out.println("Incorrect usage ! Type in    java PrimeTest -h    to see correct usage");
			}
		}
	}
}
