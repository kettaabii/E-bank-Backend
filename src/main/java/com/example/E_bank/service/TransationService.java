package com.example.E_bank.service;

import com.example.E_bank.enums.status;
import com.example.E_bank.enums.transaction_for;
import com.example.E_bank.enums.type_transaction;
import com.example.E_bank.exeption.cardBlockedException;
import com.example.E_bank.exeption.compteFermeException;
import com.example.E_bank.exeption.soldeInsuffisantExeption;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.Transaction;
import com.example.E_bank.repository.CompteRepository;
import com.example.E_bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TransationService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CompteService compteService;


    public List<Transaction> getalltransactions(Integer id) {

        return transactionRepository.findAllByCompte_AccountId(id);

    }

    public Transaction newtransaction(String accnt, Transaction transaction) throws soldeInsuffisantExeption, compteFermeException {
        Transaction transaction2 = new Transaction();
        Compte cntorg = compteService.getCompteById(transaction.getCompte().getAccountId());
        if (transaction.getTransactionFor().equals(transaction_for.INTERNE)) {
            Compte cntfor = compteService.getAccntBynumber(accnt);
            System.out.println(cntfor.getStatus() +"hna status dyal l cnt " );

            if(cntfor.getStatus().equals(status.Ferme)||cntorg.getStatus().equals(status.Ferme)) {
                String errorMessage = "Impossible de faire le transaction , compte fermé";
                throw new compteFermeException(errorMessage);
            }else {
                if (transaction.getTypeTransaction().equals(type_transaction.CREDIT)) {

                    if (cntorg.getSolde() < transaction.getMontant()) {
                        String errorMessage = "Le Solde est insuffisant";
                        throw new soldeInsuffisantExeption(errorMessage);
                    } else {
                        cntfor.setSolde(cntfor.getSolde() + transaction.getMontant());
                        cntorg.setSolde(cntorg.getSolde() - transaction.getMontant());
                        System.out.println(cntfor.getSolde() + "sold li khes ikun fih");
                        System.out.println(cntfor.getAccountId() + " l accont li khsni nsift lih ");
                        transaction2.setCompte(cntfor);
                        transaction2.setTransactionFor(transaction_for.INTERNE);
                        transaction2.setDateTransaction(Date.valueOf(LocalDate.now()));
                        transaction2.setHeureTransaction(LocalDateTime.now());
                        transaction2.setDescription(transaction.getDescription());
                        transaction2.setMontant(transaction.getMontant());
                        transaction2.setTypeTransaction(type_transaction.DEBIT);
                        transactionRepository.save(transaction2);
                    }
                } else {
                    cntfor.setSolde(cntfor.getSolde() - transaction.getMontant());
                    cntorg.setSolde(cntorg.getSolde() + transaction.getMontant());
                    System.out.println(cntfor.getSolde() + "sold li khes ikun fih");
                    System.out.println(cntfor.getAccountId() + " l accont li khsni nsift lih ");
                    transaction2.setCompte(cntfor);
                    transaction2.setTransactionFor(transaction_for.INTERNE);
                    transaction2.setDateTransaction(Date.valueOf(LocalDate.now()));
                    transaction2.setHeureTransaction(LocalDateTime.now());
                    transaction2.setDescription(transaction.getDescription());
                    transaction2.setMontant(transaction.getMontant());
                    transaction2.setTypeTransaction(type_transaction.CREDIT);
                    transactionRepository.save(transaction2);


                }}


            }else if (transaction.getTransactionFor().equals(transaction_for.EXTERNE)) {
            if (cntorg.getSolde() < transaction.getMontant()) {
                String errorMessage = "Le Solde est insuffisant";
                throw new soldeInsuffisantExeption(errorMessage);
            } else {
                cntorg.setSolde(cntorg.getSolde() - transaction.getMontant());
            return transactionRepository.save(transaction);
            }


        }
            return transactionRepository.save(transaction);}
    }

