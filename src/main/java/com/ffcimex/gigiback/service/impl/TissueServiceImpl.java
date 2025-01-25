package com.ffcimex.gigiback.service.impl;

import com.ffcimex.gigiback.dto.TissueDto;
import com.ffcimex.gigiback.entity.Fournisseur;
import com.ffcimex.gigiback.enums.TypeTissue;
import com.ffcimex.gigiback.entity.Tissue;
import com.ffcimex.gigiback.exception.ExcelProcessingException;
import com.ffcimex.gigiback.mapper.TissueMapper;
import com.ffcimex.gigiback.repository.TissueRepository;
import com.ffcimex.gigiback.service.FournisseurService;
import com.ffcimex.gigiback.service.TissueService;
import com.ffcimex.gigiback.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TissueServiceImpl implements TissueService {

    private final FournisseurService fournisseurService;
    private final TissueRepository tissueRepository;

    @Override
    @Transactional
    public void processExcelModelImportationTissue(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Excel file is required");
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            // Validate sheet count
            if (workbook.getNumberOfSheets() < 3) {
                throw new IllegalArgumentException("Excel file must contain 3 sheets: Fournisseur, Tissue, and Importation Tissue");
            }

            // Process Fournisseur sheet (index 0)
            Sheet fournisseurSheet = workbook.getSheetAt(0);
            if (!"Fournisseur".equalsIgnoreCase(fournisseurSheet.getSheetName())) {
                throw new IllegalArgumentException("First sheet must be named 'Fournisseur'");
            }
            fournisseurService.processFournisseurBatch(fournisseurSheet);

            // Process Tissue sheet (index 1)
            Sheet tissueSheet = workbook.getSheetAt(1);
            if (!"Tissue".equalsIgnoreCase(tissueSheet.getSheetName())) {
                throw new IllegalArgumentException("Second sheet must be named 'Tissue'");
            }
            processTissueBatch(tissueSheet);

            // Process Importation Tissue sheet (index 2)
            Sheet importationSheet = workbook.getSheetAt(2);
            if (!"Importation Tissue".equalsIgnoreCase(importationSheet.getSheetName())) {
                throw new IllegalArgumentException("Third sheet must be named 'Importation Tissue'");
            }
            processImportationTissueBatch(importationSheet);

        } catch (IOException e) {
            throw new RuntimeException("Failed to process Excel file: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public TissueDto addTissueIfNotExist(TissueDto tissueDto) {
        Optional<Tissue> tissueOptional = tissueRepository.findByCode(tissueDto.getCode());
        Tissue tissue = tissueOptional.orElse(new Tissue());
        if (tissue.getIdTissue() == null) {
            tissue = TissueMapper.toEntity(tissueDto);
            tissue = tissueRepository.saveAndFlush(tissue);
        }
        return TissueMapper.toDto(tissue);
    }

    @Override
    @Transactional
    public TissueDto updateStockTissue(TissueDto tissueDto) {
        // Récupérer le tissue existant
        Tissue tissue = tissueRepository.findByCode(tissueDto.getCode())
                .orElseThrow(() -> new RuntimeException("Le tissue avec le code " + tissueDto.getCode() + " n'existe pas"));

        // Récupérer le fournisseur
        Fournisseur fournisseur = fournisseurService.getFournisseurByCode(tissueDto.getFournisseurCode());

        // Vérifier si le fournisseur n'est pas déjà associé au tissue
        if (!tissue.getFournisseurs().contains(fournisseur)) {
            tissue.getFournisseurs().add(fournisseur);
        }

        if(tissue.getStockEnKg()==null){
            tissue.setStockEnKg(new BigDecimal(0));
        }
        // Mettre à jour le stock
        tissue.setStockEnKg(tissue.getStockEnKg().add(tissueDto.getStockEnKg()));

        // Sauvegarder les modifications
        return TissueMapper.toDto(tissueRepository.save(tissue));
    }


    @Transactional
    public void processTissueBatch(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.iterator();

        if (rowIterator.hasNext()) rowIterator.next();
        if (rowIterator.hasNext()) rowIterator.next();

        int rowNum = 2;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (ExcelUtil.isEmptyRow(row)) continue;

            try {
                TissueDto tissueDto = new TissueDto();
                tissueDto.setCode(ExcelUtil.getRequiredStringValue(row.getCell(0), sheetName, rowNum, 0, "Code"));
                tissueDto.setTissueName(ExcelUtil.getRequiredStringValue(row.getCell(1), sheetName, rowNum, 1, "Nom"));
                tissueDto.setMetreParKg(ExcelUtil.getRequiredNumericValue(row.getCell(2), sheetName, rowNum, 2, "Metre par Kg"));
                tissueDto.setPrixKg(ExcelUtil.getRequiredNumericValue(row.getCell(3), sheetName, rowNum, 3, "Prix"));

                String typeStr = ExcelUtil.getRequiredStringValue(row.getCell(4), sheetName, rowNum, 4, "Type");
                try {
                    tissueDto.setTypeTissue(TypeTissue.fromNomAffiche(typeStr));
                } catch (IllegalArgumentException e) {
                    throw new ExcelProcessingException("Invalid Type Tissue value", sheetName, rowNum, 4,
                            "One of " + String.join(", ", TypeTissue.values().toString()), typeStr);
                }

                addTissueIfNotExist(tissueDto);
            } catch (Exception e) {
                if (!(e instanceof ExcelProcessingException)) {
                    throw new ExcelProcessingException(e.getMessage(), sheetName, rowNum, -1);
                }
                throw e;
            }
            rowNum++;
        }
    }

    @Transactional
    public void processImportationTissueBatch(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.iterator();

        if (rowIterator.hasNext()) rowIterator.next();
        if (rowIterator.hasNext()) rowIterator.next();

        int rowNum = 2;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (ExcelUtil.isEmptyRow(row)) continue;

            try {
                String codeFournisseur = ExcelUtil.getRequiredStringValue(row.getCell(0), sheetName, rowNum, 0, "Code Fournisseur");
                String codeTissue = ExcelUtil.getRequiredStringValue(row.getCell(1), sheetName, rowNum, 1, "Code Tissue");
                double stockEnKg = ExcelUtil.getRequiredNumericValue(row.getCell(2), sheetName, rowNum, 2, "Stock");

                if (stockEnKg <= 0) {
                    throw new ExcelProcessingException("Stock must be greater than 0", sheetName, rowNum, 2,
                            "Positive number", String.valueOf(stockEnKg));
                }

                TissueDto tissueDto = new TissueDto();
                tissueDto.setCode(codeTissue);
                tissueDto.setStockEnKg(BigDecimal.valueOf(stockEnKg));
                tissueDto.setFournisseurCode(codeFournisseur);
                updateStockTissue(tissueDto);
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
