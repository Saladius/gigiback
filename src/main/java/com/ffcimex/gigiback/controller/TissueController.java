package com.ffcimex.gigiback.controller;

import com.ffcimex.gigiback.service.TissueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tissues")
public class TissueController {

    private final TissueService tissueService;

    @Autowired
    public TissueController(TissueService tissueService) {
        this.tissueService = tissueService;
    }

    @PostMapping("/upload-excel")
    public ResponseEntity<Void> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        tissueService.processExcelModelImportationTissue(file);
        return ResponseEntity.ok().build();
    }
}
