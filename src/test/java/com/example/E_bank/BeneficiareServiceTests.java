package com.example.E_bank;

import com.example.E_bank.enums.bank;
import com.example.E_bank.modal.Benificiaire;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.repository.BeneficiaireRepository;
import com.example.E_bank.service.BeneficiareService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BeneficiareServiceTests {

    @Mock
    private BeneficiaireRepository beneficiaireRepository;

    @InjectMocks
    private BeneficiareService beneficiareService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBeneficiaire() {
        Benificiaire inputBenificiaire = new Benificiaire();
        inputBenificiaire.setNamebeneficiaire("John Doe");
        inputBenificiaire.setBank(bank.valueOf("CIH"));
        inputBenificiaire.setCompte(new Compte());


        when(beneficiaireRepository.save(inputBenificiaire)).thenReturn(inputBenificiaire);


        Benificiaire result = beneficiareService.addBeneficiaire(inputBenificiaire);


        verify(beneficiaireRepository, times(1)).save(inputBenificiaire);


        assertEquals("John Doe", result.getNamebeneficiaire());
        assertEquals("CIH", result.getBank().toString());

    }

    @Test
    public void testGetAllBeneficiaireByAccount() {
        Compte compte = new Compte();
        compte.setAccountId(1);


        when(beneficiaireRepository.findAllByCompteIs(compte)).thenReturn(new ArrayList<>());


        List<Benificiaire> result = beneficiareService.getAllBeneficiaireByAccount(compte);


        verify(beneficiaireRepository, times(1)).findAllByCompteIs(compte);


        assertEquals(0, result.size());

    }

    @Test
    public void testGetBeneficiaireById() {
        int benificiaireId = 1;
        Benificiaire expectedBenificiaire = new Benificiaire();
        expectedBenificiaire.setBeneficiaireId(benificiaireId);
        expectedBenificiaire.setNamebeneficiaire("John Doe");


        when(beneficiaireRepository.findByBeneficiaireId(benificiaireId)).thenReturn(expectedBenificiaire);


        Benificiaire result = beneficiareService.getBeneficiaireById(benificiaireId);


        verify(beneficiaireRepository, times(1)).findByBeneficiaireId(benificiaireId);


        assertEquals("John Doe", result.getNamebeneficiaire());

    }

    @Test
    public void testUpdateBeneficiaire() {
        int benificiaireId = 1;
        Benificiaire inputBenificiaire = new Benificiaire();
        inputBenificiaire.setBeneficiaireId(benificiaireId);
        inputBenificiaire.setNamebeneficiaire("John Doe");
        inputBenificiaire.setBank(bank.valueOf("CIH"));
        inputBenificiaire.setCompte(new Compte());

        Benificiaire updatedBenificiaire = new Benificiaire();
        updatedBenificiaire.setBeneficiaireId(benificiaireId);
        updatedBenificiaire.setNamebeneficiaire("Updated John Doe");
        updatedBenificiaire.setBank(bank.valueOf("CIH"));
        updatedBenificiaire.setCompte(new Compte());


        when(beneficiaireRepository.findByBeneficiaireId(benificiaireId)).thenReturn(inputBenificiaire);
        when(beneficiaireRepository.save(inputBenificiaire)).thenReturn(updatedBenificiaire);


        Benificiaire result = beneficiareService.updateBeneficiaire(benificiaireId, updatedBenificiaire);


        verify(beneficiaireRepository, times(1)).findByBeneficiaireId(benificiaireId);
        verify(beneficiaireRepository, times(1)).save(inputBenificiaire);


        assertEquals("Updated John Doe", result.getNamebeneficiaire());
        assertEquals("CIH", result.getBank().toString());

    }

    @Test
    public void testDeleteBeneficiaire() {
        int benificiaireId = 1;
        Benificiaire benificiaireToDelete = new Benificiaire();
        benificiaireToDelete.setBeneficiaireId(benificiaireId);


        when(beneficiaireRepository.findByBeneficiaireId(benificiaireId)).thenReturn(benificiaireToDelete);


        beneficiareService.deleteBeneficiaire(benificiaireId);


        verify(beneficiaireRepository, times(1)).delete(benificiaireToDelete);
    }
}
