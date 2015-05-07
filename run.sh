#!/usr/bin/env bash

# run script for running the word count

# first I'll make sure that all my programs have the proper permissions
chmod a+x src/WordCount.java
chmod a+x .

# then, I'll create the output directory if it hasn't been created
#mkdir ./wc_output/

# then I'll compile the program
javac ./src/WordCount.java -d .

# finally, I execute my program
java WordCount ./wc_input/ ./wc_output/



