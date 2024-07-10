package com.example.E_bank.controller;

import com.example.E_bank.exeption.fermetureException;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import com.example.E_bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.E_bank.service.CompteService;

import java.util.List;

@RestController
public class CompteController {
  @Autowired
  private CompteService compteservice;
  @Autowired
  private UserService userservice;

  @GetMapping("/showaccounts/{id}")
  public List<Compte> getComptes(@PathVariable Integer id) {
    User user = userservice.findUserById(id);
    System.out.println(user +"Userrrrr");
    List<Compte> list = compteservice.getAllAccountsByUserId(user);
    System.out.println("///////////////////");
    list.forEach(System.out::println);
    return list;

  }

  @PostMapping("/account/add")
  public Compte addCompte(@RequestBody Compte compte) {
    return compteservice.save(compte);
  }

  @PutMapping("/account/close/{id}")
  public ResponseEntity<?> fermerCompte(@PathVariable Integer id ) {
    try {
      Compte compte1 =compteservice.fermerCompte(id);
      return ResponseEntity.ok(compte1);
    }catch (fermetureException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }


//  @GetMapping ("/soldes/{idc}/{idu}")
//  public List<Compte> getSoldes(@PathVariable Integer idc, @PathVariable Integer idu) {
//    Compte compte =compteservice.getCompteById(idc);
//    if (compte!=null){
//
//    }
//    return null;
//  }








}
