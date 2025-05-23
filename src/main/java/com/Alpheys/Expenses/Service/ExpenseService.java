package com.Alpheys.Expenses.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Alpheys.Expenses.dto.MonthlySummarydto;
import com.Alpheys.Expenses.model.Transaction;

@Service
public class ExpenseService {
	
	private final List<Transaction>transactions=new ArrayList();
	 
	public void addTransactions(Transaction t) {
		transactions.add(t);
	}

	    public MonthlySummarydto getMonthlySummary(int month) {
	        double income = 0, expense = 0;
	        Map<String, Double> categoryMap = new HashMap<>();

	        for (Transaction t : transactions) {
	            if (t.getDate().getMonthValue() == month) {
	                categoryMap.put(t.getCategory(), categoryMap.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
	                if (t.getType().equalsIgnoreCase("INCOME")) income += t.getAmount();
	                else expense += t.getAmount();
	            }
	        }

	        return new MonthlySummarydto(income, expense, income - expense, categoryMap);
	    }
	    public void loadFromCSV(MultipartFile file) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] data = line.split(",", 5);
	            if (data.length == 5) {
	                Transaction t = new Transaction(
	                        data[0],
	                        Double.parseDouble(data[2]),
	                        LocalDate.parse(data[1]),
	                        data[3],
	                        data[4]
	                );
	                transactions.add(t);
	            }
	        }
	    }

	    public List<Transaction> getAllTransactions() {
	        return transactions;
	    }
	}
	
