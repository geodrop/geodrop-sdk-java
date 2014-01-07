package com.geodrop.DropOut;

import java.util.HashMap;
import java.util.Vector;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.MustacheTemplate;
import com.geodrop.Uri;

/**
 * Request used to determine a higher estimate of the final cost of the message
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSEstimatecost extends GeodropRequest
{
    private Vector<String> destMsisdns;
    /**
     * The text of the message to send
     */
    private String messageText;
    
   /**
    * Creates a new <CODE>SMSEstimatecost</CODE> instance
    * 
    * @param destMsisdns Msisdns of the recipient
    * @param messageText Text of the message
    * @throws Exception If parameters are not valid
    */
	public SMSEstimatecost(Vector<String> destMsisdns,String messageText) throws Exception
	{
	    this.initialize(destMsisdns, messageText);
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param destMsisdns Msisdns of the recipient
	 * @param messageText Text of the message
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(Vector<String> destMsisdns,String messageText) throws Exception
	{
		//check msisdns format
		for(int index = 0; index < destMsisdns.size(); index++)
		{
			if(!this.checkMsisdnE164Format(destMsisdns.get(index),true))
			{
				throw new Exception(ErrorType.MALFORMED_MSISDNS);
			}
		}
		//set parameters
		this.uri = Uri.OUT_SMS_ESTIMATECOST;
	    this.httpMethod = HttpMethod.POST;
	    this.contentType = ContentType.XML;
	    this.templateName = MustacheTemplate.SMS_Send_Estimatecost;
	    this.destMsisdns = destMsisdns;
	    this.messageText = messageText;
	}
	
	@Override
	public boolean decodeResponse(String httpResponse) 
	{
		this.response = new SMSEstimatecost_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	public void createParams()
	{
		this.params = new HashMap<String, Object>();
		this.params.put("message", this.messageText);
		this.params.put("dest_msisdns", this.destMsisdns);
	}

	//getters
	/**
	 * @return The msisdns of the recipient
	 */
	public Vector<String> getDestMsisdns() 
	{
		return this.destMsisdns;
	}

	/**
	 * @return The text of the message
	 */
	public String getMessageText()
	{
		return this.messageText;
	}

	//setters
	/**
	 * @param destMsisdns Msisdns of the recipient
	 */
	public void setDestMsisdns(Vector<String> destMsisdns) 
	{
		this.destMsisdns = destMsisdns;
	}

	/**
	 * @param messageText Text of the message
	 */
	public void setMessageText(String messageText) 
	{
		this.messageText = messageText;
	}

}
