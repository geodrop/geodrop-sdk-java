package com.geodrop.DropPay;

import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Request used to challenge customer to confirm the telephone number
 * in content provider initiated transaction
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 *
 */
public class PortChallenge extends GeodropRequest
{
	/**
	 * DropPay port id
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
	 * Text to send, encoded in UTF-8, up to 160 characters,
	 * it must contain $$PIN$$ placeholder
	 */
	private String text;
	
	/**
	 * Creates a new <CODE>PortChallenge</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param custom The custom ID
	 * @throws Exception If parameters are not valid
	 */
	public PortChallenge(int port,String msisdn,String text,String custom) throws Exception
	{
		this.initialize(port,msisdn,text,custom);
	}
	
	/**
	 * Creates a new <CODE>PortChallenge</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @throws Exception If parameters are not valid
	 */
	public PortChallenge(int port,String msisdn,String text) throws Exception
	{
		this.initialize(port,msisdn,text,"customFromSDK");
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
		//set parameters
		this.uri = Uri.PAY_PORT_CHALLENGE;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.RAW;
		this.setPort(port);
		this.setMsisdn(msisdn);
		this.setCustom(custom);
		this.setText(text);
	}
	
	@Override
	protected boolean decodeResponse(String httpResponse) 
	{
		this.response = new PortChallenge_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	protected void createParams() 
	{
		this.params = new HashMap<String, Object>();
		this.params.put("port", this.port);
		this.params.put("msisdn", this.msisdn);
		this.params.put("custom",this.custom);
		this.params.put("text", this.text);
	}

	//getters
	/**
	 * @return DropPay port id
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
	 * @return Text to send, encoded in UTF-8, up to 160 characters,
	 * it must contain $$PIN$$ placeholder
	 */
	public String getText() 
	{
		return this.text;
	}

	//setters
	/**
	 * @param port DropPay port id
	 */
	public void setPort(int port) 
	{
		this.port = port;
	}

	/**
	 * @param msisdn Customer phone number in E.164 format (without +)
	 * @throws Exception If parameters are not valid
	 */
	public void setMsisdn(String msisdn) throws Exception 
	{
		//check msisdns format
		if(!this.checkMsisdnE164Format(msisdn,false))
		{
			throw new Exception(ErrorType.MALFORMED_MSISDN);
		}
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
	 * @param text Text to send, encoded in UTF-8, up to 160 characters,
	 * it must contain $$PIN$$ placeholder
	 * @throws Exception If parameters are not valid
	 */
	public void setText(String text) throws Exception 
	{
		//check message text
		if(!text.contains("$$PIN$$"))
		{
			throw new Exception(ErrorType.MALFORMED_TEXT_CHALLENGE);
		}
		this.text = text;
	}
}
