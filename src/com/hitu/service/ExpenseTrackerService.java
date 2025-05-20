package com.hitu.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hitu.model.Transaction;

public class ExpenseTrackerService {
	
	private String filePath;
	
	public ExpenseTrackerService(String filePath) {
		this.filePath=filePath;
	}
	
	public String addTranscation(List<Transaction> transactions) {
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(filePath,true));
			File file = new File(filePath);
			if (file.length()==0) {
				writer.write("Transaction,Category,Amount,Date");
				writer.newLine();
			}
			
	        // Write each transaction
	        for (Transaction t : transactions) {
	            writer.write(t.toCSV());
	            writer.newLine();
	        }
	        writer.close();
	        return "Transaction added successfully.";
		} catch (IOException e) {
			return "Error occured : "+e.getMessage();
		}
		
	}
	
	public void monthlySummary(int year,int month) throws IOException {
		double totalIncome=0.0,totalExpense=0.0,balance=0.0;
		String line=null;
		List<Transaction> filteredTranscation=new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		reader.readLine();
		while((line=reader.readLine()) != null) {
			Transaction transaction = new Transaction();
			
			String[] split = line.split(",");
			
			transaction.setTranscationType(split[0]);
			transaction.setCategory(split[1]);
			transaction.setAmount(Double.parseDouble(split[2]));
			transaction.setDate(LocalDate.parse(split[3]));
			
			if (transaction.getDate().getYear()==year && transaction.getDate().getMonthValue()==month) {
				filteredTranscation.add(transaction);
				System.out.println(transaction);
			}
		}
		reader.close();
		for (Transaction transaction : filteredTranscation) {
			if (transaction.getTranscationType().equals("income")) {
				totalIncome+=transaction.getAmount();
			}
			else {
				totalExpense+=transaction.getAmount();
			}
		}
		balance=totalIncome-totalExpense;
		System.out.println();
		System.out.println("Total income "+totalIncome+" Total expense "+totalExpense+" Balance "+balance);
	}
	
	public void getAllTransaction() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		List<Transaction> allTranscations=new ArrayList<>();
		String line=null;
		reader.readLine();
		while((line=reader.readLine()) != null) {
			Transaction transaction = new Transaction();
			
			String[] split = line.split(",");
			
			transaction.setTranscationType(split[0]);
			transaction.setCategory(split[1]);
			transaction.setAmount(Double.parseDouble(split[2]));
			transaction.setDate(LocalDate.parse(split[3]));
			allTranscations.add(transaction);
		}
		reader.close();
		allTranscations.forEach(System.out::println);
			
	}

}
