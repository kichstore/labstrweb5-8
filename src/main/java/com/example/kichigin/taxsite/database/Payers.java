package com.example.kichigin.taxsite.database;

import jakarta.persistence.*;

@Entity
public class Payers {

    @Id
    @SequenceGenerator(name = "pay_seq", sequenceName = "pay_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pay_seq")
    private Long payer_id;
    private Long tax_entity_id;
    private String type_payer;

    public Payers() {
    }

    public Payers(Long payer_id, Long tax_entity_id, String type_payer) {
        this.payer_id = payer_id;
        this.tax_entity_id = tax_entity_id;
        this.type_payer = type_payer;
    }

    public Long getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(Long payer_id) {
        this.payer_id = payer_id;
    }

    public Long getTax_entity_id() {
        return tax_entity_id;
    }

    public void setTax_entity_id(Long tax_entity_id) {
        this.tax_entity_id = tax_entity_id;
    }

    public String getType_payer() {
        return type_payer;
    }

    public void setType_payer(String type_payer) {
        this.type_payer = type_payer;
    }
}
