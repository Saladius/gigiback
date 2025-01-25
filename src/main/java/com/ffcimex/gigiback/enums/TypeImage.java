package com.ffcimex.gigiback.enums;

public enum TypeImage {
    PRODUIT("produit"), QR_CODE("QR Code"), LOGO("Logo"), PATRON("Patron"), MODELE("modèle");

    private final String value;

    TypeImage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TypeImage fromValue(String value) {
        for (TypeImage type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return PRODUIT;
    }
}
