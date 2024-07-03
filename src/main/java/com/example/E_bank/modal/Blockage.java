package com.example.E_bank.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "blockage")
public class Blockage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blockageId;
    private String motif;

    @ManyToOne
    @JoinColumn(name = "carteId")
    private CarteBancaire carteBancaire ;
}
