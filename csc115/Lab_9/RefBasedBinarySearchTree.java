import java.lang.Math;
/*
Nick Wurzer 
V009508568
 * RefBasedBinarySearchTree.java
 *
 * A ref-based BinaryTree meant to store values of type Integer
 */
public class RefBasedBinarySearchTree extends RefBasedBinaryTree {


    public static void main(String[] args) {
        // use these trees to test the methods you will implement
        RefBasedBinarySearchTree emptyTree = new RefBasedBinarySearchTree();
        RefBasedBinarySearchTree myTree = new RefBasedBinarySearchTree();
        myTree.insert(2);
        myTree.insert(1);
        myTree.insert(5);
        myTree.insert(7);
        myTree.insert(0);
        myTree.insert(4);
        myTree.insert(6);
        
        System.out.println("in");
        myTree.inOrder();
        System.out.println("pre");
        myTree.preOrder();
        System.out.println("post");
        myTree.postOrder();
        
        System.out.println("toString\n" + myTree);
    }

    //iIterative
    /*
    @Override
    public void insert(Integer value){
        if(this.root == null){
            this.root = new TreeNode(value);
        }
        else{
            TreeNode currentNode = this.root;
            TreeNode nextNode = this.root;
            while(nextNode != null){
                if(value < nextNode.getValue()){
                    currentNode = nextNode;
                    nextNode = nextNode.getLeft();
                }
                else{
                    currentNode = nextNode;
                    nextNode = nextNode.getRight();
                }
            }
            if(value < currentNode.getValue()){
                currentNode.setLeft(new TreeNode(value));
            }
            else{
                currentNode.setRight(new TreeNode(value));
            }
        }
    }
    */

    //recursive
    @Override
    public void insert(Integer value){
        if(this.root == null){
            root = new TreeNode(value);
        }
        else{
            insertHelper(root, root, value);
        }
    }

    private void insertHelper(TreeNode parent, TreeNode t, Integer value){
        if(t == null){
            if(value < parent.getValue()){
                parent.setLeft(new TreeNode(value));
                return;
            }
            else{
                parent.setRight(new TreeNode(value));
                return;
            }
        }
        else{
            if(value < t.getValue()){
                insertHelper(t,t.getLeft(),value);
            }
            else{
                insertHelper(t, t.getRight(), value);
            }
        }
    }
}
