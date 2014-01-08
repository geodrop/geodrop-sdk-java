package com.geodrop.DropOut;

import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Request used to retrieve the overall status of a scheduled job
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSJobsScheduledStatus extends GeodropRequest
{
	/**
	 * The action to perform (<CODE>STATUS</CODE>)
	 */
	private static String action;
	/**
	 * The job ID
	 */
	private String jobId;
	
	/**
	 * Creates a new <CODE>SMSJobsScheduledStatus</CODE> instance
	 * 
	 * @param jobId The job ID
	 */
	public SMSJobsScheduledStatus(String jobId)
	{
		this.initialize(jobId);
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param jobId The job ID
	 */
	private void initialize(String jobId)
	{
		this.uri = Uri.OUT_SMS_JOBS_SCHEDULED;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.XML;
		SMSJobsScheduledStatus.action = JobsScheduledAction.STATUS;
		this.templateName = MustacheTemplate.SMS_Scheduled_Status_Delete;
		this.jobId = jobId;
	}
	
	@Override
	protected boolean decodeResponse(String httpResponse) 
	{
		this.response = new SMSJobsScheduled_Response();
		return this.response.fillParameters(httpResponse);
	}
	
	@Override
	protected void createParams() 
	{
		this.params = new HashMap<String, Object>();
		
		//job id
		this.params.put("job_id", this.jobId);
		this.params.put("action", SMSJobsScheduledStatus.action);
	}

	//getters
	/**
	 * @return The job ID
	 */
	public String getJobId() 
	{
		return this.jobId;
	}

	//setters
	/**
	 * @param jobId The job ID
	 */
	public void setJobId(String jobId) 
	{
		this.jobId = jobId;
	}
}
