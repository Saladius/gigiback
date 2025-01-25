package com.ffcimex.gigiback.util;

import com.ffcimex.gigiback.dto.FournisseurDto;
import com.ffcimex.gigiback.dto.TissueDto;
import com.ffcimex.gigiback.enums.TypeTissue;
import com.ffcimex.gigiback.exception.ExcelProcessingException;
import com.ffcimex.gigiback.service.FournisseurService;
import com.ffcimex.gigiback.service.TissueService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class ExcelUtil {

    private final FournisseurService fournisseurService;
    private final TissueService tissueService;

    /**
     * Process the first sheet of the Excel file (Fournisseur)
     */
    public void processFournisseurBatch(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.iterator();
        
        // Skip the header rows
        if (rowIterator.hasNext()) rowIterator.next();
        if (rowIterator.hasNext()) rowIterator.next();
        
        int rowNum = 2; // Starting from third row (0-based)
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            if (isEmptyRow(row)) continue;
            
            try {
                FournisseurDto fournisseurDto = new FournisseurDto();
                fournisseurDto.setCode(getRequiredStringValue(row.getCell(0), sheetName, rowNum, 0, "Code"));
                fournisseurDto.setNom(getRequiredStringValue(row.getCell(1), sheetName, rowNum, 1, "Nom"));
                fournisseurDto.setPrenom(getCellValueAsString(row.getCell(2)));
                fournisseurDto.setRaisonSocial(getCellValueAsString(row.getCell(3)));
                fournisseurDto.setTelephone(getRequiredStringValue(row.getCell(4), sheetName, rowNum, 4, "Telephone"));
                fournisseurDto.setEmail(getCellValueAsString(row.getCell(5)));
                
                fournisseurService.addNewFournisseurIfNotExist(fournisseurDto);
            } catch (Exception e) {
                if (!(e instanceof ExcelProcessingException)) {
                    throw new ExcelProcessingException(e.getMessage(), sheetName, rowNum, -1);
                }
                throw e;
            }
            rowNum++;
        }
    }

    /**
     * Process the second sheet of the Excel file (Tissue)
     */
    public void processTissueBatch(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.iterator();
        
        if (rowIterator.hasNext()) rowIterator.next();
        if (rowIterator.hasNext()) rowIterator.next();
        
        int rowNum = 2;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            if (isEmptyRow(row)) continue;
            
            try {
                TissueDto tissueDto = new TissueDto();
                tissueDto.setCode(getRequiredStringValue(row.getCell(0), sheetName, rowNum, 0, "Code"));
                tissueDto.setTissueName(getRequiredStringValue(row.getCell(1), sheetName, rowNum, 1, "Nom"));
                tissueDto.setMetreParKg(getRequiredNumericValue(row.getCell(2), sheetName, rowNum, 2, "Metre par Kg"));
                tissueDto.setPrixKg(getRequiredNumericValue(row.getCell(3), sheetName, rowNum, 3, "Prix"));
                
                String typeStr = getRequiredStringValue(row.getCell(4), sheetName, rowNum, 4, "Type");
                try {
                    tissueDto.setTypeTissue(TypeTissue.valueOf(typeStr.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    throw new ExcelProcessingException("Invalid Type Tissue value", sheetName, rowNum, 4, 
                            "One of " + String.join(", ", TypeTissue.values().toString()), typeStr);
                }
                
                tissueService.addTissueIfNotExist(tissueDto);
            } catch (Exception e) {
                if (!(e instanceof ExcelProcessingException)) {
                    throw new ExcelProcessingException(e.getMessage(), sheetName, rowNum, -1);
                }
                throw e;
            }
            rowNum++;
        }
    }

    /**
     * Process the third sheet of the Excel file (Importation Tissue)
     */
    public void processImportationTissueBatch(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.iterator();
        
        if (rowIterator.hasNext()) rowIterator.next();
        if (rowIterator.hasNext()) rowIterator.next();
        
        int rowNum = 2;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            if (isEmptyRow(row)) continue;
            
            try {
                String codeFournisseur = getRequiredStringValue(row.getCell(0), sheetName, rowNum, 0, "Code Fournisseur");
                String codeTissue = getRequiredStringValue(row.getCell(1), sheetName, rowNum, 1, "Code Tissue");
                double stockEnKg = getRequiredNumericValue(row.getCell(2), sheetName, rowNum, 2, "Stock");
                
                if (stockEnKg <= 0) {
                    throw new ExcelProcessingException("Stock must be greater than 0", sheetName, rowNum, 2, 
                            "Positive number", String.valueOf(stockEnKg));
                }
                
                TissueDto tissueDto = new TissueDto();
                tissueDto.setCode(codeTissue);
                tissueDto.setStockEnKg(BigDecimal.valueOf(stockEnKg));
                
                tissueService.updateStockTissue(tissueDto);
            } catch (Exception e) {
                if (!(e instanceof ExcelProcessingException)) {
                    throw new ExcelProcessingException(e.getMessage(), sheetName, rowNum, -1);
                }
                throw e;
            }
            rowNum++;
        }
    }

    private String getRequiredStringValue(Cell cell, String sheetName, int rowNum, int colNum, String fieldName) {
        String value = getCellValueAsString(cell);
        if (value.isEmpty()) {
            throw new ExcelProcessingException(fieldName + " is required", sheetName, rowNum, colNum);
        }
        return value;
    }

    private double getRequiredNumericValue(Cell cell, String sheetName, int rowNum, int colNum, String fieldName) {
        if (cell == null) {
            throw new ExcelProcessingException(fieldName + " is required", sheetName, rowNum, colNum);
        }
        
        try {
            return cell.getNumericCellValue();
        } catch (IllegalStateException e) {
            throw new ExcelProcessingException(fieldName + " must be a number", sheetName, rowNum, colNum, 
                    "Number", getCellValueAsString(cell));
        }
    }

    private boolean isEmptyRow(Row row) {
        if (row == null) return true;
        for (int i = 0; i < 6; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && !getCellValueAsString(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((int)cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
