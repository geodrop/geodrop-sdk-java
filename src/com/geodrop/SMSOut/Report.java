package com.geodrop.SMSOut;

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
	 * @return The telephone number in E.164 format
	 */
	public String getMsisdn() 
	{
		return this.msisdn;
	}

	/**
	 * @return Status of the request
	 */
	public String getStatus() 
	{
		return this.status;
	}

	/**
	 * @return The job GUID
	 */
	public String getOrderId() 
	{
		return this.orderId;
	}

	//setters
	/**
	 * @param msisdn The telephone number in E.164 format
	 */
	public void setMsisdn(String msisdn) 
	{
		this.msisdn = msisdn;
	}

	/**
	 * @param status Status of the request
	 */
	public void setStatus(String status) 
	{
		this.status = status;
	}

	/**
	 * @param orderId The job GUID
	 */
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}
}
