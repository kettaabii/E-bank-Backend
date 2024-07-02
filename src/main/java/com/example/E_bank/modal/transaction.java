package com.example.E_bank.modal;

import com.example.E_bank.enums.transaction_for;
import com.example.E_bank.enums.type_transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class transaction {
    @Id
    private Long transactionId;
    private Date dateTransaction;
    private LocalDateTime heureTransaction;
    private double montant;
    private type_transaction typeTransaction;
    private String description;
    private transaction_for transactionFor;

}
