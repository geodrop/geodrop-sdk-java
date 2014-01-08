package com.geodrop.DropPay;

import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Response to a <CODE>ContentDeliveryMessage</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class ContentDeliveryMessage extends GeodropRequest
{
	/**
	 * DropPay port ID
	 */
	private int port;
	/**
	 * Customer phone number in E.164 format (without +)
	 */
	private String msisdn;
	/**
	 * Unique CP request id
	 */
	private String custom;
	  
	/**
	 * Text to send, encoded in UTF-8, up to 160 characters
	 */
	private String text;
	  
	/**
	 * Creates a new <CODE>ContentDeliveryMessage</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param custom The custom ID
	 * @throws Exception If parameters are not valid
	 */
	public ContentDeliveryMessage(int port,String msisdn,String text,String custom) throws Exception
	{
		this.initialize(port, msisdn, text, custom);
	}

	/**
	 * Creates a new <CODE>ContentDeliveryMessage</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @throws Exception If parameters are not valid
	 */
	public ContentDeliveryMessage(int port,String msisdn,String text) throws Exception
	{
		this.initialize(port, msisdn, text, "customFromSDK");
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param custom The custom ID
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(int port,String msisdn,String text,String custom) throws Exception
	{
		//check msisdns format
		if(!this.checkMsisdnE164Format(msisdn,false))
		{
			throw new Exception(ErrorType.MALFORMED_MSISDN);
		}
		//set parameters
		this.uri = Uri.PAY_CONTENT_MESSAGE;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.RAW;
		this.port = port;
		this.msisdn = msisdn;
		this.text = text;
		this.custom = custom;
	}

	@Override
	protected boolean decodeResponse(String httpResponse) 
	{
		this.response = new ContentDeliveryMessage_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	protected void createParams() 
	{
		this.params = new HashMap<String, Object>();
		this.params.put("port", this.port);
		this.params.put("msisdn", this.msisdn);
		this.params.put("text", this.text);
		this.params.put("custom", this.custom);
	}

	//getters
	/**
	 * @return DropPay port ID
	 */
	public int getPort() 
	{
		return this.port;
	}

	/**
	 * @return Customer phone number in E.164 format (without +)
	 */
	public String getMsisdn() 
	{
		return this.msisdn;
	}

	/**
	 * @return Unique CP request id
	 */
	public String getCustom() 
	{
		return this.custom;
	}

	/**
	 * @return Text to send, encoded in UTF-8, up to 160 characters
	 */
	public String getText() 
	{
		return this.text;
	}

	//setters
	/**
	 * @param port DropPay port ID
	 */
	public void setPort(int port) 
	{
		this.port = port;
	}

	/**
	 * @param msisdn Customer phone number in E.164 format (without +)
	 */
	public void setMsisdn(String msisdn) 
	{
		this.msisdn = msisdn;
	}

	/**
	 * @param custom Unique CP request id
	 */
	public void setCustom(String custom) 
	{
		this.custom = custom;
	}

	/**
	 * @param text Text to send, encoded in UTF-8, up to 160 characters
	 */
	public void setText(String text) 
	{
		this.text = text;
	}
}
