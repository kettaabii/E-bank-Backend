package com.example.E_bank.repository;

import com.example.E_bank.modal.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

List<Transaction> findAllByCompte_AccountId(Integer accountId);

}
