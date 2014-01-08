package com.geodrop.DropOut;

import java.util.HashMap;
import java.util.Vector;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;

/**
 * Request used to retrieve the status of any set of SMS messages
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSStatusAdhoc extends GeodropRequest
{
	/**
	 * The request type (<CODE>ADHOC</CODE>)
	 */
	private static String requestType;
	/**
	 * Vector of <CODE>StatusAdhocDest</CODE>,
	 * each <CODE>StatusAdhocDest</CODE>
	 * uniquely identifies a single SMS message in the Geodrop archive
	 */
	private Vector<StatusAdhocDest> dest;

	/**
	 * The client id
	 */
	private String clientId;

	/**
	 * Creates a new <CODE>SMSStatusAdhoc</CODE> instance
	 * 
	 * @param dest The vector of <CODE>StatusAdhocDest</CODE>
	 * @throws Exception If parameters are not valid
	 */
	public SMSStatusAdhoc(Vector<StatusAdhocDest> dest) throws Exception
	{
		this.initialize(dest, null);
	}
	
	/**
	 * Creates a new <CODE>SMSStatusAdhoc</CODE> instance
	 * 
	 * @param dest The vector of <CODE>StatusAdhocDest</CODE>
	 * @param clientId The client ID
	 * @throws Exception If parameters are not valid
	 */
	public SMSStatusAdhoc(Vector<StatusAdhocDest> dest, String clientId) throws Exception
	{
		this.initialize(dest, clientId);
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param dest The vector of <CODE>StatusAdhocDest</CODE>
	 * @param clientId The client ID
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(Vector<StatusAdhocDest> dest, String clientId) throws Exception
	{
		//check msisdns format
		for(int index = 0; index < dest.size(); index++)
		{
			if(!this.checkMsisdnE164Format(dest.get(index).getMsisdn(),true))
			{
				throw new Exception(ErrorType.MALFORMED_MSISDNS);
			}
		}
		//set parameters
		this.uri = Uri.OUT_SMS_STATUS;
		this.httpMethod = HttpMethod.PUT;
		this.contentType = ContentType.XML;
		SMSStatusAdhoc.requestType = StatusRequestType.ADHOC;
		this.templateName = MustacheTemplate.SMS_Status;
		this.dest = dest;
		this.clientId = clientId;
	}
	
	@Override
	protected boolean decodeResponse(String httpResponse)
	{
		this.response = new SMSStatus_Response();
		return this.response.fillParameters(httpResponse);
	}

	@Override
	protected void createParams()
	{
		this.params = new HashMap<String, Object>();
		this.params.put("client_id", this.clientId);
		this.params.put("request_type", SMSStatusAdhoc.requestType);	
		//adhoc
		Vector<HashMap<String, String>> adhoc = new Vector<HashMap<String, String>>();
		for(int index=0; index < this.dest.size(); index++ )
		{
			HashMap<String, String> adhoc_test = new HashMap<String, String>();
			adhoc_test.put("msisdn",this.dest.get(index).getMsisdn());
			adhoc_test.put("orderid",this.dest.get(index).getOrderid());
			adhoc.add(adhoc_test);
		}
		this.params.put("adhoc", adhoc);
	}

	//getters
	/**
	 * @return The vector of <CODE>StatusAdhocDest</CODE>,
	 * each <CODE>StatusAdhocDest</CODE>
	 * uniquely identifies a single SMS message in the Geodrop archive
	 */
	public Vector<StatusAdhocDest> getDest() 
	{
		return this.dest;
	}

	/**
	 * @return The client id
	 */
	public String getClientId()
	{
		return this.clientId;
	}

	//setters
	/**
	 * @param dest The vector of <CODE>StatusAdhocDest</CODE>,
	 * each <CODE>StatusAdhocDest</CODE>
	 * uniquely identifies a single SMS message in the Geodrop archive
	 */
	public void setDest(Vector<StatusAdhocDest> dest) 
	{
		this.dest = dest;
	}

	/**
	 * @param clientId The client id
	 */
	public void setClientId(String clientId) 
	{
		this.clientId = clientId;
	}
}
