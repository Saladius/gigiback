package com.ffcimex.gigiback.service;

import com.ffcimex.gigiback.dto.TissueDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface TissueService {
    void processExcelModelImportationTissue(MultipartFile file);
    TissueDto addTissueIfNotExist(TissueDto tissueDto);
    TissueDto updateStockTissue(TissueDto tissueDto);
}
