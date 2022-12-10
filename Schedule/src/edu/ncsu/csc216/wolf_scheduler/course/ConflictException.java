/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class that throws ConflictException when there are conflicting times for a course or event or both. 
 * 
 * @author jayshah
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Throws conflict exception with String message
	 * 
	 * @param message the message displayed when exception is thrown
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Second method without parameter required to throw unique exception
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}
	
}
