package com.geodrop.DropOut;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.geodrop.ErrorType;
import com.geodrop.GeodropResponse;

/**
 * Response
 * to a <CODE>SMSJobsScheduledStatus</CODE> request,
 * to a <CODE>SMSJobsScheduledModify</CODE> request and
 * to a <CODE>SMSJobsScheduledDelete</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SMSJobsScheduled_Response extends GeodropResponse
{	
	/**
	 * The job id of the job
	 */
	private String jobId;
	/**
	 * “OK” or “KO” values,
	 * indicating respectively the global success or failure of the transaction
	 */
	private String jobTransaction;
	/**
	 * A short description of any problems
	 */
	private String jobCause;
	/**
	 * Date and time used to send the message
	 */
	private Date deferred;
	/**
	 * Text of the message
	 */
	private String messageText;
	/**
	 * The personalized sender
	 */
	private String tpoa;
	/**
	 * The internal code of the error if any
	 */
	private String errorCode;
	/**
	 * The description of the error if any
	 */
	private String errorDescription;

	@Override
	public String toString() 
	{
		return "SMSJobsScheduled_Response [jobId=" + this.jobId
				+ ", jobTransaction=" + this.jobTransaction 
				+ ", jobCause="	+ this.jobCause 
				+ ", deferred=" + this.deferred 
				+ ", messageText=" + this.messageText 
				+ ", tpoa=" + this.tpoa 
				+ ", errorCode=" + this.errorCode
				+ ", errorDescription=" + this.errorDescription + "]";
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
			
			//Job info
			Element jobElement = (Element)doc.getElementsByTagName("JOB").item(0);
			if(jobElement != null)
			{
				try{ this.jobId = jobElement.getAttribute("JOBID"); }catch(Exception e){}
				try{ this.jobTransaction = jobElement.getAttribute("TRANSACTION"); }catch(Exception e){}
				try{ this.jobCause = jobElement.getAttribute("CAUSE"); }catch(Exception e){}
				try{ this.errorCode = jobElement.getAttribute("ERRORCODE"); }catch(Exception e){}
				try{ this.errorDescription = ErrorType.getDropOutSMSJobsScheduledErrorDescription(this.errorCode); }catch(Exception e){}
			}
			
			//deferred time
			Element deferredElement = (Element)doc.getElementsByTagName("DEFERREDTIME").item(0);
			if(deferredElement != null)
			{
				SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				this.deferred = dateParser.parse(deferredElement.getTextContent());
			}
			
			//message text
			Element messageElement = (Element)doc.getElementsByTagName("MESSAGETEXT").item(0);
			if(messageElement != null)
			{
				this.messageText = messageElement.getTextContent();
			}
			
			//tpoa
			Element tpoaElement = (Element)doc.getElementsByTagName("TPOA").item(0);
			if(tpoaElement != null)
			{
				this.tpoa = tpoaElement.getTextContent();
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
	 * @return The job ID
	 */
	public String getJobId() 
	{
		return this.jobId;
	}

	/**
	 * @return The job transaction
	 */
	public String getJobTransaction() 
	{
		return this.jobTransaction;
	}

	/**
	 * @return The job cause
	 */
	public String getJobCause() 
	{
		return this.jobCause;
	}

	/**
	 * @return The deferred time
	 */
	public Date getDeferred() 
	{
		return this.deferred;
	}

	/**
	 * @return The message text
	 */
	public String getMessageText() 
	{
		return this.messageText;
	}

	/**
	 * @return The tpoa
	 */
	public String getTpoa() 
	{
		return this.tpoa;
	}

	/**
	 * @return The error code
	 */
	public String getErrorCode() 
	{
		return this.errorCode;
	}

	/**
	 * @return The error description
	 */
	public String getErrorDescription() 
	{
		return this.errorDescription;
	}
}
