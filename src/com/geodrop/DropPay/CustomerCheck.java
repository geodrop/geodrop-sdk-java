package com.geodrop.DropPay;

import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Request used to return the full status of customer,
 * if the port type is SUBSCRIPTION
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class CustomerCheck extends GeodropRequest
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
	 * Creates a new <CODE>CustomerCheck</CODE> instance
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @throws Exception If parameters are not valid
	 */
	public CustomerCheck(int port,String msisdn) throws Exception
	{
		this.initialize(port, msisdn);
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param port The port number
	 * @param msisdn The msisdn
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(int port,String msisdn) throws Exception
	{
		//check msisdns format
		if(!this.checkMsisdnE164Format(msisdn,false))
		{
			throw new Exception(ErrorType.MALFORMED_MSISDN);
		}
		//set parameters
		this.uri = Uri.PAY_CUSTOMERS_CHECK;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.RAW;
		this.port = port;
		this.msisdn = msisdn;
	}
	
	@Override
	public boolean decodeResponse(String httpResponse) 
	{
		this.response = new CustomerCheck_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	public void createParams() 
	{
		this.params = new HashMap<String, Object>();
		this.params.put("port", this.port);
		this.params.put("msisdn", this.msisdn);
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

}
