import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class WordCount {

	public static void main(String[] args)  throws IOException {
		countWords(args);
		trackMedian(args);
	}
	
	private static void countWords(String[] args)  throws IOException {
		FileInputStream	fileInputStream = null;
		BufferedReader reader = null;
	
		TreeMap<String, Integer> dictionary = new TreeMap<String, Integer>();
		
		//iterate over files in input directory
		File[] files = new File(args[0]).listFiles();
		for(File file : files)
		{
			fileInputStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));

			while (true) {
				String line = reader.readLine();
				if (line == null) break;

				//remove punctuation symbols !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~	
				line = line.replaceAll("\\p{Punct}", "");

				//removes extra white spaces
				line = line.replaceAll("\\s+", " ");
				line = line.trim();

				//we then only consider non-empty lines
				if (!line.equals("")){

					line = line.toLowerCase();
					String[] words = line.split(" ");

					for(String word : words){

						if (dictionary.get(word) == null)
							dictionary.put(word, 1);
						else
							dictionary.replace(word, dictionary.get(word)+1);
							
					}
				}
			}
			reader.close();
			fileInputStream.close();
		}

		//write dictionary to file
		FileOutputStream fileOutputStream = new FileOutputStream(args[1]+"/wc_result.txt");
		
		Map.Entry<String, Integer> entry = dictionary.pollFirstEntry();
		while (entry != null) 	
		{
			String wordCount = entry.getKey() + "\t" + entry.getValue() + "\n";
			fileOutputStream.write(wordCount.getBytes());
			entry = dictionary.pollFirstEntry();
		}
		fileOutputStream.close();
	}
	
	private static void trackMedian(String[] args) throws IOException{
		FileInputStream	fileInputStream = null;
		BufferedReader reader = null;
		
		
		ArrayList<Integer> wordsPerLine = new ArrayList<Integer>();
		
		//iterate over files in input directory
		File[] files = new File(args[0]).listFiles();

		//TODO check the sorting
		Arrays.sort(files);
		FileOutputStream fileOutputStream = new FileOutputStream(args[1]+"/med_result.txt");
		
		for(File file : files)
		{
			fileInputStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));

			while (true) {
				String line = reader.readLine();
				if (line == null) break;

				//remove punctuation symbols !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~	
				line = line.replaceAll("\\p{Punct}", "");

				//removes extra white spaces
				line = line.replaceAll("\\s+", " ");
				line = line.trim();

				//we then only consider non-empty lines
				if (!line.equals(""))
					wordsPerLine.add(line.split(" ").length);
				else
					wordsPerLine.add(0);
				//sort using the natural order
				wordsPerLine.sort(null);
				
				double median;
				if (Math.IEEEremainder(wordsPerLine.size(), 2) != 0)
					median = wordsPerLine.get(wordsPerLine.size()/2);
				else 
					median = 0.5d*( wordsPerLine.get(wordsPerLine.size()/2 - 1) + wordsPerLine.get(wordsPerLine.size()/2) );
	
				String runningMedian = median+ "\n";
				fileOutputStream.write(runningMedian.getBytes());
				
			}
			reader.close();
			fileInputStream.close();
		}
		
		fileOutputStream.close();		
		
	}

}
