package com.example.E_bank.repository;

import com.example.E_bank.Dto.UserDto;
import com.example.E_bank.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
