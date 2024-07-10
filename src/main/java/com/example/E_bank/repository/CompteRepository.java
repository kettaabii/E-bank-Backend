package com.example.E_bank.repository;

import com.example.E_bank.enums.status;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.example.E_bank.enums.status.Actif;


public interface CompteRepository extends JpaRepository<Compte, Integer> {

 @Query(value = "SELECT * FROM compte c WHERE c.user_id = :userId AND c.status = :status", nativeQuery = true)
 List<Compte> findAllByUserAndStatus(@Param("userId") Integer userId, @Param("status") String status);

 @Query(value = "SELECT * FROM compte c WHERE c.account_number = :accountNumber", nativeQuery = true)
 Compte findCompteByAccountNumber(@Param("accountNumber") String accountNumber);




}
