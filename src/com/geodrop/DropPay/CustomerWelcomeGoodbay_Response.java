package com.geodrop.DropPay;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;

/**
 * Response to a <CODE>CustomerWelcome</CODE> or a <CODE>CustomerGoodbay</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 *
 */
public class CustomerWelcomeGoodbay_Response extends GeodropResponse
{
	/**
	 * Customer phone number in E.164 format (without +)
	 */
	private String msisdn;
	/**
	 * Short identifier label of network operator
	 */
	private String mno;
	/**
	 * DropPay port id
	 */
	private int port;
	/**
	 * Error code
	 */
	private int code;
	/**
	 * Verbose description of the error
	 */
	private String description;
	/**
	 * Subscriber id
	 */
	private int subscriber;
	  
	@Override
	public String toString() 
	{
		return "CustomerWelcomeGoodbay_Response: "
				+ "msisdn=" + this.msisdn 
				+ ", mno=" + this.mno 
				+ ", port=" + this.port 
				+ ", code=" + this.code 
				+ ", description=" + this.description 
				+ ", subscriber=" + this.subscriber;
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
			//rsp
			Element rspElement = (Element) payElement.getElementsByTagName("welcome-rsp").item(0);
			if(rspElement == null)
			{
				rspElement = (Element) payElement.getElementsByTagName("goodbye-rsp").item(0);
			}
			
			if(rspElement != null)
			{
				try{ this.msisdn = rspElement.getAttribute("msisdn"); }catch(Exception e){}
				try{ this.mno = rspElement.getAttribute("mno"); }catch(Exception e){}
				try{ this.port = Integer.parseInt(rspElement.getAttribute("port")); }catch(Exception e){}
				//status
				Element statusElement = (Element) rspElement.getElementsByTagName("status").item(0);
				try{ this.code = Integer.parseInt(statusElement.getAttribute("code")); }catch(Exception e){}
				try{ this.description = statusElement.getAttribute("description"); }catch(Exception e){}
				//subscriber
				try{ this.subscriber = Integer.parseInt(rspElement.getElementsByTagName("subscriber").item(0).getTextContent()); }catch(Exception e){}
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
	 * @return The msisdn
	 */
	public String getMsisdn() 
	{
		return this.msisdn;
	}

	/**
	 * @return The mno
	 */
	public String getMno() 
	{
		return this.mno;
	}

	/**
	 * @return The port
	 */
	public int getPort() 
	{
		return this.port;
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

	/**
	 * @return The subscriber
	 */
	public int getSubscriber() 
	{
		return this.subscriber;
	}
}