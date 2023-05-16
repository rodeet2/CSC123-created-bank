package com.usman.csudh.bank.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.usman.csudh.bank.settings;

public class conversionfilereaderonline {
	
	 
	 static String allexchangerates;
	 
     boolean status; 
     
     
	 public conversionfilereaderonline() throws Exception {
		super();
		this.status = statusfind() ;   
		if(status) {
		}
	}
	 
	 

	public static Boolean statusfind_online() throws Exception {	
		
		URL url;
		try {
			url = new URL(new settings().getWebservice_url());
		} catch (MalformedURLException e) {
             return false;
		}
	        HttpURLConnection conn = null;
			try {
				conn = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
	             return false;
			}
	        try {
				conn.setRequestMethod("GET");
			} catch (ProtocolException e) {
	             return false;
			}
	        System.out.print("online currencly file found");
return true;
		
	}
	
	public static Boolean statusfind_local() {	
		settings s=  new settings();
		
		File f = new File(s.getCurrency_file());
		if (f.exists()) {System.out.print("Local currencly file found");return true;}
		System.out.print("Local currencly not not not file found");
		return false;
		}
	
	
	public static Boolean statusfind() throws Exception {	
		 settings s=  new settings();
		   
		  if(s.getSupport_currencies()) {
			  
//			  if(s.getCurrencies_source().equals("webservice")) { if(statusfind_online()!=true) { return false;}}
//			  
//			  if(s.getCurrencies_source().equals("local")) { if(statusfind_local()!=true) { return false;}} 
//		
		return true;  
		
		  }else{return false;}
		  		 
	}
	
	public static void get_file_online() throws Exception {
		 
		        URL url = new URL(new settings().getWebservice_url());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");

		        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		        StringBuilder rs = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            rs.append(line);
		            rs.append("\n");
		        }

		        allexchangerates = rs.toString();
		        reader.close();
	}
	
	
	public static void get_file_locally() throws Exception {
		
	    File file = new File(new settings().getCurrency_file());
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    
	    StringBuilder result = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        result.append(line);
	        result.append("\n");
	     
	    }
	    allexchangerates = result.toString();
	    reader.close();
		
	}
	
	
	
	public static double get_rate_fromfile(String code) throws Exception {
		
		if(new settings().getCurrencies_source().equals("webservice")) {get_file_online();}
		if(new settings().getCurrencies_source().equals("local")) {get_file_locally();}
			
		StringReader currencyreader = new StringReader(allexchangerates);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
		    br = new BufferedReader(currencyreader);
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
