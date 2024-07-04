package com.example.E_bank.service;

import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import com.example.E_bank.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService {
    @Autowired
    CompteRepository compteRepository;


    public List<Compte> getAllAccountsByUserId(User user){

        List<Compte> list =compteRepository.findComptesByUserIs(user);
        return list;
    }

    public Compte save(Compte compte){
        return compteRepository.save(compte);
    }
    public Compte getCompteById(Integer id){
        return compteRepository.findById(id).get();
    }

}
