package com.geodrop.SMSOut;

import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;
import com.geodrop.SMSOut.MustacheTemplate;
import com.geodrop.SMSOut.SMSStatusJob;
import com.geodrop.SMSOut.SMSStatus_Response;
import com.geodrop.SMSOut.StatusRequestType;

/**
 * Request used to get the overall status of a job
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSStatusJob extends GeodropRequest
{
	/**
	 * The request type (<CODE>JOB</CODE>)
	 */
	private static String requestType;
	/**
	 * The Global Unique IDentifier (GUID) of the job
	 */
	private String jobOrderid;
	/**
	 * Used to paginate the result,
	 * it consist of two integers separated by a comma,
	 * the first one indicates the position of the first required result
	 * and the second the total number of result to return
	 */
	private String jobLimit;
	/**
	 * The client id
	 */
	private String clientId;

	/**
	 * Creates a new <CODE>SMSStatusJob</CODE> instance
	 * 
	 * @param jobOrderid The job ID
	 * @throws Exception If parameters are not valid
	 */
	public SMSStatusJob(String jobOrderid) throws Exception
	{
		this.initialize(jobOrderid,null,"0,100");
	}
	
	/**
	 * Creates a new <CODE>SMSStatusJob</CODE> instance
	 * 
	 * @param jobOrderid The job ID
	 * @param clientId The client ID
	 * @throws Exception If parameters are not valid
	 */
	public SMSStatusJob(String jobOrderid,String clientId) throws Exception
	{
		this.initialize(jobOrderid,clientId,"0,100");
	}
	
	/**
	 * Creates a new <CODE>SMSStatusJob</CODE> instance
	 * 
	 * @param jobOrderid The job ID
	 * @param clientId The client ID
	 * @param jobLimit The job limit
	 * @throws Exception If parameters are not valid
	 */
	public SMSStatusJob(String jobOrderid,String clientId,String jobLimit) throws Exception
	{
		this.initialize(jobOrderid,clientId,jobLimit);
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param jobOrderid The job ID
	 * @param clientId The client ID
	 * @param jobLimit The job limit
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(String jobOrderid,String clientId,String jobLimit) throws Exception
	{
		//set parameters
		this.uri = Uri.OUT_SMS_STATUS;
		this.httpMethod = HttpMethod.PUT;
		this.contentType = ContentType.XML;
		SMSStatusJob.requestType = StatusRequestType.JOB;
		this.templateName = MustacheTemplate.SMS_Status;
		this.setJobOrderid(jobOrderid);
		this.setClientId(clientId);
		this.setJobLimit(jobLimit);
	}
	
	@Override
	protected boolean decodeResponse(String httpResponse) 
	{
		this.response = new SMSStatus_Response();
		return this.response.fillParameters(httpResponse);
	}
	@Override
	protected void createParams() 
	{
		this.params = new HashMap<String, Object>();
		this.params.put("client_id", this.clientId);
		this.params.put("request_type", SMSStatusJob.requestType);
		//job
		HashMap<String, String> job = new HashMap<String, String>();
		job.put("job_orderid", this.jobOrderid);
		job.put("job_limit", this.jobLimit);
		this.params.put("job", job);
	}

	//getters
	/**
	 * @return The Global Unique IDentifier (GUID) of the job
	 */
	public String getJobOrderid() 
	{
		return this.jobOrderid;
	}

	/**
	 * @return The job limit, used to paginate the result,
	 * it consist of two integers separated by a comma,
	 * the first one indicates the position of the first required result
	 * and the second the total number of result to return
	 */
	public String getJobLimit() 
	{
		return this.jobLimit;
	}

	/**
	 * @return The client id
	 */
	public String getClientId()
	{
		return this.clientId;
	}

	//setters
	/**
	 * @param jobOrderid The Global Unique IDentifier (GUID) of the job
	 */
	public void setJobOrderid(String jobOrderid) 
	{
		this.jobOrderid = jobOrderid;
	}

	/**
	 * @param jobLimit The job limit, used to paginate the result,
	 * it consist of two integers separated by a comma,
	 * the first one indicates the position of the first required result
	 * and the second the total number of result to return
	 * @throws Exception If parameters are not valid
	 */
	public void setJobLimit(String jobLimit) throws Exception 
	{
		//check limit format
		if(!this.checkLimitFormat(jobLimit))
		{
			throw new Exception(ErrorType.MALFORMED_LIMIT);
		}
		this.jobLimit = jobLimit;
	}

	/**
	 * @param clientId The client id
	 */
	public void setClientId(String clientId) 
	{
		this.clientId = clientId;
	}
}
