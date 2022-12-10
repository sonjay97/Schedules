/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Class used for scheduling courses, adding events to schedule, getting courses
 * from catalog, exporting schedules, removing courses from schedule, setting
 * schedule title, getting schedule title, presenting activities, and adding
 * events.
 * 
 * @author Jay Shah (jsshah)
 *
 */
public class WolfScheduler {

	/** courses available for use with scheduler and registration */
	ArrayList<Course> catalog = new ArrayList<Course>();

	/** courses actively added to a user's schedule */
	ArrayList<Activity> schedule = new ArrayList<Activity>();

	/** title for schedule */
	String title;

	/** Schedule name */
	public static final String DEFAULT_SCHEDULE_NAME = "My Schedule";

	/**
	 * Creates WolfSchedule with a user's given file name and contains course
	 * objects. Attempts to populate Course catalog with courses that are read from
	 * an input file.
	 * 
	 * @param validTestFile file with Course objects
	 * @throws IllegalArgumentException when file is not present or found
	 */
	public WolfScheduler(String validTestFile) {

		// Create course catalog
//		ArrayList<Course> catalog = new ArrayList<Course>();
//		this.catalog = catalog;

		// creating schedule
//		ArrayList<Activity> schedule = new ArrayList<Activity>();
//		this.schedule = schedule;

		// set title to My Schedule as a default
		this.title = DEFAULT_SCHEDULE_NAME;

		// set up a try catch block in case file is not found
		try {
			this.catalog = CourseRecordIO.readCourseRecords(validTestFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Creates and returns 2D array of the schedule containing info about the course
	 * name, section, and title.
	 * 
	 * @return 2D string array of the schedule
	 */
	public String[][] getScheduledActivities() {

		if (this.schedule.size() == 0) {
			return new String[0][0];
		}
		// updated instead use getShortDisplay array similar to get course catalog
		// create array object
//		String[][] courseSchedule = new String[schedule.size()][3];
		if (this.schedule.size() == 0) {
			return new String[0][0];
		} else {
			// create string inside else to try and fix error
			String[][] courseSchedule = new String[this.schedule.size()][3];
			for (int i = 0; i < schedule.size(); i++) {
				courseSchedule[i] = this.schedule.get(i).getShortDisplayArray();
			}
			return courseSchedule;
		}
	}

	/**
	 * Returns the wolf scheduler catalog in a 2D String array contains info such as
	 * course name, section, and title. Code is commented well but essentially this
	 * is performing the same tasks other methods with slight tweaks.
	 * 
	 * @return catalog catalog of courses in a 2D array
	 */
	public String[][] getCourseCatalog() {

		// need to update the method to include a fourth column that provides the
		// meeting string information
		// will need to call getShortDisplayArray() since we can support returning an
		// array for Course and Event
		// we can start by creating a new array, determine if it is empty or not, and
		// then populate the array with
		// the proper short display array by iterating through it with a for loop

		String[][] courseCatalog = new String[catalog.size()][4];
		if (catalog.size() == 0) {
			return courseCatalog;
		}

		for (int i = 0; i < catalog.size(); i++) {
//			Course c = catalog.get(i);
//			courseCatalog[i] = c.getShortDisplayArray();
			courseCatalog[i][0] = this.catalog.get(i).getName();
			courseCatalog[i][1] = this.catalog.get(i).getSection();
			courseCatalog[i][2] = this.catalog.get(i).getTitle();
			courseCatalog[i][3] = this.catalog.get(i).getMeetingString();
		}
		return courseCatalog;
	}

	/**
	 * Creates a 2D array that is supposed to represent a schedule. Schedule will
	 * contain a lot of information such as course name, course section, title,
	 * credits, instructorId, and meeting days.
	 * 
	 * 
	 * @return 2D array of the schedule
	 */
	public String[][] getFullScheduledActivities() {

		// instead of iterating through the schedule to add course info, instead we
		// can iterate through the schedule and use get long display array to add the
		// relevant info
		// similar to get course catalog

		if (this.schedule.size() == 0) {
			return new String[0][0];
		}

		else {
			String[][] fullCatalog = new String[this.schedule.size()][7];
			for (int i = 0; i < this.schedule.size(); i++) {
				fullCatalog[i] = this.schedule.get(i).getLongDisplayArray();
			}
			return fullCatalog;
		}
	}

	/**
	 * Gets schedule title
	 * 
	 * @return schedule title
	 */
	public String getScheduleTitle() {

		return this.title;
	}

	/**
	 * Exports schedule to a file
	 * 
	 * @param fileName the name of the file the user will export to
	 * @throws IllegalArgumentException if file cannot be written to
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, this.schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}

	}

	/**
	 * Gets course from catalog using name and section number
	 * 
	 * @param name    name of course
	 * @param section section of course
	 * @return the course object corresponding to name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {

		// iterate through course catalog
		for (int i = 0; i < this.catalog.size(); i++) {

			// check to see if current course has the same name and section
			// as input name and section
			if (this.catalog.get(i).getName().equals(name) && this.catalog.get(i).getSection().equals(section)) {

				return this.catalog.get(i);
			}
		}

		return null;
	}

	/**
	 * Adds course to schedule if course passes a couple of parameters set in the
	 * method.
	 * 
	 * @param name    name of course
	 * @param section section of course
	 * @return true or false depending on if course exists or not already
	 */
	public boolean addCourseToSchedule(String name, String section) {
		// updated to use isDuplicate method, see for loop
		// for the updated method we now call is Duplicate and and see if schedule has
		// the existing course
		// if duplicate is false then we can add to schedule using schedule.add
		Course addingCourse = getCourseFromCatalog(name, section);

		// check if course exists in catalog
		if (addingCourse == null) {
			return false;
		}
		
			// check for course with same name in schedule
			for (int i = 0; i < schedule.size(); i++) {
				
				if (schedule.get(i).isDuplicate(addingCourse)) {
					throw new IllegalArgumentException("You are already enrolled in " + name);
				}
				try {
					addingCourse.checkConflict(schedule.get(i));
				} catch (ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
			// if tests above pass then added to schedule
			schedule.add(addingCourse);
			return true;
		

	}

	/**
	 * Removes course from schedule
	 * 
	 * @param idx TODO
	 * 
	 * @return true or false depending on if course exists or does not exist in
	 *         schedule
	 */
	public boolean removeActivityFromSchedule(int idx) {

		// updated to use remove(idx) function
		// for loop was removed since it was uneccesesary
		// using a try catch block to catch IndexOutOfBoundExceptions
		// returns false if caught
		try {
			schedule.remove(idx);
			return true; // returns true if successfully removed
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	/**
	 * Creates new empty schedule
	 */
	public void resetSchedule() {
		// updated so the method compiles and passes its test case
		// test case is asking to make sure resetting doesn't break future adds
		// also need to make sure scheduled activities.length == 0
		// you can do so similar to the original version of this method
		this.schedule = new ArrayList<Activity>();
		// simply create a new empty array list but this time with activity

	}

	/**
	 * sets title for schedule
	 * 
	 * @param title title of schedule
	 * @throws IllegalArgumentException if input is null
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		} else {
			this.title = title;
		}

	}

	/**
	 * Functionality for adding a course is separate from adding an event
	 * 
	 * @param eventTitle       title of the event
	 * @param eventMeetingDays meeting days for event
	 * @param eventStartTime   start time for the event
	 * @param eventEndTime     end time for the event
	 * @param eventDetails     details of the event
	 * @return true if event was added
	 * @throws ConflictException when the event has a time conflict
	 */
	public boolean addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			String eventDetails) throws ConflictException {
		// this method is used to add an event, we also need to check for duplicate
		// events
		// we can start by creating an event and then adding it, before we add though we
		// will check for duplication

		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);

		// iterate through event schedule to see if there is duplicate
		for (int i = 0; i < schedule.size(); i++) {
			if (event.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You have already created an event called " + event.getTitle());
			}

			try {
				event.checkConflict(schedule.get(i));
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		schedule.add(event);
		return true;
	}

}
