package com.example.E_bank;

import com.example.E_bank.enums.status;
import com.example.E_bank.enums.transaction_for;
import com.example.E_bank.enums.type_transaction;
import com.example.E_bank.exeption.compteFermeException;
import com.example.E_bank.exeption.soldeInsuffisantExeption;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.Transaction;
import com.example.E_bank.repository.TransactionRepository;
import com.example.E_bank.service.CompteService;
import com.example.E_bank.service.TransationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransactionServiceTests {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CompteService compteService;

    @InjectMocks
    private TransationService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTransactionsByAccountId() {
        // Mock data
        int accountId = 1;

        // Mock repository method
        when(transactionRepository.findAllByCompte_AccountId(accountId)).thenReturn(Collections.emptyList());

        // Call service method
        transactionService.getalltransactions(accountId);

        // Verify repository interaction
        verify(transactionRepository).findAllByCompte_AccountId(accountId);
    }

    @Test
    public void testNewTransaction_InternalCredit() throws soldeInsuffisantExeption, compteFermeException {
        // Mock data
        String accountNumber = "123456789";
        double transactionAmount = 50.0;
        Compte compteOrg = new Compte();
        compteOrg.setAccountId(1);
        compteOrg.setSolde(100.0);
        compteOrg.setStatus(status.Actif);

        Compte compteFor = new Compte();
        compteFor.setAccountId(2);
        compteFor.setAccount_number(accountNumber);
        compteFor.setSolde(200.0);
        compteFor.setStatus(status.Actif);

        Transaction transaction = new Transaction();
        transaction.setCompte(compteOrg);
        transaction.setTransactionFor(transaction_for.INTERNE);
        transaction.setTypeTransaction(type_transaction.CREDIT);
        transaction.setMontant(transactionAmount);

        // Mock service method invocations
        when(compteService.getCompteById(compteOrg.getAccountId())).thenReturn(compteOrg);
        when(compteService.getAccntBynumber(accountNumber)).thenReturn(compteFor);

        // Call service method
        transactionService.newtransaction(accountNumber, transaction);

        // Verify service method invocations
        verify(compteService).getCompteById(compteOrg.getAccountId());
        verify(compteService).getAccntBynumber(accountNumber);

        // Verify repository interaction
        verify(transactionRepository, times(2)).save(any(Transaction.class));
    }

    @Test
    public void testNewTransaction_InternalDebit() throws soldeInsuffisantExeption, compteFermeException {
        // Mock data
        String accountNumber = "123456789";
        double transactionAmount = 50.0;
        Compte compteOrg = new Compte();
        compteOrg.setAccountId(1);
        compteOrg.setSolde(100.0);
        compteOrg.setStatus(status.Actif);

        Compte compteFor = new Compte();
        compteFor.setAccountId(2);
        compteFor.setAccount_number(accountNumber);
        compteFor.setSolde(200.0);
        compteFor.setStatus(status.Actif);

        Transaction transaction = new Transaction();
        transaction.setCompte(compteOrg);
        transaction.setTransactionFor(transaction_for.INTERNE);
        transaction.setTypeTransaction(type_transaction.DEBIT);
        transaction.setMontant(transactionAmount);

        // Mock service method invocations
        when(compteService.getCompteById(compteOrg.getAccountId())).thenReturn(compteOrg);
        when(compteService.getAccntBynumber(accountNumber)).thenReturn(compteFor);

        // Call service method
        transactionService.newtransaction(accountNumber, transaction);

        // Verify service method invocations
        verify(compteService).getCompteById(compteOrg.getAccountId());
        verify(compteService).getAccntBynumber(accountNumber);

        // Verify repository interaction
        verify(transactionRepository, times(2)).save(any(Transaction.class));
    }

    @Test
    public void testNewTransaction_External() throws soldeInsuffisantExeption, compteFermeException {
        // Mock data
        double transactionAmount = 50.0;
        Compte compteOrg = new Compte();
        compteOrg.setAccountId(1);
        compteOrg.setSolde(100.0);
        compteOrg.setStatus(status.Actif);

        Transaction transaction = new Transaction();
        transaction.setCompte(compteOrg);
        transaction.setTransactionFor(transaction_for.EXTERNE);
        transaction.setTypeTransaction(type_transaction.DEBIT);
        transaction.setMontant(transactionAmount);

        // Mock service method invocations
        when(compteService.getCompteById(compteOrg.getAccountId())).thenReturn(compteOrg);

        // Call service method
        transactionService.newtransaction(null, transaction);

        // Verify service method invocations
        verify(compteService).getCompteById(compteOrg.getAccountId());

        // Verify repository interaction
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    public void testNewTransaction_CompteFermeException() {
        // Mock data
        Compte compteOrg = new Compte();
        compteOrg.setAccountId(1);
        compteOrg.setStatus(status.Actif); // Ensure compteOrg is active

        Compte compteFor = new Compte();
        compteFor.setAccountId(2);
        compteFor.setStatus(status.Ferme); // Simulate a closed account

        String accountNumber = "123456789";
        double transactionAmount = 50.0;

        Transaction transaction = new Transaction();
        transaction.setCompte(compteOrg);
        transaction.setTransactionFor(transaction_for.INTERNE);
        transaction.setTypeTransaction(type_transaction.CREDIT);
        transaction.setMontant(transactionAmount);

        // Mock service method invocations
        when(compteService.getCompteById(compteOrg.getAccountId())).thenReturn(compteOrg);
        when(compteService.getAccntBynumber(accountNumber)).thenReturn(compteFor); // Mock to return compteFor

        // Call service method and assert exception
        compteFermeException exception = assertThrows(compteFermeException.class,
                () -> transactionService.newtransaction(accountNumber, transaction));

        // Assertion on exception message
        assertEquals("Impossible de faire le transaction , compte fermé", exception.getMessage());

        // Verify service method invocations
        verify(compteService).getCompteById(compteOrg.getAccountId());
        verify(compteService).getAccntBynumber(accountNumber);

        // Verify repository interaction (should not save transaction if exception is thrown)
        verify(transactionRepository, never()).save(any(Transaction.class));
    }



}
