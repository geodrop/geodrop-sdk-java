package com.geodrop;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.geodrop.DropOut.Report;

/**
 * Generic Geodrop response
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 * 
 */
public abstract class GeodropResponse 
{ 
	  /**
	   * Performs the parsing of the http response
	   * and fills the field of the <CODE>GeodropResponse</CODE>
	   *
	   * @param httpResponse Response to the http request
	   * 
	   * @return <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	   */
	  abstract public boolean fillParameters(String httpResponse);
	  
	  /**
	   * Performs the parsing of the http response
	   * and fills the field of the <CODE>Report</CODE> elements
	   * @param reportElement The <CODE>Element</CODE> with information of report
	   * @return Vector<Report> The Vector with <CODE>Report</CODE> objects
	   */
	  protected Vector<Report> fillReportParameters(Element reportElement)
	  {	
		//ReportList
		Vector<Report> reportList = new Vector<Report>();
		//Ok
		NodeList okReports = reportElement.getElementsByTagName("OK");
		for(int index = 0; index < okReports.getLength(); index++)
		{
			Report report = new Report();
			try{ report.setMsisdn(((Element)okReports.item(index)).getAttribute("msisdn")); }catch(Exception e){}
			try{ report.setOrderId(((Element)okReports.item(index)).getAttribute("orderid")); }catch(Exception e){}
			try{ report.setStatus(((Element)okReports.item(index)).getAttribute("state")); }catch(Exception e){}
			reportList.add(report);
		}
		//Error
		NodeList errorReports = reportElement.getElementsByTagName("ERROR");
		for(int index = 0; index < errorReports.getLength(); index++)
		{
			Report report = new Report();
			try{ report.setMsisdn(((Element)errorReports.item(index)).getAttribute("msisdn")); }catch(Exception e){}
			try{ report.setOrderId(((Element)errorReports.item(index)).getAttribute("orderid")); }catch(Exception e){}
			try{ report.setStatus(((Element)errorReports.item(index)).getAttribute("state")); }catch(Exception e){}
			reportList.add(report);
		}
		return reportList;
	  }
}
