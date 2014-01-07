package com.geodrop.DropPay;

/**
 * Indicates settings for which the payment port is enabled (see Mobile Network Code)
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class Mno 
{
	/**
     * Short identifier label for network operator
     */
    private String brand;
    /**
     * Double value parameter made of two dot separated integers
     */
    private String mcnc;
    /**
     * Long network operator description
     */
    private String operator;
    /**
     * It is enabled for short messaging features;
     * it is used as sender of short messages pertinent to the payment port,
     * and/or recipient for requests coming from customers (reply messages or purchasing actions)
     */
    private int shortcode;
    
	@Override
	public String toString() 
	{
		return 	"Mno: \n\tbrand=" + this.brand 
				+ "\n\tmcnc="	+ this.mcnc 
				+ "\n\toperator="	+ this.operator 
				+ "\n\tshortcode=" + this.shortcode;
	}

	//getters
	/**
	 * @return The brand
	 */
	public String getBrand() 
	{
		return this.brand;
	}

	/**
	 * @return The mcnc
	 */
	public String getMcnc() 
	{
		return this.mcnc;
	}

	/**
	 * @return The operator
	 */
	public String getOperator() 
	{
		return this.operator;
	}

	/**
	 * @return The shortcode
	 */
	public int getShortcode() 
	{
		return this.shortcode;
	}

	//setters
	/**
	 * @param brand The brand to set
	 */
	public void setBrand(String brand) 
	{
		this.brand = brand;
	}

	/**
	 * @param mcnc The mcnc to set
	 */
	public void setMcnc(String mcnc) 
	{
		this.mcnc = mcnc;
	}

	/**
	 * @param operator The operator to set
	 */
	public void setOperator(String operator) 
	{
		this.operator = operator;
	}

	/**
	 * @param shortcode The shortcode to set
	 */
	public void setShortcode(int shortcode) 
	{
		this.shortcode = shortcode;
	}
}
