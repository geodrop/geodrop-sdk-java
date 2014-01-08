package com.geodrop.DropOut;

/**
 * Used in the REQUEST_TYPE tag of the SMS Status request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
final class StatusRequestType 
{
	/**
	 * Used to retrieve the status of all SMS messages sent in a given time interval
	 */
	static final String RANGE           = "range";
	/**
	 * Used to retrieve the overall status of a job
	 */
	static final String JOB	           = "job";
	/**
	 * Used to retrieve the status of any set of SMS messages
	 */
	static final String ADHOC           = "adhoc";
	
	StatusRequestType(){}
}
