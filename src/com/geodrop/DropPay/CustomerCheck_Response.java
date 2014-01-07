package com.geodrop.DropPay;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;

/**
 * Response to a <CODE>CustomerCheck</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class CustomerCheck_Response extends GeodropResponse
{
	/**
	 * Customer phone number in E.164 format (without +)
	 */
	private String msisdn;
	/**
	 * DropPay port number
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
	 * Short identifier label of network operator
	 */
	private String mno;
	/**
	 * Status of the port (ACTIVE, NOTACTIVE, SUSPENDED)
	 */
	private String status;
	/**
	 * Subscriber id
	 */
	private int subscriber;
	/**
	 * Subscription date
	 */
	private Date date;
	/**
	 * Status change date
	 */
	private Date updated;
	/**
	 * Subscription category
	 */
	private String category;
	  
	@Override
	public String toString() 
	{
		return "CustomerCheck_Response: "
				+ "msisdn=" + this.msisdn 
				+ ", port=" + this.port
				+ ", code=" + this.code 
				+ ", description=" + this.description 
				+ ", mno=" + this.mno 
				+ ", status=" + this.status 
				+ ", subscriber=" + this.subscriber
				+ ", date=" + this.date 
				+ ", updated=" + this.updated 
				+ ", category=" + this.category;
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
			//check-rsp
			Element rspElement = (Element) payElement.getElementsByTagName("check-rsp").item(0);
			try{ this.code = Integer.parseInt(rspElement.getAttribute("code")); }catch(Exception e){}
			try{ this.description = rspElement.getAttribute("description"); }catch(Exception e){}
			try{ this.mno = rspElement.getAttribute("mno"); }catch(Exception e){}
			try{ this.msisdn = rspElement.getAttribute("msisdn"); }catch(Exception e){}
			try{ this.port = Integer.parseInt(rspElement.getAttribute("port")); }catch(Exception e){}
			//subscription
			Element subscriptionElement = (Element) rspElement.getElementsByTagName("subscription").item(0);
			try{ this.status = subscriptionElement.getElementsByTagName("status").item(0).getTextContent().trim(); }catch(Exception e){}
			try{ this.subscriber = Integer.parseInt(subscriptionElement.getElementsByTagName("subscriber").item(0).getTextContent().trim()); }catch(Exception e){}
			try{ this.category = subscriptionElement.getElementsByTagName("category").item(0).getTextContent().trim(); }catch(Exception e){}
			
			SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(subscriptionElement.getElementsByTagName("date").item(0) != null)
			{
				try{ this.date = dateParser.parse(subscriptionElement.getElementsByTagName("date").item(0).getTextContent().trim()); }catch(Exception e){}
			}
			if(subscriptionElement.getElementsByTagName("updated").item(0) != null)
			{
				try{ this.updated = dateParser.parse(subscriptionElement.getElementsByTagName("updated").item(0).getTextContent().trim()); }catch(Exception e){}
			}
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
	 * @return The msisdn
	 */
	public String getMsisdn() 
	{
		return this.msisdn;
	}

	/**
	 * @return The port number
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
	 * @return The mno
	 */
	public String getMno()
	{
		return this.mno;
	}

	/**
	 * @return The status
	 */
	public String getStatus() 
	{
		return this.status;
	}

	/**
	 * @return The subscriber
	 */
	public int getSubscriber() 
	{
		return this.subscriber;
	}

	/**
	 * @return The date
	 */
	public Date getDate() 
	{
		return this.date;
	}

	/**
	 * @return The updated
	 */
	public Date getUpdated() 
	{
		return this.updated;
	}

	/**
	 * @return The category
	 */
	public String getCategory() 
	{
		return this.category;
	}
}
