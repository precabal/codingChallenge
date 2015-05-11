package codingChallenge;

import java.util.Map.Entry;
import java.util.TreeMap;

public class WordHistogram {

	private TreeMap<String, Integer> wordHistogram;
	
	public WordHistogram(){
		wordHistogram = new TreeMap<String, Integer>();
	}
	
	public void AddWord(String word){
		if (wordHistogram.get(word) == null)
			wordHistogram.put(word, 1);
		else
			wordHistogram.replace(word, wordHistogram.get(word)+1);	
	}
	public int ProcessLine(String line){
		int numberOfWords = 0;
		if (!line.equals("")){
			
			line = line.toLowerCase();
			String[] words = line.split(" ");

			for(String word : words)
				this.AddWord(word);
			
			numberOfWords = words.length;
		}
		return numberOfWords;
	}
	
	public Entry<String, Integer> retrieveNextEntry(){
		return wordHistogram.pollFirstEntry();
	}
	
}
