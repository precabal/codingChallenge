#!/usr/bin/env bash

# run script for running the word count

# first I'll make sure that all my programs have the proper permissions
chmod a+x src/codingChallenge/*.java
chmod a+x src/codingChallenge/

# then, I'll create the output directory if it hasn't been created
# mkdir ./wc_output/

# then I'll compile the program
javac ./src/codingChallenge/*.java

# finally, I execute my program
java -cp ./src/ codingChallenge.WordCountStats ./wc_input/ ./wc_output/



