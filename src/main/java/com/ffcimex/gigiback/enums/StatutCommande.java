package com.ffcimex.gigiback.enums;

public enum StatutCommande {
    EN_ATTENTE("En attente"),
    EN_COURS_DE_PREPARATION("En cours de préparation"),
    PRET_POUR_LIVRAISON("Prêt pour livraison"),
    EN_COURS_DE_LIVRAISON("En cours de livraison"),
    LIVREE("Livrée"),
    ANNULEE("Annulée");

    private final String label;

    StatutCommande(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static StatutCommande fromLabel(String label) {
        for (StatutCommande statut : values()) {
            if (statut.getLabel().equals(label)) {
                return statut;
            }
        }
        return EN_ATTENTE;
    }
}
