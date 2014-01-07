package com.geodrop.DropOut;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.MustacheTemplate;
import com.geodrop.Uri;

/**
 * Request used to modify a scheduled message,
 * adding or removing recipients,
 * changing its message text,
 * its custom sender or its scheduled time
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSJobsScheduledModify extends GeodropRequest
{	
	/**
	 * The action to perform (<CODE>MODIFY</CODE>)
     */
	private static String action;
    /**
     * The job id of the job to modify
     */
    private String jobId;
	/**
	 * Text of the message
	 */
	private String messageText;
	/**
	 * Array of msisdns to delete; each msisdn is in E.164 format with '+'
	 */
	private Vector<String> msisdnToDelete;
	/**
	 * Array of msisdns to add; each msisdn is in E.164 format with '+'
	 */
	private Vector<String> msisdnToAdd;
	/**
	 * Used to specify the personalized sender
	 */
	private String tpoa;
	/**
	 * Date and time in the format "Y-m-d H:i:s",
	 * used to send the message to a certain date,
	 * if not specified the message is sent immediately
	 */
	private Date deferredTime;

	/**
	 * Creates a new <CODE>SMSJobsScheduledModify</CODE> instance
	 * 
	 * @param jobId The job ID
	 * @throws Exception If parameters are not valid
	 */
	public SMSJobsScheduledModify(String jobId) throws Exception
	{
		initialize(jobId,null,null,null,null,null);
	}
	
	/**
	 * Creates a new <CODE>SMSJobsScheduledModify</CODE> instance
	 * 
	 * @param jobId The job ID
	 * @param deferredTime The deferred time
	 * @param messageText The message text
	 * @param msisdnToDelete The msisdns to delete
	 * @param msisdnToAdd The msisdns to add
	 * @param tpoa the tpoa
	 * @throws Exception If parameters are not valid
	 */
	public SMSJobsScheduledModify(String jobId,Date deferredTime,String messageText,Vector<String> msisdnToDelete,Vector<String> msisdnToAdd,String tpoa) throws Exception
	{
		initialize(jobId,deferredTime,messageText,msisdnToDelete,msisdnToAdd,tpoa);
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param jobId The job ID
	 * @param deferredTime The deferred time
	 * @param messageText The message text
	 * @param msisdnToDelete The msisdns to delete
	 * @param msisdnToAdd The msisdns to add
	 * @param tpoa the tpoa
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(String jobId,Date deferredTime,String messageText,Vector<String> msisdnToDelete,Vector<String> msisdnToAdd,String tpoa) throws Exception
	{
		//check msisdns
		int index;
		if(msisdnToDelete != null)
		{
			for(index = 0; index <= msisdnToDelete.size(); index++)
			{
				if(!this.checkMsisdnE164Format(msisdnToDelete.get(index), false))
				{
					throw new Exception(ErrorType.MALFORMED_MSISDNS);
				}
			}
		}
		if(msisdnToAdd != null)
		{
			for(index = 0; index <= msisdnToAdd.size(); index++)
			{
				if(!this.checkMsisdnE164Format(msisdnToAdd.get(index), false))
				{
					throw new Exception(ErrorType.MALFORMED_MSISDNS);
				}
			}
		}
		//check date
		if(deferredTime != null)
		{
			if (deferredTime.before(new Date()))
			{
				throw new Exception(ErrorType.MALFORMED_OR_PAST_TIME);
			}
		}
		//set parameters
		this.uri = Uri.OUT_SMS_JOBS_SCHEDULED;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.XML;
		SMSJobsScheduledModify.action = JobsScheduledAction.MODIFY;
		this.templateName = MustacheTemplate.SMS_Scheduled_Modify;
		this.jobId = jobId;
		this.messageText = messageText;
		this.msisdnToAdd = msisdnToAdd;
		this.msisdnToDelete = msisdnToDelete;
		this.tpoa = tpoa;
		this.deferredTime = deferredTime;
	}
	
	@Override
	public boolean decodeResponse(String httpResponse) 
	{
		this.response = new SMSJobsScheduled_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	public void createParams() 
	{
		this.params = new HashMap<String, Object>();
		this.params.put("job_id", this.jobId);
		this.params.put("action", SMSJobsScheduledModify.action);
		this.params.put("message_text", this.messageText);
		this.params.put("msisdn_add", this.msisdnToAdd);
		this.params.put("msisdn_del", this.msisdnToDelete);
		this.params.put("tpoa", this.tpoa);
		//deferredTime
		if(this.deferredTime != null)
		{
			SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.params.put("deferred_time", dateParser.format(this.deferredTime));
		}
	}

	//getters
	/**
	 * @return The job ID
	 */
	public String getJobId() 
	{
		return this.jobId;
	}

	/**
	 * @return The message text
	 */
	public String getMessageText() 
	{
		return this.messageText;
	}

	/**
	 * @return The msisdns to delete
	 */
	public Vector<String> getMsisdnToDelete() 
	{
		return this.msisdnToDelete;
	}

	/**
	 * @return The msisdns to add
	 */
	public Vector<String> getMsisdnToAdd() 
	{
		return this.msisdnToAdd;
	}

	/**
	 * @return The tpoa
	 */
	public String getTpoa() 
	{
		return this.tpoa;
	}

	/**
	 * @return The deferred time
	 */
	public Date getDeferredTime() 
	{
		return this.deferredTime;
	}

	//setters
	/**
	 * @param jobId The job ID
	 */
	public void setJobId(String jobId) 
	{
		this.jobId = jobId;
	}

	/**
	 * @param messageText The message text
	 */
	public void setMessageText(String messageText) 
	{
		this.messageText = messageText;
	}

	/**
	 * @param msisdnToDelete The msisdns to delete
	 */
	public void setMsisdnToDelete(Vector<String> msisdnToDelete) 
	{
		this.msisdnToDelete = msisdnToDelete;
	}

	/**
	 * @param msisdnToAdd The msisdns to add
	 */
	public void setMsisdnToAdd(Vector<String> msisdnToAdd) 
	{
		this.msisdnToAdd = msisdnToAdd;
	}

	/**
	 * @param tpoa The tpoa
	 */
	public void setTpoa(String tpoa) 
	{
		this.tpoa = tpoa;
	}

	/**
	 * @param deferredTime The deferred time
	 */
	public void setDeferredTime(Date deferredTime) 
	{
		this.deferredTime = deferredTime;
	}
}
