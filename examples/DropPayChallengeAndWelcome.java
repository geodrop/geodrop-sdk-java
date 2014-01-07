import com.geodrop.*;
import com.geodrop.DropPay.PortChallenge;
import com.geodrop.DropPay.PortChallenge_Response;
import com.geodrop.DropPay.CustomerWelcome;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DropPayChallengeAndWelcome
{
	public static void main(String[] args) 
	{
		try
		{
			//Geodrop Session
			String applicationId = "";
			String applicationSecret = "";
			String username = "";
			String password = "";
			GeodropSession session = SessionFactory.buildSession_ResourceOwnerPasswordCredentials(applicationId, applicationSecret, username, password);
			
			//Port Challenge
			int port = 0;
			String msisdn = "";
			PortChallenge requestPortChallenge = new PortChallenge(port, msisdn, "Port Challenge: pin: $$PIN$$");
			if(session.runMethod(requestPortChallenge))
			{
				//CustomerWelcome
				int order = ((PortChallenge_Response)requestPortChallenge.getResponse()).getOrder();
				System.out.println("Insert pin: ");
				InputStreamReader converter = new InputStreamReader(System.in);
				BufferedReader in = new BufferedReader(converter);
				String pin = in.readLine();
				CustomerWelcome requestCustomerWelcome = new CustomerWelcome(port,msisdn,"message text",order,pin);
				session.runMethod(requestCustomerWelcome);
				System.out.println("Response: " + requestCustomerWelcome.getResponse().toString());
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}