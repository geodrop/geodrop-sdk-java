package com.geodrop.DropPay;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;

/**
 * Response to a <CODE>PortChargePurchases</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 *
 */
public class PortChargePurchases_Response extends GeodropResponse 
{
	/**
	 * Customer phone number in E.164 format (without +)
	 */
	private String msisdn;
	/**
	 * Mno
	 */
	private String mno;
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
	  
	PortChargePurchases_Response(){}
	
	@Override
	public String toString() 
	{
		return "PortChargePurchases_Response: " 
				+ "msisdn=" + this.msisdn 
				+ ", mno=" + this.mno 
				+ ", id=" + this.id 
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
			//charge-rsp
			Element rspElement = (Element) payElement.getElementsByTagName("charge-rsp").item(0);
			try{ this.code = Integer.parseInt(rspElement.getAttribute("code")); }catch(Exception e){}
			try{ this.description = rspElement.getAttribute("description"); }catch(Exception e){}
			try{ this.id = rspElement.getAttribute("id"); }catch(Exception e){}
			try{ this.mno = rspElement.getAttribute("mno"); }catch(Exception e){}
			try{ this.msisdn = rspElement.getAttribute("msisdn"); }catch(Exception e){}
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
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
	 * @return The Mno
	 */
	public String getMno() 
	{
		return this.mno;
	}

	/**
	 * @return The id to be used to match with notification of listeners
	 */
	public String getId()
	{
		return this.id;
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
}
