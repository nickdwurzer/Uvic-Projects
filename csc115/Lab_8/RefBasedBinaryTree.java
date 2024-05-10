import java.lang.Math;
/*
 * RefBasedBinaryTree.java
 *
 * A ref-based BinaryTree meant to store values of type Integer
 * Nick Wurzer
 * V00958568
 */
public class RefBasedBinaryTree implements BinaryTree {
    protected TreeNode root;

    public RefBasedBinaryTree() {
        root = null;
    }

    public void insert(Integer value) {
        insert(null, root, value);
    }

    /*
     * Purpose: recursively determines the shortest path (root to leaf) inserts a
     * new TreeNode with given value at that leaf Parameters: TreeNode parent - the
     * parent to t TreeNode t - the current TreeNode in recursive traversal Integer
     * value - the value to be inserted Returns: nothing
     */
    private void insert(TreeNode parent, TreeNode t, Integer value) {
        if (t == null) {
            if (parent == null)
                root = new TreeNode(value);
            else if (parent.getLeft() == null)
                parent.setLeft(new TreeNode(value));
            else
                parent.setRight(new TreeNode(value));

        } else {
            int htLeft = height(t.getLeft());
            int htRight = height(t.getRight());
            if (htLeft > htRight)
                insert(t, t.getRight(), value);
            else
                insert(t, t.getLeft(), value);
        }
    }

    /*
     * Purpose: computes and returns the height of BinaryTree t NOTE a BinaryTree
     * with no node is height 0 Parameters: TreeNode t - the BinaryTree Returns: int
     * - the height
     */
    private int height(TreeNode t) {
        if (t == null) {
            return 0;
        } else if (height(t.getLeft()) == height(t.getRight())) {
            return 1 + height(t.getLeft());
        } else {
            return 1 + height(t.getRight());
        }
    }

    public void inOrder() {
        String result = inOrderHelper(root);
        String finalResult = result.substring(0,1);
        for(int i = 1; i < result.length();i++){
            finalResult = finalResult + " " + result.substring(i,i+1);
        }
        System.out.println(finalResult);
    }

    private String inOrderHelper(TreeNode node) {
        if (node == null) {
            return "";
        }
        else{
            return inOrderHelper(node.getLeft()) + node.getValue() + inOrderHelper(node.getRight());
        }
    }

    public void preOrder() {
        String result = preOrderHelper(root);
        String finalResult = result.substring(0,1);
        for(int i = 1; i < result.length();i++){
            finalResult = finalResult + " " + result.substring(i,i+1);
        }
        System.out.println(finalResult);
    }

    private String preOrderHelper(TreeNode node) {
        if (node == null) {
            return "";
        }
        else{
            return node.getValue() + preOrderHelper(node.getLeft())+ preOrderHelper(node.getRight());
        }
    }

    public void postOrder() {
        String result = postOrderHelper(root);
        String finalResult = result.substring(0,1);
        for(int i = 1; i < result.length();i++){
            finalResult = finalResult + " " + result.substring(i,i+1);
        }
        System.out.println(finalResult);
    }

    private String postOrderHelper(TreeNode node) {
        if (node == null) {
            return "";
        }
        else{
            return postOrderHelper(node.getLeft())+ postOrderHelper(node.getRight())+ node.getValue();
        }
    }

    public String toString() {
        String result = inOrderHelper(root);
        String finalResult = result.substring(0,1);
        for(int i = 1; i < result.length();i++){
            finalResult = finalResult + " " + result.substring(i,i+1);
        }
        return finalResult;
    }

    // provided for testing of RefBasedBinaryTree class
    public static void main(String[] args) {
        
        RefBasedBinaryTree myTree = new RefBasedBinaryTree();
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
