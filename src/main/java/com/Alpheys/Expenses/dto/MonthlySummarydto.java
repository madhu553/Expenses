package com.Alpheys.Expenses.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MonthlySummarydto {
	
	private double totalincome;
	
	private double totalexpenses;
	
	private double netsavings;
	
	private Map<String,Double>Categorysummary;;
	
	

}
