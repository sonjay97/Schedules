/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * CourseRecordIO is a input output program that implements the scanner and output utility to 
 * read course.txt document and write .txt documents. The functionality of this class will allow 
 * wolf scheduler to read a course catalog and store that catalog's values and create usable info 
 * to present to the user.
 * 
 * @author Jay Shah (jsshah)
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {

		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file

		ArrayList<Course> courses = new ArrayList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the ArrayList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the ArrayList with all the courses we read!
		return courses;
	}

	/**
	 * Scans each line of the Course Record input file and creates an appropriate
	 * Course object
	 * 
	 * @param line the next line in the course record input file
	 * @return constructed course object
	 * @throws IllegalArgumentException if file is at the end or unexpected token is
	 *                                  read
	 */
	private static Course readCourse(String line) {

		//create scanner object
		Scanner scan = new Scanner(line);

		//change delimiter to a comma ","
		scan.useDelimiter(",");

		//try-catch block in case an processing error occurs
		try {

			//this block of code scans in the relevant data and stores it
			//in an appropriate variable
			String name = scan.next();
			String title = scan.next();
			String section = scan.next();
			int creditHours = scan.nextInt();
			String instructorId = scan.next();
			String meetingDays = scan.next();
			
			//if arranged meeting day is found, check to see if there is 
			//a token beside it, there should not be since arranged has no
			//meeting times
			//throw IAE if there is a time for an arranged course
			if ("A".equals(meetingDays)) {

				if (scan.hasNext()) {
					scan.close();
					throw new IllegalArgumentException("Invalid meeting day.");
				} else {
					scan.close();
					return new Course(name, title, section, creditHours, instructorId, meetingDays);
				}
			}

			//else create start time and end time and scan in the values
			else {

			int startTime = scan.nextInt();
			int endTime = scan.nextInt();

			if (scan.hasNext()) {
				scan.close();
				throw new IllegalArgumentException();
			}

			//return the new course object with relevant data scanned in
			scan.close();
			return new Course(name, title, section, creditHours, instructorId, meetingDays, startTime, endTime);
			}
		} catch (Exception e) {
			scan.close();
			throw new IllegalArgumentException();
		}
	}
}
