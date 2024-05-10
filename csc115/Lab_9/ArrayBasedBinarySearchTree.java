public class ArrayBasedBinarySearchTree extends ArrayBasedBinaryTree{


    
    public static void main(String[] args) {
        ArrayBasedBinarySearchTree emptyTree = new ArrayBasedBinarySearchTree();
        
        ArrayBasedBinarySearchTree myTree = new ArrayBasedBinarySearchTree();
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

    @Override
    public void insert(Integer value){
        int index = 0;
        while(this.data[index] != null){
            if(value > this.data[index]){
                index = getRight(index);
            }
            else{
                index = getLeft(index);
            }
        }
        this.data[index] = value;
        this.size++;
        //System.out.println(size);
    }
    

}
