package com.ffcimex.gigiback.mapper;

import com.ffcimex.gigiback.dto.FournisseurDto;
import com.ffcimex.gigiback.entity.Fournisseur;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;


@UtilityClass
public class FournisseurMapper {
    
    public static FournisseurDto toDto(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        }
        
        FournisseurDto dto = new FournisseurDto();
        dto.setIdFournisseur(fournisseur.getIdFournisseur());
        dto.setCode(fournisseur.getCode());
        dto.setNom(fournisseur.getNom());
        dto.setPrenom(fournisseur.getPrenom());
        dto.setAdresse(fournisseur.getAdresse());
        dto.setTelephone(fournisseur.getTelephone());
        dto.setEmail(fournisseur.getEmail());
        dto.setRaisonSocial(fournisseur.getRaisonSocial());
        return dto;
    }

    public static Fournisseur toEntity(FournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(dto.getIdFournisseur());
        fournisseur.setCode(dto.getCode());
        fournisseur.setNom(dto.getNom());
        fournisseur.setPrenom(dto.getPrenom());
        fournisseur.setAdresse(dto.getAdresse());
        fournisseur.setTelephone(dto.getTelephone());
        fournisseur.setEmail(dto.getEmail());
        fournisseur.setRaisonSocial(dto.getRaisonSocial());
        return fournisseur;
    }
}
