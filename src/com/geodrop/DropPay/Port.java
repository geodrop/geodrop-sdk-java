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
     * An unique name you can assign to port, suitable as keyword in alternative to port number (id)
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
	 * @return The drop
	 */
	public String getDrop() 
	{
		return this.drop;
	}

	/**
	 * @return The alias
	 */
	public String getAlias() 
	{
		return this.alias;
	}

	/**
	 * @return The priceTag
	 */
	public String getPriceTag() 
	{
		return this.priceTag;
	}

	/**
	 * @return The priceValue
	 */
	public float getPriceValue() 
	{
		return this.priceValue;
	}

	/**
	 * @return The type
	 */
	public String getType() 
	{
		return this.type;
	}

	/**
	 * @return The openSince
	 */
	public Date getOpenSince() 
	{
		return this.openSince;
	}

	/**
	 * @return The openUntil
	 */
	public Date getOpenUntil() 
	{
		return this.openUntil;
	}

	/**
	 * @return The openState
	 */
	public String getOpenState() 
	{
		return this.openState;
	}

	/**
	 * @return The mnoList
	 */
	public Vector<Mno> getMnoList() 
	{
		return this.mnoList;
	}

	/**
	 * @return The subscriptionMax
	 */
	public float getSubscriptionMax() 
	{
		return this.subscriptionMax;
	}

	/**
	 * @return The subscriptionPeriod
	 */
	public int getSubscriptionPeriod() 
	{
		return this.subscriptionPeriod;
	}

	/**
	 * @return The listenerList
	 */
	public Vector<Listener> getListenerList() 
	{
		return this.listenerList;
	}

	/**
	 * @param drop The drop to set
	 */
	public void setDrop(String drop) 
	{
		this.drop = drop;
	}

	/**
	 * @param alias The alias to set
	 */
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}

	/**
	 * @param priceTag The priceTag to set
	 */
	public void setPriceTag(String priceTag) 
	{
		this.priceTag = priceTag;
	}

	/**
	 * @param priceValue The priceValue to set
	 */
	public void setPriceValue(float priceValue) 
	{
		this.priceValue = priceValue;
	}

	/**
	 * @param type The type to set
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * @param openSince The openSince to set
	 */
	public void setOpenSince(Date openSince) 
	{
		this.openSince = openSince;
	}

	/**
	 * @param openUntil The openUntil to set
	 */
	public void setOpenUntil(Date openUntil) 
	{
		this.openUntil = openUntil;
	}

	/**
	 * @param openState The openState to set
	 */
	public void setOpenState(String openState) 
	{
		this.openState = openState;
	}

	/**
	 * @param mnoList The mnoList to set
	 */
	public void setMnoList(Vector<Mno> mnoList) 
	{
		this.mnoList = mnoList;
	}

	/**
	 * @param subscriptionMax The subscriptionMax to set
	 */
	public void setSubscriptionMax(float subscriptionMax) 
	{
		this.subscriptionMax = subscriptionMax;
	}

	/**
	 * @param subscriptionPeriod The subscriptionPeriod to set
	 */
	public void setSubscriptionPeriod(int subscriptionPeriod) 
	{
		this.subscriptionPeriod = subscriptionPeriod;
	}

	/**
	 * @param listenerList The listenerList to set
	 */
	public void setListenerList(Vector<Listener> listenerList) 
	{
		this.listenerList = listenerList;
	}
}
