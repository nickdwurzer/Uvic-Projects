package Lab_4;

//Lab4Tester.java
/*
 * A tester for the methods in Rectangle and ShapeList
 *
 */
public class Lab4Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;
    
    
    // for approximate comparison of floating point numbers
    private static final double THRESHOLD = 0.01;
    
    public static void main(String[] args) {
        
        testNode();
        testList();

        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }
    
    public static void testNode() {
        Student s0  = new Student("abc", 50);
        Student s1  = new Student("def", 56);
        Student s2  = new Student("xyz", 99);
        Student s2b  = new Student("xyz", 29);
        
        StudentNode n0 = new StudentNode(s0);
        // must use == to determine whether they are the SAME object,
        // .equals will tell us if they are equivalent sIDs
        displayResults(n0.getData() == s0, "test contructor 1 arg with getData");
        displayResults(n0.getNext() == null, "test contructor 1 arg with getNext");
        
        StudentNode n1 = new StudentNode(s1, n0);
        displayResults(n1.getData() == s1, "test contructor 2 args with getData");
        displayResults(n1.getNext() == n0, "test contructor 2 args with getNext");
        
        StudentNode n2 = new StudentNode(s2, n1);
        displayResults(n2.getData() == s2, "test contructor 2 args with getData");
        displayResults(n2.getNext() == n1, "test contructor 2 args with getNext");
        displayResults(n2.getNext().getNext() == n0, "test contructor 2 args with getNext");
        
        
        n2.setData(s2b);
        displayResults(n2.getData() == s2b, "test setData with getData");
        n2.setData(s1);
        displayResults(n2.getData() == s1, "test setData with getData");
        
        n2.setNext(n0);
        displayResults(n2.getNext() == n0, "test setNext with getNext");
        displayResults(n2.getNext().getNext() == null, "test setNext with getNext");
        
        
    }
    
    public static void testList() {
        Student s0  = new Student("abc", 50);
        Student s1  = new Student("def", 56);
        Student s2  = new Student("xyz", 99);
        Student s2b  = new Student("xyz", 29);
        
        //StudentNode n0 = new StudentNode(s0);
        //StudentNode n1 = new StudentNode(s1, n0);
        //StudentNode n2 = new StudentNode(s2, n1);

        StudentLinkedList myList = new StudentLinkedList();

        myList.add(s0);
        myList.add(s1);
        myList.add(s2);
        myList.add(s2b);

        displayResults(myList.size() == 4, "check size after add");
        displayResults(myList.contains(s0), "contains s0");
        
        System.out.println(myList.toString());

        myList.removeFront();
        displayResults(myList.size() == 3, "check size after remove");
        displayResults(myList.contains(s2b) == false, "contains s0");

        System.out.println(myList.toString());
        
    }

    
    public static void displayResults (boolean passed, String testName)
    {
        /* There is some magic going on here getting the line number
         * Borrowed from:
         * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
         */
        
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testName);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        
    }
    

    
    
}