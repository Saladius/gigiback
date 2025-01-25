package com.ffcimex.gigiback.exception;

public class FournisseurNotFoundException extends RuntimeException {
    public FournisseurNotFoundException(String code) {
        super("Le fournisseur avec le code " + code + " n'a pas été trouvé");
    }
}
