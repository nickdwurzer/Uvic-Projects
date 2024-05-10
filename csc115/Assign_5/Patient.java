/*
 * represents a hospital patient
 *
 * we have implemented some methods and constructors.
 * we have left the documentation for you to follow
 * to implement the compareTo and equals methods
 */

public class Patient implements Comparable<Patient>{

	private int priorityLevel;
	private String name;
	private String waitingNumber;
	private Time checkinTime;

	public Patient(int priorityLevel, String name, String waitingNumber, Time checkinTime) {
		this.priorityLevel = priorityLevel;
		this.name = name;
		this.waitingNumber = waitingNumber;
		this.checkinTime = checkinTime;
	}

	public int getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel (int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public String getName() {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getWaitingNumber() {
		return waitingNumber;
	}

	public void setWaitingNumber (String waitingNumber) {
		this.waitingNumber = waitingNumber;
	}

	public Time getTime() {
		return checkinTime;
	}

	public void setTime (Time checkinTime) {
		this.checkinTime = checkinTime;
	}

	public String toString() {
		return name + ":" + waitingNumber;
	}

	/* Method Name: compareTo
	 * Purpose: returns the result of comparing this priorityLevel and
	 *          other priorityLevel if they are not equal
 	 *	   if priorityLevels are equal, it returns the result of 
	 *          comparing this checkinTime to other checkinTime
	 * Parameters: Patient other
	 * Precondition: other is not null
	 * Returns: a value < 0 if this patient is before other patient
	 *          a value == 0 if this patient is the same as other patient
	 *          a value >0 if this patient is after other patient
	 * HINT: the Time class implements compareable too!
	 */
	public int compareTo(Patient other) {
		if(this.getPriorityLevel() < other.getPriorityLevel()){
			return -1;
		}
		else if (this.getPriorityLevel() == other.getPriorityLevel()){
			if(this.getTime().getHour() < other.getTime().getHour()){
				return -1;
			}
			else if (this.getTime().getHour() == other.getTime().getHour()){
				if(this.getTime().getMinute() < other.getTime().getMinute()){
					return -1;
				}
				else if(this.getTime().getMinute() == other.getTime().getMinute()){
					return 0;
				}
			}
		}
			return 1;
		
	}

	/* Method Name: equals
	 * Purpose: determines whether the waiting number of this 
	 *          patient is the same as the other patient's
	 * Parameters: Patient other
	 * Precondition: other is not null
	 * Returns: true if this Patient is the same as other, false otherwise
	 */
	public boolean equals(Patient other) {
		
		if(this.getWaitingNumber().length() != other.getWaitingNumber().length()){
			return false;
		
		}
		for(int i = 0; i < this.getWaitingNumber().length(); i++){
			if(this.getWaitingNumber().charAt(i) != other.getWaitingNumber().charAt(i))
			return false;
		}
		return true;
	}
}

