package com.example.E_bank.service;

import com.example.E_bank.Dto.SignupRequest;
import com.example.E_bank.Dto.UserDto;
import com.example.E_bank.modal.User;
import com.example.E_bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * Service pour la gestion des utilisateurs.
 */

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

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

    @Transactional
    public void signUp(SignupRequest signupRequest) {
        String email =signupRequest.email();
        String hashedPassword = passwordEncoder.encode(signupRequest.password());

         userRepository.save(User.builder().userName(signupRequest.userName()).email(email).password(hashedPassword).build());
    }

}
