import java.lang.Math;
import java.util.LinkedList;
/*
Nick Wurzer 
V009508568
 * RefBasedBinaryTree.java
 *
 * A ref-based BinaryTree meant to store values of type Integer
 */
public class RefBasedBinaryTree implements BinaryTree {
    protected TreeNode root;
    
    public RefBasedBinaryTree() {
        this.root = null;
    }
    
    public void insert(Integer value){
        if (root==null)
            root = new TreeNode(value);
        else
            insert(null, root, value);
        
    }
    // not a balanced insert
    private void insert(TreeNode parent, TreeNode t, Integer value) {
        if (t==null) {
            if(parent.getLeft()==null) {
                parent.setLeft(new TreeNode(value));
			} else {
                parent.setRight(new TreeNode(value));
			}            
        }  else {
            int htLeft = height(t.getLeft());
            int htRight = height(t.getRight());
            if (htLeft>htRight) {
                insert(t, t.getRight(), value);
			} else {
                insert(t, t.getLeft(), value);
			}
        }
    }
    
    private int height(TreeNode t) {
        if (t==null) {
            return -1;
		} else {
            int highest = Math.max(height(t.getLeft()), height(t.getRight()));
            return 1 + highest;
        }
    }
    
    /*
     * Purpose: prints the value at each TreeNode in this BinaryTree
     *          following an in-order traversal
     * Parameters: none
     * Returns: Nothing
     */
    public void inOrder(){
        inOrder(root);
        System.out.println();
    }
    
    /*
     * Purpose: prints the value at each TreeNode in t,
     *          following an in-order traversal
     * Parameters: TreeNode t
     * Returns: Nothing
     */
    private void inOrder(TreeNode t){
        if (t==null)
            return;
        else {
            inOrder(t.getLeft());
            System.out.print(t.getValue() + " ");
            inOrder(t.getRight());
        }
    }
    
    /*
     * Purpose: prints the value at each TreeNode in this BinaryTree
     *          following a pre-order traversal
     * Parameters: none
     * Returns: Nothing
     */
    public void preOrder(){
        preOrder(root);
        System.out.println();
    }
    
    /*
     * Purpose: prints the value at each TreeNode in t,
     *          following a pre-order traversal
     * Parameters: TreeNode t
     * Returns: Nothing
     */
    private void preOrder(TreeNode t){
        if (t==null)
            return;
        else {
            System.out.print(t.getValue() + " ");
            preOrder(t.getLeft());
            preOrder(t.getRight());
        }
    }
    
    /*
     * Purpose: prints the value at each TreeNode in this BinaryTree
     *          following a post-order traversal
     * Parameters: none
     * Returns: Nothing
     */
    public void postOrder(){
        postOrder(root);
        System.out.println();
    }
    
    /*
     * Purpose: prints the value at each TreeNode in t,
     *          following a post-order traversal
     * Parameters: TreeNode t
     * Returns: Nothing
     */
    private void postOrder(TreeNode t){
        if (t==null)
            return;
        else {
            postOrder(t.getLeft());
            postOrder(t.getRight());
            System.out.print(t.getValue() + " ");
        }
    }
    
    /*
     * Purpose: returns a String reprensentation of this BinaryTree
     * Parameters: none
     * Returns: String - the representation
     */
    public String toString() {
        return toString(root);
    }
    
    private String toString(TreeNode t) {
        if(t==null)
            return "";
        else {
            String s = "";
            
            s += toString(t.getLeft());
            s += t.getValue() + " ";
            s += toString(t.getRight());
            
            return s;
        }
    }
    public int sum(){
        if(root == null){
            return 0;
        }
        else{
            return sum(this.root,this.root);
        }
    }
    public int sum(TreeNode parent, TreeNode t){
        if (t.getLeft() == null  && t.getRight() == null)
            return t.getValue();
        else if(t.getLeft() == null  && t.getRight() != null) {
            return t.getValue() +sum(t,t.getRight());
        }
        else if(t.getLeft() != null  && t.getRight() == null) {
            return t.getValue() + sum(t,t.getLeft());
        }
        else{
            return t.getValue() + (sum(t,t.getLeft()) + sum(t,t.getRight()));
        }
    }

    /*Will not finish in time.
    sum() will not be overridden.
    find() should be overridden because it will be more efficient in a binarySearchTree.
    getMax() will also be overridden for the sake of efficiency.
    levelOrder() will not need to be overridden, it will not be more or less efficient in a binary search tree.
    */
    
    public static void main(String[] args) {
        // use these trees to test the methods you will implement in Part II
        RefBasedBinaryTree emptyTree = new RefBasedBinaryTree();
        RefBasedBinaryTree myTree = new RefBasedBinaryTree();
        for(int i=2; i<8; i++) {
            System.out.println(myTree.sum());
            myTree.insert(i);
        }
        System.out.println(myTree.sum());
    }
    
}
