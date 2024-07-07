package com.example.E_bank.controller;

import com.example.E_bank.exeption.cardBlockedException;
import com.example.E_bank.modal.CarteBancaire;
import com.example.E_bank.service.CartebancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarteBancaireController {
    @Autowired
    CartebancaireService cartebancaireService;

    @PostMapping("/card/add")
    public CarteBancaire addCard(@RequestBody CarteBancaire carteBancaire) {
        return cartebancaireService.newCarteBancaire(carteBancaire);
    }
    @PutMapping("/card/desactivate/{id}")
    public CarteBancaire desactivateCard(@PathVariable Integer id, @RequestBody CarteBancaire carteBancaire) {
        return cartebancaireService.desactiverCarte(id, carteBancaire);
    }
    @PutMapping("/card/activate/{id}")
    public ResponseEntity<?> activateCard(@PathVariable Integer id, @RequestBody CarteBancaire carteBancaire) throws cardBlockedException {
        try {
            CarteBancaire carteActivee = cartebancaireService.activateCarte(id, carteBancaire);
            return ResponseEntity.ok(carteActivee);
        } catch (cardBlockedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("card/block/{id}&{reason}")
    public CarteBancaire blockCard(@PathVariable Integer id,@PathVariable String reason,@RequestBody CarteBancaire carteBancaire) {
        return cartebancaireService.bloquercarte(id,reason , carteBancaire);
    }
    @GetMapping("card/all/{id}")
    public List<CarteBancaire> showAllCards(@PathVariable Integer id ) {
        return cartebancaireService.carteBancairePourcompte(id);
    }

}
