package com.example.E_bank.repository;

import com.example.E_bank.modal.Benificiaire;
import com.example.E_bank.modal.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiaireRepository extends JpaRepository <Benificiaire,Integer> {
 List<Benificiaire> findAllByCompteIs(Compte compte);
 Benificiaire findByBeneficiaireId(Integer id);


}
