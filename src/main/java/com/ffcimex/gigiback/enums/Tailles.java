package com.ffcimex.gigiback.enums;

public enum Tailles {
    XXS("Extra Extra Small"),
    XS("Extra Small"),
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("Extra Large"),
    XXL("Double Extra Large"),
    XXXL("Triple Extra Large"),
    AGE("age");

    private final String description;

    Tailles(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Tailles fromDescription(String description) {
        for (Tailles taille : values()) {
            if (taille.getDescription().equals(description)) {
                return taille;
            }
        }
        return M;
    }
}
