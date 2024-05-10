/*
 * Lab6Tester.java
 */
public class Lab6Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;

    public static void main (String[] args) {
        
        try {
            testBasicStack();
            testStackUseFunctions();
            testQueue();
            
        } catch (Exception e) {
            
            System.out.println("Your code threw an Exception.");
            System.out.println("Perhaps a stack trace will help:");
            e.printStackTrace(System.out);
        }
        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }
    
    /*
     * Purpose: reverses str following this algorithm
     *  - pushes each character in str onto a new StackArrayBased,
     *  - creates a new empty result String
     *  - pops each character off the Stack adding it to the result String
     *  - returns the result String
     * Parameters: String str - the String to reverse
     * Returns: String - the reversed result String
     */
    public static String reverseString(String str) {
		Stack stk = new StackArrayBased();
		for(int i = 0; i <str.length(); i++){
			stk.push(str.charAt(i));
		}
		String result = "";
		for(int i = 0; i <str.length(); i++){
			result = result + stk.pop();
		}
        return result;
    }
    
    /*
     * Purpose: determines whether every '(' in str is matched with a ')'
     * Parameters: String str - the String to check for a bracket match
     * Returns: boolean - true if brackets are matched, false otherwise
     */
    public static boolean doBracketsMatch(String str) {
		Stack stk = new StackArrayBased();
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == ')'  && !stk.isEmpty()){
				stk.pop();
			}
			else if(str.charAt(i) == '('){
				stk.push(str.charAt(i));
			}
			else if(str.charAt(i) == ')'  && stk.isEmpty()){
				return false;
			}
		}
		if(stk.isEmpty()){
			return true;
		}
        return false;
	}
	
    
    public static void testBasicStack() {
		System.out.println("testBasicStack: start");

		StackArrayBased s = new StackArrayBased();
		int subtestSize = 10;
		boolean subtestResult = false;
		char result;

		displayResults(s.isEmpty(), "isEmpty on empty stack");
		displayResults(s.size() == 0, "size on empty stack");
		s.push('a');
		displayResults(!s.isEmpty(), "isEmpty - stack with one element");
		displayResults(s.size() == 1, "size - stack with one element");

		result = s.top();
		displayResults(result=='a', "top of stack");
		result = s.pop();
		displayResults(result=='a', "popped from stack");
		displayResults(s.isEmpty(), "isEmpty on empty stack");
		displayResults(s.size() == 0, "size on empty stack");

		subtestSize = 10;
		for (int i = 0; i < subtestSize; i++) {
			s.push((char)(i+'0'));
		}
		displayResults(!s.isEmpty(), "isEmpty - stack with multiple elements");
		displayResults(s.size() == subtestSize, "size - stack with multiple elements");

		subtestResult = true;
		for (int i = subtestSize - 1; i >= 0; i--) {
			char ii = s.pop();
			subtestResult = subtestResult && (ii == (char)(i+'0'));
		}
		displayResults(subtestResult, "pop - stack with multiple elements");
		displayResults(s.isEmpty(), "isEmpty - after pop");
		displayResults(s.size() == 0, "size - after pop");

		s.push('a');
		s.push('b');
		s.push('c');
		displayResults(!s.isEmpty(), "isEmpty - after 3 pushes");
		displayResults(s.size() == 3, "size - after 3 pushes");
		s.makeEmpty();
		displayResults(s.isEmpty(), "isEmpty - after makeEmpty");
		displayResults(s.size() == 0, "size - after makeEmpty");

		System.out.println("testBasicStack: end");
		System.out.println();
    }
    
    public static void testStackUseFunctions() {
		System.out.println("testStackUseFunctions: start");
		String resultString = "";
		String expected = "";

		resultString = reverseString("");
		expected = "";
		displayResults(resultString.equals(expected), "reverse empty string");
		resultString = reverseString("doG");
		expected = "God";
		displayResults(resultString.equals(expected), "reverse doG ");
		resultString = reverseString("I");
		expected = "I";
		displayResults(resultString.equals(expected), "reverse I");
		resultString = reverseString("evol");
		expected = "love";
		displayResults(resultString.equals(expected), "reverse evol");
		resultString = reverseString("!gnidoc");
		expected = "coding!";
		displayResults(resultString.equals(expected), "reverse !gnidoc");

		

		boolean matched;
		matched = doBracketsMatch("");
		displayResults(matched, "doBracketsMatch empty String");
		
		matched = doBracketsMatch("(a)");
		displayResults(matched, "doBracketsMatch (a)");

		matched = doBracketsMatch("(a");
		displayResults(!matched, "doBracketsMatch (a");

		matched = doBracketsMatch("a)");
		displayResults(!matched, "doBracketsMatch (a)");

		matched = doBracketsMatch("(a))");
		displayResults(!matched, "doBracketsMatch (a))");

		matched = doBracketsMatch("((a)");
		displayResults(!matched, "doBracketsMatch ((a)");

		matched = doBracketsMatch("(a(b)())");
		displayResults(matched, "doBracketsMatch (a(b)())");

		matched = doBracketsMatch("(");
		displayResults(!matched, "doBracketsMatch (");

		matched = doBracketsMatch(")");
		displayResults(!matched, "doBracketsMatch )");

		// Add more tests here. Think about the different types of ways
		// one could call the method, and make sure you write tests 
		// to cover each possibility.
		
		System.out.println("testStackUseFunctions: end");
		System.out.println();
    }
    
    public static void testQueue() {
		System.out.println("testBasicQueue: start");

		Queue<Integer> q = new QueueRefBased<Integer>();
		int subtestSize;
		boolean subtestResult;

		displayResults(q.isEmpty(), "isEmpty on empty queue");
		displayResults(q.size() == 0, "size on empty queue");

		q.enqueue(10);
		displayResults(!q.isEmpty(), "isEmpty - queue with one element");
		displayResults(q.size() == 1, "size - queue with one element");

		Integer first = q.peek();
		displayResults(first.equals(10), "peeking at item at front of queue");
		first = q.dequeue();
		displayResults(first.equals(10), "dequeuing item from front of queue");
		displayResults(q.isEmpty(), "isEmpty after dequeue");
		displayResults(q.size() == 0, "size after dequeue");

		subtestSize = 10;
		for (int i = 0; i < subtestSize; i++) {
			q.enqueue(i);
		}
		displayResults(!q.isEmpty(), "isEmpty - queue with multiple elements");
		displayResults(q.size() == subtestSize, "size - queue with multiple elements");

		subtestResult = true;
		for (int i = 0; i < subtestSize; i++) {
			Integer ii = q.dequeue();
			subtestResult = subtestResult && (ii.equals(i));
		}
		displayResults(subtestResult, "dequeue - queue with multiple elements");
		displayResults(q.isEmpty(), "isEmpty - after dequeue");
		displayResults(q.size() == 0, "size - after dequeue");

		Queue<String> qt = new QueueRefBased<String>();
		String s1 = "What ";
		String s2 = "a ";
		String s3 = "wonderful ";
		String s4 = "code ";
		String s5 = "you ";
		String s6 = "have!";

		qt.enqueue(s1);
		displayResults(qt.dequeue().equals("What "), "dequeue What");
		displayResults(qt.isEmpty(), "not empty)");
		qt.enqueue(s1);
		qt.enqueue(s2);
		qt.enqueue(s3);
		qt.enqueue(s4);
		qt.enqueue(s5);
		qt.enqueue(s6);

		displayResults(qt.size() == 6, "check size");

		displayResults(!qt.isEmpty(), "not empty)");

		System.out.println(qt.dequeue() + qt.dequeue() + qt.dequeue() + qt.dequeue() + qt.dequeue() + qt.dequeue());

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
