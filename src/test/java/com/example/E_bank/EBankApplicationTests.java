package com.example.E_bank;

import com.example.E_bank.enums.status;
import com.example.E_bank.exeption.fermetureException;
import com.example.E_bank.modal.Compte;
import com.example.E_bank.modal.User;
import com.example.E_bank.repository.CarteBancaireRepository;
import com.example.E_bank.repository.CompteRepository;

import com.example.E_bank.service.CompteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

class EBankApplicationTests {
//
//@Test
//	void contextLoads() {}
//}
}


