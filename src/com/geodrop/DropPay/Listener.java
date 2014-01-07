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
	 * @return The lclass
	 */
	public String getLclass() 
	{
		return this.lclass;
	}

	/**
	 * @return The type
	 */
	public String getType() 
	{
		return this.type;
	}

	/**
	 * @return The uri
	 */
	public String getUri() 
	{
		return this.uri;
	}

	//setters
	/**
	 * @param lclass The lclass to set
	 */
	public void setLclass(String lclass) 
	{
		this.lclass = lclass;
	}

	/**
	 * @param type The type to set
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * @param uri The uri to set
	 */
	public void setUri(String uri) 
	{
		this.uri = uri;
	}
}
