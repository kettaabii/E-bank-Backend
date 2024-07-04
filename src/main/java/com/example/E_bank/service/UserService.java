package com.example.E_bank.service;

import com.example.E_bank.modal.User;
import com.example.E_bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserById(int id) {
        return userRepository.findById(id).get();
    }
}