// Name: 
// Student number: V00

import java.util.*;
public class WordFrequencyReport<K extends Comparable<K>, V> {
	private static final int CAPACITY = 5;
	
	/*
	 * Purpose: Obtain the 5 most frequent words found
	 * Parameters: MaxFrequencyHeap h - the heap containing all the word entry data
	 * Returns: Entry[] - an array containing the top 5 entries (which are the 
	 *     word, frequency pairs with the maximum frequency values)
	 */
	public static List<Entry<String, Integer>> overallMostFrequent(MaxFrequencyHeap<String,Integer> h) {
		List<Entry<String, Integer>> top5 = new ArrayList<Entry<String,Integer>>(CAPACITY);
		Entry<String,Integer> nextEntry = h.removeMax();
		top5.add(nextEntry);
		nextEntry = h.removeMax();
		top5.add(nextEntry);
		nextEntry = h.removeMax();
		top5.add(nextEntry);
		nextEntry = h.removeMax();
		top5.add(nextEntry);
		nextEntry = h.removeMax();
		top5.add(nextEntry);
		
		
		
		
		return top5;
	}
	
	/*
	 * Purpose: Obtain the 5 most frequent words found that are at least n charaters long
	 * Parameters: MaxFrequencyHeap h - the heap containing all the word entry data
	 *             int n - minimum word length to consider
	 * Returns: Entry[] - an array containing the top 5 entries (which are the 
	 *     word, frequency pairs with the maximum frequency values of words 
	 *     that are at least n characters long)
	 */
	public static List<Entry<String, Integer>> atLeastLength(MaxFrequencyHeap<String,Integer> h, int n) {
		List<Entry<String, Integer>> top5 = new ArrayList<Entry<String,Integer>>(CAPACITY);
		try{
			for(int i = 0; i<5; i++){
				Entry<String,Integer> nextEntry = h.removeMax();
				if(nextEntry.key.length() >= n){
					top5.add(nextEntry);
				}
				else{
					i--;
				}
			}
		}
		catch(NoSuchElementException e){
			System.out.println("There are less than 5 elements that have 'n' or more characters.");
		}
		
		return top5;
	}
	
	/*
	 * Purpose: Obtain the 5 most frequent words found that begin with the given letter
	 * Parameters: MaxFrequencyHeap h - the heap containing all the word entry data
	 *             char letter - only words that begin with given letter are considered
	 * Returns: Entry[] - an array containing the top 5 entries (which are the 
	 *     word, frequency pairs with the maximum frequency values of words 
	 *     that begin with the given letter)
	 */
	public static List<Entry<String, Integer>> startsWith(MaxFrequencyHeap<String,Integer> h, char letter) {
		List<Entry<String, Integer>> top5 = new ArrayList<Entry<String,Integer>>(CAPACITY);
		try{
			for(int i = 0; i<5; i++){
				Entry<String,Integer> nextEntry = h.removeMax();
				if(nextEntry.key.charAt(0) == letter){
					top5.add(nextEntry);
				}
				else{
					i--;
				}
			}
		}
		catch(NoSuchElementException e){
			System.out.println("There are less than 5 elements that start with 'letter'.");
		}
		
		return top5;
	}	
}
 
