package com.geodrop.DropPay;

import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Request used to charge customer with the previously defined price
 * if port type is SUBSCRIPTION
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 *
 */
public class PortChargeSubscriptionPurchases extends GeodropRequest
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
	 * Subscriber id required in subscription payments
	 */
	private int subscriber;
	/**
	 * Pin code sent to customer in challenge action,
	 * it is mandatory if transaction was initiated by provider calling challenge,
	 * it is not mandatory if transaction was initiated by customer sending MO
	 */
	private String pin;
	
	/**
	 * Creates a new <CODE>PortChargeSubscriptionPurchases</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param subscriber The subscriber
	 * @param pin The pin
	 * @param custom The custom ID
	 * @throws Exception If parameters are not valid
	 */
	public PortChargeSubscriptionPurchases(int port,String msisdn,String text,int subscriber,String pin,String custom) throws Exception
	{
		this.initialize(port, msisdn, text, subscriber, pin, custom);
	}

	/**
	 * Creates a new <CODE>PortChargeSubscriptionPurchases</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param subscriber The subscriber
	 * @param pin The pin
	 * @throws Exception If parameters are not valid
	 */
	public PortChargeSubscriptionPurchases(int port,String msisdn,String text,int subscriber,String pin) throws Exception
	{
		this.initialize(port, msisdn, text, subscriber, pin, "customFromSDK");
	}
	
	/**
	 * Creates a new <CODE>PortChargeSubscriptionPurchases</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param subscriber The subscriber
	 * @throws Exception If parameters are not valid
	 */
	public PortChargeSubscriptionPurchases(int port,String msisdn,String text,int subscriber) throws Exception
	{
		this.initialize(port, msisdn, text, subscriber, null, "customFromSDK");
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @param text The message text
	 * @param subscriber The subscriber
	 * @param pin The pin
	 * @param custom The custom ID
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(int port,String msisdn,String text,int subscriber,String pin,String custom) throws Exception
	{
		//check msisdns format
		if(!this.checkMsisdnE164Format(msisdn,false))
		{
			throw new Exception(ErrorType.MALFORMED_MSISDN);
		}
		//set parameters
		this.uri = Uri.PAY_PORT_CHARGE;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.RAW;
		this.port = port;
		this.msisdn = msisdn;
		this.text = text;
		this.subscriber = subscriber;
		this.pin = pin;
		this.custom = custom;
	}
	
	@Override
	public boolean decodeResponse(String httpResponse) 
	{
		this.response = new PortChargePurchases_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	public void createParams()
	{
		this.params = new HashMap<String, Object>();
		this.params.put("port", this.port);
		this.params.put("msisdn",this.msisdn);
		this.params.put("text", this.text);
		this.params.put("subscriber", this.subscriber);
		this.params.put("pin", this.pin);
		this.params.put("custom", this.custom);
	}

	//getters
	/**
	 * @return The port number
	 */
	public int getPort() 
	{
		return this.port;
	}

	/**
	 * @return The msisdn
	 */
	public String getMsisdn() 
	{
		return this.msisdn;
	}

	/**
	 * @return The custom ID
	 */
	public String getCustom() 
	{
		return this.custom;
	}

	/**
	 * @return The message text
	 */
	public String getText() 
	{
		return this.text;
	}

	/**
	 * @return The subscriber
	 */
	public int getSubscriber() 
	{
		return this.subscriber;
	}

	/**
	 * @return The pin
	 */
	public String getPin() 
	{
		return this.pin;
	}

	//setters
	/**
	 * @param port The port number
	 */
	public void setPort(int port) 
	{
		this.port = port;
	}

	/**
	 * @param msisdn The msisdn
	 */
	public void setMsisdn(String msisdn) 
	{
		this.msisdn = msisdn;
	}

	/**
	 * @param custom The custom ID
	 */
	public void setCustom(String custom) 
	{
		this.custom = custom;
	}

	/**
	 * @param text The message text
	 */
	public void setText(String text) 
	{
		this.text = text;
	}

	/**
	 * @param subscriber The subscriber
	 */
	public void setSubscriber(int subscriber) 
	{
		this.subscriber = subscriber;
	}

	/**
	 * @param pin The pin
	 */
	public void setPin(String pin) 
	{
		this.pin = pin;
	}
}
