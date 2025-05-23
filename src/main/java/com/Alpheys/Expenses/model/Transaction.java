package com.Alpheys.Expenses.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Transaction{

	
private String type;

private double amount;
private LocalDate date;

private String category;

private String description;



}
