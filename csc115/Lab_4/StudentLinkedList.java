package Lab_4;
public class StudentLinkedList implements StudentList{

    private int count = 0;
    private StudentNode head;
    //private StudentNode temp;

    public StudentLinkedList(){
        this.count = 0;
        this.head = null;
    }
    @Override
    public void add(Student s) {
        StudentNode temp;
        temp = new StudentNode(s);
        temp.next = this.head;
        this.head = temp;
        this.count++;

    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void removeFront() {
        StudentNode temp;
        temp = this.head.next;
        head.setData(null);
        head.next = null;
        this.head = temp;
        this.count--;
    }

    @Override
    public boolean contains(Student s) {
        StudentNode cur = head;
        for(int i = 0; i < count; i++){
            if (cur.getData() == s){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString(){
        String s = "";
        StudentNode cur = head;
        for(int i = 0; i < count; i++){
            if(i < (count - 1)){
                s += cur.getData().toString() + "\n";
                
            }
            else{
                s += cur.getData().toString();
            }
            cur = cur.next;
        }
        return s;
    }

}