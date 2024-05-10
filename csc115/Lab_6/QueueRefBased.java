public class QueueRefBased<T> implements Queue<T> {
    
    private QueueNode<T> front;
    private QueueNode<T> back;
    
    public QueueRefBased() {
        front = null;
        back = null;
    }
    
    public int size() {
        int count = 0;
        QueueNode<T> n = front;
        while(n !=null){
            count++;
            n = n.next;
        }
        return count;
    }


    public boolean isEmpty() {
        if(front == null && back == null){
        return true;
        }
        return false;
    }

    @Override
    public void enqueue (T element) {
        QueueNode<T> n = new QueueNode<T>(element);
        if(front == null  && back == null){
            front = n;
            back = n;
        }
        else{
            back.setNext(n);
            back = n;
        }
    }

    public T dequeue() {
        QueueNode<T> n = front;
        front = front.next;
        if(front==null){
            back = null;
            //System.out.println("back is null");
        }
        return n.data;
    }

    public T peek() {
        
        return front.data;
    }

    public void makeEmpty() {
        QueueNode<T> n = front;
        QueueNode<T> temp;
        while(n != null){
            temp = n;
            n = n.next;
            temp.next = null;
            temp = null;
        }
    }
}

