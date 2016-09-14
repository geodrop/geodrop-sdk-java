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
	private SessionFactory()
	{}
	
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
        return new GeodropSession(applicationId, applicationSecret, username, password);
	}
}
