package com.ffcimex.gigiback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(unique = true)
    private String email;
    
    private String address;

    @OneToOne(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Panier panier;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalGain;
}
