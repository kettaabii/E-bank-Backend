package com.example.E_bank.repository;

import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CompteRepository extends JpaRepository<Compte, Integer> {
 List<Compte> findAllByUser(User user);

 @Query(value = "SELECT * FROM compte c WHERE c.account_number = :accountNumber", nativeQuery = true)
 Compte findCompteByAccountNumber(@Param("accountNumber") String accountNumber);



}
