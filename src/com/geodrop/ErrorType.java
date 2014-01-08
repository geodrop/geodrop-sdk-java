package com.geodrop;

/**
 * Contains the description of possible errors
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public final class ErrorType 
{
	public static final String MISSING_PARAMETERS        = "Error: one or more required parameters are not specified!\n";
	public static final String MISSING_REQUEST           = "Error: request must be created!\n";
	public static final String MISSING_ORDERID           = "Error: insert order id!\n";
	public static final String MISSING_JOBID             = "Error: insert job id!\n";
	  
	public static final String MALFORMED_PARAMETERS      = "Error: malformed parameters!\n";
	public static final String MALFORMED_OR_PAST_TIME    = "Error: time is past or malformed!\n";
	public static final String MALFORMED_TIME            = "Error: time is malformed!\n";
	public static final String MALFORMED_LIMIT           = "Error: limit is malformed!\n";
	public static final String MALFORMED_MSISDN          = "Error: msisdn malformed!\n";
	public static final String MALFORMED_MSISDNS         = "Error: msisdns malformed!\n";
	public static final String MALFORMED_ORDERID         = "Error: order id malformed!\n";
	public static final String MALFORMED_SUBSCRIBER      = "Error: subscriber malformed!\n";
	public static final String MALFORMED_TEXT_CHALLENGE  = "Error: text must contain $$PIN$$!\n";
	public static final String MALFORMED_PORT            = "Error: port number must be an integer!\n";
	  
	public static final String TOKEN_NOT_VALID           = "Error: invalid token!\n";
	public static final String AUTHENTICATION_FAILED     = "Error: authentication failed!\n";
	public static final String INVALID_HTTP_RESPONSE     = "Error: invalid http response!\n";
	public static final String RESPONSE_NOT_XML          = "Error: response not be parsed as XML!\n";
	
	ErrorType(){}
	
	/**
     * Returns the verbose description of a DropPay error code
     *
	 * @param code Error code
	 * @return Verbose description of the error
	 */
	public static String getDropPayErrorDescription(String code)
	{
		switch(code)
		{
			case "0":
				return "OK";
			case "-99":
				return "Internal Error";
			case "-1":
				return "Invalid Port ID";
			case "-2":
				return "Invalid msisdn";
			case "-3":
				return "Invalid custom id";
			case "-4":
				return "Invalid text";
			case "-5":
				return "Invalid subscriber id";
			case "-6":
				return "Invalid order id";
			case "-7":
				return "Customer Blacklisted";
			case "-8":
				return "Customer Already Active";
			case "-9":
				return "Customer Not Active";
			case "-10":
				return "Max free ratio message number reached";
			case "-11":
				return "Was not possible subscribe user";
			case "-12":
				return "Was not possible unsubscribe user";
			case "-13":
				return "Invalid Geodrop user";
			case "-14":
				return "Invalid XML with DropPay Geodrop Descriptor";
			case "-15":
				return "Invalid Date Format";
			case "-16":
				return "Invalid content id";
			default:
				return null;
		}
	}
	
	/**
	 * Returns the verbose description of a DropOut
	 * error code of a <CODE>SMSJobsScheduled_Response</CODE>
	 *
	 * @param code Error code
	 * @return Verbose description of the error
	 */
	public static String getDropOutSMSJobsScheduledErrorDescription(String code)
	{
	    switch(code)
	    {
	    	case "0":
	    		return "OK";
	    	case "1":
	    		return "XML malformed";
	    	case "2":
	    		return "XML ok";
	    	case "3":
	    		return "Deferred time date out";
	    	case "4":
	    		return "Deferred time date too old";
	    	case "5":
	    		return "Deferred time bad";
	    	case "6":
	    		return "Text not valid";
	    	case "7":
	    		return "Text too long";
	    	case "8":
	    		return "Text empty";
	    	case "9":
	    		return "Msisdn list empty";
	    	case "10":
	    		return "Msisdn list too long";
	    	case "11":
	    		return "Drop unavailable";
	    	case "12":
	    		return "Message malformed";
	    	case "13":
	    		return "Invalid action";
	    	case "14":
	    		return "Invalid job id";
	    	case "15":
	    		return "Invalid tpoa";
	    	case "16":
	    		return "Invalid tid";
	    	case "17":
	    		return "Internal error";
	    	case "18":
	    		return "Job not found";
	    	case "19":
	    		return "Invalid run range";
	    	case "20":
	    		return "invalid notify url";
	    	default:
	    		return null;
	    }
	}
}