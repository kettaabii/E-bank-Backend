package com.example.E_bank.service;

import com.example.E_bank.enums.status;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import com.example.E_bank.repository.CompteRepository;
import net.andreinc.mockneat.types.enums.IBANType;
import net.andreinc.mockneat.types.enums.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.andreinc.mockneat.unit.financial.IBANs.ibans;
import static net.andreinc.mockneat.unit.text.Strings.strings;

@Service
public class CompteService {
    @Autowired
    CompteRepository compteRepository;


    public List<Compte> getAllAccountsByUserId(User user){

        List<Compte> list =compteRepository.findAllByUser(user);
        return list;
    }

    public Compte save(Compte compte){
       //compte.setAccount_number(ibans().type(IBANType.FRANCE).get());
        compte.setAccount_number(strings().size(11).type(StringType.NUMBERS).get());
        return compteRepository.save(compte);
    }
    public Compte getCompteById(Integer id){

        return compteRepository.findById(id).get();
    }
    public Compte getAccntBynumber(String number){
        return compteRepository.findCompteByAccountNumber(number);
    }
    public Compte fermerCompte(Integer id){
        Compte compte = compteRepository.findById(id).get();
        compte.setStatus(status.Ferme);
        return compteRepository.save(compte);
    }

}
