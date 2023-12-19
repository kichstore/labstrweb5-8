package com.example.kichigin.taxsite.database.printinfo;

public class Orginf {

    private Long id;
    private Long payers_id;
    private String full_name_org, ceo, type, e_signature, lastname;
    private Long payer_reg_number;

    public Orginf() {
    }

    public Orginf(Long id, Long payers_id, String full_name_org, String ceo, String type, String e_signature, Long payer_reg_number, String lastname) {
        this.id = id;
        this.payers_id = payers_id;
        this.full_name_org = full_name_org;
        this.ceo = ceo;
        this.type = type;
        this.e_signature = e_signature;
        this.payer_reg_number = payer_reg_number;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayers_id() {
        return payers_id;
    }

    public void setPayers_id(Long payers_id) {
        this.payers_id = payers_id;
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

    public String getE_signature() {
        return e_signature;
    }

    public void setE_signature(String e_signature) {
        this.e_signature = e_signature;
    }

    public Long getPayer_reg_number() {
        return payer_reg_number;
    }

    public void setPayer_reg_number(Long payer_reg_number) {
        this.payer_reg_number = payer_reg_number;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
