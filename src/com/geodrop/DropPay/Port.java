package com.geodrop.DropPay;

import java.util.Date;
import java.util.Vector;

/**
 * Port configuration's properties
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class Port 
{
    /**
     * Specifies the port type (pay or premium)
     */
    private String drop;
    /**
     * An unique name you can assign to port, 
     * suitable as keyword in alternative to port number (id)
     */
    private String alias;
    /**
     * Identifier of price valid for this port
     */
    private String priceTag;
    /**
     * Price value corresponding to the tag
     */
    private float priceValue;
    /**
     * Type of port that can be ONDEMAND or SUBSCRIPTION
     */
    private String type;
    /**
     * Date and time since when port results active
     */
    private Date openSince;
    /**
     * Date and time when port will be disabled
     */
    private Date openUntil;
    /**
     * State of port, can be ACTIVE or SUSPENDED if any compliance issues have been raised
     */
   	private String openState;
   	/**
   	 * Each of MNO item indicates MNO settings for which the payment port is enabled
   	 */
  	private Vector<Mno> mnoList;
	/**
	 * Max amount of money the customer can be charged within the "period"
	 */
	private float subscriptionMax;
	/**
	 * Time window that limit the "max" amount of money the customer can be charged in days
	 */
	private int subscriptionPeriod;
	/**
	 * Settings that content provider can modify during the lifetime of the service,
	 * regarding event notification receiving
	 */
	private Vector<Listener> listenerList;
	  
	Port() {}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Port: \n\tdrop=" + this.drop 
				+ "\n\talias=" + this.alias 
				+ "\n\tpriceTag="	+ this.priceTag 
				+ "\n\tpriceValue=" + this.priceValue 
				+ "\n\ttype=" + this.type
				+ "\n\topenSince=" + this.openSince 
				+ "\n\topenUntil=" + this.openUntil
				+ "\n\topenState=" + this.openState 
				+ "\n\tmnoList=" + this.mnoList
				+ "\n\tsubscriptionMax=" + this.subscriptionMax
				+ "\n\tsubscriptionPeriod=" + this.subscriptionPeriod
				+ "\n\tlistenerList=" + this.listenerList;
	}

	/**
	 * @return The drop that specifies the port type (pay or premium)
	 */
	public String getDrop() 
	{
		return this.drop;
	}

	/**
	 * @return An unique name you can assign to port, 
	 * suitable as keyword in alternative to port number (id)
	 */
	public String getAlias() 
	{
		return this.alias;
	}

	/**
	 * @return Identifier of price valid for this port
	 */
	public String getPriceTag() 
	{
		return this.priceTag;
	}

	/**
	 * @return Price value corresponding to the tag
	 */
	public float getPriceValue() 
	{
		return this.priceValue;
	}

	/**
	 * @return Type of port that can be ONDEMAND or SUBSCRIPTION
	 */
	public String getType() 
	{
		return this.type;
	}

	/**
	 * @return Date and time since when port results active
	 */
	public Date getOpenSince() 
	{
		return this.openSince;
	}

	/**
	 * @return Date and time when port will be disabled
	 */
	public Date getOpenUntil() 
	{
		return this.openUntil;
	}

	/**
	 * @return State of port, 
	 * can be ACTIVE or SUSPENDED if any compliance issues have been raised
	 */
	public String getOpenState() 
	{
		return this.openState;
	}

	/**
	 * @return The mnoList, each of MNO item indicates MNO settings for which 
	 * the payment port is enabled
	 */
	public Vector<Mno> getMnoList() 
	{
		return this.mnoList;
	}

	/**
	 * @return Max amount of money the customer can be charged within the "period"
	 */
	public float getSubscriptionMax() 
	{
		return this.subscriptionMax;
	}

	/**
	 * @return Time window that limit the "max" amount of money the customer 
	 * can be charged in days
	 */
	public int getSubscriptionPeriod() 
	{
		return this.subscriptionPeriod;
	}

	/**
	 * @return Settings that content provider can modify during the lifetime of the service,
	 * regarding event notification receiving
	 */
	public Vector<Listener> getListenerList() 
	{
		return this.listenerList;
	}

	//setters
	/**
	 * @param drop The drop that specifies the port type (pay or premium)
	 */
	public void setDrop(String drop) 
	{
		this.drop = drop;
	}

	/**
	 * @param alias An unique name you can assign to port, 
	 * suitable as keyword in alternative to port number (id)
	 */
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}

	/**
	 * @param priceTag Identifier of price valid for this port
	 */
	public void setPriceTag(String priceTag) 
	{
		this.priceTag = priceTag;
	}

	/**
	 * @param priceValue Price value corresponding to the tag
	 */
	public void setPriceValue(float priceValue) 
	{
		this.priceValue = priceValue;
	}

	/**
	 * @param type Type of port that can be ONDEMAND or SUBSCRIPTION
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * @param openSince Date and time since when port results active
	 */
	public void setOpenSince(Date openSince) 
	{
		this.openSince = openSince;
	}

	/**
	 * @param openUntil Date and time when port will be disabled
	 */
	public void setOpenUntil(Date openUntil) 
	{
		this.openUntil = openUntil;
	}

	/**
	 * @param openState State of port, 
	 * can be ACTIVE or SUSPENDED if any compliance issues have been raised
	 */
	public void setOpenState(String openState) 
	{
		this.openState = openState;
	}

	/**
	 * @param mnoList The mnoList, each of MNO item indicates MNO settings for which 
	 * the payment port is enabled
	 */
	public void setMnoList(Vector<Mno> mnoList) 
	{
		this.mnoList = mnoList;
	}

	/**
	 * @param subscriptionMax Max amount of money the customer can be charged within the "period"
	 */
	public void setSubscriptionMax(float subscriptionMax) 
	{
		this.subscriptionMax = subscriptionMax;
	}

	/**
	 * @param subscriptionPeriod Time window that limit the "max" amount of money the customer 
	 * can be charged in days
	 */
	public void setSubscriptionPeriod(int subscriptionPeriod) 
	{
		this.subscriptionPeriod = subscriptionPeriod;
	}

	/**
	 * @param listenerList Settings that content provider can modify during the lifetime of the service,
	 * regarding event notification receiving
	 */
	public void setListenerList(Vector<Listener> listenerList) 
	{
		this.listenerList = listenerList;
	}
}
