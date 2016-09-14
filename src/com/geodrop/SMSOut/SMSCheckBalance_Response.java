package com.geodrop.SMSOut;

import java.io.StringReader;
import java.math.BigInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;

/**
 * Response to a <CODE>SMSCheckBalance</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSCheckBalance_Response extends GeodropResponse 
{
	/**
	 * Estimate of the final cost of the message
	 */
	private BigInteger balance;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return "balance: " + this.balance;
	}

	
	/**
	 * 
	 */
	SMSCheckBalance_Response() {}


	@Override
	public boolean fillParameters(String httpResponse) 
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(httpResponse));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			try
			{
				Element userElement = (Element) doc.getElementsByTagName("USER").item(0);
				String credit = userElement.getAttribute("credit");
				this.balance = new BigInteger(credit);
			}
			catch(NumberFormatException enf)
			{
				this.balance = new BigInteger("0");
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	//getters
	/**
	 * @return Balance of the user
	 */
	public BigInteger getBalance() 
	{
		return this.balance;
	}

}
