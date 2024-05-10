package Assign_3;
// Name: Nicholas Wurzer
// Student number: v00958568


public class A3LinkedList implements A3List {
	private A3Node head;
	private A3Node tail;
	private int length;
	
	public A3LinkedList() {
		head = null;
		tail = null;
		length = 0;
	}
	
	public void addFront(String s) {
        A3Node temp = new A3Node(s);
        if(tail == null){
            tail = temp;
        }
        temp.setNext(this.head);
        temp.setPrev(null);
        if(this.head != null){
            head.prev = temp;
        }
        head = temp;
        length++;

	}

	public void addBack(String s) {
        A3Node temp = new A3Node(s);
        if(head == null){
            head = temp;
        }
        temp.setNext(null);
        temp.setPrev(this.tail);
        if(this.tail != null){
            tail.next = temp;
        }
        tail = temp;
        length++;
	}
	
	public int size() {
		return length;
	}
	
	public boolean isEmpty() {
		return length==0;
	}
	
	public void removeFront() {
        A3Node temp = head.getNext();
        temp.prev = null;
        head.next = null;
        head = temp;
	}
	
	public void removeBack() {
        A3Node temp = tail.getPrev();
        temp.next = null;
        tail.prev = null;
        tail = temp;
	}
	
	
	public void rotate(int n) {
		for(int i = 0; i < n; i++){
            A3Node temp = head.next;
            temp.prev = null;
            head.next = null;

            head.prev = tail;
            tail.next = head;
            tail = head;
            head = temp;

        }
	}
	
	public void interleave(A3LinkedList other) {
        /*A3Node otherTemp;
        A3Node thisTemp;
        A3Node cur1 = this.head;
        A3Node cur2 = other.head;
        
        for(int i = 0; i < other.size(); i++){
            if(i%2 == 0){
                otherTemp = cur2.next.next;
                thisTemp = cur1.next;
                otherTemp.setPrev(thisTemp);
                thisTemp.next.setPrev(cur2.next);
                cur2.next.setNext(thisTemp.next);
                thisTemp.setNext(otherTemp);

                otherTemp = cur2.next;
                cur1.setNext(otherTemp);
                thisTemp.setPrev(cur2);
                cur2.setNext(thisTemp);
                otherTemp.setPrev(cur1);
                cur1 = cur1.next.next;
                cur2 = cur2.next.next;

            }

        }
        */
	}
	
	/* Purpose: return a string representation of the list 
	 *          when traversed from front to back
	 * Parameters: none
	 * Returns: nothing
	 */
	public String frontToBack() {
		String result = "{";
		A3Node cur = head;
		while (cur != null) {
			result += cur.getData();
			cur = cur.next;
		}
		result += "}";
		return result;
	}
	
	/* Purpose: return a string representation of the list 
	 *          when traversed from back to front
	 * Parameters: none
	 * Returns: nothing
	 */
	public String backToFront() {
		String result = "{";
		A3Node cur = tail;
		while (cur != null) {
			result += cur.getData();
			cur = cur.prev;
		}
		result += "}";
		return result;
	}
}
