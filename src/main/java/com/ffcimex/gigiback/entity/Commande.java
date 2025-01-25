package com.ffcimex.gigiback.entity;

import com.ffcimex.gigiback.enums.StatutCommande;
import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande")
@Data
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;

    @Column(unique = true, nullable = false)
    private String numeroCommande;

    @Column(nullable = false)
    private LocalDateTime dateCommande;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCommande statut;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommande> lignesCommande = new ArrayList<>();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    private String adresseLivraison;
    
    @Column(nullable = false)
    private String telephoneLivraison;
    
    private String commentaire;
    
    @Column(nullable = false)
    private boolean paye = false;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.PERSIST)
    private List<Avoir> avoirs = new ArrayList<>();
    
    @Column(precision = 10, scale = 2)
    private BigDecimal montantPaye;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal montantRestant;

    @PrePersist
    @PreUpdate
    private void calculateMontantTotal() {
        this.montantTotal = this.lignesCommande.stream()
                .map(LigneCommande::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
