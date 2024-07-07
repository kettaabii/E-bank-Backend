package com.example.E_bank.repository;

import com.example.E_bank.modal.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Integer> {
    List<CarteBancaire> findAllByCompteAccountId(Integer AccountId);
}
