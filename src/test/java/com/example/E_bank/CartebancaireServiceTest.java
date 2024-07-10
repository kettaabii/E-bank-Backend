//package com.example.E_bank;
//
//import com.example.E_bank.enums.network;
//import com.example.E_bank.enums.status_card;
//import com.example.E_bank.exeption.cardBlockedException;
//import com.example.E_bank.modal.CarteBancaire;
//import com.example.E_bank.repository.CarteBancaireRepository;
//import com.example.E_bank.service.CartebancaireService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static com.example.E_bank.enums.network.VISA;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class CartebancaireServiceTest {
//
//    @Mock
//    private CarteBancaireRepository carteBancaireRepository;
//
//    @InjectMocks
//    private CartebancaireService cartebancaireService;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testNewCarteBancaire_Visa() {
//        CarteBancaire inputCarteBancaire = new CarteBancaire();
//        inputCarteBancaire.setNetwork(network.VISA);
//
//        // Mocking repository behavior
//        when(carteBancaireRepository.save(inputCarteBancaire)).thenReturn(inputCarteBancaire);
//
//        // Call service method
//        CarteBancaire result = cartebancaireService.newCarteBancaire(inputCarteBancaire);
//
//        // Verify the save method is called once
//        verify(carteBancaireRepository, times(1)).save(inputCarteBancaire);
//
//        // Assert the returned result
//        assertNotNull(result.getCardNumber());
//        assertEquals(status_card.ACTIVATED, result.getStatusCard());
//        assertEquals(Date.valueOf(LocalDate.now().plusYears(3)), result.getExpirationDate());
//        // Add more assertions as per your requirements
//        assertEquals(network.VISA, result.getNetwork()); // Verify network type
//    }
//    @Test
//    public void testNewCarteBancaire_MasterCard() {
//        CarteBancaire inputCarteBancaire = new CarteBancaire();
//        inputCarteBancaire.setNetwork(network.MASTERCARD);
//
//
//        when(carteBancaireRepository.save(inputCarteBancaire)).thenReturn(inputCarteBancaire);
//
//
//        CarteBancaire result = cartebancaireService.newCarteBancaire(inputCarteBancaire);
//
//
//        verify(carteBancaireRepository, times(1)).save(inputCarteBancaire);
//
//
//        assertNotNull(result.getCardNumber());
//        assertEquals(status_card.ACTIVATED, result.getStatusCard());
//        assertEquals(Date.valueOf(LocalDate.now().plusYears(3)), result.getExpirationDate());
//
//        assertEquals(network.MASTERCARD, result.getNetwork()); // Verify network type
//    }
//
//    @Test
//    public void testGetCardById() {
//        int cardId = 1;
//        CarteBancaire expectedCarteBancaire = new CarteBancaire();
//        expectedCarteBancaire.setCarteId(cardId);
//
//
//        when(carteBancaireRepository.findById(cardId)).thenReturn(Optional.of(expectedCarteBancaire));
//
//
//        CarteBancaire result = cartebancaireService.getCardById(cardId);
//
//
//        verify(carteBancaireRepository, times(1)).findById(cardId);
//
//
//        assertEquals(cardId, result.getCarteId());
//
//    }
//
//    @Test
//    public void testDesactiverCarte() {
//        int cardId = 1;
//        CarteBancaire inputCarteBancaire = new CarteBancaire();
//        inputCarteBancaire.setCarteId(cardId);
//
//
//        when(carteBancaireRepository.findById(cardId)).thenReturn(Optional.of(inputCarteBancaire));
//        when(carteBancaireRepository.save(inputCarteBancaire)).thenReturn(inputCarteBancaire);
//
//
//        CarteBancaire result = cartebancaireService.desactiverCarte(cardId, inputCarteBancaire);
//
//
//        verify(carteBancaireRepository, times(1)).findById(cardId);
//        verify(carteBancaireRepository, times(1)).save(inputCarteBancaire);
//
//
//        assertEquals(status_card.INACTIVATED, result.getStatusCard());
//
//    }
//
//    @Test
//    public void testActivateCarte_ActivatedCard() throws cardBlockedException {
//        int cardId = 1;
//        CarteBancaire inputCarteBancaire = new CarteBancaire();
//        inputCarteBancaire.setCarteId(cardId);
//        inputCarteBancaire.setStatusCard(status_card.ACTIVATED);
//
//
//        when(carteBancaireRepository.findById(cardId)).thenReturn(Optional.of(inputCarteBancaire));
//        when(carteBancaireRepository.save(inputCarteBancaire)).thenReturn(inputCarteBancaire);
//
//
//        CarteBancaire result = cartebancaireService.activateCarte(cardId, inputCarteBancaire);
//
//
//        verify(carteBancaireRepository, times(1)).findById(cardId);
//        verify(carteBancaireRepository, times(1)).save(inputCarteBancaire);
//
//
//        assertEquals(status_card.ACTIVATED, result.getStatusCard());
//
//    }
//
//    @Test
//    public void testActivateCarte_BlockedCard() {
//        int cardId = 1;
//        CarteBancaire inputCarteBancaire = new CarteBancaire();
//        inputCarteBancaire.setCarteId(cardId);
//        inputCarteBancaire.setStatusCard(status_card.BLOCKED);
//
//
//        when(carteBancaireRepository.findById(cardId)).thenReturn(Optional.of(inputCarteBancaire));
//
//
//        assertThrows(cardBlockedException.class, () -> {
//            cartebancaireService.activateCarte(cardId, inputCarteBancaire);
//        });
//
//
//        verify(carteBancaireRepository, times(1)).findById(cardId);
//    }
//
//    @Test
//    public void testBloquercarte() {
//        int cardId = 1;
//        String reason = "Lost card";
//        CarteBancaire inputCarteBancaire = new CarteBancaire();
//        inputCarteBancaire.setCarteId(cardId);
//
//
//        when(carteBancaireRepository.findById(cardId)).thenReturn(Optional.of(inputCarteBancaire));
//        when(carteBancaireRepository.save(inputCarteBancaire)).thenReturn(inputCarteBancaire);
//
//
//        CarteBancaire result = cartebancaireService.bloquercarte(cardId, reason, inputCarteBancaire);
//
//
//        verify(carteBancaireRepository, times(1)).findById(cardId);
//        verify(carteBancaireRepository, times(1)).save(inputCarteBancaire);
//
//
//        assertEquals(status_card.BLOCKED, result.getStatusCard());
//        assertEquals(reason, result.getMotifBlockage());
//
//    }
//
//    @Test
//    public void testCarteBancairePourcompte() {
//        int accountId = 1;
//        List<CarteBancaire> expectedCarteBancaires = new ArrayList<>();
//
//
//        when(carteBancaireRepository.findAllByCompteAccountId(accountId)).thenReturn(expectedCarteBancaires);
//
//
//        List<CarteBancaire> result = cartebancaireService.carteBancairePourcompte(accountId);
//
//
//        verify(carteBancaireRepository, times(1)).findAllByCompteAccountId(accountId);
//
//
//        assertNotNull(result);
//        assertEquals(expectedCarteBancaires, result);
//
//    }
//}
