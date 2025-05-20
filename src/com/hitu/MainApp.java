package com.hitu;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hitu.model.Transaction;
import com.hitu.service.ExpenseTrackerService;

public class MainApp {
	public static void main(String[] args) throws IOException {
		ExpenseTrackerService trackerService = new ExpenseTrackerService("expenseTracker.csv");
		Scanner sc=new Scanner(System.in);
		List<Transaction> listOfTransaction = new ArrayList<>();
		while(true) {
			 System.out.println("\n--- Expense Tracker ---");
	            System.out.println("1. Add Income");
	            System.out.println("2. Add Expense");
	            System.out.println("3. View Monthly Summary");
	            System.out.println("4. View All Transaction ");
	            System.out.println("5. Exit ");
	            System.out.println("-----------------------");
	            System.out.print("Choose an option: ");
	            
	            int choice = sc.nextInt();
	            if (choice==1) {
					System.out.println("Enter category (Ex. Salary, Business): ");
					String category = sc.next();
					
					System.out.println("Enter Amount: ");
					double amount = sc.nextDouble();
					
					System.out.println("Enter date: (yyyy-mm-dd)");
					LocalDate date =LocalDate.parse(sc.next());
					Transaction income = new Transaction();
					income.setTranscationType("income");
					income.setCategory(category);
					income.setAmount(amount);
					income.setDate(date);
					listOfTransaction.add(income);
					System.out.println(trackerService.addTranscation(listOfTransaction));
					listOfTransaction.clear();
				}
	            else if(choice==2) {
	            	System.out.println("Enter category (Ex. food, travel) ");
					String category = sc.next();
					
					System.out.println("Enter Amount: ");
					double amount = sc.nextDouble();
					
					System.out.println("Enter date: (yyyy-mm-dd)");
					LocalDate date =LocalDate.parse(sc.next());
					Transaction expense = new Transaction();
					expense.setTranscationType("expense");
					expense.setCategory(category);
					expense.setAmount(amount);
					expense.setDate(date);
					listOfTransaction.add(expense);
					System.out.println(trackerService.addTranscation(listOfTransaction));
					listOfTransaction.clear();
	            }
	            else if(choice==3) {
	            	System.out.print("Enter year (e.g., 2025): ");
                    int year = sc.nextInt();
                    System.out.print("Enter month (1-12): ");
                    int month = sc.nextInt();
                    System.out.println();
                    System.out.println("--------Monthly Summary----------");
	            	trackerService.monthlySummary(year, month);
	            	System.out.println();
	            }
	            else if(choice==4){
	            	System.out.println("-----All Transactions------");
	            	System.out.println();
	            	trackerService.getAllTransaction();
	            }
	            else if(choice==5) {
	            	System.out.println("Thanks for using our Service");
	            	break;
	            }
	            else {
	            	System.out.println("Invalid Choice");
	            }
	            System.out.println("Do you want to continue ? y/Y");
	            String next = sc.next();
	            if (next.equalsIgnoreCase("y")) {
					continue;
				}
	            else {
	            	System.out.println("Thanks for using our Service");
	            	break;
	            }
		}
	}
}
