package com.BankingApplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	//add account rest api
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
	{
		System.out.println(accountDto);
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);		
	}
	
	//get single account details
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
	{
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	//deposit
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request)
	{
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.deposit(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}

	//withdraw
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request)
	{
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);		
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>>getAllAccounts()
	{
		List<AccountDto> accountDto=accountService.getAllAccounts();
		return ResponseEntity.ok(accountDto);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>>deleteAccount(@PathVariable Long id)
	{
		accountService.deleteAccount(id);
		Map<String,Boolean>response=new HashMap<String,Boolean>();
		response.put("delete", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
}
