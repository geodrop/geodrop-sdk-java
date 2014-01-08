/**
 * Used in the header of an http request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
package com.geodrop;

public final class ContentType 
{
    /**
     * Used when the content is JSON
     */
	public static final String JSON = "application/json";
    /**
     * Used when the content is XML
     */
	public static final String XML = "application/xml";
    /**
     * Used when the content is RAW
     */
	public static final String RAW = "application/x-www-form-urlencoded";
	
	ContentType(){}
}
