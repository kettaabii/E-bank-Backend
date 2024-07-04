package com.example.E_bank.repository;

import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompteRepository extends JpaRepository<Compte, Integer> {
 List<Compte> findComptesByUserIs(User user);

}
