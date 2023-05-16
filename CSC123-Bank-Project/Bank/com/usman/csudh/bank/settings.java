package com.usman.csudh.bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class settings {

	Boolean support_currencies;
    String	currencies_source;
	String	webservice_url;
	String  currency_file;
	
	public settings() {
		try {
			getsettings();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean getSupport_currencies() throws Exception {
	
		return support_currencies;
	}

	public String getCurrencies_source() throws Exception {
	
		return currencies_source;
	}

	public String getWebservice_url() throws Exception {
		
		return webservice_url;
	}

	public String getCurrency_file() throws Exception {
	
		return currency_file;
	}

	
	public void getsettings() throws Exception{
	  try {

		  File file = new File("C:\\Users\\srozbu1\\Desktop\\config.txt");

	        BufferedReader reader = new BufferedReader(new FileReader(file));
;
	        String data = "";
	        String full_line;
	        while ((full_line = reader.readLine()) != null) {
	            if(full_line.contains("support.currencies")){if(full_line.replace("support.currencies=", "").equals("true")) {support_currencies = true;}else{support_currencies = false;}}
	            if(full_line.contains("currencies.source")){currencies_source = full_line.replace("currencies.source=", ""); }
	            if(full_line.contains("webservice.url")){webservice_url = full_line.replace("webservice.url=","" );}
	            if(full_line.contains("currency.file")){currency_file = full_line.replace("currency.file=", "");}
	        
	        }
	        reader.close();
	        System.out.println(data);
	    } catch (FileNotFoundException e) {
	        System.out.println("Setting file not found!");
	        e.printStackTrace();
	    }
	  
}	

}