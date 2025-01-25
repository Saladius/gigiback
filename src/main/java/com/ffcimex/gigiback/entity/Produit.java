package com.ffcimex.gigiback.entity;

import com.ffcimex.gigiback.enums.Tailles;
import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produit")
@Data
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    
    private String code;
    private String hashQR;
    private double prixOfficiel;
    private double prixGrossiste;
    private BigDecimal gainTotal;
    private String pathTableauMesure;

    @OneToMany(mappedBy = "produit", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Image> images;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.PERSIST)
    private List<TissueParProduit> tissueParProduits;

    //@OneToMany(mappedBy = "produit", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //private List<OffreSpecial> offreSpecials;

    @ManyToMany
    @JoinTable(
        name = "produit_commande",
        joinColumns = @JoinColumn(name = "produit_id"),
        inverseJoinColumns = @JoinColumn(name = "commande_id")
    )
    private List<Commande> commandes;

    @ManyToMany
    @JoinTable(
        name = "produit_commande_grossiste",
        joinColumns = @JoinColumn(name = "produit_id"),
        inverseJoinColumns = @JoinColumn(name = "commande_grossiste_id")
    )
    private List<CommandeGrossiste> commandeGrossistes;

    @OneToMany(mappedBy = "produit")
    private List<LigneCommandeGrossiste> lignesCommandeGrossiste;

    @ElementCollection
    @CollectionTable(name = "produit_tailles", joinColumns = @JoinColumn(name = "produit_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "taille")
    private List<Tailles> tailles;

    @ManyToMany
    @JoinTable(
        name = "produit_tranche_age",
        joinColumns = @JoinColumn(name = "produit_id"),
        inverseJoinColumns = @JoinColumn(name = "tranche_age_id")
    )
    private List<TrancheAge> trancheAges;

    @OneToMany(mappedBy = "produit", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StockTaille> stockTailles;
}
