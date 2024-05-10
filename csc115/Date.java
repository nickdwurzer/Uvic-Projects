public class Date {
	private int month;
	private int day;

	public Date() {
		month = 1;
		day = 1;   
	}

	public Date(int month, int day) {
		this.month = month;
		this.day = day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDay() {
		return day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getMonth() {
		return month;
	}
	
	/*
	 * Purpose: get the number of days in this date's month
	 * Parameters: none
	 * Returns: int - the number of days in the current month
	 */
	public int daysInMonth() {
		// You should not modify this method in any way
		if (this.month == 2) {
			return 28;
		} else if (this.month == 4 || this.month == 6 || 
					this.month == 9 || this.month == 11) {
			return 30;
		}
		return 31;
	}

	/*
	 * Purpose: return the string representation for this date
	 * Parameters: none
	 * Returns: String - the string representation
	 */
	public String toString() {
		return this.month + "/" + this.day;
	}


	/*
	 * Purpose: determine if the current date is equal to other
	 * Parameters: Date other - the other date to compare to
	 * Returns: boolean - true if they are the same date, false otherwise
	 */
	public boolean equals (Date other) {
		if(this.day == other.getDay() && this.month == other.getMonth()){
            return true;
        }
        else{
		    return false;
        }
    }
	
	
	/*
	 * Purpose: changes this date to the next day
	 * Parameters: none
	 * Returns: void - nothing
	 */
	public void nextDay() {
		if(this.day >= this.daysInMonth()){
            this.month++;
            this.day = 1;
        }
        else{
            this.day++;
        }
	}


	/*
	 * Purpose: get the number of days it will take to reach the given date
	 * Parameters: Date other - the target date
	 * Returns: int - the number of days from this date to other
	 */
	public int daysUntil(Date other) {
        boolean done = false;
        boolean isFirstIteration = true;
        int month = this.month;
        int count = 0;

        while(!done){
            if(!isFirstIteration && (this.month == other.getMonth())){
                count += other.getDay();
                done = true;
            }
            else if(((this.day < other.getDay())) && (this.month == other.getMonth())){
                count += (other.getDay() - this.day);
                done = true;
            }
            else if(month == this.month){
                count += (this.daysInMonth() - this.day);
                if(this.month < 12){
					this.setMonth(this.month + 1);
					isFirstIteration = false;
                }
                else{
                    this.setMonth(1);
                    
                }
            }
            else{
                count += this.daysInMonth();
                if(this.month < 12){
					this.setMonth(this.month + 1);
					isFirstIteration = false;
                }
                else{
                    this.setMonth(1);
                    
                }
            }
        }
        this.setMonth(month);
		return count;
	}
	
	
    
}