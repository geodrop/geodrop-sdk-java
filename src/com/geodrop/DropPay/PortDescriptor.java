/**
 * 
 */
package com.geodrop.DropPay;

import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Request used to return the full Port configuration's properties
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class PortDescriptor extends GeodropRequest
{
	/**
	 * DropPay port id
	 */
	private int port;
	
	/**
	 * Creates a new <CODE>PortDescriptor</CODE> instance
	 */
	public PortDescriptor()
	{
		this.initialize(0);
	}
	
	/**
	 * Creates a new <CODE>PortDescriptor</CODE> instance
	 * 
	 * @param port The port number
	 */
	public PortDescriptor(int port)
	{
		this.initialize(port);
	}

	/**
	 * Initialize the request
	 * 
	 * @param port The port number
	 */
	private void initialize(int port)
	{
		this.uri = Uri.PAY_PORT_DESCRIPTOR;
		this.httpMethod = HttpMethod.POST;
		this.contentType = ContentType.RAW;
		this.port = port;
	}

	@Override
	protected boolean decodeResponse(String httpResponse) 
	{
		this.response = new PortDescriptor_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	protected void createParams() 
	{
		this.params = new HashMap<String, Object>();
		if(this.port != 0)
		{
			this.params.put("port", this.port);
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

	//setters
	/**
	 * @param port DropPay port id
	 */
	public void setPort(int port) 
	{
		this.port = port;
	}
}
