package com.ffcimex.gigiback.enums;

public enum TypeTissue {
    TOILE_ECRU("Toile écru"),
    MONTONIER_COTTON("Montonier cotton"),
    POPELINE_COTTON_IMPRIME("Popeline cotton imprimé"),
    LIN("Lin"),
    VISCOSE_ELASTHANE("Viscose elasthane"),
    JERSEY("Jersey"),
    VOILE_VISCOSE_IMPRIME("Voile viscose imprimé"),
    LAINE_CHAINE_ET_TRAME("Laine chaîne et trame"),
    JEAN("Jean"),
    JEAN_DOUBLE_STRETCH("Jean Double-Stretch"),
    AUTRE("Autre");

    private final String nomAffiche;

    TypeTissue(String nomAffiche) {
        this.nomAffiche = nomAffiche;
    }

    public String getNomAffiche() {
        return nomAffiche;
    }

    public static TypeTissue fromNomAffiche(String nomAffiche) {
        for (TypeTissue type : values()) {
            if (type.getNomAffiche().equals(nomAffiche)) {
                return type;
            }
        }
        return AUTRE;
    }
}
