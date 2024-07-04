package com.example.E_bank.controller;

import com.example.E_bank.modal.Benificiaire;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.service.BeneficiareService;
import com.example.E_bank.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BeneficiaireController {
    @Autowired
    BeneficiareService beneficiareService;
    @Autowired
    CompteService compteService;

    @PostMapping("/addbeneficiaire")
    public Benificiaire addBeneficiaire(@RequestBody Benificiaire benificiaire) {
        beneficiareService.addBeneficiaire(benificiaire);
        return benificiaire;

    }
    @GetMapping("/listbeneficiaires/{id}")
    public List<Benificiaire> listBeneficiaire(@PathVariable int id) {
        Compte compte =compteService.getCompteById(id);
        return beneficiareService.getAllBeneficiaireByAccount(compte);

    }
    @PutMapping("/updateBeneficiaire/{id}")
    public Benificiaire modifierbeneficiaire(@PathVariable int id,@RequestBody Benificiaire benificiaire) {


       return beneficiareService.updateBeneficiaire(id ,benificiaire);

    }
    @DeleteMapping("beneficiaire/delete/{id}")
    public String deleteBeneficiaire(@PathVariable int id) {
        beneficiareService.deleteBeneficiaire(id);
        return "Beneficiaire deleted";
    }

}
