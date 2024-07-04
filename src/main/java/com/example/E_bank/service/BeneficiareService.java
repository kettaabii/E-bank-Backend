package com.example.E_bank.service;

import com.example.E_bank.modal.Benificiaire;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiareService {
    @Autowired
    BeneficiaireRepository beneficiaireRepository;

    public Benificiaire addBeneficiaire(Benificiaire benificiaire){
        return beneficiaireRepository.save(benificiaire);
    }

    public List<Benificiaire> getAllBeneficiaireByAccount(Compte compte){
       List<Benificiaire> list = beneficiaireRepository.findAllByCompteIs(compte).stream().toList();
       return list;

    }
    public Benificiaire getBeneficiaireById(Integer id){
        return beneficiaireRepository.findByBeneficiaireId(id);
    }
    public Benificiaire updateBeneficiaire(Integer id ,Benificiaire benificiaire){
        Benificiaire bnUpdated = getBeneficiaireById(id);
        bnUpdated.setBank(benificiaire.getBank());
        bnUpdated.setCompte(benificiaire.getCompte());
        bnUpdated.setNamebeneficiaire(benificiaire.getNamebeneficiaire());
        return beneficiaireRepository.save(bnUpdated);
    }

    public void deleteBeneficiaire(Integer id){
        beneficiaireRepository.delete(getBeneficiaireById(id));
    }

}
