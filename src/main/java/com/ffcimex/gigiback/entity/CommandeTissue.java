package com.ffcimex.gigiback.entity;

import com.ffcimex.gigiback.enums.StatutCommande;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande_tissue")
@Data
public class CommandeTissue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroCommande;

    @Column(nullable = false)
    private LocalDateTime dateCommande;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCommande statut;

    @ManyToOne
    @JoinColumn(name = "client_tissue_id", nullable = false)
    private ClientTissue clientTissue;

    @OneToMany(mappedBy = "commandeTissue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommandeTissue> lignesCommandeTissue = new ArrayList<>();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    private String adresseLivraison;
    
    @Column(nullable = false)
    private String telephoneLivraison;
    
    private String commentaire;
    
    @Column(nullable = false)
    private boolean paye = false;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal montantPaye;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal montantRestant;

    @PrePersist
    @PreUpdate
    private void calculateMontantTotal() {
        this.montantTotal = this.lignesCommandeTissue.stream()
                .map(LigneCommandeTissue::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
