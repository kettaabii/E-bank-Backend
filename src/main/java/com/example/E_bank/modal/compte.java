package com.example.E_bank.modal;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "compte")
public class compte {
    @Id
    private Integer accountId;
    private String account_number;
    private com.example.E_bank.enums.account_type account_type;
    private Double solde ;
    private Date date_creation;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "carteBancaire")
    private List<carteBancaire> carteBancaires;

}
