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
public class CustomerWelcomeGoodbye_Response extends GeodropResponse
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
	  
	CustomerWelcomeGoodbye_Response() {}
	@Override
	public String toString() 
	{
		return "CustomerWelcomeGoodbye_Response: "
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
	 * @return Customer phone number in E.164 format (without +)
	 */
	public String getMsisdn() 
	{
		return this.msisdn;
	}

	/**
	 * @return Short identifier label of network operator
	 */
	public String getMno() 
	{
		return this.mno;
	}

	/**
	 * @return DropPay port id
	 */
	public int getPort() 
	{
		return this.port;
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
	 * @return Subscriber id
	 */
	public int getSubscriber() 
	{
		return this.subscriber;
	}
}