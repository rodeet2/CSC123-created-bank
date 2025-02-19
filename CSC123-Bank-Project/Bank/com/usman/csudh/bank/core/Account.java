package com.usman.csudh.bank.core;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.usman.csudh.util.UniqueCounter;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String accountName;
	private Customer accountHolder;
	private ArrayList<Transaction> transactions;
	private String Currency;
	

	private boolean open=true;
	private int accountNumber;

	protected Account(String name, Customer customer, String currency) {
		accountName=name;
		accountHolder=customer;
		transactions=new ArrayList<Transaction>();
		accountNumber=UniqueCounter.nextValue();
		Currency = currency;
	}
	
	public String getAccountName() {
		return accountName;
	}

	public Customer getAccountHolder() {
		return accountHolder;
	}

	public double getBalance() {
		double workingBalance=0;
		
		for(Transaction t: transactions) {
			if(t.getType()==Transaction.CREDIT)workingBalance+=t.getAmount();
			else workingBalance-=t.getAmount();
		}
		
		return workingBalance;
	}
	
	public String Currency() {
		return accountName;
	}
	
	
	
	public void deposit(double amount)  throws AccountClosedException{
		double balance=getBalance();
		if(!isOpen()&&balance>=0) {
			throw new AccountClosedException("\nAccount is closed with positive balance, deposit not allowed!\n\n");
		}
		transactions.add(new Transaction(Transaction.CREDIT,amount));
	}
	
	
	
	
	public void withdraw(double amount) throws InsufficientBalanceException {
			
		double balance=getBalance();
			
		if(!isOpen()&&balance<=0) {
			throw new InsufficientBalanceException("\nThe account is closed and balance is: "+balance+"\n\n");
		}
		
		transactions.add(new Transaction(Transaction.DEBIT,amount));
	}
	
	public void close() {
		open=false;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public String getCurrency() {
		return Currency;
	}

	public String toString() {
		// check for file
		double converted = 0;
		File currencyfile = new File("C:\\Users\\srozbu1\\CSC123-Resources\\Created bank\\CSC123-created-bank\\CSC123-Bank-Project\\exchange-rate.csv");
        // convert balance to USD
		if (currencyfile.exists()) { converted = cconverter.convert_and_return(Currency, (int)getBalance(),"USD");}

		String aName=accountNumber+"("+accountName+")"+" : "+accountHolder.toString()+" : "+ Currency + " : "+getBalance() +" : "+ converted + " : "+(open?"Account Open":"Account Closed");
		return aName;
	}
	 
	public void printTransactions(OutputStream out) throws IOException {
		
		out.write("\n\n".getBytes());
		out.write("------------------\n".getBytes());
	
		for(Transaction t: transactions) {
			out.write(t.toString().getBytes());
			out.write((byte)10);
		}
		out.write("------------------\n".getBytes());
		out.write(("Balance: "+getBalance()+"\n\n\n").getBytes());
		out.flush();
		
	}
}
