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

        // Mocking repository behavior
        when(beneficiaireRepository.save(inputBenificiaire)).thenReturn(inputBenificiaire);

        // Call service method
        Benificiaire result = beneficiareService.addBeneficiaire(inputBenificiaire);

        // Verify the save method is called once
        verify(beneficiaireRepository, times(1)).save(inputBenificiaire);

        // Assert the returned result
        assertEquals("John Doe", result.getNamebeneficiaire());
        assertEquals("CIH", result.getBank().toString());
        // Add more assertions as per your requirements
    }

    @Test
    public void testGetAllBeneficiaireByAccount() {
        Compte compte = new Compte();
        compte.setAccountId(1);

        // Mocking repository behavior
        when(beneficiaireRepository.findAllByCompteIs(compte)).thenReturn(new ArrayList<>());

        // Call service method
        List<Benificiaire> result = beneficiareService.getAllBeneficiaireByAccount(compte);

        // Verify the findAllByCompteIs method is called once
        verify(beneficiaireRepository, times(1)).findAllByCompteIs(compte);

        // Assert the result
        assertEquals(0, result.size());
        // Add more assertions as per your requirements
    }

    @Test
    public void testGetBeneficiaireById() {
        int benificiaireId = 1;
        Benificiaire expectedBenificiaire = new Benificiaire();
        expectedBenificiaire.setBeneficiaireId(benificiaireId);
        expectedBenificiaire.setNamebeneficiaire("John Doe");

        // Mocking repository behavior
        when(beneficiaireRepository.findByBeneficiaireId(benificiaireId)).thenReturn(expectedBenificiaire);

        // Call service method
        Benificiaire result = beneficiareService.getBeneficiaireById(benificiaireId);

        // Verify the findByBeneficiaireId method is called once
        verify(beneficiaireRepository, times(1)).findByBeneficiaireId(benificiaireId);

        // Assert the result
        assertEquals("John Doe", result.getNamebeneficiaire());
        // Add more assertions as per your requirements
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

        // Mocking repository behavior
        when(beneficiaireRepository.findByBeneficiaireId(benificiaireId)).thenReturn(inputBenificiaire);
        when(beneficiaireRepository.save(inputBenificiaire)).thenReturn(updatedBenificiaire);

        // Call service method
        Benificiaire result = beneficiareService.updateBeneficiaire(benificiaireId, updatedBenificiaire);

        // Verify the findByBeneficiaireId and save methods are called once
        verify(beneficiaireRepository, times(1)).findByBeneficiaireId(benificiaireId);
        verify(beneficiaireRepository, times(1)).save(inputBenificiaire);

        // Assert the updated result
        assertEquals("Updated John Doe", result.getNamebeneficiaire());
        assertEquals("CIH", result.getBank().toString());
        // Add more assertions as per your requirements
    }

    @Test
    public void testDeleteBeneficiaire() {
        int benificiaireId = 1;
        Benificiaire benificiaireToDelete = new Benificiaire();
        benificiaireToDelete.setBeneficiaireId(benificiaireId);

        // Mocking repository behavior
        when(beneficiaireRepository.findByBeneficiaireId(benificiaireId)).thenReturn(benificiaireToDelete);

        // Call service method
        beneficiareService.deleteBeneficiaire(benificiaireId);

        // Verify the delete method is called once
        verify(beneficiaireRepository, times(1)).delete(benificiaireToDelete);
    }
}
