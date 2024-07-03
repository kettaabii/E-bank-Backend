package com.example.E_bank.controller;

import com.example.E_bank.modal.Compte;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.E_bank.service.CompteService;

import java.util.List;

@RestController
public class CompteController {
  @Autowired
  private CompteService compteservice;

  @GetMapping("/showaccounts")
  public List<Compte> getComptes(){
    List<Compte> list = compteservice.getAllAccounts();
    return list;

  }

  @PostMapping("/add")
  public Compte addCompte(@RequestBody Compte compte) {
    return compteservice.save(compte);
  }
  



}
