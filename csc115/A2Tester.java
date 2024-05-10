public class A2Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;

	public static void main(String[] args) {

		// Uncomment and test one method at a time. 
		// Write additional tests as you see fit.

		/* Part 1: The Date class */
		testDateConstructor();
		testEquals();
		testNextDay();
		testDaysUntil();
		
		/* Part 2: The A2Exercises class */
		testAddEvent();
		testTotalInvites();
		testEventsInMonth();
		testClosestToDate();
		
		System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
	}
	
	public static void testDateConstructor() {
		Date d1 = new Date(4, 16);
		Date d2 = new Date(12, 22);
		int result = 0;
		int expected = 0;
		
		result = d1.getMonth();
		expected = 4;
		displayResults("Date constructor month set", expected==result);
		
		result = d1.getDay();
		expected = 16;
		displayResults("Date constructor day set", expected==result);
		
		result = d2.getMonth();
		expected = 12;
		displayResults("Date constructor month set", expected==result);
		
		result = d2.getDay();
		expected = 22;
		displayResults("Date constructor day set", expected==result);
	}
	
	public static void testEquals() {
		Date d1 = new Date(4, 16);
		Date d2 = new Date(12, 22);
		Date d3 = new Date(4, 22);
		Date d4 = new Date(4, 16);
		Date d5 = new Date(12, 22);
		
		boolean result = false;
		
		result = d1.equals(d2);
		displayResults("d1 equals d2", result==false);
		result = d1.equals(d3);
		displayResults("d1 equals d3", result==false);
		result = d2.equals(d3);
		displayResults("d2 equals d3", result==false);
		result = d1.equals(d4);
		displayResults("d1 equals d4", result==true);
		result = d2.equals(d5);
		displayResults("d2 equals d5", result==true);
		result = d4.equals(d5);
		displayResults("d4 equals d5", result==false);
	
	}
	
	public static void testNextDay() {
		Date d1 = new Date(6, 22);
		Date d2 = new Date(8, 31);
		
		Date afterD1 = new Date(6, 23);
		Date afterD2 = new Date(9, 1);
		
		displayResults("d1 before next day", !d1.equals(afterD1));
		displayResults("d2 before next day", !d2.equals(afterD2));
		
		d1.nextDay();
		displayResults("d1 after next day", d1.equals(afterD1));
		
		d2.nextDay();
        displayResults("d2 after next day", d2.equals(afterD2));
        
		afterD1.setDay(28);
        d1.nextDay();
        d1.nextDay();
        d1.nextDay();
        d1.nextDay();
        d1.nextDay();
        displayResults("d1 after next day", d1.equals(afterD1));
	}
	
	public static void testDaysUntil() {
		Date d1 = new Date(1, 20);
		Date d2 = new Date(1, 23);
        Date d3 = new Date(1, 18);
        Date d4 = new Date(6, 20);
		Date d5 = new Date(1, 1);
		int result = 0;
		int expected = 0;
		
		result = d1.daysUntil(d2);
		expected = 3;
		displayResults("d1 daysUntil d2", result==expected);
        result = d1.daysUntil(d3);
		expected = 363;
        displayResults("d1 daysUntil d3", result==expected);
        result = d5.daysUntil(d4);
		expected = 170;
		displayResults("d5 daysUntil d4", result==expected);
		
	}
	
	public static void testAddEvent() {
		Date d1 = new Date(7, 21);
		Date d2 = new Date(4, 16);
		Date d3 = new Date(10, 12);
		Date d4 = new Date(12, 31);
		
		Event e1 = new Event("Ali's Birthday", d1, 20);
		Event e2 = new Event("End of exam party", d2, 125);
		Event e3 = new Event("Thanksgiving dinner", d3, 12);
		Event e4 = new Event("New Years Eve", d4, 45);
		
		Event[] arr0 = {};
		Event[] arr1 = {e3};
		Event[] arr2 = {e3, e1};
		Event[] arr3 = {e3, e1, e2, e4};
	
		boolean arraysEqual = false;
		Event[] result = null;

		arraysEqual = arrayEquals(arr0, arr1);
		displayResults("arr0 equals arr1 before add", arraysEqual==false);
		
		result = A2Exercises.addEvent(arr0, e3);
		arraysEqual = arrayEquals(result, arr1);
		displayResults("arr0 equals arr1 after 1 add", arraysEqual==true);
		
		result = A2Exercises.addEvent(result, e1);
		arraysEqual = arrayEquals(result, arr2);
		displayResults("arr0 equals arr2 after 2 adds", arraysEqual==true);
		arraysEqual = arrayEquals(arr1, arr2);
		displayResults("arr2 equals arr2 after 1 add", arraysEqual==false);
		
		result = A2Exercises.addEvent(result, e2);
		result = A2Exercises.addEvent(result, e4);
		arraysEqual = arrayEquals(result, arr3);
		displayResults("arr0 equals arr3 after 3 add", arraysEqual==true);
	}
	
	public static boolean arrayEquals(Event[] arr1, Event[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (!arr1[i].equals(arr2[i])) {
				return false;
			}
		}
		return true;
	}
	
	public static void testTotalInvites() {
		Event e1 = new Event("Ali's Birthday", new Date(7, 21), 20);
		Event e2 = new Event("End of exam party", new Date(4, 16), 125);
		Event e3 = new Event("Thanksgiving dinner", new Date(10, 12), 12);
		Event e4 = new Event("New Years Eve", new Date(12, 31), 45);
		
		Event[] arr1 = {};
		Event[] arr2 = {e3, e1};
		Event[] arr3 = {e3, e1, e2, e4};
		
		int result = 0;
		int expected = 0;
		
		result = A2Exercises.totalInvites(arr1);
		displayResults("total invites in arr1", result==expected);
		
		result = A2Exercises.totalInvites(arr2);
		expected = 12+20;
		displayResults("total invites in arr2", result==expected);
		
		result = A2Exercises.totalInvites(arr3);
		expected = 12+20+125+45;
		displayResults("total invites in arr3", result==expected);
	}
	
	public static void testEventsInMonth() {
		Event e1 = new Event("Sam's Birthday", new Date(10, 24), 20);
		Event e2 = new Event("End of exam party", new Date(4, 16), 125);
		Event e3 = new Event("Thanksgiving dinner", new Date(10, 12), 12);
		Event e4 = new Event("New Years Eve", new Date(12, 31), 45);
		Event e5 = new Event("Halloween", new Date(10, 31), 80);
		
		Event[] arr1 = {};
		Event[] arr2 = {e1};
		Event[] arr3 = {e1, e2, e3, e4, e5};
		
		int result = 0;
		int expected = 0;
		
		result = A2Exercises.eventsInMonth(arr1, 10);
		displayResults("eventsInMonth(arr1, 10)", result==expected);
		
		result = A2Exercises.eventsInMonth(arr2, 10);
		expected = 1;
		displayResults("eventsInMonth(arr2, 10)", result==expected);
		
		result = A2Exercises.eventsInMonth(arr2, 4);
		expected = 0;
		displayResults("eventsInMonth(arr2, 4)", result==expected);
		
		result = A2Exercises.eventsInMonth(arr3, 4);
		expected = 1;
		displayResults("eventsInMonth(arr3, 4)", result==expected);
		
		result = A2Exercises.eventsInMonth(arr3, 10);
		expected = 3;
		displayResults("eventsInMonth(arr3, 10)", result==expected);
		
		result = A2Exercises.eventsInMonth(arr3, 11);
		expected = 0;
		displayResults("eventsInMonth(arr3, 11)", result==expected);
	}
	
	public static void testClosestToDate() {
		Event e1 = new Event("Sam's Birthday", new Date(10, 24), 20);
		Event e2 = new Event("End of exam party", new Date(4, 16), 125);
		Event e3 = new Event("Thanksgiving dinner", new Date(10, 12), 12);
		Event e4 = new Event("New Years Eve", new Date(12, 31), 45);
		Event e5 = new Event("Halloween", new Date(10, 31), 80);
			
		Event[] arr1 = {e1};
		Event[] arr2 = {e1, e2, e3, e4, e5};
		
		Event result = null;
		Event expected = null;
		
		Date target = new Date(10, 4);
		
		result = A2Exercises.closestToDate(arr1, target);
		expected = e1;
		displayResults("closest date in arr1 to Oct 4", result.equals(expected));
		
		result = A2Exercises.closestToDate(arr2, target);
		expected = e2;
		displayResults("closest date in arr2 to Oct 4", result.equals(expected));
		
		
		
	}
	
	
	public static void displayResults (String testName, boolean passed) {
		/* There is some magic going on here getting the line number
		* Borrowed from:
		* http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
		*/

		testCount++;
		if (passed) {
			System.out.println ("Passed test: " + testName);
			testPassCount++;
		} else {
			System.out.println ("Failed test: " + testName + " at line "
									+ Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}    
}