import java.util.*;

/*
 * An implementation of a binary search tree.
 *
 * This tree stores both keys and values associated with those keys.
 *
 * More information about binary search trees can be found here:
 *
 * http://en.wikipedia.org/wiki/Binary_search_tree
 *
 */ 
class WordFrequencyBST <K extends Comparable<K>, V>  {

	private static int testPassCount = 0;
	private static int testCount = 0;

	public static final int BST_PREORDER  = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER   = 3;

	private TreeNode<K,V> root;
	private int numElements;

	public WordFrequencyBST(){
		root = null;
		numElements = 0;
	}


	/*
	 * Purpose: Update the BST by handling input word 
	 * Parameters: K key - The key to handle
	 * Returns: Nothing
	 * Explanation: If there is no element in the tree 
	 *   with the given key, then the a new element 
	 *   should be created and placed into the correct 
	 *   location of the BST (determined by the Binary
	 *   Search Tree property. Otherwise, the existing
	 *   element with the given key should have its 
	 *   value incremented by one.
	 */	
	public void handleWord(K key) {
		TreeNode<K,V> foundWord = null;
		try{
			foundWord = findNode(key, root);
		}
		catch(KeyNotFoundException e){
			//System.out.println(e);
		}
		if(foundWord == null){
			insert(key, root, root);
		}
		else{
			foundWord.incrementValue();
		}
	}
	
	/*
	 * Purpose: Insert an element into the BST
	 * Parameters: K key - The key to insert
	 * TreeNode<K,V> current - The lower node
	 * TreeNode<K,V> parent - The upper node
	 * Returns: Nothing
	 * Explanation: This method handles only insertion of a new element int othe tree.
	 * numElements is updated here.
	 */	
	public void insert(K key, TreeNode<K,V> current, TreeNode<K,V> parent){
		if(this.root == null){
			root = new TreeNode<K,V>(key);
			numElements++;
		}
		else{
			if(current == null){
				if(key.compareTo((K)parent.key) > 0){
					parent.right = new TreeNode<K,V>(key);
				}
				else{
					parent.left = new TreeNode<K,V>(key);
				}
				numElements++;
			}
			else if(key.compareTo((K)current.key) > 0){
				insert(key, current.right, current);
			}
			else{
				insert(key, current.left, current);
			}
		}
	}

	/*
	 * Purpose: Searches for a node in the tree
	 * Parameters: K key - The key to look for
	 * TreeNode<K,V> current - The lower node
	 * Returns: TreeNode<K,V> - The TreeNode with the given key (if it is found).
	 * Explanation: This method handles only searching in the tree. If an element is in the tree,
	 * it will be returned.  If not a KeyNotFoundException is thrown
	 */
	public TreeNode<K, V> findNode(K key, TreeNode<K,V> current) throws KeyNotFoundException{
		if(current == null){
			throw new KeyNotFoundException("The word is not in the Tree");
		}
		else if(key.compareTo((K)current.key) == 0){
			return current;
		}
		else if(key.compareTo((K)current.key) > 0){
			return findNode(key, current.right);
		}
		else{
			return findNode(key, current.left);
		}
	}
	
	/*
	 * Purpose: Get the  value of the given key
	 * Parameters: K key - the key to search for
	 * Returns: V - the key's associated value
	 */	
	public int getFrequency(K key) {
		try{
		TreeNode<K,V> keyToMyHeart = findNode(key, root);
		return (int)keyToMyHeart.value;
		}
		catch(KeyNotFoundException e){
			//System.out.println(e);
			return 0;
		}
	}
	
	/*
	 * Purpose: Get the total number of nodes in the tree
	 * Parameters: None
	 * Returns: int - the total number of nodes in the tree
	 */	
	public int size() {
		return numElements;
	}

	/*
	 * Purpose: Return a list of all the key-value entries stored in the tree
	 * Parameters: none
	 * Returns: A list of all key-value entries stored in the tree, constructed 
	 *          by performining a level-order traversal of the tree.
	 *
	 * Level-order is most commonly implemented using a queue of nodes.
	 * From Wikipedia, the algorithm is:
	 *
	 * levelorder()
	 *		q = empty queue
	 *		q.enqueue(root)
	 *		while not q.empty do
	 *			node := q.dequeue()
	 *			visit(node)
	 *			if node.left != null then
	 *			      q.enqueue(node.left)
	 *			if node.right != null then
	 *			      q.enqueue(node.right)
	 */	
	public List<Entry<K,V>> entryList() {
		List<Entry<K, V>> resultList = new LinkedList<Entry<K,V>>();
		LinkedList<TreeNode<K,V>> queue = new LinkedList<TreeNode<K,V>>();
		queue.add(root);
		do{
			TreeNode<K,V> currentNode = queue.removeFirst();
			resultList.add(new Entry<K,V>(currentNode.key,currentNode.value));
			if(currentNode.left != null){
				queue.add(currentNode.left);
			}
			if(currentNode.right != null){
				queue.add(currentNode.right);
			}
		}while(queue.size() != 0);
		
		return resultList;
	}


	/****************************************************
	* Helper functions for Insertion and Search testing *
	****************************************************/
	
	public List<Entry<K,V>> entryList (int which) {
		List<Entry<K,V>> resultList = new LinkedList<Entry<K,V> >();

		if (which == BST_PREORDER) {
			preOrderRecursive(root, resultList);
		}
		else if (which == BST_INORDER) {
			inOrderRecursive(root, resultList);
		}
		else if (which == BST_POSTORDER) {
			postOrderRecursive(root, resultList);
		}

		return resultList;
	}

	// completed for you
	private void inOrderRecursive (TreeNode<K,V> n, List <Entry<K,V>> resultList) {
		if (n == null) {
			return;
		}
		inOrderRecursive(n.left, resultList);
		resultList.add(new Entry<K,V>(n.key, n.value));
		inOrderRecursive(n.right,resultList);
	}

	// completed for you
	private void preOrderRecursive (TreeNode<K,V> n, List <Entry<K,V>> resultList) {
		if (n == null) {
			return;
		}
		resultList.add(new Entry<K,V>(n.key, n.value));
		preOrderRecursive(n.left, resultList);
		preOrderRecursive(n.right,resultList);
	}

	// completed for you
	private void postOrderRecursive (TreeNode<K,V> n, List <Entry<K,V>> resultList) {
		if (n == null) {
			return;
		}
		postOrderRecursive(n.left, resultList);
		postOrderRecursive(n.right,resultList);
		resultList.add(new Entry<K,V>(n.key, n.value));
	}
	
	/****************************************************
	* Helper functions to populate a Heap from this BST *
	****************************************************/
	
	public MaxFrequencyHeap<K,V> createHeapFromTree() {
		MaxFrequencyHeap<K,V> maxHeap = new MaxFrequencyHeap<K,V>();
		addToHeap(maxHeap, root);
		return maxHeap;
	}
	
	public void addToHeap(MaxFrequencyHeap<K,V> h, TreeNode<K,V> n) {
		if (n != null) {
			addToHeap(h, n.left);
			h.insert(new Entry<K,V>(n.key, n.value));
			addToHeap(h, n.right);
		}
	}
	
}
