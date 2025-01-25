package com.ffcimex.gigiback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "ligne_commande_grossiste")
@Data
public class LigneCommandeGrossiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_grossiste_id", nullable = false)
    private CommandeGrossiste commandeGrossiste;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private Integer quantite;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prixGrossisteUnitaire;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sousTotal;

    public BigDecimal getSousTotal() {
        return sousTotal;
    }

    @PrePersist
    @PreUpdate
    private void calculateSousTotal() {
        this.sousTotal = this.prixGrossisteUnitaire.multiply(BigDecimal.valueOf(this.quantite));
    }
}
