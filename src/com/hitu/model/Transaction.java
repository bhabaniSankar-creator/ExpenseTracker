package com.hitu.model;

import java.time.LocalDate;

public class Transaction {
	
	private String transactionType;
	private String category;
	private Double amount;
	private LocalDate date;
	
	
	public String getTranscationType() {
		return transactionType;
	}
	public void setTranscationType(String transcationType) {
		this.transactionType = transcationType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String toCSV() {
		return transactionType + "," + category + "," + amount + "," + date;
	}
	@Override
	public String toString() {
		return "Transaction [transactionType=" + transactionType + ", category=" + category + ", amount=" + amount
				+ ", date=" + date + "]";
	}
	
	
	
}
