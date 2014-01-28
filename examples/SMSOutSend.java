import java.util.Vector;
import com.geodrop.*;
import com.geodrop.SMSOut.SMSSend;

public class SMSOutSend
{
	public static void main(String[] args) 
	{
		//Geodrop Session
		String applicationId = "";
		String applicationSecret = "";
		String username = "";
		String password = "";
		GeodropSession session = SessionFactory.buildSession_ResourceOwnerPasswordCredentials(applicationId, applicationSecret, username, password);
			
		//SMSSend
		try
		{
			Vector<String> destMsisdns = new Vector<>();
			destMsisdns.add("");
			SMSSend requestSMSSend = new SMSSend(destMsisdns,"message text","sender");
			session.runMethod(requestSMSSend);
			System.out.println("Response: " + requestSMSSend.getResponse().toString());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}