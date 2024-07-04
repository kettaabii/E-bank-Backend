package com.example.E_bank.service;

import com.example.E_bank.modal.Transaction;
import com.example.E_bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransationService {
    @Autowired
    TransactionRepository transactionRepository;


    public List<Transaction> getalltransactions(Integer id){

        return transactionRepository.findAll().stream().toList();

    }
    public Transaction newtransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

}
