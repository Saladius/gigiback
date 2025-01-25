package com.ffcimex.gigiback.service;

import com.ffcimex.gigiback.dto.FournisseurDto;
import com.ffcimex.gigiback.entity.Fournisseur;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

@Service
public interface FournisseurService {
    public FournisseurDto addNewFournisseurIfNotExist(FournisseurDto fournisseurDto);
    void processFournisseurBatch(Sheet sheet);
    Fournisseur getFournisseurByCode(String codeFournisseur);
}
