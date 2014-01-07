package com.geodrop.DropOut;

/**
 * Action attribute of the JOB tag in the Jobs Scheduled request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public final class JobsScheduledAction 
{
	/**
	 * To get the job status, its description (text and recipient's list)
	 * and the date and time planned for sending
	 */
	public static final String STATUS = "STATUS";
	/**
	 * To modify the text, the recipient's list and the planned date and time
	 */
	public static final String MODIFY = "MODIFY";
	/**
	 * To delete the job (the message will not be sent anymore)
	 */
	public static final String DELETE = "DELETE";
}
