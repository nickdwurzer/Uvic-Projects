/*
 * Lab7Part3Tester.java
 */
public class Lab7Part3Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;

    public static void main (String[] args) {
        
        try {
            testQueue();
            
        } catch (Exception e) {
            
            System.out.println("Your code threw an Exception.");
            System.out.println("Perhaps a stack trace will help:");
            e.printStackTrace(System.out);
        }
        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }
    
    
    public static void testQueue() {
        System.out.println("testBasicQueue: start");
        
        Queue<Integer> q;
        int subtestSize;
        boolean subtestResult;
        
        q = new QueueRefBased<Integer>();
        displayResults(q.isEmpty(), "isEmpty on empty queue");
        displayResults(q.size() == 0, "size on empty queue");
        
        q = new QueueRefBased<Integer>();
        q.enqueue(10);
        displayResults(!q.isEmpty(), "isEmpty - queue with one element");
        displayResults(q.size() == 1, "size - queue with one element");

        q = new QueueRefBased<Integer>();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            q.enqueue(i);
        }
        displayResults(!q.isEmpty(), "isEmpty - queue with multiple elements");
        displayResults(q.size() == subtestSize, "size - queue with multiple elements");
        
        subtestResult = true;
        try{
            for (int i = 0; i < subtestSize; i++) {
            
                int ii = q.dequeue();
            
                subtestResult = subtestResult && (ii == i);
            }
        }
        catch(QueueEmptyException e){
            System.out.println(" an item could not be dequeued line 50" + e);
        }
        displayResults(subtestResult, "dequeue - queue with multiple elements");
        displayResults(q.isEmpty(), "isEmpty - after dequeue");
        displayResults(q.size() == 0, "size - after dequeue");

    

        q = new QueueRefBased<Integer>();
        q.enqueue(10);
        try{
            q.peek();
        }
        catch(QueueEmptyException e){
            System.out.println("could not peek line 69" + e);
        }
        displayResults( !q.isEmpty(), "enqueue/dequeue + isEmpty - queue with one elements");
        displayResults(q.size() == 1, "enqueue/dequeue + size - queue with one elements");

    

        q = new QueueRefBased<Integer>();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            q.enqueue(i);
        }
        displayResults(!q.isEmpty(), "enqueue + isEmpty");
        displayResults(q.size() == subtestSize, "enqueue + size");
        subtestResult = true;
        try{
            for (int i = 0; i<subtestSize; i++) {
                int ii = q.peek();
                subtestResult = subtestResult && (ii == 0);
            }
        }
        catch(QueueEmptyException e){
            System.out.println("could not peek line 87" + e);
        }
        displayResults(subtestResult, "enqueue + peek");
        displayResults(!q.isEmpty(), "enqueue + peek + isEmpty");
        displayResults(q.size() == subtestSize, "enqueue + peek + size");

        
        q = new QueueRefBased<Integer>();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            q.enqueue(i);
        }
        q.makeEmpty();
        displayResults( q.isEmpty(), "makeEmpty + isEmpty - queue with multiple elements");
        
        q = new QueueRefBased<Integer>();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            q.enqueue(i);
        }
        q.makeEmpty();
        for (int i = 0; i < subtestSize; i++) {
            q.enqueue(i);
        }
        displayResults(!q.isEmpty(), "makeEmpty + enqueue + isEmpty - queue with multiple elements");
        displayResults(q.size() == subtestSize, "makeEmpty + enqueue + size - queue with multiple elements");
        

        q = new QueueRefBased<Integer>();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            q.enqueue(i);
        }
        q.makeEmpty();
        for (int i = 0; i < subtestSize; i++) {
            q.enqueue(i);
        }
        try{
            displayResults(q.peek() == 0, "makeEmpty + enqueue + peek - queue with multiple elements");
        }
        catch(QueueEmptyException e){
            System.out.println("could not peek, line 132" + e);
        }
        try{
            q.dequeue();
        }
        catch(QueueEmptyException e){
            System.out.println("Could not dequeue, line 138" + e);
        }
        try{
            displayResults(q.peek() == 1, "makeEmpty + enqueue + dequeue + peek - queue with multiple elements");
        }
        catch(QueueEmptyException e){
            System.out.println("could not peek, line 144" + e);
        }


        //Write code to test that exception is thrown when it should be
        
        try {
            q = new QueueRefBased<Integer>();
            q.dequeue();
            displayResults(false, "exception thrown when it should not be");
        }  
        catch (QueueEmptyException qee) {
            // we should get here - test passes
            displayResults(true, "exception thrown when it should be");
        }
        try {
            q = new QueueRefBased<Integer>();
            q.peek();
            displayResults(false, "exception thrown when it should not be");
        }  
        catch (QueueEmptyException qee) {
            // we should get here - test passes
            displayResults(true, "exception thrown when it should be");
        }
        
        System.out.println("testing Queue: end");
        System.out.println();
        
    }
    
    public static void displayResults (boolean passed, String testName) {
        /* There is some magic going on here getting the line number
         * Borrowed from:
         * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
         */
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testCount);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    
    
}
