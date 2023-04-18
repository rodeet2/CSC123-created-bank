package com.usman.csudh.bank.core;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Bank {
	
	private static Map<Integer,Account> accounts=new TreeMap<Integer,Account>();
	
	public static Account openCheckingAccount(String firstName, String lastName, String ssn, double overdraftLimit, String currency) {
		Customer c=new Customer(firstName,lastName, ssn);
		Account a=new CheckingAccount(c,overdraftLimit,currency);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}
	
	public static Account openSavingAccount(String firstName, String lastName, String ssn, String currency) {
		Customer c=new Customer(firstName,lastName, ssn);
		Account a=new SavingAccount(c, currency);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}

	
	public static Account lookup(int accountNumber) throws NoSuchAccountException{
		if(!accounts.containsKey(accountNumber)) {
			throw new NoSuchAccountException("\nAccount number: "+accountNumber+" nout found!\n\n");
		}
		
		return accounts.get(accountNumber);
	}
	
	public static void makeDeposit(int accountNumber, double amount) throws AccountClosedException, NoSuchAccountException{
		
		lookup(accountNumber).deposit(amount);
		
	}
	
	public static void makeWithdrawal(int accountNumber, double amount) throws InsufficientBalanceException, NoSuchAccountException {
		lookup(accountNumber).withdraw(amount);
	}
	
	public static void closeAccount(int accountNumber) throws NoSuchAccountException {
		lookup(accountNumber).close();
	}

	
	public static double getBalance(int accountNumber) throws NoSuchAccountException {
		return lookup(accountNumber).getBalance();
	}

	public static void account_info_withfile(int accountNumber) throws NoSuchAccountException {
		Account a = lookup(accountNumber);
		Customer c = a.getAccountHolder();
		System.out.print("Account Number: " + a.getAccountNumber() + "\n");
		System.out.print("Name: " + c.getFirstName() + c.getLastName() + "\n");
		System.out.print("SSN: " + c.getSSN() + "\n" );
		System.out.print("Currency: " + a.getCurrency() + "\n");
		System.out.print("Currency Balance: " + a.getBalance() + "\n");
		System.out.print("USD Balance: " + cconverter.convert_and_return(a.getCurrency(), (int)a.getBalance(), "USD"));
		System.out.println("");
	}
	
	public static void account_info(int accountNumber) throws NoSuchAccountException {
		Account a = lookup(accountNumber);
		Customer c = a.getAccountHolder();
		System.out.print("Account Number: " + a.getAccountNumber() + "\n");
		System.out.print("Name: " + c.getFirstName() + c.getLastName() + "\n");
		System.out.print("SSN: " + c.getSSN() + "\n" );
		System.out.print("Currency: " + a.getCurrency() + "\n");
		System.out.print("Currency Balance: " + a.getBalance() + "\n");
		System.out.print("Unable to convert to USD, missing file");
		System.out.println("");

	}
	public static void listAccounts(OutputStream out) throws IOException{
		
		out.write((byte)10);
		Collection<Account> col=accounts.values();
		
		for (Account a:col) {
			out.write(a.toString().getBytes());
			out.write((byte)10);
		}
		
		out.write((byte)10);
		out.flush();
	}
	
	public static void printAccountTransactions(int accountNumber, OutputStream out) throws IOException,NoSuchAccountException{
		
		lookup(accountNumber).printTransactions(out);
	}

	
	public static void Save() throws IOException{
				    
		FileOutputStream fileoutput = new FileOutputStream("C:\\Users\\srozbu1\\CSC123-Resources\\Created bank\\CSC123-created-bank\\CSC123-Bank-Project\\bankfiles.dat");
		
		
        ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
        try {
        	objectoutput.writeObject(accounts);
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
	}
	
	
	public static void load() throws FileNotFoundException{
		
		    
		  
FileInputStream fileinput = new FileInputStream("C:\\Users\\srozbu1\\CSC123-Resources\\Created bank\\CSC123-created-bank\\CSC123-Bank-Project\\bankfiles.dat");
		
		
        ObjectInputStream objectinput = null;
        
		try {
			objectinput = new ObjectInputStream(fileinput);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
        try {

        	Object data = objectinput.readObject();
        	
        	accounts = (Map<Integer, Account>) data;
        	
        } catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
		
	}
	
}
