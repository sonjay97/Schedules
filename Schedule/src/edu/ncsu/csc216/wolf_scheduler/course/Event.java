/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event will allow the user to add events in addition to their course schedule
 * to get a full idea of what their semester will look like.
 * 
 * @author Jay Shah (jsshah)
 *
 */
public class Event extends Activity {

	/**
	 * Constructor for Event Class
	 * 
	 * @param title        title of event
	 * @param meetingDays  meeting days of event
	 * @param startTime    start time of event
	 * @param endTime      end time of event
	 * @param eventDetails details of event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Contains details of an event
	 */
	private String eventDetails;

	/**
	 * Returns event details
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets event details
	 * 
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		
		this.eventDetails = eventDetails;
	}

	/**
	 * Returns a String array of length four that contains the info for the event, which will be 
	 * the title of the event and the meeting string.
	 * 
	 * @return shortDisplayArray array with event info in it
	 */
	@Override
	public String[] getShortDisplayArray() {
		
//		String[] shortDisplayArray = new String[4];
//		for(int i = 0; i < shortDisplayArray.length; i++) {
//			shortDisplayArray[i] = "";
//		}
//		
//		shortDisplayArray[2] = getTitle();
//		shortDisplayArray[3] = getMeetingString();
		
		String[] shortDisplayArray = {"", "", getTitle(), getMeetingString()};
		
		return shortDisplayArray;
	}

	/**
	 * Returns a String array of length four that contains the info for the event, which will be 
	 * the title of the event and the meeting string as well as the event details.
	 * 
	 * @return longDisplayArray array with event info in it
	 */
	@Override
	public String[] getLongDisplayArray() {
		
//		String[] longDisplayArray = new String[7];
//		
//		for (int i = 0; i < longDisplayArray.length; i++) {
//			longDisplayArray[i] = "";
//		}
		
//		longDisplayArray[2] = getTitle();
//		longDisplayArray[5] = getMeetingString();
//		longDisplayArray[6] = getEventDetails();
		
		String[] longDisplay = {"", "", getTitle(), "", "", getMeetingString(), getEventDetails()};
		
		return longDisplay;
	}

	/**
	 * Method in event that handles its own checks on meetingDays appropriate for the requirements
	 * , will then pass meetingDays, startTime, and endTime to Activity.setMeetingDaysAndTime.
	 * 
	 * @param meetingDays meeting days of the event
	 * @param startTime start time of the event
	 * @param endTime end time of the event
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//initialize local variables to count what days are present in meetingDays string
		int mCount = 0;
		int tCount = 0;
		int wCount = 0;
		int hCount = 0;
		int fCount = 0;
		int sCount = 0;
		int uCount = 0;
		
		//iterate through meetingDays string and identify what letters are present
		for (int i = 0; i < meetingDays.length(); i++) {
			//if meetingDays.charAt[i] == a letter we are looking for
			//then increase dayCount
			if (meetingDays.charAt(i) == 'M') {
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
			else if (meetingDays.charAt(i) == 'S') {
				sCount++;
			}
			else if (meetingDays.charAt(i) == 'U') {
				uCount++;
			}
			//throw IAE if none of the above
			else {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			//dayCount > 1 then something is wrong
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1 || sCount > 1 || uCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		//let Activity.setMeetingDaysAndTime() handle the common checks and final assignment
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	
	/**
	 * Creates a comma-separated list of courses and events with an ordering of information according
	 * to use case 10. 
	 * 
	 * @return eventInfor info about the event
	 */
	@Override
	public String toString() {
		
		String eventInfo;
		
		eventInfo = getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + getEventDetails();
		
		return eventInfo;
	}
	
	/**
	 * isDuplicate is the same thing as an isEquals() method
	 * The main difference is we are checking for duplicate events
	 * 
	 * @param activity is an event
	 * @return boolean true or false if title is equal to the other title
	 */
	public boolean isDuplicate(Activity activity) {
		 
		 if(getClass() != activity.getClass()) {
			 return false;
		 }
		 
		 Event other = (Event) activity;
		 return super.getTitle().equals(other.getTitle());
	 }

}













