package com.geodrop.DropOut;

import java.io.StringReader;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;
/**
 * Response
 * to a <CODE>SMSStatusJob</CODE> request,
 * to a <CODE>SMSStatusAdhoc</CODE> request and
 * to a <CODE>SMSStatusRange</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 *
 */
public class SMSStatus_Response extends GeodropResponse
{
	/**
	 * The client id
	 */
	private String clientId;
	/**
	 * The total of the requested recipients 
	 */
	private int reportRequested;
	/**
	 * Number of effectively sent SMS messages
	 */
	private int reportPosted;
	/**
	 * Contains a <CODE>Report</CODE> for each recipient's mobile phone number
	 */
	private Vector<Report> reportList;
	
	SMSStatus_Response() {}
	@Override
	public String toString() 
	{
		return "SMSStatus_Response: "
				+ "\n\tclientId=" + this.clientId
				+ "\n\treportRequested=" + this.reportRequested 
				+ "\n\treportPosted="	+ this.reportPosted 
				+ "\n\treportList=\n" + this.reportList;
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
			
			//Client ID info
			try{ this.clientId = doc.getElementsByTagName("ID_CLIENT").item(0).getTextContent(); }catch(Exception e){}
			
			//Report info
			Element reportElement = (Element) doc.getElementsByTagName("REPORT").item(0);
			if(reportElement != null)
			{
				try{ this.reportRequested = Integer.parseInt(reportElement.getAttribute("requested")); }catch(Exception e){}
				try{ this.reportPosted = Integer.parseInt(reportElement.getAttribute("posted")); }catch(Exception e){}
				try{ this.reportList = fillReportParameters(reportElement); }catch(Exception e){}
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
	 * @return The client id
	 */
	public String getClientId() 
	{
		return this.clientId;
	}

	/**
	 * @return The total of the requested recipients 
	 */
	public int getReportRequested() 
	{
		return this.reportRequested;
	}

	/**
	 * @return Number of effectively sent SMS messages
	 */
	public int getReportPosted() 
	{
		return this.reportPosted;
	}

	/**
	 * @return A vector that Contains a <CODE>Report</CODE> 
	 * for each recipient's mobile phone number
	 */
	public Vector<Report> getReportList() 
	{
		return this.reportList;
	}
}
