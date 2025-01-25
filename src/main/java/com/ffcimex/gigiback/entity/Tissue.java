package com.ffcimex.gigiback.entity;

import com.ffcimex.gigiback.enums.TypeTissue;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tissue")
@Data
public class Tissue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTissue;

    @Column(nullable = false)
    private String tissueName;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(precision = 10, scale = 3)
    private BigDecimal stockEnKg;

    @Column(name = "metre_par_kg", nullable = false)
    private double metreParKg;

    @Column(nullable = false)
    private double prixKg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeTissue typeTissue;

    @ManyToMany(mappedBy = "tissues")
    private List<Fournisseur> fournisseurs = new ArrayList<>();

    @OneToMany(mappedBy = "tissue")
    private List<LigneCommandeTissue> lignesCommandeTissue = new ArrayList<>();
}
