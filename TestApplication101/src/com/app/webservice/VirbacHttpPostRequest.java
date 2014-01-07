package com.app.webservice;

import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;
/** HttpPostRequest Getting JSON Values from URL*/
public class VirbacHttpPostRequest 
{	
	public static JSONObject JSONFromURL(String url,List<NameValuePair> pair) 
	{
		JSONObject jobject = null;
		String JSONContent = "";	  	  	 
		try 
		{	  
			HttpParams httpParameters = new BasicHttpParams();		 
			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpClient.setParams(httpParameters);

			HttpPost httpPost = new HttpPost(url);		 		 
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pair);

			Log.d("Links",url+pair);	
			httpPost.setEntity(entity);		  
			HttpResponse httpresponse = httpClient.execute(httpPost);		  
			HttpEntity content = httpresponse.getEntity();
			//String x=content.getContentType().toString();		 
			//Log.d("Tag: ", x);
			if (content != null)  
			{
				JSONContent = EntityUtils.toString(content);				
				Log.d("JSON Content : ", "" + JSONContent);				
				/*JSONContent=JSONContent.replaceAll("\\", "");*/
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