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
 * Request to send SMS messages
 * specifying a text for the message,
 * a list of recipients and a personalized sender
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSSend_Response extends GeodropResponse 
{
	/**
	 * Code that indicates the result of the request, it is set to 0 on success
	 */
	private int code;
	/**
	 * Description of error
	 */
	private String description;
	/**
	 * Total of the requested recipients
	 */
	private int reportRequested;
	/**
	 * Total of the effectively sent SMS messages
	 */
	private int reportPosted;
	/**
	 * Contains a <CODE>Report</CODE> for each recipient's mobile phone number
	 */
	private Vector<Report> reportList;
	  
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
			
			//Bad info
			Element badElement = (Element) doc.getElementsByTagName("BAD").item(0);
			if(badElement != null)
			{
				try{ this.code = Integer.parseInt(badElement.getAttribute("code")); }catch(Exception e){}
				try{ this.description = badElement.getAttribute("description"); }catch(Exception e){}
			}
			else
			{
				this.code = 0;
				this.description = "ok";
			}
			
			//Report info
			Element reportElement = (Element) doc.getElementsByTagName("REPORT").item(0);
			if(reportElement != null)
			{
				try{ this.reportRequested = Integer.parseInt(reportElement.getAttribute("requested")); }catch(Exception e){}
				try{ this.reportPosted = Integer.parseInt(reportElement.getAttribute("posted")); }catch(Exception e){}
				this.reportList = fillReportParameters(reportElement);
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
		return "SMSSend_Response [code=" + this.code 
				+ ", description=" + this.description 
				+ ", reportRequested=" + this.reportRequested
				+ ", reportPosted=" + this.reportPosted 
				+ ", reportList=" + this.reportList + "]";
	}

	//getters
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
	 * @return The report requested
	 */
	public int getReportRequested() 
	{
		return this.reportRequested;
	}

	/**
	 * @return The report posted
	 */
	public int getReportPosted() 
	{
		return this.reportPosted;
	}

	/**
	 * @return The report list
	 */
	public Vector<Report> getReportList() 
	{
		return this.reportList;
	}
}
