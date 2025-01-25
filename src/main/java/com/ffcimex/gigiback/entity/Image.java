package com.ffcimex.gigiback.entity;

import com.ffcimex.gigiback.enums.TypeImage;
import lombok.Data;
import jakarta.persistence.*;
import com.ffcimex.gigiback.entity.Produit;

@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeImage typeImage;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

}
