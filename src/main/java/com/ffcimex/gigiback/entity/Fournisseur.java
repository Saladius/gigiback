package com.ffcimex.gigiback.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "fournisseur")
@Data
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFournisseur;

    private String nom;
    
    private String prenom;
    
    private String adresse;

    private String telephone;

    @Column(unique = true,nullable = false)
    private String code;
    
    @Column(unique = true)
    private String email;
    
    @Column(name = "raison_social")
    private String raisonSocial;

    @ManyToMany
    @JoinTable(
        name = "fournisseur_tissue",
        joinColumns = @JoinColumn(name = "fournisseur_id"),
        inverseJoinColumns = @JoinColumn(name = "tissue_id")
    )
    private List<Tissue> tissues;

}
