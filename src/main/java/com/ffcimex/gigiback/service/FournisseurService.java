package com.ffcimex.gigiback.service;

import com.ffcimex.gigiback.dto.FournisseurDto;
import org.springframework.stereotype.Service;

@Service
public interface FournisseurService {
    public FournisseurDto addNewFournisseurIfNotExist(FournisseurDto fournisseurDto);
}
