package com.ffcimex.gigiback.dto;

import lombok.Data;

@Data
public class FournisseurDto {

    private Long idFournisseur;
    private String code;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String raisonSocial;
}
