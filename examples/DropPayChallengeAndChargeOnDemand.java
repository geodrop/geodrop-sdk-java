import geodropSDKjava.*;
import geodropSDKjava.dropPay.PortChallenge;
import geodropSDKjava.dropPay.PortChallenge_Response;
import geodropSDKjava.dropPay.PortChargeOnDemandPurchases;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DropPayChallengeAndChargeOnDemand
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
				//Port Charge
				int order = ((PortChallenge_Response)requestPortChallenge.getResponse()).getOrder();
				System.out.println("Insert pin: ");
				InputStreamReader converter = new InputStreamReader(System.in);
				BufferedReader in = new BufferedReader(converter);
				String pin = in.readLine();
				PortChargeOnDemandPurchases requestPortChargeOnDemandPurchases = new PortChargeOnDemandPurchases(port,msisdn,"message text",order,pin);
				session.runMethod(requestPortChargeOnDemandPurchases);
				System.out.println("Response: " + requestPortChargeOnDemandPurchases.getResponse().toString());
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}