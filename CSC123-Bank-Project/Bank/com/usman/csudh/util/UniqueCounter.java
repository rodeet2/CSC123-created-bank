package com.usman.csudh.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;

import com.usman.csudh.bank.core.Account;

public class UniqueCounter implements Serializable {
	public static int counterState = 1000;

	public static int nextValue() {
		return counterState++;
	}

	
	public static void Save() throws IOException{
	    
		FileOutputStream fileoutput = new FileOutputStream("C:\\Users\\srozbu1\\CSC123-Resources\\Created bank\\CSC123-created-bank\\CSC123-Bank-Project\\counter.dat");
		
        ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
        try {
        	objectoutput.writeObject(counterState);
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
	}
	
	public static void load() throws FileNotFoundException{
		  
		FileInputStream fileinput = new FileInputStream("C:\\Users\\srozbu1\\CSC123-Resources\\Created bank\\CSC123-created-bank\\CSC123-Bank-Project\\counter.dat");
				
		        ObjectInputStream objectinput = null;
		        
				try {
					objectinput = new ObjectInputStream(fileinput);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
		        try {

		        	Object data = objectinput.readObject();
		        	
		        	counterState =  (int) data;
		        } catch (IOException e) {
					
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		        
				
			}

}
