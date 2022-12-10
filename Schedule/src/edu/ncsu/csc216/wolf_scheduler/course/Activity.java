package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.Objects;

/**
 * Abstract class Activity is the parent class for Course and Event, 
 * who will both use this class.
 * 
 * @author Jay Shah (jsshah)
 *
 */
public abstract class Activity implements Conflict {

	/**
	 * Checks to see if there are overlapping times in an event or course in a schedule by comparing 
	 * its current instance with the parameter of possibleConflictingActivity()
	 * If there is a conflict then a checked exception ConflictException is thrown
	 * If there is no conflict nothing happens and the statement in the following code is executed
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		
		//First we want to iterate through the activity meeting days to make sure the class is not arranged. 
		//Then after iterating through we can use an if statement to see if the end time and start times conflict
		for (int i = 0; i < this.meetingDays.length(); i++) {
			if ("A".equals(this.getMeetingDays())) {
				break;
			}
			//we can compare the index to -1 to make sure the array is not null
			if (possibleConflictingActivity.getMeetingDays().indexOf(this.meetingDays.charAt(i)) != -1) {
				//now we can compare start and end times
				//the first one we throw is if possible start time is greater than activity start time and ends before activity end time does
				//if it was true than a conflict is certain and exception should be thrown
				if (possibleConflictingActivity.startTime >= this.startTime && possibleConflictingActivity.endTime <= this.endTime) {
					throw new ConflictException();
				}
				//next we throw a conflict exception for if the start time for the activity is greater than possible start time and 
				//if activity end before possible end time
				//this ensures commutative property
				if (this.startTime >= possibleConflictingActivity.startTime && this.endTime <= possibleConflictingActivity.endTime) {
					throw new ConflictException();
				}
				
				//We could say PCA start and end time is 1200-1300 and this.startandendtime is 1100-1230 to make sense of this if
				//statement condition
				if(possibleConflictingActivity.endTime >= this.startTime && this.endTime >= possibleConflictingActivity.startTime) {
					throw new ConflictException();
				}
				
				//We also need to ask if end time is greater than PCA start time to ensure commutative property as well as PCA end time
				//being greater than start time (which is ok on its own)
				//So for example: if PCA was 1200-1400 and activity was 1300-1430
				if(this.endTime >= possibleConflictingActivity.startTime && possibleConflictingActivity.endTime >= this.startTime) {
					throw new ConflictException();
				}
			}
		}
	}

	/** Maximum amount of hours according to military time */
	private static final int UPPER_HOUR = 23;
	/** Maximum amount of minutes in an hour */
	private static final int UPPER_MINUTE = 59;
	/** Number to divide military time by for minutes */
	static final int MILITARY_TIME_DIVIDE = 100;
	/** Minimum number that determines if military time is PM or AM */
	static final int MILITARY_PM_TIME = 12;
	/**
	 * Categorizes minute to see if additional formatting is required for the ones
	 * place
	 */
	static final int IS_SINGLE_DIGIT_DIVIDER = 10;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructor for Activity class
	 * 
	 * @param title       title of course
	 * @param meetingDays meeting days for course
	 * @param startTime   starting time
	 * @param endTime     end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Displays the short version of the information available in scheduler GUI.
	 * 
	 * @return short display array
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Displays the long version of the information available in scheduler GUI.
	 * 
	 * @return long display array
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Returns the Course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the parameter is null or empty
	 */
	public void setTitle(String title) {

		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * Returns the Course's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}
	
	/**
	 * Returns the Course's meeting days, start time, and end time
	 * 
	 * @param meetingDays days the Course will meet for class
	 * @param startTime   time the Course will start
	 * @param endTime     time the Course will end
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		}
		
		else {
			
			if(meetingDaysContainsInvalidCharacters(meetingDays)) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}	
		}
		
		int startHour = militaryTimeToHours(startTime);
		int startMinute = militaryTimeToMinutes(startTime);
		int endHour = militaryTimeToHours(endTime);
		int endMinute = militaryTimeToMinutes(endTime);
		
		if (startHour > endHour) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (startMinute < 0 || startMinute >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endMinute < 0 || endMinute >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * takes the time passed through in military and uses final variable to convert
	 * to standard time
	 * 
	 * @param time time in military time
	 * @return time hour in military time
	 */
	private int militaryTimeToHours(int time) {

		return time / MILITARY_TIME_DIVIDE;

	}

	/**
	 * takes the time passed through in military and uses final variable to convert
	 * to standard time
	 * 
	 * @param time time in military time
	 * @return time minutes in military time
	 */
	private int militaryTimeToMinutes(int time) {
		return time % MILITARY_TIME_DIVIDE;
	}

	/**
	 * Helper method to make sure meetingDays does not have invalid characters. Uses
	 * counters and compares the values to a total to ensure validity.
	 * 
	 * @param meetingDays is the meetingDays which need to be checked
	 * @return true if meetingDays has invalid characters and false if no invalid
	 *         characters are found.
	 */
	private boolean meetingDaysContainsInvalidCharacters(String meetingDays) {

		boolean containsInvalidChar = false;

		// initialize counters for course days
		int mCount = 0;
		int tCount = 0;
		int wCount = 0;
		int thCount = 0;
		int fCount = 0;
		//adding uCount because it will be needed for Event.java
		//uCount should not affect any other tests
		int uCount = 0;
		//adding in sCount for ActivityRecordIO
		int sCount = 0;

		// iterate through the meeting days input
		for (int i = 0; i < meetingDays.length(); i++) {

			char currentCharacter = meetingDays.charAt(i);

			// compare iteration of string to each character possible and increase count
			if (currentCharacter == 'M') {
				mCount++;
			} else if (currentCharacter == 'T') {
				tCount++;
			} else if (currentCharacter == 'W') {
				wCount++;
			} else if (currentCharacter == 'H') {
				thCount++;
			} else if (currentCharacter == 'F') {
				fCount++;
			} else if (currentCharacter == 'U') {
				uCount++;
			} else if (currentCharacter == 'S') {
				sCount++;
			}
			else {
				containsInvalidChar = true;
			}
		}

		// easy if statement to check if invalid characters are present
		if (mCount > 1 || tCount > 1 || wCount > 1 || thCount > 1 || fCount > 1 || uCount > 1 || sCount > 1) {
			containsInvalidChar = true;
		}
		// return boolean value back to setMeetingDaysAndTime method
		return containsInvalidChar;
	}

	/**
	 * Converts military time to standard time and gives a meridian string value
	 * 
	 * 
	 * @return meetingDaysString standard time of Course start and end with meridian
	 *         value
	 */
	public String getMeetingString() {

		// If meeting day is arranged than method returns arranged and stops
		if ("A".equals(this.meetingDays)) {
			return "Arranged";
		}

		// initialize the start time and end time to "any" meridian value
		// "any" in this case means that it does not matter if it is am or pm
		// because it will be potentially changed in the below if statements
		// for this case we are using "AM" but it could also be "PM"
		String startTimeDayValue = "AM";
		String endTimeDayValue = "AM";

		// call helper method to convert start time to standard time
		int startHour = militaryTimeToHours(this.startTime);
		int startMinute = militaryTimeToMinutes(this.startTime);

		// call helper method to convert end time to standard time
		int endHour = militaryTimeToHours(this.endTime);
		int endMinute = militaryTimeToMinutes(this.endTime);

		// create new variable where we can store standard time as a string
		String startHourString;
		String endHourString;

		// create a new variable where we can store standard time as a string
		String startMinuteString;
		String endMinuteString;

		// compares start time hour to 12 to see if it should have a PM meridian value
		if (startHour >= MILITARY_PM_TIME) {
			startTimeDayValue = "PM";
		}

		// compares end time hour to 12 to see if it should have a PM meridian value
		if (endHour >= MILITARY_PM_TIME) {
			endTimeDayValue = "PM";
		}

		// compares start time hour to 12 and if it is greater than 12, but not equal,
		// subtract 12 and add PM meridian value
		if (startHour > MILITARY_PM_TIME) {

			startHour -= MILITARY_PM_TIME;

			startTimeDayValue = "PM";

			// if start time hour is 0 then time is midnight
		} else if (startHour == 0) {
			startHour = MILITARY_PM_TIME;

			startTimeDayValue = "PM";

		}

		// if end time hour is greater than 12 but not equal then subtract 12
		// and add PM meridian tag
		if (endHour > MILITARY_PM_TIME) {

			endHour -= MILITARY_PM_TIME;

			endTimeDayValue = "PM";

			// if start time hour is 0 then it is midnight
		} else if (startHour == 0) {
			startHour = MILITARY_PM_TIME;

			endTimeDayValue = "PM";

		}

		// convert int to string
		startHourString = Integer.toString(startHour);
		endHourString = Integer.toString(endHour);

		// convert int to string
		startMinuteString = Integer.toString(startMinute);
		endMinuteString = Integer.toString(endMinute);

		// if start time minute has a ones place not divisible by 0 (no number
		// in the ones place is cleanly divisible by 0)
		// then concatenate a 0 and add the ones place number to get a leading zero
		// value
		if (startMinute < IS_SINGLE_DIGIT_DIVIDER) {
			startMinuteString = "0" + startMinute;
		}

		// same thing as above, except for now we are working with the end time
		// minute
		if (endMinute < IS_SINGLE_DIGIT_DIVIDER) {
			endMinuteString = "0" + endMinute;
		}

		// this block of code concatenates the all the times above to create one final
		// meetingDaysString in the format "00:00 - 00:00"
		String meetingDaysString = "";
		meetingDaysString += this.meetingDays;
		meetingDaysString += " ";
		meetingDaysString += startHourString;
		meetingDaysString += ":";
		meetingDaysString += startMinuteString;
		meetingDaysString += startTimeDayValue;
		meetingDaysString += "-";
		meetingDaysString += endHourString;
		meetingDaysString += ":";
		meetingDaysString += endMinuteString;
		meetingDaysString += endTimeDayValue;

		return meetingDaysString;
	}
	
	/**
	 * Abstract method to check is some other course or event is a duplicate. Used by Course and Event.
	 * 
	 * @param activity which is an event or course
	 * @return return a boolean value
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * hash code method
	 */
	@Override
	public int hashCode() {
		return Objects.hash(endTime, meetingDays, startTime, title);
	}

	/**
	 * equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return endTime == other.endTime && Objects.equals(meetingDays, other.meetingDays)
				&& startTime == other.startTime && Objects.equals(title, other.title);
	}

}