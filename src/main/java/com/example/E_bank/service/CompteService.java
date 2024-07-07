package com.example.E_bank.service;

import com.example.E_bank.enums.status;
import com.example.E_bank.enums.status_card;
import com.example.E_bank.exeption.cardBlockedException;
import com.example.E_bank.exeption.fermetureException;
import com.example.E_bank.modal.CarteBancaire;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import com.example.E_bank.repository.CarteBancaireRepository;
import com.example.E_bank.repository.CompteRepository;
import net.andreinc.mockneat.types.enums.IBANType;
import net.andreinc.mockneat.types.enums.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.E_bank.enums.status.Actif;
import static net.andreinc.mockneat.unit.financial.IBANs.ibans;
import static net.andreinc.mockneat.unit.text.Strings.strings;

/**
 * Service pour la gestion des opérations liées aux comptes bancaires.
 */
@Service
public class CompteService {

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    CarteBancaireRepository carteBancaireRepository;

    /**
     * Récupère tous les comptes actifs d'un utilisateur donné.
     *
     * @param user L'utilisateur dont on souhaite récupérer les comptes.
     * @return Liste des comptes actifs de l'utilisateur.
     */
    public List<Compte> getAllAccountsByUserId(User user) {
        List<Compte> list = compteRepository.findAllByUserAndStatus(user.getUserId(), String.valueOf(Actif));
        return list;
    }

    /**
     * Enregistre un nouveau compte avec un numéro de compte généré automatiquement.
     *
     * @param compte Le compte à enregistrer.
     * @return Le compte enregistré avec le numéro de compte généré.
     */
    public Compte save(Compte compte) {
        compte.setAccount_number(strings().size(11).type(StringType.NUMBERS).get());
        return compteRepository.save(compte);
    }

    /**
     * Récupère un compte par son identifiant.
     *
     * @param id L'identifiant du compte à récupérer.
     * @return Le compte correspondant à l'identifiant donné.
     */
    public Compte getCompteById(Integer id) {
        return compteRepository.findById(id).get();
    }

    /**
     * Récupère un compte par son numéro de compte.
     *
     * @param number Le numéro de compte à rechercher.
     * @return Le compte correspondant au numéro de compte donné.
     */
    public Compte getAccntBynumber(String number) {
        return compteRepository.findCompteByAccountNumber(number);
    }

    /**
     * Ferme un compte bancaire spécifié par son identifiant.
     *
     * @param id L'identifiant du compte à fermer.
     * @return Le compte mis à jour après la fermeture.
     * @throws fermetureException Si le solde du compte est inférieur ou égal à 20, une exception est levée.
     */
    public Compte fermerCompte(Integer id) throws fermetureException {
        Compte compte = compteRepository.findById(id).get();
        List<CarteBancaire> listcards = carteBancaireRepository.findAllByCompteAccountId(compte.getAccountId());
        listcards.forEach(carteBancaire -> carteBancaire.setStatusCard(status_card.BLOCKED));

        if (compte.getSolde() <= 20) {
            String errorMessage = "Impossible de fermer le compte : solde insuffisant.";
            throw new fermetureException(errorMessage);
        }

        compte.setStatus(status.Ferme);
        return compteRepository.save(compte);
    }
}
