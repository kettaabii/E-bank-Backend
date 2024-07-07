package com.example.E_bank.service;

import com.example.E_bank.enums.network;
import com.example.E_bank.enums.status_card;
import com.example.E_bank.exeption.cardBlockedException;
import com.example.E_bank.modal.CarteBancaire;
import com.example.E_bank.repository.CarteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.example.E_bank.enums.network.VISA;
import static java.lang.System.err;
import static net.andreinc.mockneat.unit.financial.CreditCards.creditCards;

/**
 * Service pour la gestion des opérations liées aux cartes bancaires.
 */
@Service
public class CartebancaireService {

    @Autowired
    CarteBancaireRepository CarteBancaireRepository;

    /**
     * Crée une nouvelle carte bancaire avec les détails fournis.
     *
     * @param carteBancaire Les détails de la carte bancaire à créer.
     * @return La carte bancaire créée.
     */
    public CarteBancaire newCarteBancaire(CarteBancaire carteBancaire) {

        if(carteBancaire.getNetwork().equals(VISA)) {
            carteBancaire.setCardNumber(creditCards().visa().get());
            carteBancaire.setStatusCard(status_card.ACTIVATED);
        } else {
            carteBancaire.setCardNumber(creditCards().masterCard().get());
        }

        carteBancaire.setExpirationDate(Date.valueOf(LocalDate.now().plusYears(3)));
        carteBancaire.setStatusCard(status_card.ACTIVATED);

        return CarteBancaireRepository.save(carteBancaire);
    }

    /**
     * Récupère une carte bancaire par son identifiant.
     *
     * @param id L'identifiant de la carte bancaire à récupérer.
     * @return La carte bancaire correspondant à l'identifiant donné.
     */
    public CarteBancaire getCardById(int id) {
        return CarteBancaireRepository.findById(id).get();
    }

    /**
     * Désactive une carte bancaire spécifiée par son identifiant.
     *
     * @param id L'identifiant de la carte bancaire à désactiver.
     * @param carteBancaire La carte bancaire à désactiver.
     * @return La carte bancaire mise à jour après désactivation.
     */
    public CarteBancaire desactiverCarte(Integer id, CarteBancaire carteBancaire) {
        CarteBancaire carteBancaire1 = getCardById(id);
        carteBancaire1.setStatusCard(status_card.INACTIVATED);
        return CarteBancaireRepository.save(carteBancaire1);
    }

    /**
     * Active une carte bancaire spécifiée par son identifiant.
     *
     * @param id L'identifiant de la carte bancaire à activer.
     * @param carteBancaire La carte bancaire à activer.
     * @return La carte bancaire mise à jour après activation.
     * @throws cardBlockedException Si la carte est déjà bloquée, une exception est levée.
     */
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

    /**
     * Bloque une carte bancaire spécifiée par son identifiant avec un motif de blocage donné.
     *
     * @param id L'identifiant de la carte bancaire à bloquer.
     * @param reason Le motif du blocage de la carte bancaire.
     * @param carteBancaire La carte bancaire à bloquer.
     * @return La carte bancaire mise à jour après blocage.
     */
    public CarteBancaire bloquercarte(Integer id, String reason, CarteBancaire carteBancaire) {
        CarteBancaire carteBancaire1 = getCardById(id);
        carteBancaire1.setMotifBlockage(reason);
        carteBancaire1.setStatusCard(status_card.BLOCKED);
        return CarteBancaireRepository.save(carteBancaire1);
    }

    /**
     * Récupère toutes les cartes bancaires associées à un compte spécifique.
     *
     * @param id L'identifiant du compte pour lequel récupérer les cartes bancaires.
     * @return Liste des cartes bancaires associées au compte spécifié.
     */
    public List<CarteBancaire> carteBancairePourcompte(Integer id) {
        return CarteBancaireRepository.findAllByCompteAccountId(id);
    }

}
