package com.example.E_bank.implementation;

import com.example.E_bank.modal.User;
import com.example.E_bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(email);
        System.out.println(user.getUsername()+"////"+user.getPassword());
        return user.builder().username(user.getUsername()).password(user.getPassword()).build();

    }
}
