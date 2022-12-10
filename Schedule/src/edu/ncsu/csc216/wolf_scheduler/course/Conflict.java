/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Finds conflicts in overlapping times for courses and events
 * 
 * @author Jay Shah (jsshah)
 *
 */
public interface Conflict {
	
	
	/**
	 * Abstract method that throws conflict exception when there are conflicting times for event or course
	 * 
	 * @param possibleConflictingActivity activity object either a course or event
	 * @throws ConflictException Exception that checks and throws in there are conflicting times
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
