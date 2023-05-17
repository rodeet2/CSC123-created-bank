package com.usman.csudh.bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.usman.csudh.bank.settings;
import com.usman.csudh.bank.net.getfilefrom_api_hook;
import com.usman.csudh.bank.net.getfilefrom_online_hook;

public abstract class conversionfilereaderonline {
	
	 static String allexchangerates;
     boolean status; 
     static InputStream inputStream;
     
     public abstract InputStream getInputStream() throws IOException;
	 
	 
		public static double get_rate_fromfile(String code) throws Exception {
	        
			if(new settings().getCurrencies_source().equals("webservice")) {inputStream = new getfilefrom_online_hook().getInputStream();}
			if(new settings().getCurrencies_source().equals("local")) {inputStream = new getfilefrom_online_hook().getInputStream();}
			if(new settings().getCurrencies_source().equals("api")) {inputStream = new getfilefrom_api_hook().getInputStream();}

	
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";

			try {
			    br = new BufferedReader(new InputStreamReader(inputStream));
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(cvsSplitBy);
			        if (values[0].equals(code)) {
			     
			            return Double.parseDouble(extractnum(line));
			        }
			    }
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (br != null) {
			        try {
			            br.close();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }
			}
			return 0;
		}
		
		public static String extractnum(String line) {
		    return line.replaceAll("[^0-9.]", "");
		}
		
	
}
