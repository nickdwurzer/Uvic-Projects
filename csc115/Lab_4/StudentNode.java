package Lab_4;

public class StudentNode{
    StudentNode next;
    private Student data;

    public StudentNode(Student student){
        this.data = student;
        this.next = null;
    }
    public StudentNode(Student student, StudentNode studentNode){
        this.data = student;
        this.next = studentNode;
    }
    public StudentNode getNext(){
        return this.next;
    }
    public void setNext(StudentNode studentNode){
        this.next = studentNode;
    }
    public Student getData(){
        return this.data;
    }
    public void setData(Student student){
        this.data = student;
    }

}