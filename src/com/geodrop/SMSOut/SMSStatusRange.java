package com.geodrop.SMSOut;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.geodrop.ContentType;
import com.geodrop.ErrorType;
import com.geodrop.GeodropRequest;
import com.geodrop.HttpMethod;
import com.geodrop.Uri;
import com.geodrop.SMSOut.MustacheTemplate;
import com.geodrop.SMSOut.SMSStatusRange;
import com.geodrop.SMSOut.SMSStatus_Response;
import com.geodrop.SMSOut.StatusRequestType;

/**
 * Request used to retrieve
 * the status of all SMS messages sent in a given time interval.
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSStatusRange extends GeodropRequest
{
	/**
	 * The request type (<CODE>RANGE</CODE>)
	 */
	private static String requestType;
	/**
	 * End date of the time interval
	 */
	private Date rangeDateTo;
	/**
	 * Start date of the time interval
	 */
	private Date rangeDateFrom;
	/**
	 * Used to paginate the result,
	 * it consist of two integers separated by a comma,
	 * the first one indicates the position of the first required result
	 * and the second the total number of result to return
	 */
	private String rangeLimit;
	/**
	 * The client id
	 */
	private String clientId;

	/**
	 * Creates a new <CODE>SMSStatusRange</CODE> instance
	 * 
	 * @param rangeDateFrom Start date of the time interval
	 * @param rangeDateTo End date of the time interval
	 * @throws Exception If parameters are not valid
	 */
	public SMSStatusRange(Date rangeDateFrom,Date rangeDateTo) throws Exception
	{
		this.initialize(rangeDateFrom,rangeDateTo,null,"0,100");
	}
	
	/**
	 * Creates a new <CODE>SMSStatusRange</CODE> instance
	 * 
	 * @param rangeDateFrom Start date of the time interval
	 * @param rangeDateTo End date of the time interval
	 * @param clientId The client ID
	 * @throws Exception If parameters are not valid
	 */
	public SMSStatusRange(Date rangeDateFrom,Date rangeDateTo,String clientId) throws Exception
	{
		this.initialize(rangeDateFrom,rangeDateTo,clientId,"0,100");
	}
	
	/**
	 * Creates a new <CODE>SMSStatusRange</CODE> instance
	 * 
	 * @param rangeDateFrom Start date of the time interval
	 * @param rangeDateTo End date of the time interval
	 * @param clientId The client ID
	 * @param rangeLimit The limit
	 * @throws Exception If parameters are not valid
	 */
	public SMSStatusRange(Date rangeDateFrom,Date rangeDateTo,String clientId,String rangeLimit) throws Exception
	{
		this.initialize(rangeDateFrom,rangeDateTo,clientId,rangeLimit);
	}
	
	/**
	 * Initialize the request
	 * 
	 * @param rangeDateFrom Start date of the time interval
	 * @param rangeDateTo End date of the time interval
	 * @param clientId The client ID
	 * @param rangeLimit The limit
	 * @throws Exception If parameters are not valid
	 */
	private void initialize(Date rangeDateFrom,Date rangeDateTo,String clientId,String rangeLimit) throws Exception
	{
		//set parameters
		this.uri = Uri.OUT_SMS_STATUS;
		this.httpMethod = HttpMethod.PUT;
		this.contentType = ContentType.XML;
		SMSStatusRange.requestType = StatusRequestType.RANGE;
		this.templateName = MustacheTemplate.SMS_Status;
		this.setRangeDateFrom(rangeDateFrom);
		this.setRangeDateTo(rangeDateTo);
		this.setClientId(clientId);
		this.setRangeLimit(rangeLimit);
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
		this.params.put("request_type", SMSStatusRange.requestType);	
		//range
		HashMap<String, String> range = new HashMap<String, String>();
		SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		range.put("range_date_from", dateParser.format(this.rangeDateFrom));
		range.put("range_date_to", dateParser.format(this.rangeDateTo));
		range.put("range_limit", this.rangeLimit);
		this.params.put("range", range);
	}

	//getters
	/**
	 * @return End date of the time interval
	 */
	public Date getRangeDateTo() 
	{
		return this.rangeDateTo;
	}

	/**
	 * @return Start date of the time interval
	 */
	public Date getRangeDateFrom() 
	{
		return this.rangeDateFrom;
	}

	/**
	 * @return The limit used to paginate the result,
	 * it consist of two integers separated by a comma,
	 * the first one indicates the position of the first required result
	 * and the second the total number of result to return
	 */
	public String getRangeLimit() 
	{
		return this.rangeLimit;
	}

	/**
	 * @return The client ID
	 */
	public String getClientId() 
	{
		return this.clientId;
	}

	//setters
	/**
	 * @param rangeDateTo End date of the time interval
	 */
	public void setRangeDateTo(Date rangeDateTo) 
	{
		this.rangeDateTo = rangeDateTo;
	}

	/**
	 * @param rangeDateFrom Start date of the time interval
	 */
	public void setRangeDateFrom(Date rangeDateFrom) 
	{
		this.rangeDateFrom = rangeDateFrom;
	}

	/**
	 * @param rangeLimit The limit used to paginate the result,
	 * it consist of two integers separated by a comma,
	 * the first one indicates the position of the first required result
	 * and the second the total number of result to return
	 * @throws Exception If parameters are not valid
	 */
	public void setRangeLimit(String rangeLimit) throws Exception 
	{
		//check limit format
		if(!this.checkLimitFormat(rangeLimit))
		{
			throw new Exception(ErrorType.MALFORMED_LIMIT);
		}
		this.rangeLimit = rangeLimit;
	}

	/**
	 * @param clientId The client ID
	 */
	public void setClientId(String clientId) 
	{
		this.clientId = clientId;
	}
}
