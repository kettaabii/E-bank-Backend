package com.example.E_bank.controller;

import com.example.E_bank.modal.Transaction;
import com.example.E_bank.repository.TransactionRepository;
import com.example.E_bank.service.TransationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransationService transationService;

    @PostMapping("/transaction/new")
    public Transaction newTransaction(@RequestBody Transaction transaction) {

        return transationService.newtransaction(transaction);
    }
    @GetMapping("/transaction/all/{id}")
    public List<Transaction> getAllTransactions(@PathVariable Integer id) {
        return transationService.getalltransactions(id);

    }


}
