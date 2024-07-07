package com.example.E_bank.service;

import com.example.E_bank.modal.User;
import com.example.E_bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service pour la gestion des utilisateurs.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Recherche un utilisateur par son identifiant.
     *
     * @param id L'identifiant de l'utilisateur à rechercher.
     * @return L'utilisateur trouvé.
     * @throws java.util.NoSuchElementException Si aucun utilisateur avec cet identifiant n'est trouvé.
     */
    public User findUserById(int id) {
        return userRepository.findById(id).get();
    }
}
