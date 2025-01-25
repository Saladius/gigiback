package com.ffcimex.gigiback.service.impl;

import com.ffcimex.gigiback.dto.TissueDto;
import com.ffcimex.gigiback.service.TissueService;
import com.ffcimex.gigiback.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class TissueServiceImpl implements TissueService {

    private final ExcelUtil excelUtil;

    public TissueServiceImpl(ExcelUtil excelUtil) {
        this.excelUtil = excelUtil;
    }

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
            excelUtil.processFournisseurBatch(fournisseurSheet);

            // Process Tissue sheet (index 1)
            Sheet tissueSheet = workbook.getSheetAt(1);
            if (!"Tissue".equalsIgnoreCase(tissueSheet.getSheetName())) {
                throw new IllegalArgumentException("Second sheet must be named 'Tissue'");
            }
            excelUtil.processTissueBatch(tissueSheet);

            // Process Importation Tissue sheet (index 2)
            Sheet importationSheet = workbook.getSheetAt(2);
            if (!"Importation Tissue".equalsIgnoreCase(importationSheet.getSheetName())) {
                throw new IllegalArgumentException("Third sheet must be named 'Importation Tissue'");
            }
            excelUtil.processImportationTissueBatch(importationSheet);

        } catch (IOException e) {
            throw new RuntimeException("Failed to process Excel file: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public TissueDto addTissueIfNotExist(TissueDto tissueDto) {
        return null;
    }

    @Override
    @Transactional
    public TissueDto updateStockTissue(TissueDto tissueDto) {
        return null;
    }
}
