package com.example.E_bank.repository;

import com.example.E_bank.modal.Compte;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompteRepository extends JpaRepository<Compte, Integer> {

}
