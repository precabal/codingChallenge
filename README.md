Insight Data Engineering - Coding Challenge
===========================================================

This coding challenge is implements my own version of Word Count that counts all the words from the text files contained in a directory named `wc_input` and outputs the counts (in alphabetical order) to a file named `wc_result.txt`, which is placed in a directory named `wc_output`.

It also implements my own version of a running median algorithm, that calculates the median number of words per line, for each line of the text files in the `wc_input` directory.  If there are multiple files in that directory, the files are combined into a single stream and processed by the running median program in alphabetical order. The resulting running median for each line should is then outputted to a text file named `med_result.txt` in the `wc_output` directory.

All the asumptions regarding the text on the FAQ at https://github.com/InsightDataScience/cc-example have been adopted. In addition, it is assumed that typos in punctuation of the sort: "foo,bar" are meant two be two separate words and are therefore separated into "foo" and "bar".

This solution was written using Java, and requires Java 8 for compiling and running. 







