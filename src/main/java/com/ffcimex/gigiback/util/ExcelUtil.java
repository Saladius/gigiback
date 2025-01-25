package com.ffcimex.gigiback.util;

import com.ffcimex.gigiback.exception.ExcelProcessingException;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

@UtilityClass
public class ExcelUtil {


    public static String getRequiredStringValue(Cell cell, String sheetName, int rowNum, int colNum, String fieldName) {
        String value = getCellValueAsString(cell);
        if (value.isEmpty()) {
            throw new ExcelProcessingException(fieldName + " is required", sheetName, rowNum, colNum);
        }
        return value;
    }

    public static double getRequiredNumericValue(Cell cell, String sheetName, int rowNum, int colNum, String fieldName) {
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

    public static boolean isEmptyRow(Row row) {
        if (row == null) return true;
        for (int i = 0; i < 6; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && !getCellValueAsString(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue().toString();
                }
                yield String.valueOf((int) cell.getNumericCellValue());
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
