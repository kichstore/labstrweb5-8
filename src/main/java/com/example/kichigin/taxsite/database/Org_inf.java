package com.example.kichigin.taxsite.database;

import jakarta.persistence.*;

@Entity
public class Org_inf {

    @Id
    @SequenceGenerator(name = "org_seq", sequenceName = "org_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "org_seq")
    private Long org_id;
    private String full_name_org;
    private String ceo;
    private String type;
    private Long payer_reg_number;
    private String e_signature;

    public Org_inf() {
    }

    public Org_inf(Long org_id, String full_name_org, String ceo, String type, Long payer_reg_number, String e_signature) {
        this.org_id = org_id;
        this.full_name_org = full_name_org;
        this.ceo = ceo;
        this.type = type;
        this.payer_reg_number = payer_reg_number;
        this.e_signature = e_signature;
    }

    public Long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Long org_id) {
        this.org_id = org_id;
    }

    public String getFull_name_org() {
        return full_name_org;
    }

    public void setFull_name_org(String full_name_org) {
        this.full_name_org = full_name_org;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPayer_reg_number() {
        return payer_reg_number;
    }

    public void setPayer_reg_number(Long payer_reg_number) {
        this.payer_reg_number = payer_reg_number;
    }

    public String getE_signature() {
        return e_signature;
    }

    public void setE_signature(String e_signature) {
        this.e_signature = e_signature;
    }
}
