package codingChallenge;

import java.util.PriorityQueue;

//class to calculate running median utilizing two Heaps. 
public class MedianTracker {

	private PriorityQueue<Integer> minHeap, maxHeap;
	
	public MedianTracker(){
		//Initialize heaps with an assumption of the expectation for the number of lines that might be fed to test the software
		minHeap = new PriorityQueue<Integer>(200);
		maxHeap = new PriorityQueue<Integer>(200, new MyComparator());
	}
	
	//inserts a new value into the proper heap
	public void insertValue(int value){ 
		
		//compare value to insert with current median and decide what heap it belongs to
		if (value >= this.GetMedian())
			minHeap.add(value);
		else
			maxHeap.add(value);
			
		//re-balance the heaps 
		BalanceHeaps();
		
	}
	
	//blanaces heaps so they have the same number of elements or the closest to that	
	private void BalanceHeaps(){
		//If the sizes of the heaps differ by more than one element, extract the min/max from the heap with more elements and insert it into the other heap.
		if(Math.abs(minHeap.size()-maxHeap.size())>1){
			if(minHeap.size() > maxHeap.size())
				maxHeap.add(minHeap.poll());
			else
				minHeap.add(maxHeap.poll());	
		}
	}
	
	private boolean HeapsAreEmpty(){
		return (minHeap.isEmpty() && maxHeap.isEmpty());
	}
	

	//calculate the median of the inserted files
	public float GetMedian(){
		//default value to indicate the heaps are empty
		float median = -1.0f;
		
		if (HeapsAreEmpty())
			return median;
		
		if (minHeap.size() == maxHeap.size()){
			median =  0.5f * (minHeap.peek() + maxHeap.peek());
		}else{
			if (minHeap.size() < maxHeap.size()){
				median = maxHeap.peek();			
			}else{
				median = minHeap.peek();
			}
		}
		return median;
	}
	
}


