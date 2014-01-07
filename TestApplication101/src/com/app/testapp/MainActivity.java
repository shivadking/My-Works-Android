package com.app.testapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.webservice.VirbacHttpGetRequest;
import com.app.webservice.VirbacHttpPostRequest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button BtnClick;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		process();
	}

	private void process() 
	{
		BtnClick.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				LoginServerTask task = new LoginServerTask(MainActivity.this);
				task.execute();
			}
		});
	}

	private void init() {
		BtnClick = (Button) findViewById(R.id.ClickBtn);
	}

	JSONObject jobject;
	boolean NetworkStatus;
	public class LoginServerTask extends AsyncTask<Void, Void, Boolean>
	{
		Context ctx;
		ProgressDialog dialog;

		public LoginServerTask(Context ctx) 
		{
			this.ctx=ctx;
			dialog = new ProgressDialog(ctx);
		}

		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setMessage("Connecting Server");
			dialog.setCancelable(false);
			dialog.show();
		}

		protected Boolean doInBackground(Void... params) 
		{
			boolean Status=false;
			NetworkStatus =Constant.isConnectingToInternet(MainActivity.this);
			if(NetworkStatus)
			{
				try 
				{
					/*String URL="http://web-billings.com/testPost.php";
					List<NameValuePair> pair = new ArrayList<NameValuePair>();
					pair.add(new BasicNameValuePair("email", "ss@gmail.com"));
					pair.add(new BasicNameValuePair("password", "1234"));
					jobject =VirbacHttpPostRequest.JSONFromURL(URL,pair);*/
					
					String URL="http://virbac.plscheckitout.com/Users.php?type=Register&userFirstname=keerthi&userLastname=s&userEmail=krish@gmail.com&password=123456&userImage=desert.jpg";
					jobject=VirbacHttpGetRequest.JSONFromURL(URL);
					Status=true;
				}			
				catch (Exception e) 
				{			
					e.printStackTrace();
				}	
			}
			return Status;
		}

		protected void onPostExecute(Boolean result) 
		{
			super.onPostExecute(result);
			dialog.dismiss();
			if(NetworkStatus)
			{
				if(result)
				{
					try 
					{
						if(jobject!=null)
						{
							

						}
						else
						{
							Constant.onErrAlert(ctx,"Internal Server Error");
							Log.d("Test: ","Parsing Error");
						}
					}
					catch (Exception e) 
					{				
						e.printStackTrace();
						Constant.onErrAlert(ctx,"Internal Server Error");
					}				 						
				}
				else
				{
					Constant.onErrAlert(ctx,"Internal Server Error");
				}
			}
			else
			{
				Constant.onErrAlert(ctx,"Unable to connect the Server");
			}
		}		
	}
}

