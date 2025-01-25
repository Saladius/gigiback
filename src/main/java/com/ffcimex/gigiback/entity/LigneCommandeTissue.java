package com.ffcimex.gigiback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "ligne_commande_tissue")
@Data
public class LigneCommandeTissue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_tissue_id", nullable = false)
    private CommandeTissue commandeTissue;

    @ManyToOne
    @JoinColumn(name = "tissue_id", nullable = false)
    private Tissue tissue;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal quantiteKg;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prixParKg;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sousTotal;

    @PrePersist
    @PreUpdate
    private void calculateSousTotal() {
        this.sousTotal = this.prixParKg.multiply(this.quantiteKg);
    }
}
