#!/bin/bash
rm docs/ -R
javadoc -verbose -d docs/ com/satvik/cli/args/*.java -author -version
