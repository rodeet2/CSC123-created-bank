package com.usman.csudh.bank.net;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONException;

import com.usman.csudh.bank.conversionfilereaderonline;
import com.usman.csudh.bank.settings;

public class getfilefrom_api_hook extends conversionfilereaderonline {

    @Override
    public InputStream getInputStream() throws IOException {
        URL url = null;
		try {
			url = new URL(new settings().getapiurl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder rs = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            rs.append(line);
            rs.append("\n");
        }
        reader.close();
        
        String raw = rs.toString();
        
        String notraw = convertJson(raw);
           
        return new ByteArrayInputStream(notraw.toString().getBytes());
    }
    
    public static String convertJson(String jsonText) {
    	
    	// this makes it the changes to match other sources 
    	
        JSONObject json = new JSONObject(jsonText);
        JSONObject rates = json.getJSONObject("rates");
        StringBuilder sb = new StringBuilder();
        for (String currency : rates.keySet()) {
            double rate = rates.getDouble(currency);
            double requiredAmount = 1.0 / rate; 
            sb.append(currency).append(",").append(requiredAmount).append("\n");
        }
 return sb.toString();
    }
}

