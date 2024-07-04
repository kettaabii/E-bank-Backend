package com.example.E_bank.controller;

import com.example.E_bank.modal.CarteBancaire;
import com.example.E_bank.service.CartebancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarteBancaireController {
    @Autowired
    CartebancaireService cartebancaireService;

    @PostMapping("/card/add")
    public CarteBancaire addCard(@RequestBody CarteBancaire carteBancaire) {
        return cartebancaireService.newCarteBancaire(carteBancaire);
    }
}
