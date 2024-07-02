package com.example.E_bank.modal;

import com.example.E_bank.enums.card_type;
import com.example.E_bank.enums.status_card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "carte_bancaire")
public class carteBancaire {
    @Id
    private Integer carteId ;
    private String cardNumber;
    private Date expirationDate ;
    private card_type cardType;
    private status_card statusCard;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private compte compte;

    @OneToMany(mappedBy = "blockage")
    private List<blockage> blockageList;

}
