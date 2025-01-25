package com.ffcimex.gigiback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "panier")
@Data
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPanier;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> lignesPanier = new ArrayList<>();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal = BigDecimal.ZERO;

    @Column(nullable = false)
    private boolean actif = true;

    @PreUpdate
    @PrePersist
    private void calculateMontantTotal() {
        this.montantTotal = this.lignesPanier.stream()
                .map(LignePanier::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
