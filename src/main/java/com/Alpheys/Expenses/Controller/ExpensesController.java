package com.Alpheys.Expenses.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Alpheys.Expenses.Service.ExpenseService;
import com.Alpheys.Expenses.dto.MonthlySummarydto;
import com.Alpheys.Expenses.model.Transaction;

import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/api/expenses")

@RequiredArgsConstructor

public class ExpensesController {
	
	
	private final ExpenseService expenseService;
	
	@PostMapping("/add")
	
	public ResponseEntity<String>addTransaction(@RequestBody Transaction t){
		expenseService.addTransactions(t);
		return ResponseEntity.ok("Transaction added");

}
	@GetMapping("/summary/{month}")
	public MonthlySummarydto getsummary(@PathVariable("month") int month) {
		return expenseService.getMonthlySummary(month);
		
	}
	 @PostMapping("/upload")
	    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
		 try {
		        
		        System.out.println("Upload API called, file: " + file.getOriginalFilename());

		        expenseService.loadFromCSV(file);
		        return ResponseEntity.ok("CSV uploaded and parsed.");
		    } catch (Exception e) {
		        e.printStackTrace(); 
		        return ResponseEntity.badRequest().body("Failed to parse file: " + e.getMessage());
		    }
		}
	    @GetMapping
	    public List<Transaction> getAll() {
	        return expenseService.getAllTransactions();
	    }
	
	
}