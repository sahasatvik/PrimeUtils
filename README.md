# PrimeUtils
A collection of simple programs which relate to prime numbers !


## Compiling the Programs
Compiling the programs in this repository is as simple as executing the following code in the directory containing the `.java` files :

    javac *.java

## Using the Programs
Following is a list of the programs in this repository :

| Program Name  | Purpose                                               | Usage                                  |
| :------------:|-------------------------------------------------------|----------------------------------------|
| Sieve         | Display prime numbers using the Sieve of Eratosthenes | `java Sieve [-h] [-cC] maxNumber`      |
| PrimeTest     | Test whether the number passed to it is prime         | `java PrimeTest [-h] number`           |


* Each program has it's own extensive help-text, which you can read by executing :
    ```
    java <program name> --help
    ```

* `Sieve` works extremely fast! Here is a timed example :
    
    ```    
    satvik@computer:~/Programming/PrimeUtils$ time java Sieve --count-only 100000000
    5761455
    real	0m2.255s
    user	0m2.212s
    sys		0m0.048s
    ```

* `ArgHandler` is a small program to simplify the interpretation of command line arguments. Implementing it is 
as simple as creating a "flag table" (which declares all flags (eg. `--help` or `-h`) to be used) and passing this, along with the 
arguments (`args[]`) array and the minimum number of arguments (excluding flags) to `ArgHandler(String[], String[][], int)`. 
After `ArgHandler` processes the data, you can query whether a flag is on, retrieve values assigned to a flag by the user
(eg. `--flag=value` ) and detect errors such as missing arguments, unrecognized flags, etc. Explore `ArgHandler.java` to find out all of it's 
features, and check out `Sieve.java` and `PrimeTest.java` to understand it's implementation.
