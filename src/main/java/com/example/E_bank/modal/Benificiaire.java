package com.example.E_bank.modal;
import com.example.E_bank.enums.bank;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "beneficiaire")
public class Benificiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer beneficiaireId ;

    private String account_number ;

    private bank bank ;
    private String namebeneficiaire;

@ManyToOne
@JoinColumn(name = "accountId")
    private Compte compte;

}
