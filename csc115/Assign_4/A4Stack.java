public class A4Stack<T> implements Stack<T> {
	
	private Node<T> head;
	private int count = 0;

	public A4Stack() {
		head = null;
	}
	
	public void push(T v) {
		Node<T> n1 = new Node<T>(v);
		n1.next = head;
		head = n1;
		count++;
	}
	
	public T pop() {
		if (isEmpty()) {
			// exception handling is done for you -- we will learn about
			// expceptions in the next unit. Don't change this.
			throw new EmptyStackException("Can't pop - stack is empty");
		}
			Node<T> temp = head;
			head = head.next;
			T tempData = temp.getData();
			temp.next = null;
			temp = null;
			count--;

		return tempData;
	}
	
	public void popAll() {
		Node<T> n = head;
		while(n.next != null){
			Node<T> temp = n;
			n = n.next;
			temp.next = null;
		}
		n.next = null;
		n = null;
		count = 0;
	}
	
	public boolean isEmpty() {
		if(count == 0){
			return true;
		}
		return false;
	}
	
	public T top() {
		if (isEmpty()) {
			// exception handling is done for you -- we will learn about
			// expcetions in the next unit. Don't change this.
			throw new EmptyStackException("Can't pop - stack is empty");
		}
		T tempData = head.getData();
		return tempData;
	}
}