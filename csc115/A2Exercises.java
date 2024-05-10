public class A2Exercises {

	/*
	 * Purpose: Add e to the given array of events
	 * Parameters: Event[] array - the array to add the event to
	 * 			   Event e - the event to add to the array
	 * Returns: Event[] - a new array containing all of the events
	 *                    found in arr plus event e
	 */
	public static Event[] addEvent(Event[] arr, Event e) {
		Event[] result = new Event[arr.length+1];
		for(int i = 0; i < arr.length; i++){
			result[i] = arr[i];
		}
		result[arr.length] = e;
        return result;
	}
	
	/*
	 * Purpose: get the sum of all invites to all events in the array
	 * Parameters: Event[] array
	 * Returns: int - the total number of invites to all events in the array
	 */
	public static int totalInvites(Event[] array) {
		int result = 0;
		for(int i = 0; i < array.length; i++){
			result += array[i].getNumInvites();
		}
		return result;
	}
	
	/*
	 * Purpose: get the number of occasions that occur in 
	 *          the given month number found in the array
	 * Parameters: Event[] arr - the array of events
	 *             int - the month number (1:January, 2:February, etc)
	 * Returns: int - the number of events that occur in the given month
	 */
	public static int eventsInMonth(Event[] arr, int monthNumber) {
		int result = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i].getDate().getMonth() == monthNumber){
				result++;
			}
		}
		return result;
	}
	
	/*
	 * Purpose: Return the event with the date found in the array 
	 *          that occurs closest to the given date d
	 * Parameters Event[] arr - the array to search through
	 *            Date d - the date to compare the event dates with
	 * Returns: Event - the Event object found in the array that occurs 
	 *          closest to the date of d
	 * Pre-conditions: arr is not empty
	 */
	public static Event closestToDate(Event[] arr, Date d) {
		Event closest = arr[0];
		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i].getDate().daysUntil(d));
			if(arr[i].getDate().daysUntil(d) < closest.getDate().daysUntil(d)){
				closest = arr[i];
				System.out.println("updated");
			}
		}
		return closest;
	}
}