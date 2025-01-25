package com.ffcimex.gigiback.exception;

import lombok.Getter;

@Getter
public class ExcelProcessingException extends RuntimeException {
    private final String sheetName;
    private final int rowNumber;
    private final int columnNumber;
    private final String expectedValue;
    private final String actualValue;

    public ExcelProcessingException(String message, String sheetName, int rowNumber, int columnNumber, 
                                  String expectedValue, String actualValue) {
        super(String.format("%s - Sheet: %s, Row: %d, Column: %d, Expected: %s, Found: %s",
                message, sheetName, rowNumber, columnNumber, expectedValue, actualValue));
        this.sheetName = sheetName;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
    }

    public ExcelProcessingException(String message, String sheetName, int rowNumber, int columnNumber) {
        this(message, sheetName, rowNumber, columnNumber, "N/A", "N/A");
    }
}
