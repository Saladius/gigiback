package com.ffcimex.gigiback.entity;

import com.ffcimex.gigiback.enums.TypeTissue;
import lombok.Data;
import jakarta.persistence.*;
import com.ffcimex.gigiback.entity.Produit;
import com.ffcimex.gigiback.entity.Tissue;

@Entity
@Table(name = "tissue_par_produit")
@Data
public class TissueParProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTissueParProduit;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "tissue_id", nullable = false)
    private Tissue tissue;

    @Column(nullable = false)
    private double quantite;

}
