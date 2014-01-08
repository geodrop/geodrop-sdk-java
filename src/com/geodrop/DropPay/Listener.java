package com.geodrop.DropPay;

/**
 * Gives information about an event posted by DropPay
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class Listener 
{
	/**
     * Event class
     */
    private String lclass;
    
    /**
     * Event type
     */
    private String type;
    
    /**
     * Listener URI
     */
    private String uri;
    
    Listener(){}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Listener: \n\tlclass=" + this.lclass 
				+ "\n\ttype=" + this.type 
				+ "\n\turi=" + this.uri;
	}

	//getters
	/**
	 * @return The Event class
	 */
	public String getLclass() 
	{
		return this.lclass;
	}

	/**
	 * @return The Event type
	 */
	public String getType() 
	{
		return this.type;
	}

	/**
	 * @return The Listener URI
	 */
	public String getUri() 
	{
		return this.uri;
	}

	//setters
	/**
	 * @param lclass The Event class
	 */
	public void setLclass(String lclass) 
	{
		this.lclass = lclass;
	}

	/**
	 * @param type The Event type
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * @param uri The Listener URI
	 */
	public void setUri(String uri) 
	{
		this.uri = uri;
	}
}
