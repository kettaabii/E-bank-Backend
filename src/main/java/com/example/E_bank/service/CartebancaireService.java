package com.example.E_bank.service;

import com.example.E_bank.repository.CarteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartebancaireService  {
    @Autowired
    CarteBancaireRepository CarteBancaireRepository;

}
