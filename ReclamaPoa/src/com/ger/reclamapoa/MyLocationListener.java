package com.ger.reclamapoa;

import android.location.Location;  
import android.location.LocationListener;  
import android.os.Bundle;  
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
  
public class MyLocationListener extends ActionBarActivity implements LocationListener {  
  
    public static double latitude;  
    public static double longitude;  
  
    @Override  
    public void onLocationChanged(Location loc)  
    {  
        loc.getLatitude();  
        loc.getLongitude();  
        latitude=loc.getLatitude();  
        longitude=loc.getLongitude();  
    }  
  
    @Override  
    public void onProviderDisabled(String provider)  
    {  
    	TextView textview = (TextView)findViewById(R.id.textView2);
    	
    	textview.setText("GPS Disabled! Check your configuratios");
        //print "Currently GPS is Disabled";  
    }  
    @Override  
    public void onProviderEnabled(String provider)  
    {  
    	TextView textview = (TextView)findViewById(R.id.textView2);
    	
    	textview.setText("GPS is Enabled!");
        //print "GPS got Enabled";  
    }  
    @Override  
    public void onStatusChanged(String provider, int status, Bundle extras)  
    {  
    }  
} 