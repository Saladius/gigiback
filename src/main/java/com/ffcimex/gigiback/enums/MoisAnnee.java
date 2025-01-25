package com.ffcimex.gigiback.enums;

public enum MoisAnnee {
    MOIS("Mois"), ANNEE("Années");

    private final String label;
    
    MoisAnnee(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static MoisAnnee fromLabel(String label) {
        for (MoisAnnee moisAnnee : values()) {
            if (moisAnnee.getLabel().equals(label)) {
                return moisAnnee;
            }
        }
        return MOIS;
    }
}
