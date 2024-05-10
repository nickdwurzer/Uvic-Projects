/*
 * ArrayBasedBinaryTree.java
 *
 * An array-based BinaryTree meant to store values of type Integer
 */
public class ArrayBasedBinaryTree {
    private static final int CAPACITY = 100;
    protected Integer[] data;
    protected int root;
    protected int size;
    
    public ArrayBasedBinaryTree() {
        size = 0;
        root = 1;
        data = new Integer[CAPACITY];
    }
    
    /*
     * Purpose: inserts the given value into data at next available
     *  position in a level-order traversal
     *  The tree remains complete after insertion.
     * Parameters: Integer value - value to insert
     * Returns: nothing
     */
    public void insert(Integer value) {
        /*int i = 0;
        while(i < size){
            i++;
            if(data[i] == null){
                data[i] = value;
                i = size;
            }
        }
        */
        data[size + 1] = value;
        size++;
    }
    
    /*
     * Purpose: calculates and returns the index of t's left child
     * Parameters: int t - index of current element in this ArrayBasedBinaryTree
     * Returns: int - index of left child
     */
    protected int getLeft(int t) {
        return (t * 2);
    }
    
    /*
     * Purpose: calculates and returns the index of t's right child
     * Parameters: int t - index of current element in this ArrayBasedBinaryTree
     * Returns: int - index of right child
     */
    protected int getRight(int t) {
        return (t * 2 + 1);
    }
    
    
    public void inOrder(){
        System.out.println(inOrderHelper(root));
    }
    
    private String inOrderHelper(int index){
        String result = "";
        if(getLeft(index) <= size  && getRight(index) <= size){
            result += inOrderHelper(getLeft(index)) + ", " + data[index] + ", " + inOrderHelper(getRight(index));
        }
        else if(getLeft(index) <= size){
            result += inOrderHelper(getLeft(index)) + ", " + data[index];
        }
        else{
            result += data[index];
        }


        return result;
    }

    public void preOrder(){
        System.out.println(preOrderHelper(root));
    }
    
    private String preOrderHelper(int index){
        String result = "";
        if(getLeft(index) <= size  && getRight(index) <= size){
            result += data[index] + ", " + preOrderHelper(getLeft(index)) + ", " + preOrderHelper(getRight(index));
        }
        else if(getLeft(index) <= size){
            result += data[index] + ", " + preOrderHelper(getLeft(index));
        }
        else{
            result += data[index];
        }


        return result;
    }
    
    public void postOrder(){
        System.out.println(postOrderHelper(root));
    }
    
    private String postOrderHelper(int index){
        String result = "";
        if(getLeft(index) <= size  && getRight(index) <= size){
            result += postOrderHelper(getLeft(index)) + ", " + postOrderHelper(getRight(index)) + ", " +  data[index];
        }
        else if(getLeft(index) <= size){
            result += postOrderHelper(getLeft(index)) + ", " + data[index];
        }
        else{
            result += data[index];
        }


        return result;
    }
    
    public String toString() {
        return this.inOrderHelper(root);
    }
    
    
    
    public static void main(String[] args) {
        
        ArrayBasedBinaryTree myTree = new ArrayBasedBinaryTree();
        for(int i=2; i<8; i++) {
            myTree.insert(i);
        }
        System.out.println("in");
        myTree.inOrder();
        System.out.println("pre");
        myTree.preOrder();
        System.out.println("post");
        myTree.postOrder();
        
        System.out.println("toString\n" + myTree);
    }
    
}
