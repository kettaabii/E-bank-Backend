package com.example.E_bank.service;

import com.example.E_bank.enums.network;
import com.example.E_bank.enums.status_card;
import com.example.E_bank.exeption.cardBlockedException;
import com.example.E_bank.modal.CarteBancaire;
import com.example.E_bank.repository.CarteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.E_bank.enums.network;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.example.E_bank.enums.network.VISA;
import static java.lang.System.err;
import static net.andreinc.mockneat.unit.financial.CreditCards.creditCards;

@Service
public class CartebancaireService  {
    @Autowired
    CarteBancaireRepository CarteBancaireRepository;

    public CarteBancaire newCarteBancaire(CarteBancaire carteBancaire) {

        if(carteBancaire.getNetwork().equals(VISA)) {
            carteBancaire.setCardNumber(creditCards().visa().get());
        }else {  carteBancaire.setCardNumber(creditCards().masterCard().get());}
        carteBancaire.setExpirationDate(Date.valueOf(LocalDate.now().plusYears(3)));

       return CarteBancaireRepository.save(carteBancaire);

    }
    public CarteBancaire getCardById(int id) {
        return CarteBancaireRepository.findById(id).get();
    }
    public CarteBancaire desactiverCarte(Integer id , CarteBancaire carteBancaire){
        CarteBancaire carteBancaire1 = getCardById(id);

        carteBancaire1.setStatusCard(status_card.INACTIVATED);
        return CarteBancaireRepository.save(carteBancaire1);
    }
    public CarteBancaire activateCarte(Integer id, CarteBancaire carteBancaire) throws cardBlockedException {
        CarteBancaire carteBancaire1 = getCardById(id);

        if (carteBancaire1.getStatusCard().equals(status_card.BLOCKED)) {
            String errorMessage = "La carte est bloquée. Débloquez la carte et réessayez.";
            throw new cardBlockedException(errorMessage);
        } else {
            carteBancaire1.setStatusCard(status_card.ACTIVATED);
            return CarteBancaireRepository.save(carteBancaire1);
        }
    }


    public CarteBancaire bloquercarte(Integer id ,String reason , CarteBancaire carteBancaire){
        CarteBancaire carteBancaire1 = getCardById(id);
        carteBancaire1.setMotifBlockage(reason);
        carteBancaire1.setStatusCard(status_card.BLOCKED);
        return CarteBancaireRepository.save(carteBancaire1);
    }

    public List<CarteBancaire> carteBancairePourcompte(Integer id) {
        return CarteBancaireRepository.findAllByCompteAccountId(id);
    }

}
