package com.app.testapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/** Constant for OnErrorAlert and Checking Internet Connection */
public class Constant 
{	
	public static void onErrAlert(Context ctx,String msg)
	{   
		new AlertDialog.Builder(ctx)
		.setTitle("Virbac")
		.setMessage(msg)
		.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface arg0, int arg1) 
			{
			}
		}).show();		
	}
	
	
	public static Boolean isConnectingToInternet(Context ctx)    
	{
		Boolean isConnected=false;
		try
		{
			ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			isConnected = info != null && info.isAvailable() && info.isConnected();			
			return isConnected;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Log.d("isConnected: ", ""+isConnected);
		return isConnected;   
	}
}
