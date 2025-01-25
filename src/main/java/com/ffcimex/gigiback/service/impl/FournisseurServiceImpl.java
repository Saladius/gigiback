package com.ffcimex.gigiback.service.impl;

import com.ffcimex.gigiback.dto.FournisseurDto;
import com.ffcimex.gigiback.entity.Fournisseur;
import com.ffcimex.gigiback.exception.ExcelProcessingException;
import com.ffcimex.gigiback.exception.FournisseurNotFoundException;
import com.ffcimex.gigiback.mapper.FournisseurMapper;
import com.ffcimex.gigiback.repository.FournisseurRepository;
import com.ffcimex.gigiback.service.FournisseurService;
import com.ffcimex.gigiback.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {
    private final FournisseurRepository fournisseurRepository;

    @Override
    @Transactional
    public FournisseurDto addNewFournisseurIfNotExist(FournisseurDto fournisseurDto) {
        Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findByCode(fournisseurDto.getCode());
        Fournisseur fournisseur = fournisseurOptional.orElse(new Fournisseur());
        if (fournisseur.getIdFournisseur() == null) {
            fournisseur = FournisseurMapper.toEntity(fournisseurDto);
            fournisseur = fournisseurRepository.saveAndFlush(fournisseur);
        }
        return FournisseurMapper.toDto(fournisseur);
    }

    @Override
    public Fournisseur getFournisseurByCode(String codeFournisseur) {
        return fournisseurRepository.findByCode(codeFournisseur)
                .orElseThrow(() -> new FournisseurNotFoundException(codeFournisseur));
    }

    @Override
    @Transactional
    public void processFournisseurBatch(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.iterator();

        // Skip the header rows
        if (rowIterator.hasNext()) rowIterator.next();
        if (rowIterator.hasNext()) rowIterator.next();

        int rowNum = 2; // Starting from third row (0-based)
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (ExcelUtil.isEmptyRow(row)) continue;

            try {
                FournisseurDto fournisseurDto = new FournisseurDto();
                fournisseurDto.setCode(ExcelUtil.getRequiredStringValue(row.getCell(0), sheetName, rowNum, 0, "Code"));
                fournisseurDto.setNom(ExcelUtil.getRequiredStringValue(row.getCell(1), sheetName, rowNum, 1, "Nom"));
                fournisseurDto.setPrenom(ExcelUtil.getCellValueAsString(row.getCell(2)));
                fournisseurDto.setRaisonSocial(ExcelUtil.getCellValueAsString(row.getCell(3)));
                fournisseurDto.setTelephone(ExcelUtil.getRequiredStringValue(row.getCell(4), sheetName, rowNum, 4, "Telephone"));
                fournisseurDto.setEmail(ExcelUtil.getCellValueAsString(row.getCell(5)));

                addNewFournisseurIfNotExist(fournisseurDto);
            } catch (Exception e) {
                if (!(e instanceof ExcelProcessingException)) {
                    throw new ExcelProcessingException(e.getMessage(), sheetName, rowNum, -1);
                }
                throw e;
            }
            rowNum++;
        }
    }
}
