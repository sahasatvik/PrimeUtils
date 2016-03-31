# PrimeUtils
A collection of simple programs which relate to prime numbers !

Programs in this repository :

| Program Name  | Purpose                                               | Usage                                  |
| :------------:|-------------------------------------------------------|:--------------------------------------:|
| Sieve         | Display prime numbers using the Sieve of Eratosthenes | `java Sieve [-h] [-c | -C] maxNumber`  |


+ `Sieve` works extremely fast! Here is a timed example :
        
	```
        satvik@computer:~/Programming/PrimeUtils$ time java Sieve -C 100000000
        5761455
        real	0m2.255s
        user	0m2.212s
        sys	0m0.048s
        ```
    
