package com.geodrop.DropPay;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.geodrop.GeodropResponse;

/**
 * Response to a <CODE>PortDescriptor</CODE> request
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 *
 */
public class PortDescriptor_Response extends GeodropResponse
{
	/**
	 * Array of ports
	 */
	private HashMap<Integer,Port> ports;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "PortDescriptor_Response [ports=" + this.ports + "]";
	}

	PortDescriptor_Response(){}
	@Override
	public boolean fillParameters(String httpResponse) 
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		ports = new HashMap<Integer,Port>();
		try
		{
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(httpResponse));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			
			Element portsElement = (Element) doc.getElementsByTagName("ports").item(0);
			
			if(portsElement != null)
			{
				NodeList nodePorts = portsElement.getElementsByTagName("port");
				for(int index = 0; index < nodePorts.getLength(); index++)
				{
					Element portElement = (Element) nodePorts.item(index);
					try
					{
						int idPort = Integer.parseInt(portElement.getAttribute("id"));
						Port port = this.fillPortParameters(portElement);
						ports.put(idPort, port);
					}
					catch(Exception e)
					{}
				}
			}
			else //only one port
			{
				Element portElement = (Element) doc.getElementsByTagName("port").item(0);
				try
				{
					int idPort = Integer.parseInt(portElement.getAttribute("id"));
					Port port = this.fillPortParameters(portElement);
					ports.put(idPort, port);
				}
				catch(Exception e)
				{}
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Performs the parsing of the http response
	 * and fills the field of a single <CODE>Port</CODE> object
	 *
	 * @param portElement Response to the http request, relating to the port
	 *  
	 * @return Returns <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	 */
	private Port fillPortParameters(Element portElement)
	{
		Port port = new Port();
		//port info
		try{ port.setDrop(portElement.getAttribute("drop")); }catch(Exception e){}
		
		//alias info
		try{ port.setAlias((portElement.getElementsByTagName("alias").item(0).getTextContent()).trim()); }catch(Exception e){}
		
		//price info
		Element priceElement = (Element) portElement.getElementsByTagName("price").item(0);
		try{ port.setPriceValue(Float.parseFloat((priceElement.getAttribute("value")).replaceAll(",","."))); }catch(Exception e){}
		try{ port.setPriceTag(priceElement.getAttribute("tag")); }catch(Exception e){}
		
		//type info
		try{ port.setType(portElement.getAttribute("type")); }catch(Exception e){}
		
		//open info
		Element openElement = (Element) portElement.getElementsByTagName("open").item(0);
		SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{ port.setOpenSince(dateParser.parse(openElement.getAttribute("since"))); }catch(Exception e){}
		try{ port.setOpenUntil(dateParser.parse(openElement.getAttribute("until"))); }catch(Exception e){}
		try{ port.setOpenState(openElement.getAttribute("state")); }catch(Exception e){}
		
		//mno list
		NodeList nodeMnos = portElement.getElementsByTagName("mno");
		Vector<Mno> mnos = new Vector<Mno>();
		for(int index = 0; index < nodeMnos.getLength(); index++)
		{
			Element mnoElement = (Element) nodeMnos.item(index);
			Mno mno = new Mno();
			try{ mno.setBrand(mnoElement.getAttribute("brand")); }catch(Exception e){}
			try{ mno.setMcnc(mnoElement.getAttribute("mcnc")); }catch(Exception e){}
			try{ mno.setOperator(mnoElement.getAttribute("operator")); }catch(Exception e){}
			try{ mno.setShortcode(Integer.parseInt(mnoElement.getAttribute("shortcode"))); }catch(Exception e){}
			mnos.add(mno);
		}
		port.setMnoList(mnos);
		
		//subscription info
		Element subscriptionElement = (Element) portElement.getElementsByTagName("subscription").item(0);
		try{ port.setSubscriptionMax(Float.parseFloat((subscriptionElement.getAttribute("max")).replaceAll(",","."))); }catch(Exception e){}
		try{ port.setSubscriptionPeriod(Integer.parseInt(subscriptionElement.getAttribute("period"))); }catch(Exception e){}
		
		//listener list
		Element eventsElement = (Element) portElement.getElementsByTagName("events").item(0);
		NodeList nodeListeners = eventsElement.getElementsByTagName("listener");
		Vector<Listener> listeners = new Vector<Listener>();
		for(int index = 0; index < nodeListeners.getLength(); index++)
		{
			Element listenerElement = (Element) nodeListeners.item(index);
			Listener listener = new Listener();
			try{ listener.setLclass(listenerElement.getAttribute("class")); }catch(Exception e){}
			try{ listener.setType(listenerElement.getAttribute("type")); }catch(Exception e){}
			try{ listener.setUri(listenerElement.getAttribute("URI")); }catch(Exception e){}
			listeners.add(listener);
		}
		port.setListenerList(listeners);
		
		//return
		return port;
	}

	/**
	 * @return Array of ports
	 */
	public HashMap<Integer, Port> getPorts()
	{
		return ports;
	}
}
