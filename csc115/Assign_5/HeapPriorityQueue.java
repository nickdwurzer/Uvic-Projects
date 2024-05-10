/*
 * HeapPriorityQueue.java
 *
 * An implementation of a minimum PriorityQueue using a heap.
 * based on the implementation in "Data Structures and Algorithms
 * in Java", by Goodrich and Tamassia
 *
 * This implementation will throw a Runtime, HeapEmptyException
 *	if the heap is empty and removeLow is called.
 *
 * This implementation will throw a Runtime, HeapFullException
 *	if the heap is full and insert is called.
 *
 */

public class HeapPriorityQueue implements PriorityQueue {
    
	protected final static int DEFAULT_SIZE = 10000;

	protected Comparable[] storage;
	protected int currentSize;
	protected int root = 1;

	/* constructor
	 *
	 * PURPOSE:
	 *  initializes storage to new Comparable[] of DEFAULT_SIZE
	 *      and initializes currentSize to 0
	 *
	 * PARAMETERS:
	 *  none
	 */
	public HeapPriorityQueue(){
		storage = new Comparable[DEFAULT_SIZE];
		currentSize = 0;
	}

	/* constructor
	 *
	 * PURPOSE:
	 *  initializes storage to new Comparable[] of given size
	 *      and initializes currentSize to 0
	 *
	 * PARAMETERS:
	 *  int size
	 */

	public HeapPriorityQueue(int size){
		storage = new Comparable[size + 1];
		currentSize = 0;
	}
	
	




	/*
	 * PURPOSE:
	 *    constructs a String representation of the elements in storage
	 *      ordered by their position in storage NOT by priority 
	 *
	 * PARAMETERS:
	 *    None.
	 *
	 * RETURNS:
	 *    String - the String representation
	 *
	 */
	public String toString() {
		String s = "";
		String sep = "";
		for(int i = 1;i <= currentSize;i++) {
			s+= sep + storage[i];
			sep = " ";
		}
		return s;
	}

	@Override
	public void insert(Comparable element) throws HeapFullException{
		if(currentSize == (storage.length-1)){
			throw new HeapFullException("The item cannot be added, the heap is full");
		}
		if(currentSize == 0){
			storage[root] = element;
		}
		else{
			storage[currentSize + 1] = element;
			bubbleUp(currentSize + 1);
		}
		currentSize++;
	}

	public void bubbleUp(int insertionIndex){
		if(insertionIndex / 2 > 0){
			if(storage[insertionIndex].compareTo(storage[insertionIndex/2]) < 0){
				//System.out.println("entered bubbleup");
				Comparable temp = storage[insertionIndex];
				storage[insertionIndex] = storage[insertionIndex/2];
				storage[insertionIndex/2] = temp;
				bubbleUp(insertionIndex/2);
			}
		}
	}

	@Override
	public Comparable removeMin() throws HeapEmptyException {
		if(currentSize == 0){
			throw new HeapEmptyException("Cannot remove item, heap is empty");
		}
		Comparable temp = storage[root];
		storage[root] = storage[currentSize];
		storage[currentSize] = null;
		bubbleDown(root);
		currentSize--;
		return temp;
	}

	public void bubbleDown(int index){
		Comparable temp = storage[index];
		if((index * 2 + 1 ) < currentSize){
			if(storage[index].compareTo(storage[index*2]) > 0 || storage[index].compareTo(storage[index*2+1]) > 0){
				if(storage[index*2].compareTo(storage[index*2+1]) < 0 ){
					storage[index] = storage[index * 2];
					storage[index * 2] = temp;
					bubbleDown(index * 2);
				}
				else{
					storage[index] = storage[index * 2 + 1];
					storage[index * 2 + 1] = temp;
					bubbleDown(index * 2 + 1);
				}
			}
		}
		else if(((index * 2) < currentSize) && (storage[index].compareTo(storage[index*2]) > 0)){
			storage[index] = storage[index * 2];
			storage[index * 2] = temp;
			bubbleDown(index * 2);
		}
	}

	@Override
	public boolean isEmpty() {
		if(currentSize == 0){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return currentSize;
	}

}
