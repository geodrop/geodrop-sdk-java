package com.geodrop.SMSOut;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;
import com.geodrop.SMSOut.MustacheTemplate;
import com.geodrop.SMSOut.SMSSend_Response;

/**
 * Request to send SMS messages
 * specifying a text for the message,
 * a list of recipients and a personalized sender
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */

public class SMSSend extends GeodropRequest
{
    /**
     * The text of the message to send
     */
    private String messageText;
    /**
     * Contains the msisdns of the recipients;
     * each msisdn is in E.164 format with '+'
     */
    private Vector<String> destMsisdns;
    /**
     * Used to specify the personalized sender
     */
    private String tpoa;
    /**
     * Date and time in the format "Y-m-d H:i:s",
     * used to send the message to a certain date,
     * if not specified the message is sent immediately
     */
	private Date deferred;
	/**
	 * Url for job notification
	 */
	private Vector<String> jobnotify;
	/**
	 * Url for dlr notification
	 */
	private Vector<String> dlrnotify;
	/**
	 * Time from which messages are not sent (HH:MM)
	 */
	private Date runrange_ltime;
	/**
	 * Number of hours during which messages are not sent
	 */
	private Integer runrange_timewindow;

    /**
     * Creates a new <CODE>SMSSend</CODE> instance
     * 
     * @param destMsisdns The msisdns
     * @param messageText The message text
     * @param tpoa The tpoa
     * @throws Exception If parameters are not valid
     */
    public SMSSend(Vector<String> destMsisdns,String messageText,String tpoa) throws Exception
    {
    	this.initialize(destMsisdns,messageText,tpoa,null,null,null,null,null);
    }
    
    /**
     * Creates a new <CODE>SMSSend</CODE> instance
     * 
     * @param destMsisdns The msisdns
     * @param messageText The message text
     * @param tpoa The tpoa
     * @param deferred The deferred time
     * @throws Exception If parameters are not valid
     */
    public SMSSend(
    		Vector<String> destMsisdns,
    		String messageText,
    		String tpoa,
    		Date deferred,
    		Vector<String> jobnotify,
        	Vector<String> dlrnotify,
        	Date runrange_ltime,
        	Integer runrange_timewindow ) throws Exception
    {
    	this.initialize(destMsisdns,messageText,tpoa,deferred,jobnotify,dlrnotify,runrange_ltime,runrange_timewindow);
    }
    
    /**
     * Initialize the request
     * 
     * @param destMsisdns The msisdns
     * @param messageText The message text
     * @param tpoa The tpoa
     * @param deferred The deferred time
     * @param jobnotify Url for job notification
     * @param dlrnotify Url for dlr notification
     * @param runrange_ltime Time from which messages are not sent (HH:MM)
     * @param runrange_timewindow Number of hours during which messages are not sent
     * @throws Exception If parameters are not valid
     */
    private void initialize(
    	Vector<String> destMsisdns,
    	String messageText,
    	String tpoa,
    	Date deferred,
    	Vector<String> jobnotify,
    	Vector<String> dlrnotify,
    	Date runrange_ltime,
    	Integer runrange_timewindow ) throws Exception
    {
    	//set parameters
	    this.uri = Uri.OUT_SMS_SEND;
	    this.httpMethod = HttpMethod.POST;
	    this.contentType = ContentType.XML;
	    this.templateName = MustacheTemplate.SMS_Send_Estimatecost;
	    this.setDestMsisdns(destMsisdns);
	    this.setMessageText(messageText);
	    this.setTpoa(tpoa);
	    this.setDeferred(deferred);
	    this.set_jobnotify(jobnotify);
	    this.set_dlrnotify(dlrnotify);
	    this.set_runrange_ltime(runrange_ltime);
	    this.set_runrange_timewindow(runrange_timewindow);
    }
    
