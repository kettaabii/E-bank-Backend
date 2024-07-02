package com.example.E_bank.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity(name = "user")

public class User {
    @Id
    private Integer userId;
    private String userName;
    private Integer age;


    @OneToMany(mappedBy ="user")
    private List<compte> comptes;


}
