package com.ffcimex.gigiback.enums;

public enum EtatCommande {
    EN_PREPARATION("en préparation"), EN_COURS("en cours de livraison"), LIVREE("livrée");

    private final String label;
    
    EtatCommande(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EtatCommande fromLabel(String label) {
        for (EtatCommande etat : values()) {
            if (etat.getLabel().equals(label)) {
                return etat;
            }
        }
        return EN_PREPARATION;
    }
}
