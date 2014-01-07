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
     * Contains the msisdn of the recipient or an array of msisdns if there are many recipients;
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
     * Creates a new <CODE>SMSSend</CODE> instance
     * 
     * @param destMsisdns The msisdns
     * @param messageText The message text
     * @param tpoa The tpoa
     * @throws Exception If parameters are not valid
     */
    public SMSSend(Vector<String> destMsisdns,String messageText,String tpoa) throws Exception
    {
    	this.initialize(destMsisdns,messageText,tpoa,null);
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
    public SMSSend(Vector<String> destMsisdns,String messageText,String tpoa,Date deferred) throws Exception
    {
    	this.initialize(destMsisdns,messageText,tpoa,deferred);
    }
    
    /**
     * Initialize the request
     * 
     * @param destMsisdns The msisdns
     * @param messageText The message text
     * @param tpoa The tpoa
     * @param deferred The deferred time
     * @throws Exception If parameters are not valid
     */
    private void initialize(Vector<String> destMsisdns,String messageText,String tpoa,Date deferred) throws Exception
    {
		//check msisdns format
		for(int index = 0; index < destMsisdns.size(); index++)
		{
			if(!this.checkMsisdnE164Format(destMsisdns.get(index),true))
			{
				throw new Exception(ErrorType.MALFORMED_MSISDNS);
			}
		}
		//check date
		if(deferred != null)
		{
			if (deferred.before(new Date()))
			{
				throw new Exception(ErrorType.MALFORMED_OR_PAST_TIME);
			}
		}
    	//set parameters
	    this.uri = Uri.OUT_SMS_SEND;
	    this.httpMethod = HttpMethod.POST;
	    this.contentType = ContentType.XML;
	    this.templateName = MustacheTemplate.SMS_Send_Estimatecost;
	    this.destMsisdns = destMsisdns;
	    this.messageText = messageText;
	    this.tpoa = tpoa;
	    this.deferred = deferred;
    }
    
	@Override
	public boolean decodeResponse(String httpResponse)
	{
		this.response = new SMSSend_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	public void createParams() 
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
	}

	//getters
	/**
	 * @return The message text
	 */
	public String getMessageText() 
	{
		return this.messageText;
	}

	/**
	 * @return The msisdns
	 */
	public Vector<String> getDestMsisdns() 
	{
		return this.destMsisdns;
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
	public Date getDeferred() 
	{
		return this.deferred;
	}
	
	//setters
	/**
	 * @param messageText The message text
	 */
	public void setMessageText(String messageText) 
	{
		this.messageText = messageText;
	}

	/**
	 * @param destMsisdns The msisdns
	 */
	public void setDestMsisdns(Vector<String> destMsisdns) 
	{
		this.destMsisdns = destMsisdns;
	}

	/**
	 * @param tpoa The tpoa
	 */
	public void setTpoa(String tpoa) 
	{
		this.tpoa = tpoa;
	}

	/**
	 * @param deferred The deferred time
	 */
	public void setDeferred(Date deferred) 
	{
		this.deferred = deferred;
	}
}
