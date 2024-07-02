package com.example.E_bank.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "blockage")
public class blockage {
    @Id
    private int blockageId;
    private String motif;

    @ManyToOne
    @JoinColumn(name = "carteId")
    private carteBancaire carteBancaire ;
}
