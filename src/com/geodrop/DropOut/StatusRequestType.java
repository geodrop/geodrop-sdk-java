package com.geodrop.DropOut;

/**
 * Used in the REQUEST_TYPE tag of the SMS Status request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public final class StatusRequestType 
{
	/**
	 * Used to retrieve the status of all SMS messages sent in a given time interval
	 */
	public static final String RANGE           = "range";
	/**
	 * Used to retrieve the overall status of a job
	 */
	public static final String JOB	           = "job";
	/**
	 * Used to retrieve the status of any set of SMS messages
	 */
	public static final String ADHOC           = "adhoc";
}
