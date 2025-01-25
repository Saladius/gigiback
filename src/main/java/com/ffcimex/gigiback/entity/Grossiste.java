package com.ffcimex.gigiback.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grossiste")
@Data
public class Grossiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrossiste;

    @Column(nullable = false)
    private String nom;
    
    private String prenom;
    
    private String adresse;
    
    @Column(nullable = false)
    private String telephone;
    
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "grossiste")
    private List<CommandeGrossiste> commandesGrossiste = new ArrayList<>();

}
