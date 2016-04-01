# PrimeUtils
A collection of simple programs which relate to prime numbers !

Programs in this repository :

| Program Name  | Purpose                                               | Usage                                  |
| :------------:|-------------------------------------------------------|:--------------------------------------:|
| Sieve         | Display prime numbers using the Sieve of Eratosthenes | `java Sieve [-h] [-cC] maxNumber`      |
| PrimeTest     | Test whether the number passed to it is prime         | `java PrimeTest [-h] number`           |

+ `Sieve` works extremely fast! Here is a timed example :
    
    satvik@computer:~/Programming/PrimeUtils$ time java Sieve --count-only 100000000
    5761455
    real	0m2.255s
    user	0m2.212s
    sys		0m0.048s

* `ArgHandler` is a small program to simplify the interpretation of command line arguments. Implementing it is as
simple as creating a "switch table", which declares all switches to be used and passing this, along with the `args[]`
array and the minimum number of arguments (excluding switches) to it's constructor. After `ArgHandler` processes the data,
you can query whether a switch is on, pop and push arguments into the queue and detect errors such as missing 
arguments, unrecognized options, etc. Check out `ArgHandler.java` to see all of it's features, and explore 
`Sieve.java` and `PrimeTest.java` to understand it's implementation.
