package com.geodrop.DropPay;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;

/**
 * Response to a <CODE>PortChallenge</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 *
 */
public class PortChallenge_Response extends GeodropResponse 
{
	/**
	 * Customer phone number in E.164 format (without +)
	 */
	private String msisdn;
	/**
	 * DropPay port id
	 */
	private int port;
	/**
	 * Unique CP request id
	 */
	private String custom;
	/**
	 * Error code
	 */
	private int code;
	/**
	 * Verbose description of the error
	 */
	private String description;
	/**
	 * Order id appended by Geodrop
	 */
	private int order;
	  
	
	/**
	 * 
	 */
	PortChallenge_Response(){}

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
			//challenge-rsp
			Element rspElement = (Element) payElement.getElementsByTagName("challenge-rsp").item(0);
			try{ this.msisdn = rspElement.getAttribute("msisdn"); }catch(Exception e){}
			try{ this.port = Integer.parseInt(rspElement.getAttribute("port")); }catch(Exception e){}
			try{ this.custom = rspElement.getAttribute("custom"); }catch(Exception e){}
			//status
			Element statusElement = (Element) rspElement.getElementsByTagName("status").item(0);
			try{ this.code = Integer.parseInt(statusElement.getAttribute("code")); }catch(Exception e){}
			try{ this.description = statusElement.getAttribute("description"); }catch(Exception e){}
			//order
			try{ this.order = Integer.parseInt(rspElement.getElementsByTagName("order").item(0).getTextContent().trim()); }catch(Exception e){}
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "PortChallenge_Response: "+
				" [msisdn=" + this.msisdn 
				+ ", port=" + this.port
				+ ", custom=" + this.custom 
				+ ", code=" + this.code 
				+ ", description=" + this.description
				+ ", order=" + this.order + "]";
	}

	//getters
	/**
	 * @return Customer phone number in E.164 format (without +)
	 */
	public String getMsisdn() 
	{
		return this.msisdn;
	}

	/**
	 * @return DropPay port id
	 */
	public int getPort() 
	{
		return this.port;
	}

	/**
	 * @return Unique CP request id
	 */
	public String getCustom() 
	{
		return this.custom;
	}

	/**
	 * @return Error code
	 */
	public int getCode() 
	{
		return this.code;
	}

	/**
	 * @return Verbose description of the error
	 */
	public String getDescription() 
	{
		return this.description;
	}

	/**
	 * @return Order id appended by Geodrop
	 */
	public int getOrder() 
	{
		return this.order;
	}
}
