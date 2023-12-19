package com.example.kichigin.taxsite.database.printinfo;

public class Tax_info {

    private String type_tax;
    private Long incom_tax_ind;
    private Long profit_tax;
    private Long fszn_tax;

    public Tax_info() {
    }

    public Tax_info(String type_tax, Long incom_tax_ind, Long profit_tax, Long fszn_tax) {
        this.type_tax = type_tax;
        this.incom_tax_ind = incom_tax_ind;
        this.profit_tax = profit_tax;
        this.fszn_tax = fszn_tax;
    }

    public String getType_tax() {
        return type_tax;
    }

    public void setType_tax(String type_tax) {
        this.type_tax = type_tax;
    }

    public Long getIncom_tax_ind() {
        return incom_tax_ind;
    }

    public void setIncom_tax_ind(Long incom_tax_ind) {
        this.incom_tax_ind = incom_tax_ind;
    }

    public Long getProfit_tax() {
        return profit_tax;
    }

    public void setProfit_tax(Long profit_tax) {
        this.profit_tax = profit_tax;
    }

    public Long getFszn_tax() {
        return fszn_tax;
    }

    public void setFszn_tax(Long fszn_tax) {
        this.fszn_tax = fszn_tax;
    }
}
