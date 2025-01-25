package com.ffcimex.gigiback.dto;

public class FournisseurDto {

    private Long idFournisseur;
    private String code;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String raisonSocial;

    public FournisseurDto() {
    }

    public Long getIdFournisseur() {
        return this.idFournisseur;
    }

    public String getCode() {
        return this.code;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRaisonSocial() {
        return this.raisonSocial;
    }

    public void setIdFournisseur(Long idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FournisseurDto)) return false;
        final FournisseurDto other = (FournisseurDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$idFournisseur = this.getIdFournisseur();
        final Object other$idFournisseur = other.getIdFournisseur();
        if (this$idFournisseur == null ? other$idFournisseur != null : !this$idFournisseur.equals(other$idFournisseur))
            return false;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
        final Object this$nom = this.getNom();
        final Object other$nom = other.getNom();
        if (this$nom == null ? other$nom != null : !this$nom.equals(other$nom)) return false;
        final Object this$prenom = this.getPrenom();
        final Object other$prenom = other.getPrenom();
        if (this$prenom == null ? other$prenom != null : !this$prenom.equals(other$prenom)) return false;
        final Object this$adresse = this.getAdresse();
        final Object other$adresse = other.getAdresse();
        if (this$adresse == null ? other$adresse != null : !this$adresse.equals(other$adresse)) return false;
        final Object this$telephone = this.getTelephone();
        final Object other$telephone = other.getTelephone();
        if (this$telephone == null ? other$telephone != null : !this$telephone.equals(other$telephone)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$raisonSocial = this.getRaisonSocial();
        final Object other$raisonSocial = other.getRaisonSocial();
        if (this$raisonSocial == null ? other$raisonSocial != null : !this$raisonSocial.equals(other$raisonSocial))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FournisseurDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $idFournisseur = this.getIdFournisseur();
        result = result * PRIME + ($idFournisseur == null ? 43 : $idFournisseur.hashCode());
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        final Object $nom = this.getNom();
        result = result * PRIME + ($nom == null ? 43 : $nom.hashCode());
        final Object $prenom = this.getPrenom();
        result = result * PRIME + ($prenom == null ? 43 : $prenom.hashCode());
        final Object $adresse = this.getAdresse();
        result = result * PRIME + ($adresse == null ? 43 : $adresse.hashCode());
        final Object $telephone = this.getTelephone();
        result = result * PRIME + ($telephone == null ? 43 : $telephone.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $raisonSocial = this.getRaisonSocial();
        result = result * PRIME + ($raisonSocial == null ? 43 : $raisonSocial.hashCode());
        return result;
    }

    public String toString() {
        return "FournisseurDto(idFournisseur=" + this.getIdFournisseur() + ", code=" + this.getCode() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom() + ", adresse=" + this.getAdresse() + ", telephone=" + this.getTelephone() + ", email=" + this.getEmail() + ", raisonSocial=" + this.getRaisonSocial() + ")";
    }
}
