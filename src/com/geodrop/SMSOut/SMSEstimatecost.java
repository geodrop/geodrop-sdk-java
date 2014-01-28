package com.geodrop.SMSOut;

import java.util.HashMap;
import java.util.Vector;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;
import com.geodrop.SMSOut.MustacheTemplate;
import com.geodrop.SMSOut.SMSEstimatecost_Response;

/**
 * Request used to determine a higher estimate of the final cost of the message
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSEstimatecost extends GeodropRequest
{
	/**
	 * Contains msisdns of the recipients;
	 * each msisdn is in E.164 format with '+'
	 */
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
		//set parameters
		this.uri = Uri.OUT_SMS_ESTIMATECOST;
	    this.httpMethod = HttpMethod.POST;
	    this.contentType = ContentType.XML;
	    this.templateName = MustacheTemplate.SMS_Send_Estimatecost;
	    this.setDestMsisdns(destMsisdns);
	    this.setMessageText(messageText);
	}
	
	@Override
	protected boolean decodeResponse(String httpResponse) 
	{
		this.response = new SMSEstimatecost_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	protected void createParams()
	{
		this.params = new HashMap<String, Object>();
		this.params.put("message", this.messageText);
		this.params.put("dest_msisdns", this.destMsisdns);
	}

	//getters
	/**
	 * @return A vector that contains msisdns of the recipients;
	 * each msisdn is in E.164 format with '+'
	 */
	public Vector<String> getDestMsisdns() 
	{
		return this.destMsisdns;
	}

	/**
	 * @return The text of the message to send
	 */
	public String getMessageText()
	{
		return this.messageText;
	}

	//setters
	/**
	 * @param destMsisdns A vector that contains msisdns of the recipients;
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
	 * @param messageText The text of the message to send
	 */
	public void setMessageText(String messageText) 
	{
		this.messageText = messageText;
	}

}
