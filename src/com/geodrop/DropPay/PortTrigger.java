package com.geodrop.DropPay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Request used to charge all subscribed customers
 * with the previously defined price
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class PortTrigger extends GeodropRequest
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
	 * Text to send, encoded in UTF-8, up to 160 characters
	 */
	private String text;
	/**
	 * Time to schedule trigger, default is now
	 */
	private Date time;
	
	/**
	 * Creates a new <CODE>PortTrigger</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param time The deferred time
	 * @param custom The custom ID
	 * @throws Exception If parameters are not valid
	 */
	public PortTrigger(int port,String msisdn,String text,Date time,String custom) throws Exception
	{
		this.initialize(port, msisdn, text, time, custom);
	}

	/**
	 * Creates a new <CODE>PortTrigger</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param time The deferred time
	 * @throws Exception If parameters are not valid
	 */
	public PortTrigger(int port,String msisdn,String text,Date time) throws Exception
	{
		this.initialize(port, msisdn, text, time, "customFromSDK");
	}

	/**
	 * Creates a new <CODE>PortTrigger</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @throws Exception If parameters are not valid
	 */
	public PortTrigger(int port,String msisdn,String text) throws Exception
	{
		this.initialize(port, msisdn, text, null, "customFromSDK");
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param time The deferred time
	 * @param custom The custom ID
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(int port,String msisdn,String text,Date time,String custom) throws Exception
	{
		//set parameters
		this.uri = Uri.PAY_PORT_TRIGGER;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.RAW;
		this.setPort(port);
		this.setMsisdn(msisdn);
		this.setText(text);
		this.setTime(time);
		this.setCustom(custom);
	}
	  
	@Override
	protected boolean decodeResponse(String httpResponse) 
	{
		this.response = new PortTrigger_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	protected void createParams() 
	{
		this.params = new HashMap<String, Object>();
		this.params.put("port", this.port);
		this.params.put("msisdn", this.msisdn);
		this.params.put("text",this.text);
		this.params.put("custom", this.custom);
		if(this.time != null)
		{	
			SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.params.put("time", dateParser.format(this.time));
		}
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
	 * @return Text to send, encoded in UTF-8, up to 160 characters
	 */
	public String getText() 
	{
		return this.text;
	}

	/**
	 * @return Time to schedule trigger, default is now
	 */
	public Date getTime() 
	{
		return this.time;
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
	 * @param text Text to send, encoded in UTF-8, up to 160 characters
	 */
	public void setText(String text) 
	{
		this.text = text;
	}

	/**
	 * @param time Time to schedule trigger, default is now
	 * @throws Exception If parameters are not valid
	 */
	public void setTime(Date time) throws Exception 
	{
		//check date
		if(time != null)
		{
			if (time.before(new Date()))
			{
				throw new Exception(ErrorType.MALFORMED_OR_PAST_TIME);
			}
		}
		this.time = time;
	}
}
