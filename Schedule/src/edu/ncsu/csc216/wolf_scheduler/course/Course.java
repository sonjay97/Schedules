/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.Objects;

/**
 * Class constructor for the WolfScheduler program. Creates a course objects
 * which is used for scheduling, adding, and dropping courses on WolfScheduler.
 * Contains the following fields: meeting days, start time, end time, credit
 * hours, course title, course name, course section and instructor ID. Contains
 * the following methods: getters and setters for each field.
 * 
 * 
 * @author Jay Shah (jsshah)
 *
 */
public class Course extends Activity {

	/** Minimum length allowed for Course's name */
	private static final int MIN_NAME_LENGTH = 5;
	/** Maximum length allowed for Course's name */
	private static final int MAX_NAME_LENGTH = 8;
	/** Minimum length allowed for Course's character count */
	private static final int MIN_LETTER_COUNT = 1;
	/** Maximum length allowed for Course's character count */
	private static final int MAX_LETTER_COUNT = 4;
	/**
	 * Max amount of digits allowed for entry by user when searching a Course's
	 * number identity
	 */
	private static final int DIGIT_COUNT = 3;
	/** Max amount of digits allowed for the section length. */
	private static final int SECTION_LENGTH = 3;
	/** Minimum amount of credits Course is allowed to have */
	private static final int MIN_CREDITS = 1;
	/** Maximum amount of credits Course is allowed to have */
	private static final int MAX_CREDITS = 5;

	
	
	//where multiple private fields used to be
	
	
	
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Constructs a Course object with values for all the fields.
	 * 
	 * @param name         name of the Course
	 * @param title        title of the Course
	 * @param section      section of the Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
    public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
            int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
    }

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {

		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		int letterAmount = 0;
		int digitAmount = 0;
		boolean isSpace = false;

		for (int i = 0; i < name.length(); i++) {
			if (!isSpace) {
				if (Character.isLetter(name.charAt(i))) {
					letterAmount++;
				} else if (" ".equals(String.valueOf(name.charAt(i)))) {
					isSpace = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (isSpace) {
				if (Character.isDigit(name.charAt(i))) {
					digitAmount++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}

		if (letterAmount < MIN_LETTER_COUNT || letterAmount > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		if (digitAmount != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the param is null, empty, or contains a
	 *                                  character
	 */
	public void setSection(String section) {

		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}

	/**
	 * Return's the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits are greater than max or less than
	 *                                  minimum
	 */
	public void setCredits(int credits) {

		if (credits > MAX_CREDITS || credits < MIN_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Return's the Course's instructor ID.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor ID.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if param is null or empty
	 */
	public void setInstructorId(String instructorId) {

		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}


	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Main left unpopulated for now.
	 * 
	 * @param args command line arguments not used
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * generates hash code
	 * 
	 * @return result of the method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}

	/**
	 * equals method
	 * 
	 * @return credits credits are used to verify hash code
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
	}

	/**
	 * Returns shortDisplayArray which contains info about a class
	 * 
	 * @return shortDisplayCourse which contains all info
	 */
	@Override
	public String[] getShortDisplayArray() {
		
//		String[] shortDisplayCourse = new String[SHORT_DISPLAY_SIZE];
//		
//		shortDisplayCourse[INDEX_FOR_NAME] = this.name;
//		shortDisplayCourse[INDEX_FOR_SECTION] = this.section;
//		shortDisplayCourse[INDEX_FOR_TITLE] = getTitle();
//		shortDisplayCourse[INDEX_FOR_MEETING_STRING] = getMeetingString();
//
//		return shortDisplayCourse;
		
		String[] shortDisplayCourse = {name, section, getTitle(), getMeetingString()};
		return shortDisplayCourse;
	}

	/**
	 * Returns longDisplayArray which contains info about a event
	 * 
	 * @return longDisplayArray which contains all info about an event.
	 */
	@Override
	public String[] getLongDisplayArray() {
		
//		String[] longDisplayCourse = new String[LONG_DISPLAY_SIZE];
//		
//		longDisplayCourse[INDEX_FOR_NAME] = this.name;
//		longDisplayCourse[INDEX_FOR_SECTION] = this.section;
//		longDisplayCourse[INDEX_FOR_TITLE] = getTitle();
//		longDisplayCourse[INDEX_FOR_CREDITS] = Integer.toString(this.credits);
//		longDisplayCourse[INDEX_FOR_INSTRUCTOR_ID] = this.instructorId;
//		longDisplayCourse[INDEX_FOR_MEETING_STRING_LONG] = getMeetingString();
//		longDisplayCourse[INDEX_FOR_EVENT] = "";
//		
//		return longDisplayCourse;
		String[] longDisplayCourse = {name, section, getTitle(), "" + credits, instructorId, getMeetingString(), ""};
		
		return longDisplayCourse;
	}
	
	/**
	 * Ensures that UC1 is satisfied and adds additional parameters for setMeetingDaysAndTime specific
	 * for a Course in a schedule
	 * 
	 * @param meetingDays meeting days of course
	 * @param startTime starting time of course
	 * @param endTime end time of course
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		if(meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//if meeting day is arranged there is no start and end time
		//therefore we can just pass 0 for start and end time to Activity.setMeetingDaysAndTime
		if("A".equals(meetingDays)) {
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
		

		else {
			//now we need to initialize local variables to count what days are present in our meetingDays 
			//string by iterating through the meetingDays String. Then we are going to check and ensure 
			//that the days listed meet our specifications
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
			
			//For Course, there is nothing on the weekend, according to user case we only need to worry about
			//weekdays, Event will make use of weekends however
			
			//now we iterate through the meetingDays string to get a specific day
			for (int i = 0; i < meetingDays.length(); i++) {
				//if char at meetingDays[i] == weekday 
				//increase dayCount for that char
				if(meetingDays.charAt(i) == 'M') {
					mCount++;
				}
				
				else if (meetingDays.charAt(i) == 'T') {
					tCount++;
				}
				else if (meetingDays.charAt(i) == 'W') {
					wCount++;
				}
				else if (meetingDays.charAt(i) == 'H') {
					hCount++;
				}
				else if (meetingDays.charAt(i) == 'F') {
					fCount++;
				}
				//if nothing else throw IAE
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
				
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			//all our info is checked according to course specifics, now we send it to activity.java
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}
	
	/**
	 * Checks for duplicate activities similar to the isEquals() method
	 * 
	 * @param activity is the activity (course or event) on the schedule
	 * @return true if getName() = other.getName()
	 */
	public boolean isDuplicate(Activity activity) {
		
		if (getClass() != activity.getClass()) {
			return false;
		}
		Course other = (Course) activity;
		return this.getName().equals(other.getName());
	}
}









