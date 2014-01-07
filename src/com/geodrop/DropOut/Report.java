package com.geodrop.DropOut;

/**
 * Gives information about the recipient's phone number
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class Report 
{
	/**
	 * The telephone number in E.164 format
	 */
	private String msisdn;
	/**
	 * Status of the request
	 */
	private String status;

	 /**
	  * The job GUID
	  */
	private String orderId;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Report: "
				+ "msisdn=" + this.msisdn 
				+ ", status=" + this.status 
				+ ", orderId=" + this.orderId + "\n";
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
	 * @return The status
	 */
	public String getStatus() 
	{
		return this.status;
	}

	/**
	 * @return The orderId
	 */
	public String getOrderId() 
	{
		return this.orderId;
	}

	//setters
	/**
	 * @param msisdn The msisdn to set
	 */
	public void setMsisdn(String msisdn) 
	{
		this.msisdn = msisdn;
	}

	/**
	 * @param status The status to set
	 */
	public void setStatus(String status) 
	{
		this.status = status;
	}

	/**
	 * @param orderId The orderId to set
	 */
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}
}
