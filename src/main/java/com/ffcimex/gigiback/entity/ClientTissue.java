package com.ffcimex.gigiback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "client_tissue")
@Data
public class ClientTissue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String telephone;

    @Column(unique = true)
    private String email;

    private String adresse;

    @OneToMany(mappedBy = "clientTissue")
    private List<CommandeTissue> commandes;
}
