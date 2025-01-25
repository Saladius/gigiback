package com.ffcimex.gigiback.enums;

public enum EtatCommande {
    EN_PREPARATION("en préparation"), EN_COURS("en cours de livraison"), LIVREE("livrée");

    private String label;
    EtatCommande(String label) {
        this.label = label;
    }
}
