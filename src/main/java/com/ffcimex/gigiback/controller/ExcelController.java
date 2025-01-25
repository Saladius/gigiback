package com.ffcimex.gigiback.controller;

import com.ffcimex.gigiback.exception.ExcelProcessingException;
import com.ffcimex.gigiback.service.TissueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
@Tag(name = "Excel", description = "API pour le traitement des fichiers Excel")
public class ExcelController {

    private final TissueService tissueService;

    @Operation(summary = "Importer un fichier Excel de tissus", description = "Traite un fichier Excel contenant des informations sur les tissus")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fichier traité avec succès"),
        @ApiResponse(responseCode = "400", description = "Erreur dans le traitement du fichier")
    })
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            tissueService.processExcelModelImportationTissue(file);
            return ResponseEntity.ok("File processed successfully");
        } catch (ExcelProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
