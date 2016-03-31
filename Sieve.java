class Sieve {
	
	int size;
	boolean[] sieve;
		
	Sieve (int size) {
		this.size = size;
		sieve = new boolean[size+1];
		resetSieve ();
		sievePrimes();
	}
	void resetSieve () {
		for (int i = 1; i <+ size; i++) {
			sieve[i] = true;	
		}
	}
	void sievePrimes () {
		int p = 2;
		while ((p * p) <= size) {
			int m = p * 2;
			while (m <= size) {
				sieve[m] = false;
				m += p;
			} 
			while (!sieve[++p]); 
		}
	}
	void displayPrimes () {
		int primeCount = 0;
		for (int i = 2; i <= size; i++) {
			if (sieve[i]) {
				primeCount++;
				System.out.printf("%d ", i);
			}
		}
		System.out.printf("%nPrime Count : %d%n", primeCount);
	}

	public static void main (String[] args) {
		int size = (args.length > 0)? Integer.parseInt(args[0]) : 100;
		Sieve eratosthenes = new Sieve(size);
		eratosthenes.displayPrimes();
	}
}
