package com.geodrop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

/**
 * GeodropSession
 *
 * @author A-Tono s.r.l.
 * @since 1.0
 *
 */
public class GeodropSession 
{
	  /**
	   * Access token
	   */
	  private String accessToken;
	  /**
	   * Refresh token
	   */
	  private String refreshToken;
	  /**
	   * Refresh time that is the time of creation of the token
	   */
	  private Date refreshTime;
	  /**
	   * Token type
	   */
	  private String tokenType;
	  
	  /**
	   * Application id
	   */
	  private String applicationId;
	  
	  /**
	   * Application secret
	   */
	  private String applicationSecret;
	  
	  /**
	   * Username
	   */
	  private String username;

	  /**
	   * User password
	   */
	  private String password;

	  /**
	   * Creates a new <CODE>GeodropSession</CODE> instance
	   * 
	   * @param tokenResponse The http response to the token request
	   * @param applicationId The application id
	   * @param applicationSecret The application secret
	   * @throws ParseException If the http response cannot be parsed
	   *
	   */
    public GeodropSession(String applicationId, String applicationSecret, String username, String password)
	  {
		  this.applicationId = applicationId;
		  this.applicationSecret = applicationSecret;
          this.username = username;
          this.password = password;
	  }
	  
	  /**
	   * Returns a string representation of the object;
	   * in general, the toString method returns a string that "textually represents" this object;
	   * the result should be a concise but informative representation that is easy for a person to read
	   * 
	   * @return A string representation of the object
	   */
	  public String toString() 
	  {
		  String result = "GeodropSession:\n" + 
	    	            "\taccess_token: " + this.accessToken + "\n" +
	    	            "\ttoken_type: " + this.tokenType + "\n" +
	    	            "\trefresh_time: " + this.refreshTime + "\n" +
	    	            "\trefresh_token: " + this.refreshToken + "\n" +
	    	            "\tapplication_id: " + this.applicationId + "\n" +
                        "\tusername: " + this.username + "\n";
	    return result;
	  }
	  
	  /**
	   * Performs the parsing of the http response
	   * and fills the field of the <CODE>GeodropSession</CODE>
	   *
	   * @param tokenResponse Response to the http request
	   * 
	   * @throws ParseException If the http response cannot be parsed
	   */
	  private void parseTokenResponse(String tokenResponse) throws ParseException
	  {
		  JSONParser parser = new JSONParser();
		  JSONObject tokenResponseObj = (JSONObject) parser.parse(tokenResponse);
		  this.tokenType = (String)tokenResponseObj.get("token_type");
		  this.accessToken = (String)tokenResponseObj.get("access_token");
		  this.refreshToken = (String)tokenResponseObj.get("refresh_token");
		  Long refreshTime = (Long)tokenResponseObj.get("expire_in");
		  this.refreshTime = new Date(refreshTime);
	  }
	  
	  /**
	   * Run a <CODE>GeodropRequest</CODE>
	   *
	   * @param request The <CODE>GeodropRequest</CODE>
	   * @return Returns <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	   */
	  public boolean runMethod(GeodropRequest request) 
	  {
		  //body
		  String body;
		  request.createParams();
		  if(request.getTemplateName() != null)
		  {
			  body = prepareBodyFromMustacheTemplate(request);
		  }
		  else
		  {
			  body = preparePostFields(request);
		  }
		  //System.out.println(body);

          if (accessToken == null) {
              if (!getTokens()) {
                  System.err.println(ErrorType.AUTHENTICATION_FAILED);
                  return false;
              }
          }

		  //http request
		  int attempt = 0;
		  boolean failed = true;
		  String httpResponse = "";
		  do
		  {
			  if(attempt > 0)
			  {
				  if(!this.tokenRefresh())
				  {
                      if (!getTokens()) {
                          System.err.println(ErrorType.AUTHENTICATION_FAILED);
                          return false;
                      }
				  }
			  }
			  
			  try
			  {
				  attempt++;
				  //creation of connection
				  HttpURLConnection conn = (HttpURLConnection) new URL(request.getUri()).openConnection();
				  //header
				  conn.setRequestProperty("Authorization", this.tokenType + " " + this.accessToken);
				  conn.setRequestProperty("Content-type", request.getContentType());
				  conn.setRequestMethod(request.getHttpMethod());
				  conn.setDoOutput(true);
				  //run request
				  OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		          out.write(body);
		          out.flush();
		          //get the response
		          BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		          
                  httpResponse = "";
		          String receivedLine = "";
		          while((receivedLine = br.readLine()) != null)
		          {
		        	  httpResponse += receivedLine;
		          }
		          //close connection
		          out.close();
		          br.close();
		          //
		          if(!httpResponse.contains("<title>401 Authorization Required</title>"))
		          {
		        	  failed = false;
		          }
			  }
			  catch(Exception e)
			  {
			  }
		  }
		  while(attempt < 2 && failed);

		  //analyze http response
		  if(failed)
		  {
			  System.err.println(ErrorType.TOKEN_NOT_VALID);
			  return false;
		  }
		  //System.out.println("GeodropSession:runMethod: " + httpResponse);
		  return request.decodeResponse(httpResponse);
	  }
	  
	  /**
	   * Creates the tokens
	   *
	   * @return Returns <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	   */
	  private boolean getTokens()
	  {
          try {
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
              parseTokenResponse(tokenResponse);
              return true;
          } catch (Exception e) {
              System.err.println(e.getMessage());
              return false;
          }
      }

	  /**
	   * Refreshes the token
	   * 
	   * @return Returns <CODE>true</CODE> on success, <CODE>false</CODE> otherwise
	   */
	  private boolean tokenRefresh() 
	  {
		try
		{
			//creation of connection
			HttpURLConnection conn = (HttpURLConnection) new URL(Uri.TOKEN_REQUEST).openConnection();
			
			//definition of header parameters
			String fields = "grant_type=refresh_token&refresh_token=" + URLEncoder.encode(this.refreshToken,"UTF-8");
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
            
            //parse response
            this.parseTokenResponse(tokenResponse);
		}
		catch(Exception e)
		{
			return false;
		}
	    return true;
	  }
	  
	  /**
	   * Builds the POST field
	   * 
	   * @param request The <CODE>GeodropRequest</CODE>
	   * @return The POST field
	   */
	  private String preparePostFields(GeodropRequest request) 
	  {
		  String result = "";
		  Iterator<Entry<String, Object>> it = request.getParams().entrySet().iterator();
		  while(it.hasNext())
		  {
			  Map.Entry<String, Object> pairs = (Map.Entry<String, Object>) it.next();
			  if(pairs.getValue() != null)
			  {
				  String valueEncoded;
				  try
				  {
					  valueEncoded = URLEncoder.encode(pairs.getValue().toString(),"UTF-8");
				  } 
				  catch (UnsupportedEncodingException e) 
				  {
					  valueEncoded = pairs.getValue().toString();
				  }
				  result = result + pairs.getKey() + "=" + valueEncoded + "&";
			  }
			  it.remove(); // avoids a ConcurrentModificationException
		  }
		  if(result.endsWith("&"))
		  {
			  result = result.substring(0,result.length()-1);
		  }
		  return result;
	  }

	  /**
	   * Builds the body using a Mustache template
	   * 
	   * @param request The <CODE>GeodropRequest</CODE>
	   * @return The body
	   */
	  private String prepareBodyFromMustacheTemplate(GeodropRequest request)
	  {
		  Template template = Mustache.compiler().compile(request.getTemplateName());	
		  return template.execute(request.getParams());
	  }
}
