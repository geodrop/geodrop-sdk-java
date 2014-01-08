package com.geodrop.DropOut;

/**
 * Uniquely identifies a single SMS message in the Geodrop archive
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 * 
 */
public class StatusAdhocDest
{
	/**
	 * The msisdn
	 */
	private String msisdn;
	/**
	 * The order ID
	 */
	private String orderid;
	
	public StatusAdhocDest(String msisdn, String orderid)
	{
		this.msisdn = msisdn;
		this.orderid = orderid;
	}
	//setters
	/**
	 * @param msisdn The msisdn
	 */
	public void setMsisdn(String msisdn) 
	{
		this.msisdn = msisdn;
	}
	/**
	 * @param orderid The order ID
	 */
	public void setOrderid(String orderid) 
	{
		this.orderid = orderid;
	}
	
	//getters
	/**
	 * @return The msisdn
	 */
	public String getMsisdn() 
	{
		return this.msisdn;
	}
	/**
	 * @return The order ID
	 */
	public String getOrderid() 
	{
		return this.orderid;
	}
}
