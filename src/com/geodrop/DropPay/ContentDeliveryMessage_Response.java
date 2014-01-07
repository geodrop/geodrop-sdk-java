package com.geodrop.DropPay;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;

/**
 * Request used to
 * send a content as a smart message free of charge for customer
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 * 
 */
public class ContentDeliveryMessage_Response extends GeodropResponse
{
	/**
	 * To be used to match with notification of listeners
	 */
	private String id;
	/**
	 * Error code
	 */
	private int code;
	/**
	 * Verbose description of the error
	 */
    private String description;
	  
	@Override
	public String toString()
	{
		return "ContentDeliveryMessage_Response: "
				+"id=" + this.id 
				+ ", code=" + this.code
				+ ", description=" + this.description;
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
			
			//pay
			Element payElement = (Element) doc.getElementsByTagName("pay").item(0);
			//send-rsp
			Element rspElement = (Element) payElement.getElementsByTagName("send-rsp").item(0);
			try{ this.code = Integer.parseInt(rspElement.getAttribute("code")); }catch(Exception e){}
			try{ this.description = rspElement.getAttribute("description"); }catch(Exception e){}
			try{ this.id = rspElement.getAttribute("id"); }catch(Exception e){}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//getters
	/**
	 * @return The ID
	 */
	public String getId() 
	{
		return this.id;
	}

	/**
	 * @return The code
	 */
	public int getCode()
	{
		return this.code;
	}

	/**
	 * @return The description
	 */
	public String getDescription() 
	{
		return this.description;
	}
}
