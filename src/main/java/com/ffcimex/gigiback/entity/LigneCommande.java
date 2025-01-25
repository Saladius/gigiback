package com.ffcimex.gigiback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "ligne_commande")
@Data
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private Integer quantite;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sousTotal;

    public BigDecimal getSousTotal() {
        return sousTotal;
    }

    @PrePersist
    @PreUpdate
    private void calculateSousTotal() {
        this.sousTotal = this.prixUnitaire.multiply(BigDecimal.valueOf(this.quantite));
    }
}
