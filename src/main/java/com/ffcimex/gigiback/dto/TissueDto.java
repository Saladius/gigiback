package com.ffcimex.gigiback.dto;

import com.ffcimex.gigiback.enums.TypeTissue;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TissueDto {

    private Long idTissue;
    private String code;
    private String tissueName;
    private BigDecimal stockEnKg;
    private double metreParKg;
    private double prixKg;
    private TypeTissue typeTissue;

    private String fournisseurCode;
}
