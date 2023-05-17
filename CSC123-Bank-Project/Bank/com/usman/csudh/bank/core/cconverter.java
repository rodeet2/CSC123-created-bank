package com.usman.csudh.bank.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.usman.csudh.bank.settings;
import com.usman.csudh.bank.net.conversionfilereaderonline;

public class cconverter {
	
	public static void convert(String code1, int ammount, String code2) throws Exception {
		
		String c1 = code1;
		String c2 = code2;
				
		
		
	 // Check if USD is present
		
	 if (c1.equals("USD") || c2.equals("USD") ){
         
		  if(code1.equals("USD")) {
			 //First code is USD, meaning need to divive amount with exchange rate of code1
			  
			  System.out.println("The exchange rate is " + com.usman.csudh.bank.conversionfilereaderonline.get_rate_fromfile(c2) + " and you will get " +  ammount / com.usman.csudh.bank.conversionfilereaderonline.get_rate_fromfile(c2) + "\n");
			  System.out.println("");
		  }else{
			  //Second code is USD, meaning need to multiply amount with exchange rate of code1
			  			  
			  System.out.println("The exchange rate is " + com.usman.csudh.bank.conversionfilereaderonline.get_rate_fromfile(c1) + " and you will get " + ammount * com.usman.csudh.bank.conversionfilereaderonline.get_rate_fromfile(c1) + "\n");
			  System.out.println("");
		  }
		 
	 }else {
		 
		 System.out.print("Sorry, only conversions with USD is possible! \n");
		 System.out.println("");
		 }
		
	 }
	 
		public static double convert_and_return(String code1, int ammount, String code2) throws Exception {
			
			String c1 = code1;
			String c2 = code2;
			
	
				
			
			 if (c1.equals("USD") || c2.equals("USD") ){
				 			
		         
				  if(c1 == "USD") {
					 //First code is USD, meaning need to divide amount with exchange rate of code2
					  
					  return ammount / com.usman.csudh.bank.conversionfilereaderonline.get_rate_fromfile(c2);
					  
				  }else {
					  //Second code is USD, meaning need to divide amount with exchange rate of code1
					  			  
					  return ammount * com.usman.csudh.bank.conversionfilereaderonline.get_rate_fromfile(c1);
				  }
					
				 
			 }else {
				 
				 System.out.print("Sorry, only conversions with USD is possible! \n");
				 System.out.println("");
				
			 }
			 
			return 0;
	
	}
	

	

}
