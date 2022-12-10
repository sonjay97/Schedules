package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Handles writing activities to output files and reading input files for course
 * Also creates a course catalog
 * 
 * @author Jay Shah (jsshah)
 *
 */
public class ActivityRecordIO {

	/**
	 * This method will write the course object that has been populated with data
	 * to the output file requested
	 * 
	 * @param fileName file to write schedule of Courses to
	 * @param activities activities to be added to schedule
	 * @throws IOException if cannot write to file
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> activities) throws IOException {
		
		//initialize print stream object which allows us to write to an output file
		PrintStream courseWriter = new PrintStream(new File(fileName));
		
		//iterate through list and print one course per one line
		for (int i = 0; i < activities.size(); i++) {
			courseWriter.println(activities.get(i).toString());
		}
	
		//make sure you close courseWriter (could be any name depending on what 
		//you initialized print stream object to)
		courseWriter.close();
	
	}

}
