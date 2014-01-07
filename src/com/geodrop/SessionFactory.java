package com.geodrop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
  

/**
 * Used for the creation of a <CODE>GeodropSession</CODE>
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class SessionFactory 
{
	/**
	 * Creates a <CODE>GeodropSession</CODE>
	 * with the method of Resource Owner Password Credentials
	 * @param applicationId The application ID
	 * @param applicationSecret The application secret
	 * @param username The username
	 * @param password The password
	 * @return The session
	 */
	public static GeodropSession buildSession_ResourceOwnerPasswordCredentials(String applicationId,String applicationSecret,String username,String password)
	{
		try
		{
			//creation of connection
			HttpURLConnection conn = (HttpURLConnection) new URL(Uri.TOKEN_REQUEST).openConnection();
			
			//definition of header parameters
			String fields = "grant_type=password&username="+URLEncoder.encode(username,"UTF-8")+"&password="+URLEncoder.encode(password,"UTF-8");
			String authStr = applicationId + ":" + applicationSecret;
			String auth = javax.xml.bind.DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));
			
			//set connection parameters
			conn.setRequestProperty("Authorization", "Basic " + auth);
			conn.setRequestProperty("Content-type", ContentType.RAW);
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoOutput(true);

			//run request
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(fields);
            out.flush();

            //get the response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String tokenResponse = br.readLine();

            //close connection
            out.close();
            br.close();
            return new GeodropSession(tokenResponse, applicationId, applicationSecret);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}
}