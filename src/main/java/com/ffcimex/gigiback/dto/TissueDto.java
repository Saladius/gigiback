package com.ffcimex.gigiback.dto;

import com.ffcimex.gigiback.enums.TypeTissue;

import java.math.BigDecimal;

public class TissueDto {

    private Long idTissue;
    private String code;
    private String tissueName;
    private BigDecimal stockEnKg;
    private double metreParKg;
    private double prixKg;
    private TypeTissue typeTissue;

    public TissueDto() {
    }

    public Long getIdTissue() {
        return this.idTissue;
    }

    public String getCode() {
        return this.code;
    }

    public String getTissueName() {
        return this.tissueName;
    }

    public BigDecimal getStockEnKg() {
        return this.stockEnKg;
    }

    public double getMetreParKg() {
        return this.metreParKg;
    }

    public double getPrixKg() {
        return this.prixKg;
    }

    public TypeTissue getTypeTissue() {
        return this.typeTissue;
    }

    public void setIdTissue(Long idTissue) {
        this.idTissue = idTissue;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTissueName(String tissueName) {
        this.tissueName = tissueName;
    }

    public void setStockEnKg(BigDecimal stockEnKg) {
        this.stockEnKg = stockEnKg;
    }

    public void setMetreParKg(double metreParKg) {
        this.metreParKg = metreParKg;
    }

    public void setPrixKg(double prixKg) {
        this.prixKg = prixKg;
    }

    public void setTypeTissue(TypeTissue typeTissue) {
        this.typeTissue = typeTissue;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TissueDto)) return false;
        final TissueDto other = (TissueDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$idTissue = this.getIdTissue();
        final Object other$idTissue = other.getIdTissue();
        if (this$idTissue == null ? other$idTissue != null : !this$idTissue.equals(other$idTissue)) return false;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
        final Object this$tissueName = this.getTissueName();
        final Object other$tissueName = other.getTissueName();
        if (this$tissueName == null ? other$tissueName != null : !this$tissueName.equals(other$tissueName))
            return false;
        final Object this$stockEnKg = this.getStockEnKg();
        final Object other$stockEnKg = other.getStockEnKg();
        if (this$stockEnKg == null ? other$stockEnKg != null : !this$stockEnKg.equals(other$stockEnKg)) return false;
        if (Double.compare(this.getMetreParKg(), other.getMetreParKg()) != 0) return false;
        if (Double.compare(this.getPrixKg(), other.getPrixKg()) != 0) return false;
        final Object this$typeTissue = this.getTypeTissue();
        final Object other$typeTissue = other.getTypeTissue();
        if (this$typeTissue == null ? other$typeTissue != null : !this$typeTissue.equals(other$typeTissue))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TissueDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $idTissue = this.getIdTissue();
        result = result * PRIME + ($idTissue == null ? 43 : $idTissue.hashCode());
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        final Object $tissueName = this.getTissueName();
        result = result * PRIME + ($tissueName == null ? 43 : $tissueName.hashCode());
        final Object $stockEnKg = this.getStockEnKg();
        result = result * PRIME + ($stockEnKg == null ? 43 : $stockEnKg.hashCode());
        final long $metreParKg = Double.doubleToLongBits(this.getMetreParKg());
        result = result * PRIME + (int) ($metreParKg >>> 32 ^ $metreParKg);
        final long $prixKg = Double.doubleToLongBits(this.getPrixKg());
        result = result * PRIME + (int) ($prixKg >>> 32 ^ $prixKg);
        final Object $typeTissue = this.getTypeTissue();
        result = result * PRIME + ($typeTissue == null ? 43 : $typeTissue.hashCode());
        return result;
    }

    public String toString() {
        return "TissueDto(idTissue=" + this.getIdTissue() + ", code=" + this.getCode() + ", tissueName=" + this.getTissueName() + ", stockEnKg=" + this.getStockEnKg() + ", metreParKg=" + this.getMetreParKg() + ", prixKg=" + this.getPrixKg() + ", typeTissue=" + this.getTypeTissue() + ")";
    }
}
