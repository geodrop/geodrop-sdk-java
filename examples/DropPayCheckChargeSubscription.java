import geodropSDKjava.*;
import geodropSDKjava.dropPay.CustomerCheck;
import geodropSDKjava.dropPay.CustomerCheck_Response;
import geodropSDKjava.dropPay.PortChargeSubscriptionPurchases;

public class DropPayCheckChargeSubscription
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
			
			//CustomerCheck
			int port = 0;
			String msisdn = "";
			CustomerCheck requestCustomerCheck = new CustomerCheck(port,msisdn);
			if(session.runMethod(requestCustomerCheck))
			{
				int subscriber = ((CustomerCheck_Response) requestCustomerCheck.getResponse()).getSubscriber();
				PortChargeSubscriptionPurchases requestPortChargeSubscriptionPurchases = new PortChargeSubscriptionPurchases(port,msisdn,"PortChargeSubscriptionPurchases:ciao",subscriber);
				session.runMethod(requestPortChargeSubscriptionPurchases);
				System.out.println("Response: " + requestPortChargeSubscriptionPurchases.getResponse().toString());
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}