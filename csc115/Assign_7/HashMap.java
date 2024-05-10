import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

	private List<List<Entry<K,V>>> 	table;
	private int	count;
	private int  tableSize;

	// For Part III
	private long getLoops;
	private long putLoops;
	

	public HashMap() {
		tableSize = 1000003; // prime number
		table = new ArrayList<List<Entry<K,V>>>(tableSize);

		// initializes table as a list of empty lists
		for (int i = 0; i < tableSize; i++) {
			table.add(new LinkedList<Entry<K,V>>());
		}

		count = 0;

		// For Part III:
		resetGetLoops();
		resetPutLoops();
	}

	// For Part III
	public long getGetLoopCount() {
		return getLoops;
	}

	// For Part III
	public long getPutLoopCount() {
		return putLoops;
	}

	// For Part III
	public void resetGetLoops() {
		getLoops = 0;
	}
	
	// For Part III
	public void resetPutLoops() {
		putLoops = 0;
	}

	public boolean containsKey(K key) {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;
		List<Entry<K,V>> list = table.get(index);
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			Entry<K,V> e = (Entry<K,V>)iter.next();
			if(e.getKey() == key){
				return true;
			}
		}
	return false;
	}

	public V get (K key)  throws KeyNotFoundException{
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;
		List<Entry<K,V>> list = table.get(index);
		Iterator iter = list.iterator();
		
		K mykey = null;
		while(iter.hasNext()){
			Entry<K,V> e = (Entry<K,V>)iter.next();
			mykey = e.getKey();
			if(e.getKey().equals(key)){
				return e.getValue();
			}
		}
		throw new KeyNotFoundException();
	}


	public List<Entry<K,V> >	entryList() {
		List <Entry<K,V>> resultList = new LinkedList<Entry<K,V>>();
		
		for(int i = 0; i < tableSize; i++){
			
			Iterator it = table.get(i).iterator();
			while(it.hasNext()){
				resultList.add((Entry<K,V>)it.next());
			}
		}

		// Tip: you will need to iterate through each index in the table (and each index holds a list)
		//      you will THEN need to iterate through each element in the linked list chain at a 
		//      specific index and add each element to l

		return resultList;
	}

	public void put (K key, V value){
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode())%tableSize;
		Iterator it = table.get(index).iterator();
		
		boolean found = false;
		while(it.hasNext()){
			Entry<K,V> e = (Entry<K,V>)it.next();
			if(e.getKey() == key){
				e.setValue(value);
				found = true;
			}
		}
		if(!found){
			table.get(index).add(new Entry<K,V>(key, value));
			count++;
		}


	}

	public int size() {
		return count;
	}

	public void clear() {
		table.clear();
		count = 0;
	}

}