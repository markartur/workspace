package com.ger.reclamapoa;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.provider.Settings;

public class MainActivity extends ActionBarActivity {
	
	public final String MESSAGE = "com.ger.ReclamaPoa";
	public ArrayList<CAR> lista;
	String caremail="";
	String carsubject="";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListCAR listCAR = new ListCAR();
		
		lista = listCAR.lista;

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public void SendMessage(View view){
		
		
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		TextView textview = (TextView)findViewById(R.id.textView2);
		TextView textview3 = (TextView)findViewById(R.id.textView3);
		// check if enabled and if not send user to the GSP settings
		// Better solution would be to display a dialog and suggesting to 
		// go to the settings
		if (!enabled) {
		  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		  startActivity(intent);
		}
		else{
			textview.setText("Ligue o seu GPS!");
		}
		
		
		
		 LocationManager mlocManager=null;  
         LocationListener mlocListener;  
         mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);  
         mlocListener = new MyLocationListener();  
        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);  
  
        if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  
            if(MyLocationListener.latitude!=0)  
            {  
            	 textview.setText("Sua latitude: " + MyLocationListener.latitude + '\n'+ "Sua longitude: " + MyLocationListener.longitude + '\n');
            	
                // et_field_name.append("Latitude:- " + MyLocationListener.latitude + '\n');  
                // et_field_name.append("Longitude:- " + MyLocationListener.longitude + '\n'); 
            	 
            	 
            	 
            	 double menorlat=1000000;
            	 double menorlon=1000000;
            	 
            	 double menordistancia=100000;
            	 double distanciatemp=0;
            	 double lattemp = 0;
            	 double lontemp = 0;
            	 
            	 String carname="";
            	 
            	 
            	 
            	 for(CAR c:lista){
            		 lattemp = c.lat-MyLocationListener.latitude;
            		 lontemp = c.lon-MyLocationListener.longitude;
            		 distanciatemp = Math.abs(lattemp)+Math.abs(lontemp);
            		 
            		 if(Math.abs(distanciatemp)<Math.abs(menordistancia)){
            			 menordistancia = distanciatemp;
            			 carname = c.name;
        				 caremail = c.email;
        				 carsubject = c.subject;
            		 }
            	 }
            	 
            	 
            	/* for(CAR c: lista){
            		 if((c.lat*1-MyLocationListener.latitude)<=menorlat){
            			 menorlat = c.lat*1-MyLocationListener.latitude;
            			 if((c.lon*1-MyLocationListener.longitude)<=menorlon){
            				 menorlon = c.lon*1-MyLocationListener.longitude;
            				 carname = c.name;
            				 caremail = c.email;
            				 carsubject = c.subject;
            			 }
            		 }
            	 }*/
            	 
            	 textview3.setText("CAR mais próximo: "+carname);
            	 
            	 
            	 //Descomentar para gerar APK/Testar em aparelho live.
            	 /*Intent i = new Intent(Intent.ACTION_SEND);
     			i.setType("message/rfc822");
     			i.putExtra(Intent.EXTRA_EMAIL  , new String[]{caremail});
     			i.putExtra(Intent.EXTRA_SUBJECT, carsubject);
     			i.putExtra(Intent.EXTRA_TEXT   , "Insira aqui sua reclamação");
     			try {
     			    startActivity(Intent.createChooser(i, "Send mail..."));
     			} catch (android.content.ActivityNotFoundException ex) {
     			    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
     			}*/
            	 
            	 
            	 
            	 
            	 
            	 
            	 
            	 
            	 
             }  
            
            
            
            
             else  
             {  
                  /*alert.setTitle("Wait");  
                  alert.setMessage("GPS in progress, please wait.");  
                  alert.setPositiveButton("OK", null);  
                  alert.show();  */
              }  
          } else {  
        	  textview.setText("Ligue o seu GPS!!");
          }  
  
      }
		
		
		
	  public void EnviarEmail(View view){
		  
		  Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL  , new String[]{caremail});
			i.putExtra(Intent.EXTRA_SUBJECT, carsubject);
			i.putExtra(Intent.EXTRA_TEXT   , "Insira aqui sua reclamação");
			try {
			    startActivity(Intent.createChooser(i, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
		  
	  }
		
		
		
		
		
		/*
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		String car = spinner.getSelectedItem().toString();
		
		TextView textview = (TextView)findViewById(R.id.textView2);
		
		
		if(car.equals("CAR Sul")){
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sul@car.gov.br"});
			i.putExtra(Intent.EXTRA_SUBJECT, "Reclamação Sul");
			i.putExtra(Intent.EXTRA_TEXT   , "Insira aqui sua reclamação");
			try {
			    startActivity(Intent.createChooser(i, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
		}
		if(car.equals("CAR Norte")){
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"norte@car.gov.br"});
			i.putExtra(Intent.EXTRA_SUBJECT, "Reclamação Sul");
			i.putExtra(Intent.EXTRA_TEXT   , "Insira aqui sua reclamação");
			try {
			    startActivity(Intent.createChooser(i, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
		}
		if(car.equals("CAR Centro")){
			textview.setText("centro@car.gov.br");
		}
		if(car.equals("CAR Leste")){
			textview.setText("leste@car.gov.br");
		}*/
	}


