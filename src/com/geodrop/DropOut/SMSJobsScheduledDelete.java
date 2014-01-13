package com.geodrop.DropOut;

import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Request used to delete a scheduled job 
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSJobsScheduledDelete extends GeodropRequest
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
	 * Creates a new <CODE>SMSJobsScheduledDelete</CODE> instance
	 * @param jobId The job ID
	 */
	public SMSJobsScheduledDelete(String jobId)
	{
		this.initialize(jobId);
	}
	
	/**
	 * Initialize the request
	 * @param jobId The job ID
	 */
	private void initialize(String jobId)
	{
		this.uri = Uri.OUT_SMS_JOBS_SCHEDULED;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.XML;
		SMSJobsScheduledDelete.action = JobsScheduledAction.DELETE;
		this.templateName = MustacheTemplate.SMS_Scheduled_Status_Delete;
		this.setJobId(jobId);
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
		this.params.put("action", SMSJobsScheduledDelete.action);
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
