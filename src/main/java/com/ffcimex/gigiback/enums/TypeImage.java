package com.ffcimex.gigiback.enums;

public enum TypeImage {
    PRODUIT("produit"),QR_CODE("QR Code"),LOGO("Logo"),PATRON("Patron"), MODELE("modèle");

    private String value;

    TypeImage(String value) {
        this.value = value;
    }
}
