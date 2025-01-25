package com.ffcimex.gigiback.entity;

import com.ffcimex.gigiback.enums.MoisAnnee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class TrancheAge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrancheAge;

    private String trancheAge;

    private MoisAnnee moisAnnee;
    
    @Column(columnDefinition = "boolean default true")
    private boolean fixe;

    private int valMin;
    private int valMax;
    private int valFixe;

    @ManyToMany(mappedBy = "trancheAges")
    private List<Produit> produits;
}
