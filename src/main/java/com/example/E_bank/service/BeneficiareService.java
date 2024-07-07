package com.example.E_bank.service;

import com.example.E_bank.modal.Benificiaire;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Couche de service pour la gestion des opérations liées aux bénéficiaires.
 */
@Service
public class BeneficiareService {

    @Autowired
    BeneficiaireRepository beneficiaireRepository;

    /**
     * Ajoute un nouveau bénéficiaire.
     *
     * @param benificiaire Le bénéficiaire à ajouter.
     * @return Le bénéficiaire ajouté.
     */
    public Benificiaire addBeneficiaire(Benificiaire benificiaire){
        return beneficiaireRepository.save(benificiaire);
    }

    /**
     * Récupère tous les bénéficiaires associés à un compte spécifique.
     *
     * @param compte Le compte pour lequel récupérer les bénéficiaires.
     * @return Liste des bénéficiaires associés au compte donné.
     */
    public List<Benificiaire> getAllBeneficiaireByAccount(Compte compte){
        List<Benificiaire> list = beneficiaireRepository.findAllByCompteIs(compte).stream().toList();
        return list;
    }

    /**
     * Récupère un bénéficiaire par son identifiant unique.
     *
     * @param id L'identifiant du bénéficiaire à récupérer.
     * @return Le bénéficiaire s'il est trouvé, sinon null.
     */
    public Benificiaire getBeneficiaireById(Integer id){
        return beneficiaireRepository.findByBeneficiaireId(id);
    }

    /**
     * Met à jour les informations d'un bénéficiaire existant.
     *
     * @param id L'identifiant du bénéficiaire à mettre à jour.
     * @param benificiaire Le bénéficiaire mis à jour.
     * @return Le bénéficiaire mis à jour.
     */
    public Benificiaire updateBeneficiaire(Integer id ,Benificiaire benificiaire){
        Benificiaire bnUpdated = getBeneficiaireById(id);
        bnUpdated.setBank(benificiaire.getBank());
        bnUpdated.setCompte(benificiaire.getCompte());
        bnUpdated.setNamebeneficiaire(benificiaire.getNamebeneficiaire());
        return beneficiaireRepository.save(bnUpdated);
    }

    /**
     * Supprime un bénéficiaire par son identifiant unique.
     *
     * @param id L'identifiant du bénéficiaire à supprimer.
     */
    public void deleteBeneficiaire(Integer id){
        beneficiaireRepository.delete(getBeneficiaireById(id));
    }

}
