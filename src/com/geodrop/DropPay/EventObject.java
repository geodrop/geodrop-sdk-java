package com.geodrop.DropPay;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;


/**
 * It is used to parser the Events.
 * Events are asynchronous messages posted to the URLs you previously communicated to Geodrop.
 * In order to catch an event posted by DropPay you must provide us with one or more URLs we call Listeners.
 * 
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class EventObject 
{	
	/**
	 * The Event Class 
	 * (it can be: Message, Call, Click, Billing, Customer, Kit)
	 */
	private String class_event;
	/**
	 * The Event type
	 * (it can be: MO, DLR, IN, ACK, SUBSCRIBE, UNSUBSCRIBE, SUNSPENSION, DETECT, SUBSCRIPTION, BILLING)
	 */
	private String type;
	/**
	 * The port
	 */
	private int port;
	/**
	 * The time
	 */
	private Date time;
	/**
	 * The order ID
	 */
	private int order;
	/**
	 * The session ID
	 */
	private String session;
	/**
	 * The message characteristics
	 */
	private Message message;
	/**
	 * The call characteristics
	 */
	private Call call;
	/**
	 * The click characteristics
	 */
	private Click click;
	/**
	 * The subscriber characteristics
	 */
	private Subscriber subscriber;
	/**
	 * The detection characteristics
	 */
	private Detection detection;

	//Inner classes
	public class Message 
	{
		private String msisdn;
		private String mno;
		private String shortcode;
		private int order;
		private String id;
		private String text;
		private String status;
		private String description;
		private String session;
		
		/**
		 * @return the msisdn
		 */
		public String getMsisdn() 
		{
			return this.msisdn;
		}

		/**
		 * @return the mno
		 */
		public String getMno() 
		{
			return this.mno;
		}

		/**
		 * @return the shortcode
		 */
		public String getShortcode() 
		{
			return this.shortcode;
		}

		/**
		 * @return the order
		 */
		public int getOrder() 
		{
			return this.order;
		}

		/**
		 * @return the id
		 */
		public String getId()
		{
			return this.id;
		}

		/**
		 * @return the text
		 */
		public String getText() 
		{
			return this.text;
		}

		/**
		 * @return the status
		 */
		public String getStatus() 
		{
			return this.status;
		}

		/**
		 * @return the description
		 */
		public String getDescription() 
		{
			return this.description;
		}

		/**
		 * @return the session
		 */
		public String getSession() 
		{
			return this.session;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() 
		{
			return "Message:"
					+ "\n\tmsisdn=" + this.msisdn 
					+ "\n\tmno=" + this.mno
					+ "\n\tshortcode=" + this.shortcode 
					+ "\n\torder=" + this.order 
					+ "\n\tid=" + this.id 
					+ "\n\ttext=" + this.text 
					+ "\n\tstatus=" + this.status
					+ "\n\tdescription=" + this.description 
					+ "\n\tsession=" + this.session;
		}
	}
	
	public class Call 
	{
		private String msisdn;
		private String mno;
		private int order;
		
		/**
		 * @return the msisdn
		 */
		public String getMsisdn()
		{
			return this.msisdn;
		}

		/**
		 * @return the mno
		 */
		public String getMno()
		{
			return this.mno;
		}

		/**
		 * @return the order
		 */
		public int getOrder()
		{
			return this.order;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() 
		{
			return "Call:" 
					+ "\n\tmsisdn=" + this.msisdn 
					+ "\n\tmno=" + this.mno 
					+ "\n\torder=" + this.order;
		}
	}
	
	public class Click 
	{
		private String msisdn;
		private String mno;
		private int media;
		
		/**
		 * @return the msisdn
		 */
		public String getMsisdn() 
		{
			return this.msisdn;
		}

		/**
		 * @return the mno
		 */
		public String getMno() 
		{
			return this.mno;
		}

		/**
		 * @return the media
		 */
		public int getMedia() 
		{
			return this.media;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() 
		{
			return "Click: "
					+ "\n\tmsisdn=" + this.msisdn 
					+ "\n\tmno=" + this.mno 
					+ "\n\tmedia=" + this.media;
		}
	}

	public class Subscriber 
	{
		private String msisdn;
		private String id;
		
		/**
		 * @return the msisdn
		 */
		public String getMsisdn()
		{
			return this.msisdn;
		}

		/**
		 * @return the id
		 */
		public String getId()
		{
			return this.id;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() 
		{
			return "Subscriber:" 
					+ "\n\tmsisdn=" + this.msisdn 
					+ "\n\tid=" + this.id;
		}		
	}
	
	public class Detection 
	{
		private String msisdn;
		private String mno;
		private String session;
		
		/**
		 * @return the msisdn
		 */
		public String getMsisdn() 
		{
			return this.msisdn;
		}

		/**
		 * @return the mno
		 */
		public String getMno() 
		{
			return this.mno;
		}

		/**
		 * @return the session
		 */
		public String getSession()
		{
			return this.session;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() 
		{
			return "Detection: "
					+ "\n\tmsisdn=" + this.msisdn 
					+ "\n\tmno=" + this.mno
					+ "\n\tsession=" + this.session;
		}
	}
	//Builder
	/**
	 * Creates a new <CODE>EventObject</CODE> instance
	 * 
	 * @param xmlString The event to be notified encoded in XML
	 */
	public EventObject(String xmlString)
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xmlString));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			
			SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			
			//Event info
			Element eventElement = (Element) doc.getElementsByTagName("event").item(0);
			try{ this.class_event = eventElement.getAttribute("class"); }catch(Exception e){}
			try{ this.type = eventElement.getAttribute("type"); }catch(Exception e){}
			try{ this.port = Integer.parseInt(eventElement.getAttribute("port")); }catch(Exception e){}
			try{ this.time = dateParser.parse(eventElement.getAttribute("time")); }catch(Exception e){}
			try{ this.order = Integer.parseInt(eventElement.getAttribute("order")); }catch(Exception e){}
			try{ this.session = eventElement.getAttribute("session"); }catch(Exception e){}
			
			//Message info
			Element messageElement = (Element) eventElement.getElementsByTagName("message").item(0);
			this.message = new Message();
			try{ this.message.msisdn = messageElement.getAttribute("msisdn"); }catch(Exception e){}
			try{ this.message.mno = messageElement.getAttribute("mno"); }catch(Exception e){}
			try{ this.message.shortcode = messageElement.getAttribute("shortcode"); }catch(Exception e){}
			try{ this.message.order = Integer.parseInt(messageElement.getAttribute("order")); }catch(Exception e){}
			try{ this.message.id = messageElement.getAttribute("id"); }catch(Exception e){}
			try{ this.message.session = messageElement.getAttribute("session"); }catch(Exception e){}
			try{ this.message.status = messageElement.getElementsByTagName("status").item(0).getTextContent(); }catch(Exception e){}
			try{ this.message.description = messageElement.getElementsByTagName("description").item(0).getTextContent(); }catch(Exception e){}
			if(this.message.status == null && this.message.description == null)
			{
				try{ this.message.text = messageElement.getTextContent(); }catch(Exception e){}
			}
			
			//Call info
			Element callElement = (Element) eventElement.getElementsByTagName("call").item(0);
			this.call = new Call();
			try{ this.call.msisdn = callElement.getAttribute("msisdn"); }catch(Exception e){}
			try{ this.call.mno = callElement.getAttribute("mno"); }catch(Exception e){}
			try{ this.call.order = Integer.parseInt(callElement.getAttribute("order")); }catch(Exception e){}
			
			//Click info
			Element clickElement = (Element) eventElement.getElementsByTagName("click").item(0);
			this.click = new Click();
			try{ this.click.msisdn = clickElement.getAttribute("msisdn"); }catch(Exception e){}
			try{ this.click.mno = clickElement.getAttribute("mno"); }catch(Exception e){}
			try{ this.click.media = Integer.parseInt(clickElement.getAttribute("media")); }catch(Exception e){}
			
			//Subscriber info
			Element subscriberElement = (Element) eventElement.getElementsByTagName("subscriber").item(0);
			this.subscriber = new Subscriber();
			try{ this.subscriber.msisdn = subscriberElement.getAttribute("msisdn"); }catch(Exception e){}
			try{ this.subscriber.id = subscriberElement.getAttribute("id"); }catch(Exception e){}
			
			//Detection info
			Element detectionElement = (Element) eventElement.getElementsByTagName("detection").item(0);
			this.detection = new Detection();
			try{ this.detection.msisdn = detectionElement.getAttribute("msisdn"); }catch(Exception e){}
			try{ this.detection.mno = detectionElement.getAttribute("mno"); }catch(Exception e){}
			try{ this.detection.session = detectionElement.getAttribute("session"); }catch(Exception e){}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return The Event Class 
	 * (it can be: Message, Call, Click, Billing, Customer, Kit)
	 */
	public String getClass_event() 
	{
		return this.class_event;
	}

	/**
	 * @return The Event type
	 * (it can be: MO, DLR, IN, ACK, SUBSCRIBE, UNSUBSCRIBE, SUNSPENSION, DETECT, SUBSCRIPTION, BILLING)
	 */
	public String getType() 
	{
		return this.type;
	}

	/**
	 * @return The port
	 */
	public int getPort() 
	{
		return this.port;
	}

	/**
	 * @return The time
	 */
	public Date getTime() 
	{
		return this.time;
	}

	/**
	 * @return The order ID
	 */
	public int getOrder() 
	{
		return this.order;
	}

	/**
	 * @return The session ID
	 */
	public String getSession() 
	{
		return this.session;
	}

	/**
	 * @return The message characteristics
	 */
	public Message getMessage() 
	{
		return this.message;
	}

	/**
	 * @return The call characteristics
	 */
	public Call getCall() 
	{
		return this.call;
	}

	/**
	 * @return The click characteristics
	 */
	public Click getClick() 
	{
		return this.click;
	}

	/**
	 * @return The subscriber characteristics
	 */
	public Subscriber getSubscriber() 
	{
		return this.subscriber;
	}

	/**
	 * @return The detection characteristics
	 */
	public Detection getDetection() 
	{
		return this.detection;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "EventObject: "
				+ "\n\tclass_event=" + this.class_event
				+ "\n\ttype=" + this.type
				+ "\n\tport=" + this.port 
				+ "\n\ttime=" + this.time.toString()
				+ "\n\torder=" + this.order
				+ "\n\tsession=" + this.session 
				+ "\nmessage=" + this.message 
				+ "\ncall="	+ this.call 
				+ "\nclick=" + this.click 
				+ "\nsubscriber=" + this.subscriber
				+ "\ndetection=" + this.detection;
	}
}
