package com.geodrop;

import java.util.HashMap;


/**
 * Generic Geodrop request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public abstract class GeodropRequest 
{
	  /**
	   * The request URI
	   */
	  protected String uri;
	  /**
	   * The request method
	   */
	  protected String httpMethod;
	  /**
	   * The request content type
	   */
	  protected String contentType;
	  /**
	   * The <CODE>GeodropResponse</CODE> to the request
	   */
	  protected GeodropResponse response;
	  /**
	   * The parameters of the request
	   */
	  protected HashMap<String,Object> params;
	  
	  /**
	   * The Mustache template name
	   */
	  protected String templateName;
	  
	  /**
	   * Decodes the response
	   * @param httpResponse Response to the http request
	   * @return <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	   */
	  abstract public boolean decodeResponse(String httpResponse);
	  
	  /**
	   * Creates the parameters for the http request
	   */
	  abstract public void createParams();
	    
	  /**
	   * Checks if the format of the msisdn is E.164;
	   * plan-conforming numbers are limited to a maximum of 15 digits
	   * usually prefixed with the character +
	   *
	   * @param msisdn Msisdn to check
	   * @param plus <CODE>true</CODE> if the msisdn must be preceded by "+",
	   * <CODE>false</CODE> otherwise
	   * @return <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	   */
	  protected final boolean checkMsisdnE164Format(String msisdn,boolean plus)
	  {
		  String msisdnDigit = null;
		  if(plus)
		  {
			  if(!msisdn.startsWith("+"))
			  {
				  return false;
			  }
			  msisdnDigit = msisdn.substring(1);
		  }
		  else
		  {
			  msisdnDigit = msisdn;
		  }
		  if(msisdnDigit.length() <= 15 && this.isNumeric(msisdnDigit))
		  {
			  return true;
		  }
		  return false;
	  }
	  
	  /**
	   * Check if the input string represents a numeric value
	   * @param str The input string
	   * @return <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	   */
	  private boolean isNumeric(String str)
	  {
		  boolean numeric = true;
		  char[] seq = str.toCharArray();
		  for (int i=0; i< seq.length; i++) 
		  {
			  try 
			  {
				  Integer.parseInt(Character.toString(seq[i]));
			  } 
			  catch (Exception e) 
			  {
				  numeric = false;
			  }
		  }
		  return numeric;
	  }
	  
	  /**
	   * Checks the limit format; limit is used to paginate the result,
	   * it consists of two integers separated by a comma,
	   * the first one indicates the position of the first required result
	   * and the second the total number of result to return
	   * 
	   * @param limit Limit to check
	   * @return <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	   */
	  public final boolean checkLimitFormat(String limit)
	  {
		  //int,int
		  String[] limitSplitted = limit.split(",");
		  if(limitSplitted.length != 2)
		  {
			  return false;
		  }
		  if(this.isNumeric(limitSplitted[0]) && this.isNumeric(limitSplitted[1]))
		  {
			  return true;
		  }
		  return false;
	  }
	  
	  //getters
	  /**
	   * @return The URI used for the http request
	   */
	  public String getUri()
	  {
		  return this.uri;
	  }
	  
	  /**
	   * @return The content type
	   */
	  public String getContentType()
	  {
		  return this.contentType;
	  }
	  
	  /**
	   * @return The http method
	   */
	  public String getHttpMethod()
	  {
		  return this.httpMethod;
	  }
	  
	  /**
	   * @return The parameters
	   */
	  public HashMap<String, Object> getParams() 
	  {
		return this.params;
	  }

	  /**
	   * @return The response
	   */
	  public GeodropResponse getResponse() 
	  {
		return this.response;
	  }
	  
	  /**
	   * @return The template name
	   */
	  public String getTemplateName() 
	  {
		return this.templateName;
	  }
}
