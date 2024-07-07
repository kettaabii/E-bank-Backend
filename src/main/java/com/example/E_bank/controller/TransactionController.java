package com.example.E_bank.controller;

import com.example.E_bank.exeption.cardBlockedException;
import com.example.E_bank.exeption.compteFermeException;
import com.example.E_bank.exeption.soldeInsuffisantExeption;
import com.example.E_bank.modal.Transaction;
import com.example.E_bank.repository.TransactionRepository;
import com.example.E_bank.service.TransationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransationService transationService;

    @PostMapping("/transaction/new/{accnt}")
    public ResponseEntity<?> newTransaction(@PathVariable String accnt , @RequestBody Transaction transaction) {
        try {
            Transaction transaction1= transationService.newtransaction(accnt, transaction);
            return ResponseEntity.ok(transaction1);
        } catch (soldeInsuffisantExeption | compteFermeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/transaction/all/{id}")
    public List<Transaction> getAllTransactions(@PathVariable Integer id) {
        return transationService.getalltransactions(id);

    }



}
