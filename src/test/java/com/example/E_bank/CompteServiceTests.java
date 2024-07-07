package com.example.E_bank;

import com.example.E_bank.enums.status;
import com.example.E_bank.exeption.fermetureException;
import com.example.E_bank.modal.CarteBancaire;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import com.example.E_bank.repository.CarteBancaireRepository;
import com.example.E_bank.repository.CompteRepository;
import com.example.E_bank.service.CompteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CompteServiceTests {

    @Mock
    private CompteRepository compteRepository;
    @Mock
    private CarteBancaireRepository carteBancaireRepository;

    @InjectMocks
    private CompteService compteService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAccountsByUserId() {

        String accountNumber = "123456789";
        Compte compte = new Compte();
        compte.setAccount_number(accountNumber);
        compte.setSolde(100.0);
        compte.setStatus(status.Actif);


        when(compteRepository.findCompteByAccountNumber(accountNumber)).thenReturn(compte);


        Compte foundCompte = compteService.getAccntBynumber(accountNumber);


        assertEquals(accountNumber, foundCompte.getAccount_number());
    }

    @Test
    public void testFermerCompte_withSufficientBalance() throws fermetureException {

        int accountId = 1;
        Compte compte = new Compte();
        compte.setAccountId(accountId);
        compte.setSolde(100.0);
        compte.setStatus(status.Actif);

        List<CarteBancaire> mockCartes = new ArrayList<>();

        when(carteBancaireRepository.findAllByCompteAccountId(accountId)).thenReturn(mockCartes);


        when(compteRepository.findById(accountId)).thenReturn(Optional.of(compte));
        when(compteRepository.save(any(Compte.class))).thenReturn(compte);


        Compte closedCompte = compteService.fermerCompte(accountId);


        verify(compteRepository).findById(accountId);
        verify(compteRepository).save(any(Compte.class));
        verify(carteBancaireRepository).findAllByCompteAccountId(accountId);


        assertEquals(status.Ferme, closedCompte.getStatus());
    }

    @Test
    public void testFermerCompte_withInsufficientBalance() {

        int accountId = 1;
        Compte compte = new Compte();
        compte.setAccountId(accountId);
        compte.setSolde(10.0); // Insufficient balance
        compte.setStatus(status.Actif);


        when(compteRepository.findById(accountId)).thenReturn(Optional.of(compte));


        fermetureException exception = assertThrows(fermetureException.class,
                () -> compteService.fermerCompte(accountId));


        assertEquals("impossible de fermer le compte : solde insuffisant ", exception.getMessage());
    }
}
