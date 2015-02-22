package com.example.avoidagglomeration;



import java.io.FileNotFoundException;
import java.io.IOException;









import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Mapsfragment extends Fragment {
	private String[] values;
	private JSONObject[] notes;
	private ArrayList<Sensor> sensorlist;
    
MapView m;
private GoogleMap mMap;	
Uri targetUri;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		// inflat and return the layout
		String lugar = Environment.getExternalStorageDirectory()
	            + "/Android/data/"
	            + getActivity().getPackageName()
	            + "/Files/"; 
		System.out.println(lugar);
		//targetUri= Uri.parse(uri);
		System.out.println(targetUri);
		View v = inflater.inflate(R.layout.fragment_map, container, false);
		m = (MapView) v.findViewById(R.id.map);
		
		MapsInitializer.initialize(getActivity());
		new getDAtafromSensors().execute("");
		new getSensors().execute("");
		
		
	
		m.onCreate(savedInstanceState);
		 if (mMap == null) {
	            mMap = ((MapView) v.findViewById(R.id.map)).getMap();
	            setUpMap2();
	           }
	            
		
		
		return v;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		m.onResume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		m.onPause();
	}
	private void setUpMap(Double lat1, Double long2,String value) {
		String valor = "";
		int i = 0;
		Double val = 0.0;
		while (i<sensorlist.size()) {
			if(value.equals(sensorlist.get(i).getDeviceName())){
				valor = sensorlist.get(i).getValue().toString();
				val= Double.parseDouble(valor);
			}
			i++;
			
		}
		if(val==0.0)
		{
			valor= "NO DATA";
			
			mMap.addMarker(new MarkerOptions().position(new LatLng(lat1,long2)).title(value).snippet(valor).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
		}
		if (val >80.5 )
		{
			mMap.addMarker(new MarkerOptions().position(new LatLng(lat1,long2)).title(value).snippet(valor).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

		}
		if(val < 80.5 && val !=0)
		{
        //mMap.addMarker(new MarkerOptions().position(new LatLng(42.7,2.1)).title("Marker"));
		mMap.addMarker(new MarkerOptions().position(new LatLng(lat1,long2)).title(value).snippet(valor).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42, 1), 10));
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ORIGEN, 12));
		} 
    }
	private void setUpMap2() {
		
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.401501, 2.156860), 12));
       
        
    }
	
    
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		m.onDestroy();
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		m.onLowMemory();
	}
	
	
	private class getSensors extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            
        }

     

        @Override
        protected void onPostExecute(JSONArray args) {
           
            // Close the progressdialog
            //mProgressDialog.dismiss();
          
        	values = new String[args.length()];
        	System.out.println("1");
        	notes = new JSONObject[args.length()];
        	for (int i = 0; i < args.length(); i++) {
        		try {
        			JSONObject note = args.getJSONObject(i);
        			notes[i] = note;
        			values[i] = "Origen:   "+note.get("longitude") + "      Destino: " + note.get("latitude");
        			System.out.println(values[i]);
        			 setUpMap(Double.parseDouble(note.get("latitude").toString()),Double.parseDouble(note.get("longitude").toString()),note.get("name").toString());
        			System.out.println("2");
        		} catch (JSONException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	}
        	
        	
        	
           
            	
            
        }

		@Override
		protected JSONArray doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("ARRANCA");
        	String path = params[0];
        	SmartWayApi api = new SmartWayApi();
        	JSONArray resp = null;
        	try {
				 try {
					resp =api.GetSensors();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 System.out.println(resp);
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resp;
		}
    }
	
	
	private class getDAtafromSensors extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            
        }

     

        @Override
        protected void onPostExecute(JSONArray args) {
           
            // Close the progressdialog
            //mProgressDialog.dismiss();
          sensorlist = new ArrayList<Sensor>();
        	values = new String[args.length()];
        	System.out.println("1");
        	notes = new JSONObject[args.length()];
        	for (int i = 0; i < args.length(); i++) {
        		try {
        			JSONObject note = args.getJSONObject(i);
        			notes[i] = note;
        			values[i] = "SENSOR: "+note.get("deviceName")+"DATOS:   "+note.get("value");
        			System.out.println(values[i]);
        			Sensor ssor = new Sensor(note.get("deviceName").toString(), note.get("value").toString());
        			// setUpMap(Double.parseDouble(note.get("latitude").toString()),Double.parseDouble(note.get("longitude").toString()),note.get("name").toString());
        			System.out.println("ANTES DE NULLPOINT"+ssor.getDeviceName());
        			sensorlist.add(ssor);
        			System.out.println("2");
        		} catch (JSONException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	}

        }
		@Override
		protected JSONArray doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("ARRANCA");
        	String path = params[0];
        	SmartWayApi api = new SmartWayApi();
        	JSONArray resp = null;
        	try {
				 try {
					resp =api.getDataFromSensors();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 System.out.println(resp);
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resp;
		}
    }
	
	
	

	
	
	
	
}