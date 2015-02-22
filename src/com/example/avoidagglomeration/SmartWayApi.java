package com.example.avoidagglomeration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartWayApi {

	
	public JSONArray GetSensors() throws ClientProtocolException, IOException, JSONException 
	{
		   System.out.println("Get Photos");
			HttpClient httpclient = new DefaultHttpClient();
			
			HttpGet httpget = new HttpGet("http://icity-gw.icityproject.com:8080/developer/api/devices?infrastructureID=20&cityName=Barcelona&propertyName=urn%3Anoise&apikey=l7xxe24ccd26ec9a466aadf95d590760c52f");
			httpget.setHeader("Content-Type", "application/json");
			httpget.setHeader("Accept", "application/json");
			JSONArray array = null;
			//JSONObject user = null;
		try {
				
				HttpResponse response = httpclient.execute(httpget);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				System.out.println("13");
				while ((line = reader.readLine()) != null)
					sb.append(line);
				
				System.out.println("14");
				String respuesta = sb.toString();
				array = new JSONArray(respuesta);
				
				System.out.println("----------------hello");
				System.out.println("----------------"+respuesta);
				return array;
			
			} catch (ClientProtocolException e) {
				System.out.println("problema");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			System.out.println("EN API"+array);
			return array;
		
	}
	public JSONArray getDataFromSensors() throws ClientProtocolException, IOException, JSONException 
	{
		   System.out.println("Get Photos");
			HttpClient httpclient = new DefaultHttpClient();
			
			HttpGet httpget = new HttpGet("http://icity-gw.icityproject.com:8080/developer/api/observations/interval?property=urn%3Anoise&from=2014-10-30T08%3A02%3A16&to=2014-12-12T07%3A45%3A50&infrastructureid=20&apikey=l7xxe24ccd26ec9a466aadf95d590760c52f");
			httpget.setHeader("Content-Type", "application/json");
			httpget.setHeader("Accept", "application/json");
			JSONArray array = null;
			//JSONObject user = null;
		try {
				
				HttpResponse response = httpclient.execute(httpget);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				System.out.println("13");
				while ((line = reader.readLine()) != null)
					sb.append(line);
				
				System.out.println("14");
				String respuesta = sb.toString();
				array = new JSONArray(respuesta);
				
				System.out.println("----------------hello");
				System.out.println("----------------"+respuesta);
				return array;
			
			} catch (ClientProtocolException e) {
				System.out.println("problema");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			System.out.println("EN API"+array);
			return array;
		
	}
	
	
}
