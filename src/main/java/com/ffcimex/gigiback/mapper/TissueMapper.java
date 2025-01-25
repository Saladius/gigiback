package com.ffcimex.gigiback.mapper;

import com.ffcimex.gigiback.dto.TissueDto;
import com.ffcimex.gigiback.entity.Tissue;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;


@UtilityClass
public class TissueMapper {
    
    public static TissueDto toDto(Tissue tissue) {
        if (tissue == null) {
            return null;
        }
        
        TissueDto dto = new TissueDto();
        dto.setIdTissue(tissue.getIdTissue());
        dto.setCode(tissue.getCode());
        dto.setTissueName(tissue.getTissueName());
        dto.setStockEnKg(tissue.getStockEnKg());
        dto.setMetreParKg(tissue.getMetreParKg());
        dto.setPrixKg(tissue.getPrixKg());
        dto.setTypeTissue(tissue.getTypeTissue());
        return dto;
    }

    public static Tissue toEntity(TissueDto dto) {
        if (dto == null) {
            return null;
        }
        
        Tissue tissue = new Tissue();
        tissue.setIdTissue(dto.getIdTissue());
        tissue.setCode(dto.getCode());
        tissue.setTissueName(dto.getTissueName());
        tissue.setStockEnKg(dto.getStockEnKg());
        tissue.setMetreParKg(dto.getMetreParKg());
        tissue.setPrixKg(dto.getPrixKg());
        tissue.setTypeTissue(dto.getTypeTissue());
        return tissue;
    }
}
