package com.geodrop.SMSOut;

import java.util.HashMap;
import java.util.Vector;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;
import com.geodrop.SMSOut.MustacheTemplate;


/**
 * Request used to determine a higher estimate of the final cost of the message
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSCheckBalance extends GeodropRequest
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
    * Creates a new <CODE>SMSCheckBalance</CODE> instance
    * 
    * @throws Exception If parameters are not valid
    */
	public SMSCheckBalance() throws Exception
	{
	    this.initialize();
	}
	
	/**
	 * Initialize the request
	 * 
	 * @throws Exception If parameters are not valid
	 */
	private void initialize() throws Exception
	{
	    //set parameters
	    this.uri = Uri.OUT_SMS_CHECK_BALANCE;
	    this.httpMethod = HttpMethod.POST;
	    this.contentType = ContentType.XML;
	    this.templateName = MustacheTemplate.SMS_Send_Estimatecost;

        Vector<String> destMsisdns = new Vector<String>();
		destMsisdns.add("+390123456789");
	    String messageText = "fake";
	    this.setDestMsisdns(destMsisdns);
	    this.setMessageText(messageText);
	}
	
	@Override
	protected boolean decodeResponse(String httpResponse) 
	{
		this.response = new SMSCheckBalance_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	protected void createParams()
	{
		this.params = new HashMap<String, Object>();
		this.params.put("message", this.messageText);
		this.params.put("dest_msisdns", this.destMsisdns);
	}
	
	//setters
	/**
	 * @param destMsisdns A vector that contains msisdns of the recipients;
	 * each msisdn is in E.164 format with '+'
	 * @throws Exception If parameters are not valid
	 */
	private void setDestMsisdns(Vector<String> destMsisdns) throws Exception 
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
	private void setMessageText(String messageText) 
	{
		this.messageText = messageText;
	}

}
