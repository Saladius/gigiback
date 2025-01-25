package com.ffcimex.gigiback.entity;

import com.ffcimex.gigiback.enums.Tailles;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock_taille")
@Data
public class StockTaille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStockTaille;

    @ManyToOne
    @JoinColumn(name = "tranche_age_id", nullable = false)
    private TrancheAge trancheAge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tailles taille;

    @Column(nullable = false)
    private int nombrePiece;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
}
