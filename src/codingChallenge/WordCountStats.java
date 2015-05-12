package codingChallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Arrays;

/*
main class that invokes a wordHistogram object to do the word counting task, 
and a MedianTracker object to calculate the running median. It also handles the file I/O
*/
public class WordCountStats {

	public static void main(String[] args)  throws IOException {
	
		if (args.length != 2){
			System.out.print("Usage: java WordCount <input directory> <output directory>\n");
			System.exit(1);
		}
		String inputDir = args[0];
		String outputDir = args[1];
		
		//initialize most important objects
		FileOutputStream fileOutputStreamMedian = new FileOutputStream(outputDir+"/med_result.txt");
		FileInputStream	fileInputStream = null;
		BufferedReader reader = null;
		
		WordHistogram wordHistogram = new WordHistogram();
		MedianTracker medianTracker = new MedianTracker();
		
		//get array with input files
		File[] files = new File(inputDir).listFiles();
		//make sure they are in alphabetical order
		Arrays.sort(files);
		
		//iterate over files
		for(File file : files){
			
			fileInputStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));

			//iterate over lines in files
			String line = null;
			while ( (line = getNextParsedLine(reader)) != null) {
				
				int numberOfWords = wordHistogram.ProcessLine(line);
				
				//update running median	
				medianTracker.insertValue(numberOfWords);
				
				//write current line to median tracker file
				writeStringToFile(fileOutputStreamMedian, new String(medianTracker.GetMedian() + "\n"));
			}	
			reader.close();
			fileInputStream.close();
		}		
		
		
		fileOutputStreamMedian.close();
		
		//write histogram to file
		writeHistogramToFile(wordHistogram, new String(outputDir+"/wc_result.txt"));
	}
	
	private static String getNextParsedLine(BufferedReader reader) throws IOException{
		
		String line = reader.readLine();
		if (line == null) return null;
		
		//get rid of dashes and apostrophes, as per assumptions discused in the FAQ
		line = line.replaceAll("\\[-']", "");
		
		/*replace other punctuation symbols !"#$%&()*+,./:;<=>?@[\]^_`{|}~ for white spaces
		to make sure that a typo like foo,bar woudl still be recognized as two separate words*/
		line = line.replaceAll("\\p{Punct}", "");

		//remove extra white spaces
		line = line.replaceAll("\\s+", " ");
		line = line.trim();
		
		return line;
	}
	
	private static void writeHistogramToFile(WordHistogram wordHistogram, String outputPath) throws IOException{

		//write dictionary to file
		FileOutputStream fileOutputStreamWordCount = new FileOutputStream(outputPath);
		
		Map.Entry<String, Integer> entry;
		while ((entry = wordHistogram.retrieveNextEntry()) != null) 	
		{	
			writeStringToFile(fileOutputStreamWordCount, new String(entry.getKey() + "\t" + entry.getValue() + "\n"));	
		}
		fileOutputStreamWordCount.close();
	}
	
	private static void writeStringToFile(FileOutputStream outputStream, String line) throws IOException {
		
		outputStream.write(line.getBytes());
	}
	

}
