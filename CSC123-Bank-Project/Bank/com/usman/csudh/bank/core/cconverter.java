package com.usman.csudh.bank.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class cconverter {
	
	public static void convert(String code1, int ammount, String code2) {
	
	 // Check if USD is present
		
	 if (code1.equals("USD") || code2.equals("USD") ){
		 			
         
		  if(code1 == "USD") {
			 //First code is USD, meaning need to divive amount with exchange rate of code1
			  
			  System.out.print("The exchange rate is " + get_rate_fromfile(code2) + " and you will get USD " +  ammount / get_rate_fromfile(code2) + "\n");
			  System.out.println("");
		  }else {
			  //Second code is USD, meaning need to divide amount with exchange rate of code1
			  			  
			  System.out.print("The exchange rate is " + get_rate_fromfile(code1) + " and you will get USD " + ammount * get_rate_fromfile(code1) + "\n");
			  System.out.println("");
		  }
			
		 
	 }else {
		 
		 System.out.print("Sorry, only conversions with USD is possible! \n");
		 System.out.println("");
		
	 }
	 	
	 
		public static double convert_and_return(String code1, int ammount, String code2) {
			
			 // Check if USD is present
				
			 if (code1.equals("USD") || code2.equals("USD") ){
				 			
		         
				  if(code1 == "USD") {
					 //First code is USD, meaning need to divive amount with exchange rate of code1
					  
					  return ammount / get_rate_fromfile(code2);
					  
				  }else {
					  //Second code is USD, meaning need to divide amount with exchange rate of code1
					  			  
					  return ammount * get_rate_fromfile(code1);
				  }
					
				 
			 }else {
				 
				 System.out.print("Sorry, only conversions with USD is possible! \n");
				 System.out.println("");
				
			 }
			 
			return 0;
	
	}
	
	public static double get_rate_fromfile(String code) {
		
		 //load file
		
			File currencyfile = new File("C:\\Users\\srozbu1\\CSC123-Resources\\Created bank\\CSC123-created-bank\\CSC123-Bank-Project\\exchange-rate.csv");

			BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";

		
		   try {
	            br = new BufferedReader(new FileReader(currencyfile));
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