	@Override
	protected boolean decodeResponse(String httpResponse)
	{
		this.response = new SMSSend_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	protected void createParams() 
	{
		this.params = new HashMap<String, Object>();
		this.params.put("message", this.messageText);
		this.params.put("dest_msisdns", this.destMsisdns);
		this.params.put("tpoa", this.tpoa);
		if(this.deferred != null)
		{	
			SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.params.put("deferred", dateParser.format(this.deferred));
		}
		if(this.jobnotify != null)
		{
			this.params.put("job_notifyurl",this.jobnotify);
		}
		if(this.dlrnotify != null)
		{
			this.params.put("dlr_notifyurl",this.dlrnotify);
		}
		if(this.runrange_ltime != null && this.runrange_timewindow != null)
		{
			Vector<HashMap<String, String>> runrange = new Vector<HashMap<String, String>>();
			HashMap<String, String> runrange_entry = new HashMap<String, String>();
			SimpleDateFormat dateParser = new SimpleDateFormat("HH:mm");
			runrange_entry.put("runrange_ltime", dateParser.format(this.runrange_ltime));
			runrange_entry.put("runrange_timewindow", this.runrange_timewindow.toString());
			runrange.add(runrange_entry);
			this.params.put("runrange",runrange);
		}
	}

	//getters
	/**
	 * @return The text of the message to send
	 */
	public String getMessageText() 
	{
		return this.messageText;
	}

	/**
	 * @return The vector of msisdns of the recipients;
     * each msisdn is in E.164 format with '+'
	 */
	public Vector<String> getDestMsisdns() 
	{
		return this.destMsisdns;
	}

	/**
	 * @return The tpoa used to specify the personalized sender
	 */
	public String getTpoa() 
	{
		return this.tpoa;
	}

	/**
	 * @return Date and time in the format "Y-m-d H:i:s",
     * used to send the message to a certain date,
     * if not specified the message is sent immediately
	 */
	public Date getDeferred() 
	{
		return this.deferred;
	}
	
	/**
	 * @return The url for job notification
	 */
	public Vector<String> get_jobnotify()
	{
		return this.jobnotify;
	}

	/**
	 * @return The url for dlr notification
	 */
	public Vector<String> get_dlrnotify()
	{
		return this.dlrnotify;
	}

	/**
	 * @return The time from which messages are not sent
	 */
	public Date get_runrange_ltime()
	{
		return this.runrange_ltime;
	}

	/**
	 * @return The number of hours during which messages are not sent
	 */
	public Integer get_runrange_timewindow()
	{
		return this.runrange_timewindow;
	}

	//setters
	/**
	 * @param messageText The text of the message to send
	 */
	public void setMessageText(String messageText) 
	{
		this.messageText = messageText;
	}

	/**
	 * @param destMsisdns The vector of msisdns of the recipients;
     * each msisdn is in E.164 format with '+'
	 * @throws Exception If parameters are not valid
	 */
	public void setDestMsisdns(Vector<String> destMsisdns) throws Exception 
	{
		//check msisdns format
		for(int index = 0; index < destMsisdns.size(); index++)
		{
			if(!this.checkMsisdnE164Format(destMsisdns.get(index),true))
			{
				throw new Exception(ErrorType.MALFORMED_MSISDNS);
			}
		}
		this.destMsisdns = destMsisdns;
	}

	/**
	 * @param tpoa The tpoa used to specify the personalized sender
	 */
	public void setTpoa(String tpoa) 
	{
		this.tpoa = tpoa;
	}

	/**
	 * @param deferred Date and time in the format "Y-m-d H:i:s",
     * used to send the message to a certain date,
     * if not specified the message is sent immediately
	 * @throws Exception If parameters are not valid
	 */
	public void setDeferred(Date deferred) throws Exception 
	{
		//check date
		if(deferred != null)
		{
			if (deferred.before(new Date()))
			{
				throw new Exception(ErrorType.MALFORMED_OR_PAST_TIME);
			}
		}
		this.deferred = deferred;
	}
	/**
	 * @param jobnotify Url for job notification
	 */
	public void set_jobnotify(Vector<String> jobnotify)
	{
		this.jobnotify = jobnotify;
	}

	/**
	 * @param dlrnotify Url for dlr notification
	 */
	public void set_dlrnotify(Vector<String> dlrnotify)
	{
		this.dlrnotify = dlrnotify;
	}

	/**
 	 * @param date runrange_ltime Time from which messages are not sent (HH:MM)
	 */
	public void set_runrange_ltime(Date runrange_ltime)
	{
		this.runrange_ltime = runrange_ltime;
	}

	/**
	 * @param runrange_timewindow Number of hours during which messages are not sent
	 */
	public void set_runrange_timewindow(Integer runrange_timewindow)
	{
		this.runrange_timewindow = runrange_timewindow;
	}
}
