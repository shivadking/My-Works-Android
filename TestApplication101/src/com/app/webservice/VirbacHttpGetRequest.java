package com.app.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import android.util.Log;
/** HttpGet Request for getting JSON values from URL */
public class VirbacHttpGetRequest 
{	
	public static JSONObject JSONFromURL(String url) 
	{
		JSONObject jobject = null;
		String JSONContent = "";
		try   
		{	  
			DefaultHttpClient request = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);		 		 
			Log.d("Links",url);
			HttpResponse httpresponse =request.execute(httpGet);		  
			HttpEntity content = httpresponse.getEntity();
			//content.consumeContent();
			if (content != null )  
			{
				JSONContent = EntityUtils.toString(content);				
				Log.d("JSON Content : ", "" + JSONContent);				
				jobject = new JSONObject(JSONContent);

			}			 		
		}
		catch(Exception e)
		{
			Log.d("Error Status: ","RES: " + JSONContent );
			e.printStackTrace();
		}	 	 
		return jobject;
	}
}
