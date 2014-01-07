package com.geodrop.DropOut;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;

/**
 * Response to a <CODE>SMSEstimatecost</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSEstimatecost_Response extends GeodropResponse 
{
	/**
	 * Estimate of the final cost of the message
	 */
	private int estimateCost;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return "estimate cost: " + this.estimateCost;
	}

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
				this.estimateCost = Integer.parseInt(doc.getElementsByTagName("ESTIMATECOST").item(0).getTextContent());
			}
			catch(NumberFormatException enf)
			{
				this.estimateCost = 0;
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
	 * @return The estimate cost
	 */
	public int getEstimateCost() 
	{
		return this.estimateCost;
	}

}
